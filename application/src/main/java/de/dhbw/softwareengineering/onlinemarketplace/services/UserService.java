package de.dhbw.softwareengineering.onlinemarketplace.services;

import de.dhbw.softwareengineering.onlinemarketplace.domain.user.User;
import de.dhbw.softwareengineering.onlinemarketplace.domain.user.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserById(UUID id) {
        return userRepository.getUserById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User createOrUpdateUser(User user) {
        userRepository.createOrUpdate(user);
        return user;
    }

    public void deleteUser(UUID id) {
        userRepository.deleteUser(id);
    }
}
