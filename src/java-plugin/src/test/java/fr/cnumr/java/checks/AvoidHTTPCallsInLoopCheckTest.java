package fr.cnumr.java.checks;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class AvoidHTTPCallsInLoopCheckTest {

    @Test
    public void test() {
        JavaCheckVerifier.newVerifier()
                .onFile("src/test/files/AvoidHTTPCallsInLoopCheck.java")
                .withCheck(new AvoidHTTPCallsInLoop())
                .verifyIssues();
    }

}
