package com.engineeringcc.productmanagement.product.infrastructure;

import com.engineeringcc.productmanagement.common.mediator.Mediator;
import com.engineeringcc.productmanagement.product.application.query.ProductResponse;
import com.engineeringcc.productmanagement.product.application.query.getAllProducts.GetAllProductsRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    private Mediator mediator;
    private ProductController controller;
    private List<ProductResponse> mockProducts;

    @BeforeEach
    void setUp() {
        mediator = mock(Mediator.class);
        controller = new ProductController(mediator);
        mockProducts = List.of(
                new ProductResponse(1L, "Product 1", "Description 1", 10.0, "http://image1.com"),
                new ProductResponse(2L, "Product 2", "Description 2", 20.0, "http://image2.com")
        );
    }

    @Test
    void getAll_shouldReturnAllProducts() {
        when(mediator.dispatch(any(GetAllProductsRequest.class))).thenReturn(mockProducts);

        var response = controller.getAll();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(2, response.getBody().size());
        assertEquals(1L, response.getBody().get(0).id());
        assertEquals("Product 1", response.getBody().get(0).name());
        assertEquals(2L, response.getBody().get(1).id());
        assertEquals("Product 2", response.getBody().get(1).name());
        verify(mediator).dispatch(any(GetAllProductsRequest.class));
    }

    @Test
    void getAll_shouldReturnEmptyListWhenNoProducts() {
        when(mediator.dispatch(any(GetAllProductsRequest.class))).thenReturn(List.of());

        var response = controller.getAll();

        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody().isEmpty());
        verify(mediator).dispatch(any(GetAllProductsRequest.class));
    }
}