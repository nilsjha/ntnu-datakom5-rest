package no.ntnu.nilsjarh.rest;

import org.json.JSONObject;

/**
 *  The taskrunner class runs the different tasks for this assignment
 */
public class TaskRunner {
    private JSONObject json1;
    private RestClient rest1;
    private JsonParser parser1;
    private String host = "datakomm.work";
    private int port = 80;
    
    private String phone = "9592222";
    private String mail = "nisfsf@stud.ntnu.no";
    
    private int sessionId;
    private int userId;
    
    public TaskRunner() {
        json1 = new JSONObject();
        rest1 = new RestClient();
        parser1 = new JsonParser();
        rest1.setEndpointUrl(host, port, false);
    }
    
    /**
     *  STEP 1 Authorize
      */
    public void authorize() {
        String authJson;
        json1.put("email",mail);
        json1.put("phone",phone);
        authJson = json1.toString();
        String authResponse = rest1.send("dkrest/auth",authJson);
        sessionId = parser1.extractInt(authResponse,"sessionId");
        userId = parser1.extractInt(authResponse,"userId");
        System.out.println(authResponse);
        
    }
}
