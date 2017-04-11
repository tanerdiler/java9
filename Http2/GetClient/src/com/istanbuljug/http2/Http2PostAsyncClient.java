package com.istanbuljug.http2;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;
import jdk.incubator.http.WebSocket;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/*
    Compile command
    .../jdk-9/bin/javac src/com/kodcu/http2/Http2GetClient.java

    Run command
    .../jdk-9/bin/java -cp src -Djavax.net.ssl.trustStore=keystore/keystore Http2GetClient
 */
public class Http2PostAsyncClient
{

    private static final Logger LOG = Logger.getLogger("Http2GetClient");

    public static void main(String[] args) throws Exception
    {
        String url = "https://localhost:8443/save";

        System.out.println("Get Request Send Start");

        /* Code goes here */
        HttpRequest request = HttpRequest.newBuilder(URI.create(url)).version(HttpClient.Version.HTTP_2).POST(HttpRequest.BodyProcessor.fromString("firstname=taner,lastname=diler")).build();

        SSLContext sslContext = getSslContext();
        CompletableFuture<HttpResponse<String>> callback = HttpClient.newBuilder().sslContext(sslContext).version(HttpClient.Version.HTTP_2).build()
                .sendAsync(request, HttpResponse.BodyHandler.asString());

        String responseString = callback
                .thenApplyAsync(response -> response.body())
                .completeOnTimeout("A timeout has occured", 10, TimeUnit.MILLISECONDS)
                .get();

        System.out.println("Response:       " + responseString);
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
