package de.dhbw.softwareengineering.onlinemarketplace.plugins.rest;

import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.user.UserDto;
import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.user.UserToUserDtoMapper;
import de.dhbw.softwareengineering.onlinemarketplace.domain.user.User;
import de.dhbw.softwareengineering.onlinemarketplace.services.user.CreateUserRequest;
import de.dhbw.softwareengineering.onlinemarketplace.services.user.UserAlreadyExistsException;
import de.dhbw.softwareengineering.onlinemarketplace.services.user.UserService;
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
	public ResponseEntity<UserDto> getUserById(@PathVariable UUID id) {
		return userService.getUserById(id)
				.map(toDtoMapper)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> users = userService.getAllUsers().stream()
				.map(toDtoMapper)
				.collect(Collectors.toList());
		return ResponseEntity.ok(users);
	}

	@PostMapping
	@SecurityRequirements()
	public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest request) {
		User createdUser;
		try {
			createdUser = userService.create(request);
		} catch (UserAlreadyExistsException e) {
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
