package no.ntnu.nilsjarh.rest;

public class App {
    
    public static void main(String[] args) {
  
        // demo to datakomm.work
        RestClient demo = new RestClient();
        demo.setEndpointUrl("datakomm.work",80,false);
        String getDemo1 = demo.send("dkrest/test/get");
        String getDemo2 = demo.send("dkrest/test/get2");
        System.out.println(getDemo1);
        System.out.println(getDemo2);
        JsonParser parser = new JsonParser();
        int parsed = parser.extractInt(getDemo2,"a");
        String sortedDemo2 = parser.sortJsonString(getDemo2);
        System.out.println(sortedDemo2);
        System.out.println(parsed);
    }
    
}
