package de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.user;

import java.util.UUID;

public record UserDto(UUID id, String firstName, String lastName, String email) {}