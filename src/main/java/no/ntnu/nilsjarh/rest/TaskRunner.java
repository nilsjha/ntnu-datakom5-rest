package no.ntnu.nilsjarh.rest;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

/**
 *  The taskrunner class runs the different tasks for this assignment
 */
public class TaskRunner {
    
    private JSONObject json1;
    private RestClient rest1;
    private JsonParser parser1;
    private String host = "datakomm.work";
    private int port = 80;
    
    private String phone = "95927996";
    private String mail = "nilsjarh@stud.ntnu.no";
    
    private int sessionId;
    private int userId;
    
    private JSONArray currentTaskArgs;
    
    public TaskRunner() {
        json1 = new JSONObject();
        rest1 = new RestClient();
        parser1 = new JsonParser();
        rest1.setEndpointUrl(host, port, false);
    }
    /**
     *  STEP 0 Authorize
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
    /**
     *  STEP 1 - Send hello
     */
    public void step1Hello() {
        if (this.askForTask(1)) {
            JSONObject jsonHello = new JSONObject();
            jsonHello.put("sessionId",sessionId);
            jsonHello.put("msg","Hello");
            String helloPostResponse = rest1.send("dkrest/solve",jsonHello.toString());
            System.out.println(helloPostResponse);
        }
    }
    /**
     * STEP 2 - Echo response
     */
    public void step2Echo() {
        if (this.askForTask(2)) {
            String theEcho;
            JSONObject jsonEcho = new JSONObject();
            // WIP Return the echo with the correct arguments from array
            theEcho = parser1.extractStringFromArray(currentTaskArgs);
            System.out.println("PARSED ECHO: "+ theEcho);
            jsonEcho.put("sessionId", sessionId);
            jsonEcho.put("msg",theEcho);
            String echoResponse = rest1.send("dkrest/solve",jsonEcho.toString());
            System.out.println(echoResponse);
        }
    }
    
    /**
     * STEP 3 - Multiplication
     */
    public void step3Multiplication() {
        if (this.askForTask(3)) {
            JSONObject jsonObj = new JSONObject();
            Iterator <Object> it = currentTaskArgs.iterator();
            int result = 1;
            // Iterate through all array elements
            while (it.hasNext()) {
                int currentNumber = Integer.parseInt(it.next().toString());
                System.out.print(currentNumber + "*" + result);
                // Multiply previous result with current number
                result = result*currentNumber;
                System.out.println("=" + result);
            }
            System.out.println("STEP 3: FINAL RESTULT:" + result);
            jsonObj.put("sessionId", sessionId);
            jsonObj.put("result", result);
            String step3Response = rest1.send("dkrest/solve",
                jsonObj.toString());
            System.out.println(step3Response);
        }
    }
    
    /**
     * STEP 4 - Crack pin code
     */
    public void step4crackPin() {
        if (this.askForTask(4)) {
            JSONObject pinObj = new JSONObject();
            String md5Hash = parser1.extractStringFromArray(currentTaskArgs);
            System.out.print("MD5 hash: ");
            System.out.println(md5Hash);
   
            String correctPin = "";
            int d1;
            int d2;
            int d3;
            int d4;
   
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                for (d1 = 0; d1 < 10; d1++) {
                    for (d2 = 0; d2 < 10; d2++) {
                        for (d3 = 0; d3 < 10; d3++) {
                            for (d4 = 0; d4 < 10; d4++) {
                                String pin = Integer.toString(d1)
                                        +Integer.toString(d2)
                                        +Integer.toString(d3)
                                        +Integer.toString(d4);
                                md.update(pin.getBytes());
                                byte[] digest = md.digest();
                                String foundHash = DatatypeConverter
                                    .printHexBinary(digest).toLowerCase();
                                //System.out.print(pin + ",");
                                //System.out.println(foundHash);
                                if (md5Hash.equals(foundHash)) {
                                   System.out.print("OK hash:" + foundHash +
                                       ";" + pin);
                                   correctPin = pin;
                               }
                            }
                
                        }
                    }
        
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
                if (correctPin.equals("")) {
                    System.out.println("No match!");
            }
                else {
                    System.out.println("Found pin" + correctPin);
                    pinObj.put("sessionId",sessionId);
                    pinObj.put("pin",correctPin);
                    String step3Response = rest1.send("dkrest/solve",
                        pinObj.toString());
                    System.out.println(step3Response);
                }
        }
        
        
        
    }
    
    /**
     *  This method asks the server to solve a task, and then verifies the
     *  response from the server, and extracts arguments to the related task
     * @param taskNumber The task to be executed
     * @return TRUE if the server responds with the requested task, otherwise
     * FALSE
     */
    private boolean askForTask(int taskNumber) {
        // Set non-exisiting tasknumber, before server has responded.
        int checkTaskNumber = -1;
        if ((1 <= taskNumber) && (taskNumber <= 4)) {
            JSONObject jsonAsk = new JSONObject();
            String response = rest1.send("dkrest/gettask/"
                + taskNumber + "?sessionId=" + sessionId);
            checkTaskNumber = parser1.extractInt(response,"taskNr");
            System.out.println("RESPONSE:" + response);
            
            JSONObject responseObj = parser1.generateJsonObject(response);
            // Check if the response has arguments, then put them in an array
            if (responseObj.has("arguments")) {
                currentTaskArgs = responseObj.getJSONArray("arguments");
                System.out.println("ARGS STORED:" + currentTaskArgs);
            }
        }
        System.out.print("Asking for task "+ taskNumber +"...");
        if (taskNumber == checkTaskNumber) {
            System.out.println(" OK!");
            return true;
        }else  {
            System.out.println(" mismatch(" + taskNumber
                + "!=" + checkTaskNumber + "!");
            return false;
        }
    }
}
