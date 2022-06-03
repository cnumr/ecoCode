package fr.cnumr.java.checks;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

class AvoidHTTPCallsWithRestTemplateInLoopCheck {

    public void testGetWithNoLoop() {
        // Create rest template
        RestTemplate restTemplate = new RestTemplate();

        // do http call
        String testResourceUrl = "http://openjdk.java.net/";
        restTemplate.getForEntity(testResourceUrl + "/1", String.class);
    }

    public void testPostForObjWithNoLoop() {
        // Create rest template
        RestTemplate restTemplate = new RestTemplate();

        // do http call
        String testResourceUrl = "http://openjdk.java.net/";
        restTemplate.postForObject(testResourceUrl, null, String.class);
    }

    public void testPostForLocWithNoLoop() {
        // Create rest template
        RestTemplate restTemplate = new RestTemplate();

        // do http call
        String testResourceUrl = "http://openjdk.java.net/";
        restTemplate.postForLocation(testResourceUrl, null);
    }

    public void testPostForEntityWithNoLoop() {
        // Create rest template
        RestTemplate restTemplate = new RestTemplate();

        // do http call
        String testResourceUrl = "http://openjdk.java.net/";
        restTemplate.postForEntity(testResourceUrl, null, String.class);
    }

    public void testExchangePostWithNoLoop() {
        // Create rest template
        RestTemplate restTemplate = new RestTemplate();

        // do http call
        String testResourceUrl = "http://openjdk.java.net/";
        restTemplate.exchange(testResourceUrl, HttpMethod.POST, null, String.class);
    }

    public void testOptionsForAllowWithNoLoop() {
        // Create rest template
        RestTemplate restTemplate = new RestTemplate();

        // do http call
        String testResourceUrl = "http://openjdk.java.net/";
        restTemplate.optionsForAllow(testResourceUrl);
    }

    public void testHeadForHeadersWithNoLoop() {
        // Create rest template
        RestTemplate restTemplate = new RestTemplate();

        // do http call
        String testResourceUrl = "http://openjdk.java.net/";
        restTemplate.headForHeaders(testResourceUrl);
    }

    public void testDeleteWithNoLoop() {
        // Create rest template
        RestTemplate restTemplate = new RestTemplate();

        // do http call
        String testResourceUrl = "http://openjdk.java.net/1";
        restTemplate.delete(testResourceUrl);
    }

    //////////////// Get with loop

    public void testGetWithForeachLoop() {
        // Create rest template
        RestTemplate restTemplate = new RestTemplate();

        // do http call
        String testResourceUrl = "http://openjdk.java.net/";
        for (int i = 0; i <= 10; i++) {
            restTemplate.getForEntity(testResourceUrl + "/1", String.class); // Noncompliant
        }
    }

    public void testGetWithForLoop() {
        // Create rest template
        RestTemplate restTemplate = new RestTemplate();

        // do http call
        String testResourceUrl = "http://openjdk.java.net/";
        int[] intArray = {10, 20, 30, 40, 50};
        for (int i : intArray) {
            restTemplate.getForEntity(testResourceUrl + "/1", String.class); // Noncompliant
        }
    }

    public void testGetWithWhileLoop() {
        // Create rest template
        RestTemplate restTemplate = new RestTemplate();

        // do http call
        String testResourceUrl = "http://openjdk.java.net/";
        while (true) {
            restTemplate.getForEntity(testResourceUrl + "/1", String.class); // Noncompliant
        }
    }

    public void testGetWithDoWhileLoop() {
        // Create rest template
        RestTemplate restTemplate = new RestTemplate();

        // do http call
        String testResourceUrl = "http://openjdk.java.net/";
        do {
            restTemplate.getForEntity(testResourceUrl + "/1", String.class); // Noncompliant
        } while (true);
    }

    //////////////// PostForObject with loop

    public void testPostForObjWithForeachLoop() {
        // Create rest template
        RestTemplate restTemplate = new RestTemplate();

        // do http call
        String testResourceUrl = "http://openjdk.java.net/";
        for (int i = 0; i <= 10; i++) {
            restTemplate.postForObject(testResourceUrl, null, String.class); // Noncompliant
        }
    }

    public void testPostForObjWithForLoop() {
        // Create rest template
        RestTemplate restTemplate = new RestTemplate();

        // do http call
        String testResourceUrl = "http://openjdk.java.net/";
        int[] intArray = {10, 20, 30, 40, 50};
        for (int i : intArray) {
            restTemplate.postForObject(testResourceUrl, null, String.class); // Noncompliant
        }
    }

