package adengine.service;

import adengine.model.Advertiser;

import java.util.Map;

public interface AdvertiserOnboardingService {
    String addAdvertiser(String name, Map<String, Advertiser> globalAdvertisers);

    void addBudget(String advertiserId, double amount, Map<String, Advertiser> globalAdvertisers);
}
