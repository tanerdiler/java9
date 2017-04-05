package com.kodcu.http2;

import java.net.*;
import java.net.http.*;

/*
    Compile command
    .../jdk-9/bin/javac src/com/kodcu/http2/Http2Client.java

    Run command
    .../jdk-9/bin/java -cp src -Djavax.net.ssl.trustStore=keystore/keystore com.kodcu.http2.Http2Client
 */
public class Http2Client {

    public static void main(String[] args) throws Exception {
        
        String url = "https://31.210.55.49:8443";
        
        String firstName = "[FIRST NAME]";
        String lastName = "[LAST NAME]";
        String email = "[EMAIL]";
        String password = "1q2w3e4r"; // Should remain like this otherwise server will response with fail message 

        
        String parameters = "firstname=" + firstName + ",lastname=" + lastName + ",email=" + email + ",pass=" + password;
        
        /* POST Request */
        
        System.out.println("Post Request Send Start");
        
        String postBody = HttpClient.create()
                .version(HttpClient.Version.HTTP_2)
                .build()
                .request(new URI(url + "/post"))
                .body(HttpRequest.fromString(parameters))              
                .POST()
                .response()
                .body(HttpResponse.asString());

        System.out.println("Post Request Send End");
        System.out.println("Post Response: " + postBody);

        /* GET Request */
        
        System.out.println("Get Request Send Start");
        
        String getBody = HttpClient.create()
                .version(HttpClient.Version.HTTP_2)
                .build()
                .request(new URI(url + "/get"))
                .body(HttpRequest.fromString("body"))      
                .GET()
                .response()
                .body(HttpResponse.asString());

        System.out.println("Get Request Send End");
        System.out.println("Get Response: " + getBody);
        
        
        System.err.println("Code Execution Completed");
    }
}
