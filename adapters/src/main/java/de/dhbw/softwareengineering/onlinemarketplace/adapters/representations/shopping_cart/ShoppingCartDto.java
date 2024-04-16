package de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.shopping_cart;

import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart.CartItem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record ShoppingCartDto(UUID id, UUID userId, List<CartItem> items) {}