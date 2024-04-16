package de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.user.mappers;

import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.user.UserDto;
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
        UserDto dto = new UserDto();
        dto.setId(user.id());
        dto.setFirstName(user.name().firstName());
        dto.setLastName(user.name().lastName());
        dto.setEmail(user.email());
        return dto;
    }
}
