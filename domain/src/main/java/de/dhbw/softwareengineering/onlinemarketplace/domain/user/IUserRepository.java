package de.dhbw.softwareengineering.onlinemarketplace.domain.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserRepository {
    Optional<User> getUserById(UUID id);
    List<User> getAllUsers();
    void createOrUpdate(User user);
    void deleteUser(UUID id);
}
