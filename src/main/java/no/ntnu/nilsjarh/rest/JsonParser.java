package no.ntnu.nilsjarh.rest;
import org.json.JSONException;
import org.json.JSONObject;

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
    
    
    
}
