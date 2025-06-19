package adengine.service.impl;

import adengine.model.AdCampaign;
import adengine.model.Advertiser;
import adengine.model.TargetingCriteria;
import adengine.model.User;
import adengine.service.AdServer;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class AdServerImpl implements AdServer {
    private final Map<String, Queue<String>> userAdHistory = new ConcurrentHashMap<>();
    private final Map<String, Queue<Long>> globalAdFrequency = new ConcurrentHashMap<>();

    @Override
    public AdCampaign matchAdvertisement(User user, TargetingCriteria criteria,
                                         Map<String, AdCampaign> campaigns,
                                         Map<String, Advertiser> advertisers) {
        if (user == null) return null;
        int userAge = user.calculateAge();
        String userId = user.getId();
        List<AdCampaign> eligibleCampaigns = new ArrayList<>();
        for (AdCampaign campaign : campaigns.values()) {
            if (campaign.getTargetingCriteria().matches(userAge, criteria.getCity())) {
                Advertiser adv = advertisers.get(campaign.getAdvertiserId());
                if (adv != null && adv.getBudget() >= campaign.getBidAmount()) {
                    if (!hasUserSeenCampaignRecently(user.getId(), campaign.getId()) && !isGlobalFrequencyExceeded(campaign.getId())) {
                        eligibleCampaigns.add(campaign);
                    }
                }
            }
        }

        eligibleCampaigns.sort((c1, c2) -> Double.compare(c2.getBidAmount(), c1.getBidAmount()));
        for (AdCampaign campaign : eligibleCampaigns) {
            String campaignId = campaign.getId();
            String advertiserId = campaign.getAdvertiserId();
            try {
                Advertiser adv = advertisers.get(advertiserId);
                if (adv == null || adv.getBudget() < campaign.getBidAmount()) continue;
                adv.deductBudget(campaign.getBidAmount());
                updateUserHistory(userId, campaignId);
                updateGlobalFrequency(campaignId);
                return campaign;
            } catch (Exception ex) {
                System.out.println("Not able to match advertisement for advertiser id - " + advertiserId);
            }
        }
        return null;
    }

    private boolean hasUserSeenCampaignRecently(String userId, String campaignId) {
        Queue<String> history = userAdHistory.get(userId);
        return history != null && history.contains(campaignId);
    }

    private boolean isGlobalFrequencyExceeded(String campaignId) {
        Queue<Long> timestamps = globalAdFrequency.get(campaignId);
        if (timestamps == null) return false;
        long now = System.currentTimeMillis();
        while (!timestamps.isEmpty() && timestamps.peek() < now - 60000) {
            timestamps.poll();
        }
        return timestamps.size() >= 5;
    }

    private void updateUserHistory(String userId, String campaignId) {
        Queue<String> history = userAdHistory.computeIfAbsent(userId, k -> new LinkedList<>());
        history.add(campaignId);
        if (history.size() > 10) {
            history.poll();
        }
    }

    private void updateGlobalFrequency(String campaignId) {
        Queue<Long> timestamps = globalAdFrequency.computeIfAbsent(campaignId, k -> new LinkedList<>());
        timestamps.add(System.currentTimeMillis());
    }
}

