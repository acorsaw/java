package com.corsaw.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AppTest {

    private App app = new App();

    @Test
    void greeting() {
        assertEquals("Hello World!", app.getGreeting());
    }
}
