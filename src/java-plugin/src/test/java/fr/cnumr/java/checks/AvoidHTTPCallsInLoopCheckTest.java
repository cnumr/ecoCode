package fr.cnumr.java.checks;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.FilesUtils;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class AvoidHTTPCallsInLoopCheckTest {

    @Test
    public void testHttpClient() {
        JavaCheckVerifier.newVerifier()
                .onFile("src/test/files/AvoidHTTPCallsWithNativeClientInLoopCheck.java")
                .withCheck(new AvoidHTTPCallsInLoop())
                .verifyIssues();
    }

    @Test
    @Ignore
    public void testWebClient() {
        JavaCheckVerifier.newVerifier()
                .onFile("src/test/files/AvoidHTTPCallsWithWebClientInLoopCheck.java")
                .withCheck(new AvoidHTTPCallsInLoop())
                .withClassPath(FilesUtils.getClassPath("target/test-jars"))
                .verifyIssues();
    }

}
