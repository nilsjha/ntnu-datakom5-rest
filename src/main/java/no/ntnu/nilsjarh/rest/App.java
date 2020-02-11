/**  RestAPI Example in Java  Copyright (C) 2019 Nils-Jarle Haugen
 *   This program comes with ABSOLUTELY NO WARRANTY
 *   This is free software, and you are welcome to redistribute it
 *   under certain conditions; type `show c' for details.
 */

package no.ntnu.nilsjarh.rest;

public class App {
    public static final boolean debug = true;
    
    public static void main(String[] args) {
  
        // demo to datakomm.work
        if (debug) {
            RestClient demo = new RestClient();
            demo.setEndpointUrl("datakomm.work",80,false);
            System.out.println(demo.send("dkrest/test/get"));
    
    
            String jsonAbc = demo.send("dkrest/test/get2");
            JsonParser abcParse = new JsonParser();
            System.out.println(abcParse.sortJsonString(jsonAbc));
            System.out.println(abcParse.extractInt(jsonAbc,"a"));
            System.out.println(abcParse.extractInt(jsonAbc,"b"));
            System.out.println(abcParse.extractInt(jsonAbc,"c"));
    
            String randomPost = abcParse.generateDemoAbcNumbers();
            System.out.println("Sending POST: " + randomPost);
            System.out.println(demo.send("dkrest/test/post", randomPost));
        }
        
        TaskRunner assignment = new TaskRunner();
        assignment.authorize();
        assignment.step1Hello();
        assignment.step2Echo();
        assignment.step3Multiplication();
        assignment.step4crackPin();
        assignment.stepExtra();
        assignment.step5feedback();
        System.out.println("USER-ID:"+ assignment.getUserId());
        System.out.println("SESSION-ID:" + assignment.getSessionId());
    }
}
