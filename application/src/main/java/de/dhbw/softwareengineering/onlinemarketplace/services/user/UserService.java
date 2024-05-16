package de.dhbw.softwareengineering.onlinemarketplace.services.user;

import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart.IShoppingCartRepository;
import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart.ShoppingCart;
import de.dhbw.softwareengineering.onlinemarketplace.domain.user.IUserRepository;
import de.dhbw.softwareengineering.onlinemarketplace.domain.user.Name;
import de.dhbw.softwareengineering.onlinemarketplace.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final IUserRepository userRepository;
    private final IShoppingCartRepository shoppingCartRepository;
    private final IPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(IUserRepository userRepository, IShoppingCartRepository shoppingCartRepository, IPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> getUserById(UUID id) {
        return userRepository.getUserById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User create(CreateUserRequest request) throws UserAlreadyExistsException {
        var existingUser = userRepository.getUserByEmail(request.email());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException();
        }

        var name = new Name(request.firstName(), request.lastName());
        var user = new User(name, request.email(), passwordEncoder.encode(request.password()));
        userRepository.create(user);

        var shoppingCart = new ShoppingCart(user.id());
        shoppingCartRepository.create(shoppingCart);
        return user;
    }

    public void deleteUser(UUID id) {
        userRepository.deleteUser(id);

        var shoppingCard = shoppingCartRepository.getShoppingCartOfUser(id);
        shoppingCard.ifPresent(shoppingCartRepository::delete);
    }
}
