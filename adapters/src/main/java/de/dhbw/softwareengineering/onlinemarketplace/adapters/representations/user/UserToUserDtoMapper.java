package de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.user;

import de.dhbw.softwareengineering.onlinemarketplace.domain.user.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserToUserDtoMapper implements Function<User, UserDTO> {

    @Override
    public UserDTO apply(User user) {
        return map(user);
    }

    private UserDTO map(User user) {
        return new UserDTO(user.id(), user.name().firstName(), user.name().lastName(), user.email());
    }
}
