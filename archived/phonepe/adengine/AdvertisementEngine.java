package adengine;

import adengine.model.*;
import adengine.service.AdServer;
import adengine.service.AdvertiserOnboardingService;
import adengine.service.CampaignService;
import adengine.service.UserMetadataService;
import adengine.service.impl.AdServerImpl;
import adengine.service.impl.AdvertiserOnboardingServiceImpl;
import adengine.service.impl.CampaignServiceImpl;
import adengine.service.impl.UserMetadataServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AdvertisementEngine {
    private AdServer adServer;
    private AdvertiserOnboardingService onboardingService;
    private CampaignService campaignService;
    private UserMetadataService metadataService;
    private final Map<String, User> users = new ConcurrentHashMap<>();
    private Map<String, Advertiser> advertisers = new ConcurrentHashMap<>();
    private final Map<String, AdCampaign> campaigns = new ConcurrentHashMap<>();
    private TargetingCriteria criteria;

    public AdvertisementEngine(TargetingCriteria criteria) {
        this.adServer = new AdServerImpl();
        this.onboardingService = new AdvertiserOnboardingServiceImpl();
        this.campaignService = new CampaignServiceImpl();
        this.metadataService = new UserMetadataServiceImpl();
        this.criteria = criteria;
    }

    public List<AdCampaign> showAdvertisements(String advertiserName, double budget, String userId,
                                               Date dob, String gender, double bidAmount,
                                                String url, ContentType contentType) {
        List<AdCampaign> shownAds = new ArrayList<>();
        String advertiserId = onboardingService.addAdvertiser(advertiserName, advertisers);
        onboardingService.addBudget(advertiserId, budget, advertisers);
        metadataService.addUser(userId, dob, gender, users);
        for (Advertiser advertiser : advertisers.values()) {
            String campaignId = campaignService.createCampaign(advertiser, criteria, bidAmount, url, contentType, campaigns);
            System.out.println("Created campaign with id - " + campaignId + " for advertiser- " + advertiser);
        }
        for (User user : users.values()) {
            AdCampaign campaign = adServer.matchAdvertisement(user, criteria, campaigns, advertisers);
            shownAds.add(campaign);
            System.out.println("Matched advertisement campaign : " + campaign + " for user - " + user + " ");
        }
        return shownAds;
    }

}
