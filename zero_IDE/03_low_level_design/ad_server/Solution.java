import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Low-Level Design: Ad Serving & Auction Engine (PhonePe R1 Problem)
 *
 * Requirements:
 * 1. Campaign & Ad data model with targeting criteria (Country, Device, Tags).
 * 2. Budget and daily spend tracking.
 * 3. Frequency capping per user per ad.
 * 4. Auction / eCPM selection engine returning highest ranking eligible ad.
 */
public class Solution {

    public static class TargetingCriteria {
        public final Set<String> allowedCountries;
        public final Set<String> allowedDevices;
        public final Set<String> requiredTags;

        public TargetingCriteria(Set<String> countries, Set<String> devices, Set<String> tags) {
            this.allowedCountries = countries != null ? countries : Collections.emptySet();
            this.allowedDevices = devices != null ? devices : Collections.emptySet();
            this.requiredTags = tags != null ? tags : Collections.emptySet();
        }

        public boolean matches(UserContext user) {
            if (!allowedCountries.isEmpty() && !allowedCountries.contains(user.country)) return false;
            if (!allowedDevices.isEmpty() && !allowedDevices.contains(user.device)) return false;
            if (!requiredTags.isEmpty()) {
                boolean hasTag = false;
                for (String tag : requiredTags) {
                    if (user.interests.contains(tag)) {
                        hasTag = true;
                        break;
                    }
                }
                if (!hasTag) return false;
            }
            return true;
        }
    }

    public static class UserContext {
        public final String userId;
        public final String country;
        public final String device;
        public final Set<String> interests;

        public UserContext(String userId, String country, String device, Set<String> interests) {
            this.userId = userId;
            this.country = country;
            this.device = device;
            this.interests = interests;
        }
    }

    public static class Ad {
        public final String id;
        public final String campaignId;
        public final double bidAmount; // Cost per impression
        public final double qualityScore;
        public final TargetingCriteria targeting;
        public final int frequencyCapPerUser; // Max impressions per user

        public Ad(String id, String campaignId, double bidAmount, double qualityScore,
                  TargetingCriteria targeting, int frequencyCapPerUser) {
            this.id = id;
            this.campaignId = campaignId;
            this.bidAmount = bidAmount;
            this.qualityScore = qualityScore;
            this.targeting = targeting;
            this.frequencyCapPerUser = frequencyCapPerUser;
        }

        public double getECPM() {
            return bidAmount * qualityScore;
        }
    }

    public static class Campaign {
        public final String id;
        public final double dailyBudget;
        private final AtomicLong currentSpendMicros = new AtomicLong(0);

        public Campaign(String id, double dailyBudget) {
            this.id = id;
            this.dailyBudget = dailyBudget;
        }

        public boolean trySpend(double cost) {
            long costMicros = (long) (cost * 1_000_000);
            long budgetMicros = (long) (dailyBudget * 1_000_000);

            while (true) {
                long current = currentSpendMicros.get();
                if (current + costMicros > budgetMicros) {
                    return false; // Budget exhausted
                }
                if (currentSpendMicros.compareAndSet(current, current + costMicros)) {
                    return true;
                }
            }
        }
    }

    public static class AdServerEngine {
        private final Map<String, Campaign> campaigns = new ConcurrentHashMap<>();
        private final Map<String, Ad> ads = new ConcurrentHashMap<>();
        // user_id:ad_id -> impressionCount
        private final Map<String, AtomicLong> userImpressions = new ConcurrentHashMap<>();

        public void registerCampaign(Campaign campaign) {
            campaigns.put(campaign.id, campaign);
        }

        public void registerAd(Ad ad) {
            ads.put(ad.id, ad);
        }

        public Ad serveAd(UserContext user) {
            List<Ad> eligibleAds = new ArrayList<>();

            for (Ad ad : ads.values()) {
                Campaign campaign = campaigns.get(ad.campaignId);
                if (campaign == null) continue;

                // 1. Targeting filter
                if (!ad.targeting.matches(user)) continue;

                // 2. Frequency capping filter
                String userAdKey = user.userId + ":" + ad.id;
                AtomicLong impressions = userImpressions.get(userAdKey);
                if (impressions != null && impressions.get() >= ad.frequencyCapPerUser) {
                    continue;
                }

                eligibleAds.add(ad);
            }

            if (eligibleAds.isEmpty()) return null;

            // 3. Auction ranking: sort descending by eCPM
            eligibleAds.sort((a, b) -> Double.compare(b.getECPM(), a.getECPM()));

            // 4. Select top ad whose budget can be successfully charged
            for (Ad candidate : eligibleAds) {
                Campaign campaign = campaigns.get(candidate.campaignId);
                if (campaign.trySpend(candidate.bidAmount)) {
                    String userAdKey = user.userId + ":" + candidate.id;
                    userImpressions.computeIfAbsent(userAdKey, k -> new AtomicLong(0)).incrementAndGet();
                    return candidate;
                }
            }

            return null; // All candidates exceeded budget
        }
    }

    public static void main(String[] args) {
        AdServerEngine engine = new AdServerEngine();
        Campaign c1 = new Campaign("camp_1", 10.0);
        engine.registerCampaign(c1);

        TargetingCriteria criteria = new TargetingCriteria(
                Collections.singleton("US"),
                Collections.singleton("MOBILE"),
                Collections.singleton("TECH")
        );

        Ad ad1 = new Ad("ad_101", "camp_1", 2.50, 0.9, criteria, 2);
        engine.registerAd(ad1);

        UserContext user = new UserContext("u_1", "US", "MOBILE", Collections.singleton("TECH"));

        Ad served1 = engine.serveAd(user);
        System.out.println("Served 1: " + (served1 != null ? served1.id : "None"));
        Ad served2 = engine.serveAd(user);
        System.out.println("Served 2: " + (served2 != null ? served2.id : "None"));
        Ad served3 = engine.serveAd(user); // Blocked by frequency cap
        System.out.println("Served 3 (Frequency capped): " + (served3 != null ? served3.id : "None"));
    }
}
