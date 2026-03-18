package adengine.model;

public class TargetingCriteria {
    private Integer minAge;
    private Integer maxAge;
    private String city;

    public TargetingCriteria(Integer minAge, Integer maxAge, String city) {
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.city = city;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean matches(int userAge, String userCity) {
        if (minAge != null && userAge < minAge) return false;
        if (maxAge != null && userAge > maxAge) return false;
        if (city != null && !city.equals(userCity)) return false;
        return true;
    }
}
