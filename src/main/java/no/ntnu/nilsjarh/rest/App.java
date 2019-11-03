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
        // ask for task 1
        boolean task1Ok = assignment.askForTask(1);
        if (task1Ok) {
            System.out.print("Got task 1");
        }
    }
}
