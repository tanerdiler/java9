package com.istanbuljug.http2;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpHeaders;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
    Compile command
    .../jdk-9/bin/javac src/com/kodcu/http2/Http2GetClient.java

    Run command
    .../jdk-9/bin/java -cp src -Djavax.net.ssl.trustStore=keystore/keystore Http2GetClient
 */
public class Http2GetClient {

    private static final Logger LOG = Logger.getLogger("Http2GetClient");

    public static void main(String[] args) throws Exception {
        String url = "https://localhost:8443/hello";
       
        System.out.println("Get Request Send Start");

        /* Code goes here */
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(url))
                .version(HttpClient.Version.HTTP_2)
                .GET()
                .build();

        SSLContext sslContext = getSslContext();
        HttpResponse<String> response = HttpClient.newBuilder()
                .sslContext(sslContext)
                .version(HttpClient.Version.HTTP_2)
                .build()
                .send(request, HttpResponse.BodyHandler.asString());

        printInfoOfResponse(response);
    }

    private static void printInfoOfResponse(HttpResponse<String> response)
    {
        if (response.body() != null)
        {
            System.out.println("Response:     " + response.body());
        }
        System.out.println("HTTP-Version: " + response.version());
        System.out.println("Statuscode:   " + response.statusCode());
        System.out.println("Header:");

        response.headers().map().forEach(
                (header, values) -> System.out.println("  " + header + " = "
                        + values.stream().map(String::trim)
                        .reduce(String::concat).orElse("hallo")));

        System.out.println("Get Request Send End");

        System.out.println("Code Execution Completed");
    }

    private static SSLContext getSslContext() throws NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException, KeyManagementException, IOException, CertificateException
    {
        KeyStore ks = KeyStore.getInstance("PKCS12");
        // load the contents of the KeyStore
        ks.load(new FileInputStream("/home/taner/Downloads/http_java9/http2/http2-examples/jetty-http2-server-example/src/main/resources/keystore"),
                "123456".toCharArray());
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(ks, "123456".toCharArray());

        TrustManagerFactory tmf = TrustManagerFactory
                .getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(ks);
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(keyManagerFactory.getKeyManagers(), tmf.getTrustManagers(), null);
        return sslContext;
    }
}
