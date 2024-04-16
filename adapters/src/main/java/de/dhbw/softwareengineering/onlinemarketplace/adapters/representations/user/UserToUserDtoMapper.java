package de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.user;

import de.dhbw.softwareengineering.onlinemarketplace.domain.user.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserToUserDtoMapper implements Function<User, UserDto> {

    @Override
    public UserDto apply(User user) {
        return map(user);
    }

    private UserDto map(User user) {
        return new UserDto(user.id(), user.name().firstName(), user.name().lastName(), user.email());
    }
}
