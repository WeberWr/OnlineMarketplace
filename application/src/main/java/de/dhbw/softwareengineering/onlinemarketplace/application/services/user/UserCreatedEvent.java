package de.dhbw.softwareengineering.onlinemarketplace.application.services.user;
import org.springframework.context.ApplicationEvent;

import java.util.UUID;


public class UserCreatedEvent extends ApplicationEvent {
    private final UUID userId;

    public UserCreatedEvent(Object source, UUID userId) {
        super(source);
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }
}
