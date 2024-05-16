package de.dhbw.softwareengineering.onlinemarketplace.plugins.rest.user;

import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.user.UserDTO;
import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.user.UserToUserDtoMapper;
import de.dhbw.softwareengineering.onlinemarketplace.domain.user.User;
import de.dhbw.softwareengineering.onlinemarketplace.services.CreateUserRequest;
import de.dhbw.softwareengineering.onlinemarketplace.services.UserAlreadyExistsException;
import de.dhbw.softwareengineering.onlinemarketplace.services.UserService;
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

	@PostMapping
	@SecurityRequirements()
	public ResponseEntity<UserDTO> createUser(@RequestBody CreateUserRequest request) {
		User createdUser;
		try {
			createdUser = userService.create(request);
		} catch (UserAlreadyExistsException e) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(toDtoMapper.apply(createdUser));
	}

	//ToDo add updateMethod

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
		userService.deleteUser(id);
		return ResponseEntity.ok().build();
	}
}
