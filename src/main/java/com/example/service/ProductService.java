package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Currency;
import java.util.Locale;

/**
 * Service class for managing product-related operations.
 * Provides methods for retrieving product details, adding new products, and listing all products.
 */
@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
    private static final double MIN_PRICE_THRESHOLD = 0.01;

    /**
     * Retrieves details for a product by its unique identifier.
     *
     * @param id the unique identifier of the product, must be non-null and positive
     * @return a string representation of the product details
     * @throws IllegalArgumentException if the ID is null or non-positive
     */
    public String getProductDetails(Long id) {
        validateId(id);
        LOGGER.info("Fetching details for product ID: {}", sanitizeForLogging(id));
        return "Product details for ID: " + id;
    }

    /**
     * Retrieves details for a product by its name.
     *
     * @param name the name of the product, must be non-null and non-blank
     * @return a string representation of the product details
     * @throws IllegalArgumentException if the name is null or blank
     */
    public String getProductDetailsByName(String name) {
        validateNotBlank(name, "Product name");
        LOGGER.info("Fetching details for product name: {}", sanitizeForLogging(name));
        return "Product details for name: " + name;
    }

    /**
     * Adds a new product with the specified name and price.
     *
     * @param name  the name of the product, must be non-null and non-blank
     * @param price the price of the product, must be non-null and at least {@value #MIN_PRICE_THRESHOLD}
     * @return a success message confirming the product addition
     * @throws IllegalArgumentException if the name is invalid or the price is below threshold
     */
    public String addProduct(String name, Double price) {
        validateNotBlank(name, "Product name");
        validatePrice(price);

        String currencySymbol = Currency.getInstance(Locale.US).getSymbol();
        LOGGER.info("Adding new product: {} with price: {}{}", 
                sanitizeForLogging(name), currencySymbol, sanitizeForLogging(price));
        return String.format("Product '%s' added with price: %s%.2f", name, currencySymbol, price);
    }

    /**
     * Lists all products available in the inventory.
     *
     * @return a message indicating that all products are being listed
     */
    public String listAllProducts() {
        LOGGER.info("Listing all products from inventory");
        return "Listing all products from inventory";
    }

    private void validateId(Long id) {
        if (id == null) {
            LOGGER.error("Validation failed: Product ID is null");
            throw new IllegalArgumentException("Product ID must not be null");
        }
        if (id <= 0) {
            LOGGER.error("Validation failed: Product ID must be positive. Provided: {}", sanitizeForLogging(id));
            throw new IllegalArgumentException("Product ID must be positive");
        }
    }

    private void validateNotBlank(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            LOGGER.error("Validation failed: {} is null or empty", fieldName);
            throw new IllegalArgumentException(fieldName + " must not be null or empty");
        }
    }

    private void validatePrice(Double price) {
        if (price == null) {
            LOGGER.error("Validation failed: Price is null");
            throw new IllegalArgumentException("Product price must not be null");
        }
        if (price < MIN_PRICE_THRESHOLD) {
            LOGGER.error("Validation failed: Price must be at least {}. Provided: {}", 
                    MIN_PRICE_THRESHOLD, sanitizeForLogging(price));
            throw new IllegalArgumentException("Product price must be at least " + MIN_PRICE_THRESHOLD);
        }
    }

    private Object sanitizeForLogging(Object input) {
        if (input == null) {
            return "null";
        }
        String clean = input.toString().replace('\n', '_').replace('\r', '_');
        if (clean.length() > 255) {
            return clean.substring(0, 255) + "...";
        }
        return clean;
    }
}
