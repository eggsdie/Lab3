package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * A minimal example of reading and using the JSON data from resources/sample.json.
 */
public class JSONTranslationExample {

    public static final int CANADA_INDEX = 30;
    private final JSONArray jsonArray;

    // Note: CheckStyle is configured so that we are allowed to omit javadoc for constructors
    public JSONTranslationExample() {
        try {
            // Split the long line for CheckStyle compliance
            String jsonString = Files.readString(
                    Paths.get(getClass().getClassLoader().getResource("sample.json").toURI()));
            this.jsonArray = new JSONArray(jsonString);
        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException("Error reading JSON file", ex);
        }
    }

    /**
     * Returns the Spanish translation of Canada.
     * @return the Spanish translation of Canada
     */
    public String getCanadaCountryNameSpanishTranslation() {
        // Using CANADA_INDEX instead of magic number 30
        JSONObject canada = jsonArray.getJSONObject(CANADA_INDEX);
        return canada.getString("es");
    }

    /**
     * Returns the name of the country based on the provided country and language codes.
     * @param countryCode the country, as its three-letter code.
     * @param languageCode the language to translate to, as its two-letter code.
     * @return the translation of country to the given language or "Country not found" if there is no translation.
     */
    public String getCountryNameTranslation(String countryCode, String languageCode) {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject country = jsonArray.getJSONObject(i);
            // Assuming each country object has a "code" key with the three-letter country code
            if (countryCode.equalsIgnoreCase(country.getString("code"))) {
                if (country.has(languageCode)) {
                    return country.getString(languageCode);
                } else {
                    return "Translation not found for the specified language";
                }
            }
        }
        return "Country not found";
    }

    /**
     * Prints the Spanish translation of Canada.
     * @param args not used
     */
    public static void main(String[] args) {
        JSONTranslationExample jsonTranslationExample = new JSONTranslationExample();

        // Print Spanish translation for Canada
        System.out.println("Spanish translation of Canada: "
                + jsonTranslationExample.getCanadaCountryNameSpanishTranslation());

        // Get country translation for Canada in Spanish
        String translation = jsonTranslationExample.getCountryNameTranslation("CAN", "es");
        System.out.println("Translation for CAN in es: " + translation);
    }
}
