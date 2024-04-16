package de.dhbw.softwareengineering.onlinemarketplace.services.user;

import de.dhbw.softwareengineering.onlinemarketplace.domain.user.IUserRepository;
import de.dhbw.softwareengineering.onlinemarketplace.domain.user.User;
import de.dhbw.softwareengineering.onlinemarketplace.domain.valueObject.Name;
import de.dhbw.softwareengineering.onlinemarketplace.services.IPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final IUserRepository userRepository;
    private final IPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(IUserRepository userRepository, IPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> getUserById(UUID id) {
        return userRepository.getUserById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User create(CreateUserRequest request) {
        var name = new Name(request.firstName(), request.lastName());
        var user = new User(UUID.randomUUID(), name, request.email(), passwordEncoder.encode(request.password()));
        userRepository.create(user);
        return user;
    }

    public User update(User user) {
        userRepository.update(user);
        return user;
    }

    public void deleteUser(UUID id) {
        userRepository.deleteUser(id);
    }
}
