package de.dhbw.softwareengineering.onlinemarketplace.services.user;

public record CreateUserRequest(String firstName, String lastName, String email, String password) {
}
