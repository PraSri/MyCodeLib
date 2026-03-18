package adengine.service;

import adengine.model.AdCampaign;
import adengine.model.Advertiser;
import adengine.model.TargetingCriteria;
import adengine.model.User;

import java.util.Map;

public interface AdServer {
    AdCampaign matchAdvertisement(User user, TargetingCriteria criteria,
                                  Map<String, AdCampaign> globalCampaigns,
                                  Map<String, Advertiser> globalAdvertisers);
}
