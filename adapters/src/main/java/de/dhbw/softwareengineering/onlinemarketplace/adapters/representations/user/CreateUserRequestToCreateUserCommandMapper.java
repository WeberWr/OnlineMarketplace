package de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.user;

import de.dhbw.softwareengineering.onlinemarketplace.services.user.CreateUserCommand;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CreateUserRequestToCreateUserCommandMapper implements Function<CreateUserRequest, CreateUserCommand> {

    @Override
    public CreateUserCommand apply(CreateUserRequest request) {
        return map(request);
    }

    private CreateUserCommand map(CreateUserRequest request) {
        return new CreateUserCommand(request.firstName(), request.lastName(), request.email(), request.password());
    }
}
