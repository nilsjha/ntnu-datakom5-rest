package no.ntnu.nilsjarh.rest;

import java.net.URL;

public class RestClient {
        private String endpointUrl;
        
        public void setEndpointUrl(String host, int port, boolean ssl) {
                if (ssl) {
                        this.endpointUrl = "https://" + host + ":" + port + "/";
                } else {
                        this.endpointUrl = "http://" + host + ":" + port + "/";
                }
        }
}
