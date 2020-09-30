package net.vrabie.takereactiveclient001.daos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GreetingsRequestTest {

    private String showMessage() {
        return "they are not equal";
    }
    @Test
    public void iaka() {
        assertEquals(5,5, this::showMessage);
    }
}