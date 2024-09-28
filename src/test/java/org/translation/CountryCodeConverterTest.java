package org.translation;

import org.junit.Test;
import static org.junit.Assert.*;

public class CountryCodeConverterTest {

    @Test
    public void testFromCountryCode_USA() {
        CountryCodeConverter converter = new CountryCodeConverter();
        // Adjust expected result based on actual data, ensuring consistency
        assertEquals("United States of America", converter.fromCountryCode("USA"));
    }

    @Test
    public void testAllCountryCodesLoaded() {
        CountryCodeConverter converter = new CountryCodeConverter();
        // Ensure that all 249 country codes are loaded
        assertEquals(249, converter.getNumCountries());
    }

    @Test
    public void testInvalidCountryCode() {
        CountryCodeConverter converter = new CountryCodeConverter();
        // Test how invalid country code is handled
        assertEquals("Unknown Code", converter.fromCountryCode("XXX"));
    }

    @Test
    public void testLowercaseCountryCode() {
        CountryCodeConverter converter = new CountryCodeConverter();
        // If the converter is case-insensitive, this should pass
        assertEquals("United States of America", converter.fromCountryCode("usa"));
    }
}
