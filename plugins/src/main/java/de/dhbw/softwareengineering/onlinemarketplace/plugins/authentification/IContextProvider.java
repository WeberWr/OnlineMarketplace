package de.dhbw.softwareengineering.onlinemarketplace.plugins.authentification;

import de.dhbw.softwareengineering.onlinemarketplace.domain.user.User;

public interface IContextProvider {
    public User getUser();
}
