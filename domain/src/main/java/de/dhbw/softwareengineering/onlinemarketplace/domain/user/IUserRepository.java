package de.dhbw.softwareengineering.onlinemarketplace.domain.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserRepository {
    Optional<User> getUserById(UUID id);
    Optional<User> getUserByEmail(String email);
    List<User> getAllUsers();

    void create(User user);

    void update(User user);
    void deleteUser(UUID id);
}
