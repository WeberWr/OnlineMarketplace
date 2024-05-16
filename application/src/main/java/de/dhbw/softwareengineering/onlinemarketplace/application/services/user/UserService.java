package de.dhbw.softwareengineering.onlinemarketplace.application.services.user;

import de.dhbw.softwareengineering.onlinemarketplace.domain.user.IUserRepository;
import de.dhbw.softwareengineering.onlinemarketplace.domain.user.Name;
import de.dhbw.softwareengineering.onlinemarketplace.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final IUserRepository userRepository;
    private final IPasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public UserService(IUserRepository userRepository, IPasswordEncoder passwordEncoder, ApplicationEventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.eventPublisher = eventPublisher;
    }

    public Optional<User> getUserById(UUID id) {
        return userRepository.getUserById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User create(CreateUserCommand request) throws UserAlreadyExistsException {
        var existingUser = userRepository.getUserByEmail(request.email());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException();
        }

        var name = new Name(request.firstName(), request.lastName());
        var user = new User(name, request.email(), passwordEncoder.encode(request.password()));
        userRepository.create(user);

        eventPublisher.publishEvent(new UserCreatedEvent(this, user.id()));
        return user;
    }

    public void deleteUser(UUID id) {
        userRepository.deleteUser(id);
        eventPublisher.publishEvent(new UserDeletedEvent(this, id));
    }
}
