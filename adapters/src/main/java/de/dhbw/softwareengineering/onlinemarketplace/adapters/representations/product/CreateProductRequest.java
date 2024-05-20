package de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.product;


public record CreateProductRequest(String name, String description, double price) {
}
