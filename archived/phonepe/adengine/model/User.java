package adengine.model;

import java.util.*;

public class User {
    private String id;
    private Date dob;

    @Override
    public String toString() {
        return "User{" + "id='" + id + '\'' + ", dob=" + dob + ", gender='" + gender + '\'' + ", interests=" + interests + '}';
    }

    private String gender;
    private Set<String> interests = new HashSet<>();

    public User(String id, Date dob, String gender) {
        this.id = id;
        this.dob = dob;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Set<String> getInterests() {
        return interests;
    }

    public void setInterests(Set<String> interests) {
        this.interests = interests;
    }

    public void addInterest(String interest) {
        interests.add(interest);
    }

    public int calculateAge() {
        Calendar dobCal = Calendar.getInstance();
        dobCal.setTime(dob);
        Calendar now = Calendar.getInstance();
        int age = now.get(Calendar.YEAR) - dobCal.get(Calendar.YEAR);
        if (now.get(Calendar.DAY_OF_YEAR) < dobCal.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        return age;
    }
}
