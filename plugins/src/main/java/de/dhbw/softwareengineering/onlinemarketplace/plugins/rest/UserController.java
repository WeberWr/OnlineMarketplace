package de.dhbw.softwareengineering.onlinemarketplace.plugins.rest;

import de.dhbw.softwareengineering.OnlineMarketplace.application.services1.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/book")
public class UserController {

	private final UserService userService;

	private final BookToBookResourceMapper bookToBookResourceMapper;


	 @GetMapping("/user/{id}")
	    public String test(@PathVariable String id) {
	        return "test: " + id;
	    }
}
