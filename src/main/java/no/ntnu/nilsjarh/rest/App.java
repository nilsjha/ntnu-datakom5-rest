package no.ntnu.nilsjarh.rest;

public class App {
    
    public static void main(String[] args) {
  
        // demo to datakomm.work
        RestClient demo = new RestClient();
        demo.setEndpointUrl("datakomm.work",80,false);
        System.out.println(demo.send("dkrest/test/get"));
        

        String jsonAbc = demo.send("dkrest/test/get2");
        JsonParser abcParse = new JsonParser();
        System.out.println(abcParse.sortJsonString(jsonAbc));
        System.out.println(abcParse.extractInt(jsonAbc,"a"));
        System.out.println(abcParse.extractInt(jsonAbc,"b"));
        System.out.println(abcParse.extractInt(jsonAbc,"c"));
    }
    
}
