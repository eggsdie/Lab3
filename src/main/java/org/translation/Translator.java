package org.translation;

import java.util.List;

/**
 * An interface providing methods related to translating country names between
 * different languages.<br/>
 */
public interface Translator {

    /**
     * Returns the language codes for all languages whose translations are
     * available for the given country.
     * @param country the country
     * @return list of language codes which are available for this country
     */
    List<String> getCountryLanguages(String country);

    /**
     * Returns the country codes for all countries whose translations are
     * available from this Translator.
     * @return list of country codes for which we have translations available
     */
    List<String> getCountries();

    /**
     * Returns the name of the country based on the specified country abbreviation and language abbreviation.
     * @param country the country code
     * @param language the language code
     * @return the name of the country in the given language or null if no translation is available
     */
    String translate(String country, String language);

    /**
     * Converts the country name into its 3-letter country code.
     * @param countryName the name of the country
     * @return the 3-letter country code, or "Unknown Country" if not found
     */
    String fromCountry(String countryName);

    /**
     * Converts the language name into its 2-letter language code.
     * @param languageName the name of the language
     * @return the 2-letter language code, or "Unknown Language" if not found
     */
    String fromLanguage(String languageName);
}
