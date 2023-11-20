package entity;

import com.pubnub.api.UserId;

public class UserFactory {

    public User create(String name, String password){
        return new User(name, password);
    }
}
