package fr.cnumr.java.checks;

import org.springframework.web.reactive.function.client.WebClient;

public class AvoidHTTPCallsWithWebClientInLoopCheck {

    public void testWithNoLoopGet() {
        // Create http client
        WebClient webClient = WebClient.create("https://example.org");

        // do the http get call
        webClient.get();
    }

    public void testWithNoLoopPost() {
        // Create http client
        WebClient webClient = WebClient.create("https://example.org");

        // do the http get call
        webClient.post();
    }

    public void testWithNoLoopPut() {
        // Create http client
        WebClient webClient = WebClient.create("https://example.org");

        // do the http get call
        webClient.put();
    }

    public void testWithNoLoopDelete() {
        // Create http client
        WebClient webClient = WebClient.create("https://example.org");

        // do the http get call
        webClient.delete();
    }

    public void testWithNoLoopHead() {
        // Create http client
        WebClient webClient = WebClient.create("https://example.org");

        // do the http get call
        webClient.head();
    }

    public void testWithForLoopGet() {
        // Create http client
        WebClient webClient = WebClient.create("https://exqmple.org");

        for (int i = 0; i <= 10; i++) {
            // do http call
            webClient.get(); // Noncompliant
        }
    }

    public void testWithForLoopPost() {
        // Create http client
        WebClient webClient = WebClient.create("https://exqmple.org");

        for (int i = 0; i <= 10; i++) {
            // do http call
            webClient.post(); // Noncompliant
        }
    }

    public void testWithForLoopPut() {
        // Create http client
        WebClient webClient = WebClient.create("https://exqmple.org");

        for (int i = 0; i <= 10; i++) {
            // do http call
            webClient.put(); // Noncompliant
        }
    }

    public void testWithForLoopDelete() {
        // Create http client
        WebClient webClient = WebClient.create("https://exqmple.org");

        for (int i = 0; i <= 10; i++) {
            // do http call
            webClient.delete(); // Noncompliant
        }
    }

    public void testWithForLoopHead() {
        // Create http client
        WebClient webClient = WebClient.create("https://exqmple.org");

        for (int i = 0; i <= 10; i++) {
            // do http call
            webClient.head(); // Noncompliant
        }
    }

    public void testWithForEachLoopGet() {
        // Create http client
        WebClient webClient = WebClient.create("https://exqmple.org");

        int[] intArray = {10, 20, 30, 40, 50};
        for (int i : intArray) {
            // do http call
            webClient.get(); // Noncompliant
        }
    }

    public void testWithForEachLoopPost() {
        // Create http client
        WebClient webClient = WebClient.create("https://exqmple.org");

        int[] intArray = {10, 20, 30, 40, 50};
        for (int i : intArray) {
            // do http call
            webClient.post(); // Noncompliant
        }
    }

    public void testWithForEachLoopPut() {
        // Create http client
        WebClient webClient = WebClient.create("https://exqmple.org");

        int[] intArray = {10, 20, 30, 40, 50};
        for (int i : intArray) {
            // do http call
            webClient.put(); // Noncompliant
        }
    }

    public void testWithForEachLoopDelete() {
        // Create http client
        WebClient webClient = WebClient.create("https://exqmple.org");

        int[] intArray = {10, 20, 30, 40, 50};
        for (int i : intArray) {
            // do http call
            webClient.delete(); // Noncompliant
        }
    }

    public void testWithForEachLoopHead() {
        // Create http client
        WebClient webClient = WebClient.create("https://exqmple.org");

        int[] intArray = {10, 20, 30, 40, 50};
        for (int i : intArray) {
            // do http call
            webClient.head(); // Noncompliant
        }
    }

    public void testWithWhileLoopGet() {
        // Create http client
        WebClient webClient = WebClient.create("https://exqmple.org");

        while (true) {
            // do http call
            webClient.get(); // Noncompliant
        }
    }

    public void testWithWhileLoopPost() {
        // Create http client
        WebClient webClient = WebClient.create("https://exqmple.org");

        while (true) {
            // do http call
            webClient.post(); // Noncompliant
        }
    }

    public void testWithWhileLoopPut() {
        // Create http client
        WebClient webClient = WebClient.create("https://exqmple.org");

        while (true) {
            // do http call
            webClient.put(); // Noncompliant
        }
    }

    public void testWithWhileLoopDelete() {
        // Create http client
        WebClient webClient = WebClient.create("https://exqmple.org");

        while (true) {
            // do http call
            webClient.delete(); // Noncompliant
        }
    }

    public void testWithWhileLoopHead() {
        // Create http client
        WebClient webClient = WebClient.create("https://exqmple.org");

        while (true) {
            // do http call
            webClient.head(); // Noncompliant
        }
    }

    public void testWithDoWhileLoopGet() {
        // Create http client
        WebClient webClient = WebClient.create("https://exqmple.org");

        do {
            // do http call
            webClient.get(); // Noncompliant
        } while (true);
    }

    public void testWithDoWhileLoopPost() {
        // Create http client
        WebClient webClient = WebClient.create("https://exqmple.org");

        do {
            // do http call
            webClient.post(); // Noncompliant
        } while (true);
    }

    public void testWithDoWhileLoopPut() {
        // Create http client
        WebClient webClient = WebClient.create("https://exqmple.org");

        do {
            // do http call
            webClient.put(); // Noncompliant
        } while (true);
    }

    public void testWithDoWhileLoopDelete() {
        // Create http client
        WebClient webClient = WebClient.create("https://exqmple.org");

        do {
            // do http call
            webClient.delete(); // Noncompliant
        } while (true);
    }

    public void testWithDoWhileLoopHead() {
        // Create http client
        WebClient webClient = WebClient.create("https://exqmple.org");

        do {
            // do http call
            webClient.head(); // Noncompliant
        } while (true);
    }

}
