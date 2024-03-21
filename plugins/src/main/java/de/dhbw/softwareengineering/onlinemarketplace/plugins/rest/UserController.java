package de.dhbw.softwareengineering.onlinemarketplace.plugins.rest;

import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.UserDTO;
import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.mappers.mappers.UserDTOToUserMapper;
import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.mappers.mappers.UserToUserDTOMapper;
import de.dhbw.softwareengineering.onlinemarketplace.domain.user.User;
import de.dhbw.softwareengineering.onlinemarketplace.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

	private final UserService userService;
	private final UserToUserDTOMapper userToUserDTOMapper;

	@Autowired
	public UserController(final UserService userService, final UserToUserDTOMapper userToUserDTOMapper) {
		this.userService = userService;
		this.userToUserDTOMapper = userToUserDTOMapper;
	}

	@GetMapping("/user")
	public List<UserDTO> Get() {
		return this.userService.findAllUsers().stream()
				.map(userToUserDTOMapper)
				.collect(Collectors.toList());
	}


	@PostMapping("/user")
	public User Create(@RequestBody UserDTO userDTO) {
		var user = new User(userDTO.getId(), userDTO.getName(), userDTO.getBirthDate(), userDTO.getAddress());
		return this.userService.create(user);
	}

}
