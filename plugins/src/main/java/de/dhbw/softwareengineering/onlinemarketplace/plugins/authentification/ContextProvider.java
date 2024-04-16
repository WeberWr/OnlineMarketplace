package de.dhbw.softwareengineering.onlinemarketplace.plugins.authentification;

import de.dhbw.softwareengineering.onlinemarketplace.domain.user.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ContextProvider implements IContextProvider {

    public CustomUserDetails getUserDetails() {
        return (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public User getUser()  {
        return getUserDetails().getUser();
    }
}
