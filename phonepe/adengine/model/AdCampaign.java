package adengine.model;

public class AdCampaign {
    private String id;
    private String advertiserId;
    private double bidAmount;
    private String url;
    private ContentType contentType;
    private TargetingCriteria targetingCriteria;

    public AdCampaign(String id, String advertiserId, double bidAmount, String url, ContentType contentType, TargetingCriteria targetingCriteria) {
        this.id = id;
        this.advertiserId = advertiserId;
        this.bidAmount = bidAmount;
        this.url = url;
        this.contentType = contentType;
        this.targetingCriteria = targetingCriteria;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdvertiserId() {
        return advertiserId;
    }

    public void setAdvertiserId(String advertiserId) {
        this.advertiserId = advertiserId;
    }

    public void setBidAmount(double bidAmount) {
        this.bidAmount = bidAmount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public void setTargetingCriteria(TargetingCriteria targetingCriteria) {
        this.targetingCriteria = targetingCriteria;
    }

    public String getId() {
        return id;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public TargetingCriteria getTargetingCriteria() {
        return targetingCriteria;
    }

    @Override
    public String toString() {
        return "AdCampaign{" + "id='" + id + '\'' + ", advertiserId='" + advertiserId + '\'' + ", bidAmount=" + bidAmount + ", url='" + url + '\'' + ", contentType=" + contentType + ", targetingCriteria=" + targetingCriteria + '}';
    }
}
