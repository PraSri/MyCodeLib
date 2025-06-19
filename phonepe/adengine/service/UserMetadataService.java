package adengine.service;

import adengine.model.User;

import java.util.Date;
import java.util.Map;

public interface UserMetadataService {
    void addUser(String userId, Date dob, String gender, Map<String, User> users);

    void addAttributes(String userId, String interest, Map<String, User> users);
}
