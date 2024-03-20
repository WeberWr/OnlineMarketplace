package de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.mappers.mappers;

import java.util.function.Function;

import de.dhbw.softwareengineering.onlinemarketplace.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserDTOToUser implements Function<UserDTO, User> {

    @Override
    public User apply(UserDTO user) {

        return map(user);
    }

    private User map(final UserDTO user) {
        return new User(user.getId(), user.getName(), user.getBirthDate(), user.getAddress());
    }
}
