package de.dhbw.softwareengineering.onlinemarketplace.application.services.product;


public record CreateProductCommand(String name, String description, double price) {
}
