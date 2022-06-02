package fr.cnumr.java.checks;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.FilesUtils;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class AvoidSpringRepositoryCallInLoopCheckTest {

    @Test
    void test() {
        JavaCheckVerifier.newVerifier()
                .onFile("src/test/files/AvoidSpringRepositoryCallInLoopCheck.java")
                .withCheck(new AvoidSpringRepositoryCallInLoopCheck())
                .withClassPath(FilesUtils.getClassPath("target/test-jars"))
                .verifyIssues();
    }

}
