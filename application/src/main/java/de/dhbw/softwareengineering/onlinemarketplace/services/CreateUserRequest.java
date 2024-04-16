package de.dhbw.softwareengineering.onlinemarketplace.services;

public record CreateUserRequest(String firstName, String lastName, String email, String password) {
}
