package no.ntnu.nilsjarh.rest;

import org.json.JSONObject;

/**
 *  The taskrunner class runs the different tasks for this assignment
 */
public class TaskRunner {
    private boolean debug = false;   // Enable verbose debugging
    
    private JSONObject json1;
    private RestClient rest1;
    private JsonParser parser1;
    private String host = "datakomm.work";
    private int port = 80;
    
    private String phone = "95927996";
    private String mail = "nilsjarh@stud.ntnu.no";
    
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
        boolean success;
        json1.put("email",mail);
        json1.put("phone",phone);
        String authResponse = rest1.send("dkrest/auth",json1.toString());
        success = parser1.extractBoolean(authResponse,"success");
        if (success) {
            sessionId = parser1.extractInt(authResponse,"sessionId");
            userId = parser1.extractInt(authResponse,"userId");
            System.out.println("Grabbed sessionID:"
                + sessionId + " and userId:" + userId + " successfully!");
        } else {
            System.out.println("Auth failed, see response for details: ");
            System.out.println(authResponse);
        }
        
    }
    
    public boolean askForTask(int taskNumber) {
        String response;
        int checkTaskNumber = -1;
        if ((1 <= taskNumber) && (taskNumber <= 4)) {
            JSONObject jsonAsk = new JSONObject();
            response = rest1.send("dkrest/gettask/"
                + taskNumber + "?sessionId=" + sessionId);
            checkTaskNumber = parser1.extractInt(response,"taskNr");
           
            if (App.debug) {
                System.out.println(response);
                System.out.println(checkTaskNumber);
            }
        }
        System.out.print("Asking for task "+ taskNumber +"...");
        if (taskNumber == checkTaskNumber) {
            System.out.println(" OK!");
            return true;
        }else  {
            return false;
        }
    }
}