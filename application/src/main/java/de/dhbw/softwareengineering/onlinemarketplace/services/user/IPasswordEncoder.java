package de.dhbw.softwareengineering.onlinemarketplace.services.user;

public interface IPasswordEncoder {
    String encode(CharSequence rawPassword);
}
