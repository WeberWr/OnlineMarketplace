package de.dhbw.softwareengineering.onlinemarketplace.plugins.rest.user;

import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.user.CreateUserRequest;
import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.user.UserDto;
import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.user.mappers.CreateRequestToUserMapper;
import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.user.mappers.UserToUserDtoMapper;
import de.dhbw.softwareengineering.onlinemarketplace.domain.user.User;
import de.dhbw.softwareengineering.onlinemarketplace.plugins.authentification.ContextProvider;
import de.dhbw.softwareengineering.onlinemarketplace.services.UserService;
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
	private final ContextProvider contextProvider;
	private final UserToUserDtoMapper toDtoMapper = new UserToUserDtoMapper();
	private final CreateRequestToUserMapper toUserMapper = new CreateRequestToUserMapper();

	@Autowired
	public UserController(UserService userService, ContextProvider contextProvider) {
		this.userService = userService;
		this.contextProvider = contextProvider;
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
	public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest request) {
		User user = toUserMapper.apply(request);
		User createdUser = userService.createOrUpdateUser(user);
		return ResponseEntity.ok(toDtoMapper.apply(createdUser));
	}

	@PutMapping()
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
		UUID id = contextProvider.getUser().id();
		if (id != userDto.getId()){
			return ResponseEntity.badRequest().build();
		}

		CreateUserRequest request = new CreateUserRequest(userDto, contextProvider.getUser().password());
		User user = toUserMapper.apply(request);
		User updatedUser = userService.createOrUpdateUser(user);
		return ResponseEntity.ok(toDtoMapper.apply(updatedUser));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
		userService.deleteUser(id);
		return ResponseEntity.ok().build();
	}
}
