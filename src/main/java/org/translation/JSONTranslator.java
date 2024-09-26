package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * An implementation of the Translator interface which reads in the translation
 * data from a JSON file. The data is read in once each time an instance of this class is constructed.
 */
public class JSONTranslator implements Translator {

    private Map<String, List<String>> countryToLanguages;
    private Map<String, Map<String, String>> translations;

    // Additional maps to store reverse lookups for country and language names to codes
    private Map<String, String> countryNameToCode;
    private Map<String, String> languageNameToCode;

    /**
     * Constructs a JSONTranslator using data from the sample.json resources file.
     */
    public JSONTranslator() {
        this("sample.json");
    }

    /**
     * Constructs a JSONTranslator populated using data from the specified resources file.
     * @param filename the name of the file in resources to load the data from
     * @throws RuntimeException if the resource file can't be loaded properly
     */
    public JSONTranslator(String filename) {
        countryToLanguages = new HashMap<>();
        translations = new HashMap<>();
        countryNameToCode = new HashMap<>();
        languageNameToCode = new HashMap<>();

        // read the file to get the data to populate things...
        try {
            String jsonString = Files.readString(Paths.get(getClass().getClassLoader().getResource(filename).toURI()));
            JSONArray jsonArray = new JSONArray(jsonString);

            // Populate instance variables
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject countryData = jsonArray.getJSONObject(i);
                String countryCode = countryData.getString("code");
                final String countryName = countryData.getString("en");
                countryNameToCode.put(countryName, countryCode);

                // Store language codes and their translations
                List<String> languageCodes = new ArrayList<>();
                Map<String, String> languageToTranslation = new HashMap<>();

                for (String key : countryData.keySet()) {
                    if (!"code".equals(key)) {
                        languageCodes.add(key);
                        languageToTranslation.put(key, countryData.getString(key));

                        // Populate languageNameToCode map
                        if (!languageNameToCode.containsKey(countryData.getString(key))) {
                            languageNameToCode.put(countryData.getString(key), key);
                        }
                    }
                }

                countryToLanguages.put(countryCode, languageCodes);
                translations.put(countryCode, languageToTranslation);

                // Populate the countryNameToCode map for reverse lookup
                countryNameToCode.put(countryName, countryCode);
            }
        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<String> getCountryLanguages(String country) {
        // Return a copy of the language list to avoid aliasing with mutable objects
        return new ArrayList<>(countryToLanguages.getOrDefault(country, new ArrayList<>()));
    }

    @Override
    public List<String> getCountries() {
        // Return a copy of the country list to avoid aliasing with mutable objects
        return new ArrayList<>(countryToLanguages.keySet());
    }

    @Override
    public String translate(String country, String language) {
        String result = "Country not found";

        // Check if the country exists in the translations map
        if (translations.containsKey(country)) {
            Map<String, String> languageMap = translations.get(country);
            // Check if the language exists for the country
            if (languageMap.containsKey(language)) {
                result = languageMap.get(language);
            }
            else {
                result = "Translation not found for the specified language";
            }
        }

        return result;
    }

    @Override
    public String fromCountry(String countryName) {
        // Look up the country code using the country name
        return countryNameToCode.getOrDefault(countryName, "Unknown Country");
    }

    @Override
    public String fromLanguage(String languageName) {
        // Look up the language code using the language name
        return languageNameToCode.getOrDefault(languageName, "Unknown Language");
    }
}
