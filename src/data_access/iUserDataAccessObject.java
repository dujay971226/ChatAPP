package data_access;

import entity.User;

public interface iUserDataAccessObject {
    void save(User user);

    User get(String username);

    boolean existsByName(String identifier);
}
