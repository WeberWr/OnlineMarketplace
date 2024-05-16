package de.dhbw.softwareengineering.onlinemarketplace.services.product;


public record CreateProductCommand(String name, String description, double price){}
