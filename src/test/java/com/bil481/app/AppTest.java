package com.bil481.app;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class AppTest {

    @Test
    public void testValidInput() {
        ArrayList<Integer> integerList = new ArrayList<>(Arrays.asList(21, 20, 11, 21, 5, 13, 25, 16));
        int[] changeArray = {26, 52, 78, 0, 0, 104, -7, 15};
        int wordCount = 2;
        int[] wordLengths = {4, 4};

        String result = App.decipher(integerList, changeArray, wordCount, wordLengths);

        assertNotNull(result);
        assertEquals("utku emre", result);
    }

    @Test
    public void testInvalidWordCount() {
        ArrayList<Integer> integerList = new ArrayList<>(Arrays.asList(2, 7, 12, 1, 15, 8));
        int[] changeArray = {3, 5, 1, 7, 2, 4};
        int wordCount = 4;
        int[] wordLengths = {3, 3};

        String result = App.decipher(integerList, changeArray, wordCount, wordLengths);

        assertNull(result);
    }

    @Test
    public void testInvalidSumOfWordLengths() {
        ArrayList<Integer> integerList = new ArrayList<>(Arrays.asList(2, 7, 12, 1, 15, 8));
        int[] changeArray = {3, 5, 1, 7, 2, 4};
        int wordCount = 2;
        int[] wordLengths = {5, 2};

        String result = App.decipher(integerList, changeArray, wordCount, wordLengths);

        assertNull(result);
    }

    @Test
    public void testInvalidOfSumLessThanOne() {
        ArrayList<Integer> integerList = new ArrayList<>(Arrays.asList(2, 7, 12, 1, 15, 8));
        int[] changeArray = {3, -10, 1, 7, 2, 4};
        int wordCount = 2;
        int[] wordLengths = {3, 3};

        String result = App.decipher(integerList, changeArray, wordCount, wordLengths);

        assertNull(result);
    }

    @Test
    public void testEmptyInput() {
        ArrayList<Integer> integerList = new ArrayList<>();
        int[] changeArray = {};
        int wordCount = 0;
        int[] wordLengths = {};

        String result = App.decipher(integerList, changeArray, wordCount, wordLengths);

        assertNull(result);
    }
}
