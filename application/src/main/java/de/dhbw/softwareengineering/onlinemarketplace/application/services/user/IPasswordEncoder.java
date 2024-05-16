package de.dhbw.softwareengineering.onlinemarketplace.application.services.user;

public interface IPasswordEncoder {
    String encode(CharSequence rawPassword);
}
