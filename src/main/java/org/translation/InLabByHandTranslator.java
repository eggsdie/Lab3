package org.translation;

import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of the Translator interface which translates
 * the country code "can" to several languages.
 */
public class InLabByHandTranslator implements Translator {

    public static final String CANADA = "can";

    /**
     * Returns the language abbreviations for all languages whose translations are
     * available for the given country.
     *
     * @param country the country
     * @return list of language abbreviations which are available for this country
     */
    @Override
    public List<String> getCountryLanguages(String country) {
        if (CANADA.equals(country)) {
            // Added support for Spanish ("es") and French ("fr")
            return new ArrayList<>(List.of("de", "en", "zh", "es", "fr"));
        }
        return new ArrayList<>();
    }

    /**
     * Returns the country abbreviations for all countries whose translations are
     * available from this Translator.
     *
     * @return list of country abbreviations for which we have translations available
     */
    @Override
    public List<String> getCountries() {
        return new ArrayList<>(List.of(CANADA));
    }

    /**
     * Returns the name of the country based on the specified country abbreviation and language abbreviation.
     *
     * @param country  the country
     * @param language the language
     * @return the name of the country in the given language or null if no translation is available
     */
    @Override
    public String translate(String country, String language) {
        if (!CANADA.equals(country)) {
            return null;
        }

        // Use switch to reduce the number of return statements
        switch (language) {
            case "de": return "Kanada";
            case "en":
            case "fr": return "Canada";
            case "zh": return "加拿大";
            case "es": return "Canadá";
            default: return null;
        }
    }

    /**
     * Returns the language abbreviation based on the given language name.
     * @param language the name of the language
     * @return the abbreviation of the language or "Unknown Language" if not found
     */
    @Override
    public String fromLanguage(String language) {
        switch (language.toLowerCase()) {
            case "german": return "de";
            case "english": return "en";
            case "french": return "fr";
            case "chinese": return "zh";
            case "spanish": return "es";
            default: return "Unknown Language";
        }
    }
}
