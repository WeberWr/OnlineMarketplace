package de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.user;

import java.util.UUID;

public record UserDTO(UUID id, String firstName, String lastName, String email) {
}