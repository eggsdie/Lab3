package org.translation;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class JSONReaderTest {

    private JSONTranslationExample jsonTranslationExample;

    @Before
    public void setUp() {
        jsonTranslationExample = new JSONTranslationExample();
    }

    @Test
    public void testGetCountryNameTranslationUSA_EN() {
        String result = jsonTranslationExample.getCountryNameTranslation("USA", "en");
        assertEquals("United States of America", result);
    }

    @Test
    public void testGetCountryNameTranslationFRA_FR() {
        String result = jsonTranslationExample.getCountryNameTranslation("FRA", "fr");
        assertEquals("France", result);
    }

    @Test
    public void testGetCountryNameTranslationDEU_DE() {
        String result = jsonTranslationExample.getCountryNameTranslation("DEU", "de");
        assertEquals("Deutschland", result);
    }

    @Test
    public void testGetCountryNameTranslationInvalidCountry() {
        String result = jsonTranslationExample.getCountryNameTranslation("UT", "en");
        assertEquals("Country not found", result);
    }

    @Test
    public void testGetCountryNameTranslationInvalidLanguage() {
        String result = jsonTranslationExample.getCountryNameTranslation("USA", "xyz");
        assertEquals("Translation not found for the specified language", result);
    }

    @Test
    public void testGetCountryNameTranslationLowercaseInput() {
        String result = jsonTranslationExample.getCountryNameTranslation("usa", "en");
        assertEquals("United States of America", result);
    }

    @Test
    public void testGetCountryNameTranslationMixedCaseInput() {
        String result = jsonTranslationExample.getCountryNameTranslation("uSA", "En");
        assertEquals("United States of America", result);
    }
}
