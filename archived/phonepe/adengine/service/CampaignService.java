package adengine.service;

import adengine.model.*;

import java.util.Map;

public interface CampaignService {
    // Returns campaign id
    String createCampaign(Advertiser advertiser, TargetingCriteria criteria,
                          double bidAmount, String url, ContentType content,
                          Map<String, AdCampaign> globalCampaigns);
}
