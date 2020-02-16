package com.parsefile;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class MenuTest {
    @Test
    public void getInputFromConsoleShouldReturnOne() {
        Menu menu = new Menu();
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertEquals(1, menu.getInput());
    }

    @Test(expected = NoSuchElementException.class)
    public void getInputFromConsoleShouldReturnExceptionWhenOutOfRange() {
        Menu menu = new Menu();
        String input = "0";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        menu.getInput();
    }

    @Test(expected = NoSuchElementException.class)
    public void getInputFromConsoleShouldReturnExceptionWhenNotInteger() {
        Menu menu = new Menu();
        String input = "aaa";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        menu.getInput();
    }
}

