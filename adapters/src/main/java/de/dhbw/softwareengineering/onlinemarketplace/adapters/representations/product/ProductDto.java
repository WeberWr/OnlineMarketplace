package de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.product;

import java.util.UUID;

public record ProductDto(UUID id, UUID userId, String name, double price) {}
