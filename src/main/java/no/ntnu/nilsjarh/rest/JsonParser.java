package no.ntnu.nilsjarh.rest;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class JsonParser {
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
        int a = randA.nextInt();
        int b = randB.nextInt();
        int c = randC.nextInt();
        
        JSONObject abcRandJson = new JSONObject();
        abcRandJson.put("a",a);
        abcRandJson.put("b",b);
        abcRandJson.put("c",c);
        
        return abcRandJson.toString();
    }
    
    public String encodeSingleStringToJson(String key, String data) {
        JSONObject json = new JSONObject();
        json.put(key,data);
        return json.toString();
    }
    
}
