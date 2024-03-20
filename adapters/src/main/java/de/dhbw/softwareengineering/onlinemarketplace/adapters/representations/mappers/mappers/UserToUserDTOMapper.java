package de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.mappers.mappers;

import java.util.function.Function;

import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.UserDTO;
import de.dhbw.softwareengineering.onlinemarketplace.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDTOMapper implements Function<User, UserDTO> {

    @Override
    public UserDTO apply(User user) {

        return map(user);
    }

    private UserDTO map(final User user) {
        return new UserDTO(user.getId(), user.getName(), user.getBirthDate(), user.getAddress());
    }
}
