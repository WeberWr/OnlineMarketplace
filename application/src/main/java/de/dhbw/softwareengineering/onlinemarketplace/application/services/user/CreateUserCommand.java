package de.dhbw.softwareengineering.onlinemarketplace.application.services.user;

public record CreateUserCommand(String firstName, String lastName, String email, String password) {
}
