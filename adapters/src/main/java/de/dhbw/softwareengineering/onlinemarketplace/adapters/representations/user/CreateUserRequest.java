package de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.user;

public record CreateUserRequest(String firstName, String lastName, String email, String password) {
}
