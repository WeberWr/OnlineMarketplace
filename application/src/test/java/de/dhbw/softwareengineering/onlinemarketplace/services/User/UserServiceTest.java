package de.dhbw.softwareengineering.onlinemarketplace.services.User;

import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart.IShoppingCartRepository;
import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart.ShoppingCart;
import de.dhbw.softwareengineering.onlinemarketplace.domain.user.IUserRepository;
import de.dhbw.softwareengineering.onlinemarketplace.domain.user.Name;
import de.dhbw.softwareengineering.onlinemarketplace.domain.user.User;
import de.dhbw.softwareengineering.onlinemarketplace.services.user.CreateUserCommand;
import de.dhbw.softwareengineering.onlinemarketplace.services.user.IPasswordEncoder;
import de.dhbw.softwareengineering.onlinemarketplace.services.user.UserAlreadyExistsException;
import de.dhbw.softwareengineering.onlinemarketplace.services.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IShoppingCartRepository shoppingCartRepository;

    @Mock
    private IPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser_Success() throws UserAlreadyExistsException {
        CreateUserCommand request = new CreateUserCommand("John", "Doe", "john.doe@example.com", "password123");
        when(userRepository.getUserByEmail(request.email())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(request.password())).thenReturn("encodedPassword");

        User[] capturedUser = new User[1];
        doAnswer(invocation -> {
            capturedUser[0] = invocation.getArgument(0);
            return null;
        }).when(userRepository).create(any(User.class));

        userService.create(request);

        assertNotNull(capturedUser[0]);
        assertEquals("John", capturedUser[0].name().firstName());
        assertEquals("encodedPassword", capturedUser[0].password());
        verify(shoppingCartRepository).create(any(ShoppingCart.class));
    }

    @Test
    public void testCreateUser_Failure_UserAlreadyExists() {
        CreateUserCommand request = new CreateUserCommand("Jane", "Doe", "jane.doe@example.com", "password123");
        Name name = new Name(request.firstName(), request.lastName());
        when(userRepository.getUserByEmail(request.email())).thenReturn(Optional.of(new User(name, request.email(), request.password())));

        assertThrows(UserAlreadyExistsException.class, () -> userService.create(request));
    }

    @Test
    public void testDeleteUser() {
        UUID userId = UUID.randomUUID();
        when(shoppingCartRepository.getShoppingCartOfUser(userId)).thenReturn(Optional.of(new ShoppingCart(userId)));

        userService.deleteUser(userId);

        verify(userRepository).deleteUser(userId);
        verify(shoppingCartRepository).delete(any(ShoppingCart.class));
    }
}
