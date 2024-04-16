package de.dhbw.softwareengineering.onlinemarketplace.plugins.rest;

import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.user.UserDto;
import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.user.mappers.UserDtoToUserMapper;
import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.user.mappers.UserToUserDtoMapper;
import de.dhbw.softwareengineering.onlinemarketplace.domain.user.User;
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
	private final UserToUserDtoMapper toDtoMapper = new UserToUserDtoMapper();
	private final UserDtoToUserMapper toUserMapper = new UserDtoToUserMapper();

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
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
		User user = toUserMapper.apply(userDto);
		User createdUser = userService.createOrUpdateUser(user);
		return ResponseEntity.ok(toDtoMapper.apply(createdUser));
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable UUID id, @RequestBody UserDto userDto) {
		userDto.setId(id);
		User user = toUserMapper.apply(userDto);
		User updatedUser = userService.createOrUpdateUser(user);
		return ResponseEntity.ok(toDtoMapper.apply(updatedUser));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
		userService.deleteUser(id);
		return ResponseEntity.ok().build();
	}
}
