package use_case;

import entity.User;

public interface UserSignupDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User user);
}
