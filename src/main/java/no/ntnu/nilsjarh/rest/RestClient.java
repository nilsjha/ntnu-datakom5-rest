/**  RestAPI Example in Java  Copyright (C) 2019 Nils-Jarle Haugen
 *   This program comes with ABSOLUTELY NO WARRANTY
 *   This is free software, and you are welcome to redistribute it
 *   under certain conditions; type `show c' for details.
 */

package no.ntnu.nilsjarh.rest;

import java.io.*;
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
         * This method sends HTTP GET requests and returns the result
         * @param getDir root directory for GET request
         * @return the result encapsulated as string
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
         * This method sends HTTP POST requests and returns the result
         * @param postDir root directory for POST request
         * @param jsonPayload JSON-encoded payload to POST
         * @return the result encapsulated as string
         */
        public String send(String postDir, String jsonPayload) {
                boolean cts = true;
                String response = null;
                // check that all of the required elements are ready for tx
                if (this.endpointUrl == null || jsonPayload.equals("")) {
                        System.err.println("Endpoint URL or JSON not set, " +
                            "ignoring...");
                        cts = false;
                }
                if (cts) {
                        try  {
                                String url = this.endpointUrl + postDir;
                                URL urlObj = new URL(url);
                                HttpURLConnection httpPost=
                                    (HttpURLConnection) urlObj.openConnection();
                                
                                // Add POST-specific parameters to httpPost
                                httpPost = setSettings("POST",
                                    httpPost);
        
                                OutputStream out = httpPost.getOutputStream();
                                out.write(jsonPayload.getBytes());
                                out.flush();
                                
                                int status = httpPost.getResponseCode();
                                
                                /* http 200 == OK */
                                if (status == 200) {
                                    InputStream in = httpPost.getInputStream();
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
         * Sets required method properties to a speficil HttpURLConnection
         * object
         * @param method Type of connection, must be POST or GET
         * @param http The connection to write properties to
         * @return Return of http param, now with POST/GET properties
         */
        private HttpURLConnection setSettings(String method,
                                          HttpURLConnection http) {
                HttpURLConnection connection;
                if (method.equals("POST")) {
                        try {
                                http.setRequestMethod(method);
                                http.setRequestProperty("Content-Type",
                                    "application/json");
                                http.setDoOutput(true);
                        } catch (ProtocolException e) {
                                e.printStackTrace();
                        }
                } else if (method.equals("GET")) {
                
                }
                return http;
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
