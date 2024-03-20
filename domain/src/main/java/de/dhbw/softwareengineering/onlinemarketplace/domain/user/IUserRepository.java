package de.dhbw.softwareengineering.onlinemarketplace.domain.user;

import java.util.List;
import java.util.UUID;

public interface IUserRepository {
    List<User> findAllUsers();

    User findUserWithId(UUID id);

    User create(User user);

    boolean deleteById(UUID id);
}
