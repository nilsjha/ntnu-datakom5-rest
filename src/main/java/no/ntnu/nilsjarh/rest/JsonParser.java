package no.ntnu.nilsjarh.rest;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class JsonParser {
    /**
     *  Get int from specified key in JSON-encoded string
     * @param jsonInput The JSON-encoded string
     * @param key the corresponding key in the JSON-encoded string
     * @return The converted Integer
     */
    public int extractInt(String jsonInput, String key) {
        int result = 0;
        try {
            JSONObject jsonObj = new JSONObject(jsonInput);
            result = jsonObj.getInt(key);
        } catch (JSONException e) {
            System.err.println("Unable to parse JSON: " + e.getMessage());
        }
        return result;
    }
    
    /**
     *  Get String from a specified key in a JSON-encoded string
     * @param jsonInput The JSON-encoded string
     * @param key the corresponding key to extract data from
     * @return The converted String
     */
    public String extractString(String jsonInput, String key) {
        String result = "";
        try {
            JSONObject jsonObj = new JSONObject(jsonInput);
            result = jsonObj.getString(key);
        } catch (JSONException e) {
            System.err.println("Unable to parse JSON: " + e.getMessage());
        }
        return result;
    }
    
    /**
     *  Extract a boolean value from JSON-string and return this value
     * @param jsonInput The JSON-encoded string
     * @param key The key with the boolean value
     * @return The extracted boolean value from JSON e.g. true/false
     */
    public boolean extractBoolean(String jsonInput, String key) {
        try {
            JSONObject jsonObj = new JSONObject(jsonInput);
            // return the boolean value if successful
            return jsonObj.getBoolean(key);
        } catch (JSONException e) {
            System.err.println("Unable to parse JSON: " + e.getMessage());
            return false;
        }
    }
    
    /**
     *  Sorts the JSON elements in an encoded string
     * @param jsonInput String with JSON-encoded elements
     * @return String with the JSON elements sorted alphabetically
     */
    public String sortJsonString(String jsonInput) {
       try {
           JSONObject jsonObj = new JSONObject(jsonInput);
           return jsonObj.toString();
       } catch (JSONException e) {
           System.err.println("Unable to parse JSON: " + e.getMessage());
           return "";
       }
    }
    
    /**
     *  Demo purposes only, generates a JSON-encoded string with three random
     *  numbers.
     * @return JSON-string with a,b & c keys with random values.
     */
    public String generateDemoAbcNumbers() {
        Random randA = new Random();
        Random randB = new Random();
        Random randC = new Random();
        int a = randA.nextInt((100) +1);
        int b = randB.nextInt((100) +1);
        
        JSONObject abcRandJson = new JSONObject();
        abcRandJson.put("a",a);
        abcRandJson.put("b",b);
        
        return abcRandJson.toString();
    }
    
    /**
     *  encode a single string to a JSON-encoded object
     * @param key String to identify the key
     * @param data String to identify the keyed data
     * @return The generated JSON-object
     */
    public JSONObject encodeSingleStringToJson(String key, String data) {
        JSONObject json = new JSONObject();
        json.put(key,data);
        return json;
    }
    
}
