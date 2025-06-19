package adengine.service.impl;

import adengine.model.User;
import adengine.service.UserMetadataService;

import java.util.Date;
import java.util.Map;

public class UserMetadataServiceImpl implements UserMetadataService {
    @Override
    public void addUser(String userId, Date dob, String gender, Map<String, User> users) {
        users.put(userId, new User(userId, dob, gender));
    }

    @Override
    public void addAttributes(String userId, String interest, Map<String, User> users) {
        User user = users.get(userId);
        if (user != null) {
            user.addInterest(interest);
        }
    }

}
