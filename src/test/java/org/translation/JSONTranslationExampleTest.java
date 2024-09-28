package org.translation;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JSONTranslationExampleTest {

    private JSONTranslationExample jsonTranslationExample;

    @Before
    public void setUp() {
        jsonTranslationExample = new JSONTranslationExample();
    }

    @Test
    public void testGetCountryNameTranslationForCanadaInSpanish() {
        String expected = jsonTranslationExample.getCanadaCountryNameSpanishTranslation();
        String result = jsonTranslationExample.getCountryNameTranslation("CAN", "es");

        assertEquals("Expected Spanish translation for 'CAN' to be '" + expected + "', but got '" + result + "'", expected, result);
    }

    @Test
    public void testGetCountryNameTranslationLowercaseInput() {
        // Ensures case insensitivity if the implementation supports it
        String expected = jsonTranslationExample.getCanadaCountryNameSpanishTranslation();
        String result = jsonTranslationExample.getCountryNameTranslation("can", "es");

        assertEquals("Expected Spanish translation for 'can' (lowercase) to be '" + expected + "', but got '" + result + "'", expected, result);
    }
}
