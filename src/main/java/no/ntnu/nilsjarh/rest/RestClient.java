package no.ntnu.nilsjarh.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
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
        
        /**
         * This method sends HTTP GET methods to the predefiend endpoint
         * @param getDir root directory for GET request
         * @return true if successful, otherwise false
         */
        public String send(String getDir) {
                boolean cts = true;
                String response = null;
                // check that all of the required elements are ready for tx
                if (this.endpointUrl == null) {
                        System.err.println("Endpoint URL not set, ignoring...");
                        cts = false;
                }
                if (cts) {
                        try  {
                                String url = this.endpointUrl + getDir;
                                URL urlObj = new URL(url);
                                HttpURLConnection httpConn =
                                    (HttpURLConnection) urlObj.openConnection();
                                
                                int status = httpConn.getResponseCode();
                                
                                /* http 200 == OK */
                                if (status == 200) {
                                        InputStream in =
                                            httpConn.getInputStream();
                                        String responseBody =
                                            convertStreamToString(in);
                                        in.close();
                                        response = responseBody;
                                }
                                
                        } catch (ProtocolException e) {
                                e.printStackTrace();
                                return null;
                        } catch (IOException e) {
                                e.printStackTrace();
                                return null;
                        }
                        
                }
                return response;
        }
        
        
        
        /**
         * Read the whole content from an InputStream, return it as a string
         * @param is Inputstream to read the body from
         * @return The whole body as a string
         */
        private String convertStreamToString(InputStream is) {
                BufferedReader in = new BufferedReader(new InputStreamReader(is));
                StringBuilder response = new StringBuilder();
                try {
                        String inputLine;
                        while ((inputLine = in.readLine()) != null) {
                                response.append(inputLine);
                                response.append('\n');
                        }
                } catch (IOException ex) {
                        System.out.println("Could not read the data from HTTP response: " + ex.getMessage());
                }
                return response.toString();
        }
}
