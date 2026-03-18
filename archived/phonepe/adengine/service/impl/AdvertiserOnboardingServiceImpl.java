package adengine.service.impl;

import adengine.model.Advertiser;
import adengine.service.AdvertiserOnboardingService;

import java.util.Map;
import java.util.UUID;

public class AdvertiserOnboardingServiceImpl implements AdvertiserOnboardingService {

    @Override
    public String addAdvertiser(String name, Map<String, Advertiser> globalAdvertisers) {
        String advertiserId = UUID.randomUUID().toString();
        Advertiser newAdvertiser = new Advertiser(advertiserId, name);
        globalAdvertisers.put(advertiserId, newAdvertiser);
        return advertiserId;
    }

    @Override
    public void addBudget(String advertiserId, double amount, Map<String, Advertiser> globalAdvertisers) {
        Advertiser adv = globalAdvertisers.get(advertiserId);
        if (adv != null) {
            adv.addBudget(amount);
        } else {
            throw new IllegalArgumentException("No advertiser with id - " + advertiserId + " is found");
        }
    }
}
