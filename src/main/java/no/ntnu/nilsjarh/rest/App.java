package no.ntnu.nilsjarh.rest;

public class App {
    
    public static void main(String[] args) {
  
        // demo to datakomm.work
        RestClient demo = new RestClient();
        demo.setEndpointUrl("datakomm.work",80,false);
        System.out.println(demo.send("dkrest/test/get"));
    }
    
}
