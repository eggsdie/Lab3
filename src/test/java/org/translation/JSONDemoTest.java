package org.translation;

import org.json.JSONArray;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class JSONDemoTest {

    @Test
    public void testGetKeyOneOfSecond() {
        String jsonData = "[{\"key1\" : \"string1a\", \"key2\":21}, {\"key1\" : \"string1b\", \"key2\":22}]";
        JSONArray jsonArray = new JSONArray(jsonData);
        assertEquals("string1b", JSONDemo.getKeyOneOfSecond(jsonArray));
    }

    @Test
    public void testGetKeyOneOfSecondWithInsufficientElements() {
        String jsonData = "[{\"key1\" : \"string1a\", \"key2\":21}]"; // Only one element
        JSONArray jsonArray = new JSONArray(jsonData);

        try {
            JSONDemo.getKeyOneOfSecond(jsonArray);
            fail("Expected an exception due to insufficient elements");
        } catch (IndexOutOfBoundsException e) {
            // Test passes if exception is caught
        }
    }

    @Test
    public void testGetKeyOneOfSecondWithMissingKey() {
        String jsonData = "[{\"key1\" : \"string1a\", \"key2\":21}, {\"key2\":22}]"; // No "key1" in second element
        JSONArray jsonArray = new JSONArray(jsonData);

        try {
            JSONDemo.getKeyOneOfSecond(jsonArray);
            fail("Expected an exception due to missing 'key1'");
        } catch (org.json.JSONException e) {
            // Test passes if exception is caught
        }
    }
}
