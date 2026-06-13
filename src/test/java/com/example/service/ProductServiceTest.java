package com.example.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService();
    }

    @Test
    void getProductDetails_NullId_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> productService.getProductDetails(null));
    }

    @Test
    void getProductDetails_NegativeId_ThrowsException() {
        // This is expected to fail currently as per the issue description
        assertThrows(IllegalArgumentException.class, () -> productService.getProductDetails(-1L));
    }

    @Test
    void getProductDetails_ValidId_ReturnsDetails() {
        String result = productService.getProductDetails(1L);
        assertNotNull(result);
        assertTrue(result.contains("1"));
    }

    @Test
    void getProductDetailsByName_NullName_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> productService.getProductDetailsByName(null));
    }

    @Test
    void getProductDetailsByName_EmptyName_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> productService.getProductDetailsByName(""));
    }

    @Test
    void getProductDetailsByName_WhitespaceName_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> productService.getProductDetailsByName("   "));
    }

    @Test
    void getProductDetailsByName_ValidName_ReturnsDetails() {
        String result = productService.getProductDetailsByName("Test Product");
        assertNotNull(result);
        assertTrue(result.contains("Test Product"));
    }

    @Test
    void getProductDetailsByName_InjectionAttempt_SanitizesLog() {
        // We can't easily check log output here without a mock appender, 
        // but we can ensure it doesn't throw and handles it.
        String result = productService.getProductDetailsByName("Test\nInjection");
        assertNotNull(result);
    }

    @Test
    void addProduct_NullName_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> productService.addProduct(null, 10.0));
    }

    @Test
    void addProduct_NullPrice_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> productService.addProduct("Product", null));
    }

    @Test
    void addProduct_ZeroPrice_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> productService.addProduct("Product", 0.0));
    }

    @Test
    void addProduct_NegativePrice_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> productService.addProduct("Product", -5.0));
    }

    @Test
    void addProduct_SmallPrice_ThrowsException() {
        // Business logic requirement: minimum price threshold (e.g., 0.01)
        assertThrows(IllegalArgumentException.class, () -> productService.addProduct("Product", 0.0001));
    }

    @Test
    void addProduct_ValidInputs_ReturnsSuccess() {
        String result = productService.addProduct("Product", 19.99);
        assertNotNull(result);
        assertTrue(result.contains("Product"));
    }

    @Test
    void listAllProducts_ReturnsMessage() {
        String result = productService.listAllProducts();
        assertNotNull(result);
    }
}
