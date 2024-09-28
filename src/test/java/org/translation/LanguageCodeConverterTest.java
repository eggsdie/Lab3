package org.translation;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LanguageCodeConverterTest {

    private LanguageCodeConverter converter;

    @Before
    public void setUp() {
        converter = new LanguageCodeConverter();
    }

    @Test
    public void testFromLanguageCode_English() {
        String result = converter.fromLanguageCode("en");
        assertEquals("Expected 'en' to return 'English'", "English", result);
    }

    @Test
    public void testFromLanguageCode_French() {
        String result = converter.fromLanguageCode("fr");
        assertEquals("Expected 'fr' to return 'French'", "French", result);
    }

    @Test
    public void testFromLanguageCodeAllLoaded() {
        int numLanguages = converter.getNumLanguages();
        assertEquals("Expected 184 languages to be loaded, but got " + numLanguages, 184, numLanguages);
    }

    @Test
    public void testInvalidLanguageCode() {
        String result = converter.fromLanguageCode("xyz");
        assertEquals("Expected 'xyz' to return 'Unknown Code'", "Unknown Code", result);
    }

    @Test
    public void testEmptyLanguageCode() {
        String result = converter.fromLanguageCode("");
        assertEquals("Expected empty string to return 'Unknown Code'", "Unknown Code", result);
    }

    @Test
    public void testNullLanguageCode() {
        String result = converter.fromLanguageCode(null);
        assertEquals("Expected null to return 'Unknown Code'", "Unknown Code", result);
    }

    @Test
    public void testLowercaseLanguageCode() {
        // Test if converter is case-insensitive
        String result = converter.fromLanguageCode("EN");
        assertEquals("Expected 'EN' (uppercase) to return 'English'", "English", result);
    }
}
