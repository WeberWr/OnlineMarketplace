package de.dhbw.softwareengineering.onlinemarketplace.services.product;


public record CreateProductRequest(String name, String description, double price, int amount){}
