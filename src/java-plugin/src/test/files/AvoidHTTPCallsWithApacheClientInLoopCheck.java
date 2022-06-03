package fr.cnumr.java.checks;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AvoidHTTPCallsWithApacheClientInLoopCheck {

    public void testWithNoLoop() throws IOException {
        // Create apache client
        HttpClient client = HttpClientBuilder.create().build();

        // prepare request
        HttpGet request = new HttpGet("https://example.org");

        // execute request
        client.execute(request);
    }

    public void testWithForLoop() throws IOException {
        // Create apache client
        HttpClient client = HttpClientBuilder.create().build();

        // prepare request
        HttpGet request = new HttpGet("https://example.org");

        for (int i = 0; i <= 20; i++) {
            // execute request
            client.execute(request); // Noncompliant
        }
    }

    public void testWithForEachLoop() throws IOException, InterruptedException {
        // Create apache client
        HttpClient client = HttpClientBuilder.create().build();

        // prepare request
        HttpGet request = new HttpGet("https://example.org");

        int[] intArray = {10, 20, 30, 40, 50};
        for (int i : intArray) {
            // do http call
            client.execute(request); // Noncompliant
        }
    }

    public void testWithWhileLoop() throws InterruptedException, IOException {
        // Create apache client
        HttpClient client = HttpClientBuilder.create().build();

        // prepare request
        HttpGet request = new HttpGet("https://example.org");

        while (true) {
            // do http call
            client.execute(request); // Noncompliant
        }
    }

    public void testWithDoWhile() throws IOException, InterruptedException {
        // Create apache client
        HttpClient client = HttpClientBuilder.create().build();

        // prepare request
        HttpGet request = new HttpGet("https://example.org");

        do {
            // do http call
            client.execute(request); // Noncompliant
        } while (true);
    }
}
