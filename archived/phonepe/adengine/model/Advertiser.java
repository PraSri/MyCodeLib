package adengine.model;

public class Advertiser {
    private String id;
    private String name;
    private double budget;

    public Advertiser(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Advertiser{" + "id='" + id + '\'' + ", name='" + name + '\'' + '}';
    }

    public void addBudget(double amount) {
        budget += amount;
    }

    public void deductBudget(double amount) {
        budget -= amount;
    }

    public double getBudget() {
        return budget;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}
