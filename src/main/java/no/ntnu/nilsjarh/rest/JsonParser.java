
/**  RestAPI Example in Java  Copyright (C) 2019 Nils-Jarle Haugen
 *   This program comes with ABSOLUTELY NO WARRANTY
 *   This is free software, and you are welcome to redistribute it
 *   under certain conditions; type `show c' for details.
 */

package no.ntnu.nilsjarh.rest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Random;

public class JsonParser {
    
    /**
     *  Creates a json object from a json.encoded string
     * @param json JSON-encoded string
     * @return The returned object
     */
    public JSONObject generateJsonObject(String json) {
        JSONObject obj = new JSONObject(json);
        return obj;
    }
    
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
     *  Returns a single string from all elements in an JSON-array
     * @param array JSON-array to parse strings from
     * @return Single string with contents of the array
     */
   public String extractStringFromArray(JSONArray array) {
       Iterator<Object> hashIt = array.iterator();
       StringBuilder sb = new StringBuilder();
       while (hashIt.hasNext()) {
           sb.append(hashIt.next().toString());
       }
       return sb.toString();
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
        Random rand = new Random();
        int a = rand.nextInt((100) +1);
        int b = rand.nextInt((100) +1);
        
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
