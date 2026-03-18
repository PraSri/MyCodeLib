package adengine.service.impl;

import adengine.model.AdCampaign;
import adengine.model.*;
import adengine.service.CampaignService;

import java.util.Map;
import java.util.UUID;

public class CampaignServiceImpl implements CampaignService {
    private static final String CAMPAIGN_PREFIX = "PHONEPE-FY26Q2-";

    @Override
    public String createCampaign(Advertiser advertiser, TargetingCriteria criteria,
                                 double bidAmount, String url, ContentType content,
                                 Map<String, AdCampaign> globalCampaigns) {
        String campaignId = CAMPAIGN_PREFIX + UUID.randomUUID();
        AdCampaign campaign = new AdCampaign(campaignId, advertiser.getId(), bidAmount, url, content, criteria);
        globalCampaigns.put(campaignId, campaign);
        return campaignId;
    }
}
