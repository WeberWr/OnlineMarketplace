package de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.user.mappers;

import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.user.UserDto;
import de.dhbw.softwareengineering.onlinemarketplace.domain.user.User;
import de.dhbw.softwareengineering.onlinemarketplace.domain.valueObject.Address;
import de.dhbw.softwareengineering.onlinemarketplace.domain.valueObject.Name;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserDtoToUserMapper implements Function<UserDto, User> {

    @Override
    public User apply(UserDto dto) {
        return map(dto);
    }

    private User map(UserDto dto) {
        Name name = new Name(dto.getFirstName(), dto.getLastName());
        Address address = new Address(dto.getZipcode(), dto.getStreet(), dto.getStreetNumber());
        return new User(dto.getId(), name, address);
    }
}
