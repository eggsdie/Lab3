package org.translation;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class JSONTranslatorTest {

    private JSONTranslator jsonTranslator;

    @Before
    public void setUp() {
        jsonTranslator = new JSONTranslator();
    }

    @Test
    public void testGetCountryLanguages() {
        List<String> countryLanguages = jsonTranslator.getCountryLanguages("CAN");
        assertEquals("Expected 35 languages for 'CAN', but got " + countryLanguages.size(), 35, countryLanguages.size());
    }

    @Test
    public void testGetCountries() {
        List<String> countries = jsonTranslator.getCountries();
        assertEquals("Expected 193 countries, but got " + countries.size(), 193, countries.size());
    }

    @Test
    public void testTranslateEnglish() {
        assertEquals("Expected translation of 'CAN' to 'en' to be 'Canada'", "Canada", jsonTranslator.translate("CAN", "en"));
    }

    @Test
    public void testTranslateFrench() {
        assertEquals("Expected translation of 'CAN' to 'fr' to be 'Canada'", "Canada", jsonTranslator.translate("CAN", "fr"));
    }

    @Test
    public void testInvalidCountryCode() {
        assertEquals("Expected 'Country not found' for invalid country code", "Country not found", jsonTranslator.translate("XYZ", "en"));
    }

    @Test
    public void testInvalidLanguageCode() {
        assertEquals("Expected 'Translation not found for the specified language' for invalid language code", "Translation not found for the specified language", jsonTranslator.translate("CAN", "xyz"));
    }

    @Test
    public void testLowercaseCountryCode() {
        // This tests case insensitivity for the country code
        assertEquals("Expected translation of 'can' (lowercase) to 'en' to be 'Canada'", "Canada", jsonTranslator.translate("can", "en"));
    }

    @Test
    public void testMixedCaseInput() {
        // This tests case insensitivity for both country and language codes
        assertEquals("Expected translation of 'cAn' to 'En' to be 'Canada'", "Canada", jsonTranslator.translate("cAn", "En"));
    }
}
