package com.example.boot;

import com.example.apirest.PriceController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BootApplicationTest {

    @Autowired
    private PriceController priceController;

    @Test
    void contextLoads() {
    }

    @Test
    void priceControllerShouldBeLoaded() {
        assertNotNull(priceController);
    }
}
