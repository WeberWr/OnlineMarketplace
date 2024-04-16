package de.dhbw.softwareengineering.onlinemarketplace.services;

public interface IPasswordEncoder {
    String encode(CharSequence rawPassword);
}
