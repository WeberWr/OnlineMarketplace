package de.dhbw.softwareengineering.onlinemarketplace.domain.user;

import de.dhbw.softwareengineering.onlinemarketplace.domain.valueObject.Name;

import java.util.UUID;

public record User(UUID id, Name name, String email, String password) {
}