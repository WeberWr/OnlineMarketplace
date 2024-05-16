package de.dhbw.softwareengineering.onlinemarketplace.services.user;
import org.springframework.context.ApplicationEvent;

import java.util.UUID;


public class UserDeletedEvent extends ApplicationEvent {
    private final UUID userId;

    public UserDeletedEvent(Object source, UUID userId) {
        super(source);
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }
}
