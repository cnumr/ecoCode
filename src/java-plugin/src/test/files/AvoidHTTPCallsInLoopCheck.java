package fr.cnumr.java.checks;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class AvoidHTTPCallsInLoopCheck {

    AvoidHTTPCallsInLoopCheck(AvoidHTTPCallsInLoopCheck mc) {}

    public void testWithNoLoop() throws IOException, InterruptedException {
        // Create http client
        HttpClient client = HttpClient.newHttpClient();

        // prepare http request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://openjdk.java.net/"))
                .build();

        // do http call
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public void testWithNoLoopAsync() {
        // Create http client
        HttpClient client = HttpClient.newHttpClient();

        // prepare http request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://openjdk.java.net/"))
                .build();

        // do http call
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
    }

    public void testWithForLoop() throws IOException, InterruptedException {
        // Create http client
        HttpClient client = HttpClient.newHttpClient();

        // prepare http request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://openjdk.java.net/"))
                .build();

        for (int i = 0; i <= 10; i++) {
            // do http call
            client.send(request, HttpResponse.BodyHandlers.ofString()); // Noncompliant
        }
    }

    public void testWithForLoopAsync() {
        // Create http client
        HttpClient client = HttpClient.newHttpClient();

        // prepare http request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://openjdk.java.net/"))
                .build();

        for (int i = 0; i <= 10; i++) {
            // do http call
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString()) // Noncompliant
                    .thenApply(HttpResponse::body)
                    .thenAccept(System.out::println)
                    .join();
        }
    }

    public void testWithForEachLoop() throws IOException, InterruptedException {
        // Create http client
        HttpClient client = HttpClient.newHttpClient();

        // prepare http request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://openjdk.java.net/"))
                .build();

        int[] intArray = {10, 20, 30, 40, 50};
        for (int i : intArray) {
            // do http call
            client.send(request, HttpResponse.BodyHandlers.ofString()); // Noncompliant
        }
    }

    public void testWithForEachLoopAsync() {
        // Create http client
        HttpClient client = HttpClient.newHttpClient();

        // prepare http request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://openjdk.java.net/"))
                .build();

        int[] intArray = {10, 20, 30, 40, 50};
        for (int i : intArray) {
            // do http call
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString()) // Noncompliant
                    .thenApply(HttpResponse::body)
                    .thenAccept(System.out::println)
                    .join();
        }
    }

    public void testWithWhileLoop() throws InterruptedException, IOException {
        // Create http client
        HttpClient client = HttpClient.newHttpClient();

        // prepare http request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://openjdk.java.net/"))
                .build();

        while (true) {
            client.send(request, HttpResponse.BodyHandlers.ofString()) // Noncompliant
                    .body();
        }
    }

    public void testWithWhileLoopAsync() {
        // Create http client
        HttpClient client = HttpClient.newHttpClient();

        // prepare http request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://openjdk.java.net/"))
                .build();

        while (true) {
            // do http call
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString()) // Noncompliant
                    .thenApply(HttpResponse::body)
                    .thenAccept(System.out::println)
                    .join();
        }
    }

    public void testWithDoWhile() throws IOException, InterruptedException {
        // Create http client
        HttpClient client = HttpClient.newHttpClient();

        // prepare http request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://openjdk.java.net/"))
                .build();

        do {
            // do http call
            client.send(request, HttpResponse.BodyHandlers.ofString()); // Noncompliant
        } while (true);
    }

    public void testWithDoWhileAsync() {
        // Create http client
        HttpClient client = HttpClient.newHttpClient();

        // prepare http request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://openjdk.java.net/"))
                .build();

        do {
            // do http call
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString()) // Noncompliant
                    .thenApply(HttpResponse::body)
                    .thenAccept(System.out::println)
                    .join();
        } while (true);
    }

}