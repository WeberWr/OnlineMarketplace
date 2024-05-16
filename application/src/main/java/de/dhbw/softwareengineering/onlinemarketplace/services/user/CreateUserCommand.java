package de.dhbw.softwareengineering.onlinemarketplace.services.user;

public record CreateUserCommand(String firstName, String lastName, String email, String password) {
}
