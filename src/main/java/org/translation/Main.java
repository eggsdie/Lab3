package org.translation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for this program.
 * Complete the code according to the "to do" notes.<br/>
 * The system will:<br/>
 * - prompt the user to pick a country name from a list<br/>
 * - prompt the user to pick the language they want it translated to from a list<br/>
 * - output the translation<br/>
 * - at any time, the user can type quit to quit the program<br/>
 */
public class Main {

    /**
     * This is the main entry point of our Translation System!<br/>
     * A class implementing the Translator interface is created and passed into a call to runProgram.
     * @param args not used by the program
     */
    public static void main(String[] args) {

        // Use JSONTranslator after its implementation is done.
        Translator translator = new JSONTranslator();

        runProgram(translator);
    }

    /**
     * This is the method which we will use to test your overall program, since
     * it allows us to pass in whatever translator object that we want!
     * See the class Javadoc for a summary of what the program will do.
     * @param translator the Translator implementation to use in the program
     */
    public static void runProgram(Translator translator) {
        final String quitCommand = "quit";

        while (true) {
            String country = promptForCountry(translator);
            if (quitCommand.equals(country)) {
                break;
            }

            // Convert the country name back to the country code
            String countryCode = translator.fromCountry(country);

            String language = promptForLanguage(translator, countryCode);
            if (quitCommand.equals(language)) {
                break;
            }

            // Convert the language name back to the language code
            String languageCode = translator.fromLanguage(language);

            System.out.println(country + " in " + language + " is " + translator.translate(countryCode, languageCode));
            System.out.println("Press enter to continue or quit to exit.");
            Scanner s = new Scanner(System.in);
            String textTyped = s.nextLine();

            if (quitCommand.equals(textTyped)) {
                break;
            }
        }
    }

    // Note: CheckStyle is configured so that we don't need javadoc for private methods
    private static String promptForCountry(Translator translator) {
        List<String> countryCodes = translator.getCountries();
        List<String> countryNames = new ArrayList<>();

        // Convert country codes to their corresponding country names
        for (String countryCode : countryCodes) {
            countryNames.add(translator.translate(countryCode, "en")); // Assuming 'en' is the language code for English
        }

        // Sort country names alphabetically
        Collections.sort(countryNames);

        // Print out each country name
        for (String countryName : countryNames) {
            System.out.println(countryName);
        }

        System.out.println("Select a country from above (or type 'quit' to exit):");

        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

    // Note: CheckStyle is configured so that we don't need javadoc for private methods
    private static String promptForLanguage(Translator translator, String countryCode) {
        List<String> languageCodes = translator.getCountryLanguages(countryCode);
        List<String> languageNames = new ArrayList<>();

        // Convert language codes to their corresponding language names
        for (String languageCode : languageCodes) {
            languageNames.add(translator.fromLanguage(languageCode)); // Assuming 'fromLanguage' converts code to name
        }

        // Sort language names alphabetically
        Collections.sort(languageNames);

        // Print out each language name
        for (String languageName : languageNames) {
            System.out.println(languageName);
        }

        System.out.println("Select a language from above (or type 'quit' to exit):");

        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }
}