    public void testPostForObjWithWhileLoop() {
        // Create rest template
        RestTemplate restTemplate = new RestTemplate();

        // do http call
        String testResourceUrl = "http://openjdk.java.net/";
        while (true) {
            restTemplate.postForObject(testResourceUrl, null, String.class); // Noncompliant
        }
    }

    public void testPostForObjWithDoWhileLoop() {
        // Create rest template
        RestTemplate restTemplate = new RestTemplate();

        // do http call
        String testResourceUrl = "http://openjdk.java.net/";
        do {
            restTemplate.postForObject(testResourceUrl, null, String.class); // Noncompliant
        } while (true);
    }

    //////////////// PostForEntity with loop

    public void testPostForEntityWithForeachLoop() {
        // Create rest template
        RestTemplate restTemplate = new RestTemplate();

        // do http call
        String testResourceUrl = "http://openjdk.java.net/";
        for (int i = 0; i <= 10; i++) {
            restTemplate.postForEntity(testResourceUrl, null, String.class); // Noncompliant
        }
    }

    public void testPostForEntityWithForLoop() {
        // Create rest template
        RestTemplate restTemplate = new RestTemplate();

        // do http call
        String testResourceUrl = "http://openjdk.java.net/";
        int[] intArray = {10, 20, 30, 40, 50};
        for (int i : intArray) {
            restTemplate.postForEntity(testResourceUrl, null, String.class); // Noncompliant
        }
    }

    public void testPostForEntityWithWhileLoop() {
        // Create rest template
        RestTemplate restTemplate = new RestTemplate();

        // do http call
        String testResourceUrl = "http://openjdk.java.net/";
        while (true) {
            restTemplate.postForEntity(testResourceUrl, null, String.class); // Noncompliant
        }
    }

    public void testPostForEntityWithDoWhileLoop() {
        // Create rest template
        RestTemplate restTemplate = new RestTemplate();

        // do http call
        String testResourceUrl = "http://openjdk.java.net/";
        do {
            restTemplate.postForEntity(testResourceUrl, null, String.class); // Noncompliant
        } while (true);
    }

    //////////////// Exchange Post with loop

    public void testExchangePostWithForeachLoop() {
        // Create rest template
        RestTemplate restTemplate = new RestTemplate();

        // do http call
        String testResourceUrl = "http://openjdk.java.net/";
        for (int i = 0; i <= 10; i++) {
            restTemplate.exchange(testResourceUrl, HttpMethod.POST, null, String.class); // Noncompliant
        }
    }

    public void testExchangePostWithForLoop() {
        // Create rest template
        RestTemplate restTemplate = new RestTemplate();

        // do http call
        String testResourceUrl = "http://openjdk.java.net/";
        int[] intArray = {10, 20, 30, 40, 50};
        for (int i : intArray) {
            restTemplate.exchange(testResourceUrl, HttpMethod.POST, null, String.class); // Noncompliant
        }
    }

    public void testExchangePostWithWhileLoop() {
        // Create rest template
        RestTemplate restTemplate = new RestTemplate();

        // do http call
        String testResourceUrl = "http://openjdk.java.net/";
        while (true) {
            restTemplate.exchange(testResourceUrl, HttpMethod.POST, null, String.class); // Noncompliant
        }
    }

    public void testExchangePostWithDoWhileLoop() {
        // Create rest template
        RestTemplate restTemplate = new RestTemplate();

        // do http call
        String testResourceUrl = "http://openjdk.java.net/";
        do {
            restTemplate.exchange(testResourceUrl, HttpMethod.POST, null, String.class); // Noncompliant
        } while (true);
    }

    //////////////// OptionsForAllow with loop

    public void testOptionsForAllowWithLoop() {
        // Create rest template
        RestTemplate restTemplate = new RestTemplate();

        // do http call
        String testResourceUrl = "http://openjdk.java.net/";
        for (int i = 0; i <= 10; i++) {
            restTemplate.optionsForAllow(testResourceUrl); // Noncompliant
        }
    }

    //////////////// HeadForHeaders with loop
    public void testHeadForHeadersWithLoop() {
        // Create rest template
        RestTemplate restTemplate = new RestTemplate();

        // do http call
        String testResourceUrl = "http://openjdk.java.net/";
        for (int i = 0; i <= 10; i++) {
            restTemplate.headForHeaders(testResourceUrl); // Noncompliant
        }
    }

    //////////////// Delete with loop
    public void testDeleteWithLoop() {
        // Create rest template
        RestTemplate restTemplate = new RestTemplate();

        // do http call
        String testResourceUrl = "http://openjdk.java.net/1";
        for (int i = 0; i <= 10; i++) {
            restTemplate.delete(testResourceUrl); // Noncompliant
        }
    }
}