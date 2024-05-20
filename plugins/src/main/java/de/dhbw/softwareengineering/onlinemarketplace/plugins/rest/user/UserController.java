package de.dhbw.softwareengineering.onlinemarketplace.plugins.rest.user;

import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.user.CreateUserRequest;
import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.user.CreateUserRequestToCreateUserCommandMapper;
import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.user.UserDTO;
import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.user.UserToUserDtoMapper;
import de.dhbw.softwareengineering.onlinemarketplace.application.services.user.UserAlreadyExistsException;
import de.dhbw.softwareengineering.onlinemarketplace.application.services.user.UserService;
import de.dhbw.softwareengineering.onlinemarketplace.domain.user.User;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserToUserDtoMapper toDtoMapper = new UserToUserDtoMapper();
    private final CreateUserRequestToCreateUserCommandMapper toCommandMapper = new CreateUserRequestToCreateUserCommandMapper();

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id) {
        return userService.getUserById(id)
                .map(toDtoMapper)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers().stream()
                .map(toDtoMapper)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @PostMapping("/create")
    @SecurityRequirements()
    public ResponseEntity<UserDTO> createUser(@RequestBody CreateUserRequest request) {
        var createCommand = toCommandMapper.apply(request);
        User createdUser;
        try {
            createdUser = userService.create(createCommand);
        } catch (UserAlreadyExistsException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(toDtoMapper.apply(createdUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
