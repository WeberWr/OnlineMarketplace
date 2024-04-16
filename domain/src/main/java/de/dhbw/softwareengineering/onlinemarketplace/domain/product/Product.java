package de.dhbw.softwareengineering.onlinemarketplace.domain.product;

import org.springframework.data.mongodb.core.mapping.Document;
import java.util.UUID;

@Document
public record Product(UUID id, UUID ownerId, String name, double price, int amount ){}