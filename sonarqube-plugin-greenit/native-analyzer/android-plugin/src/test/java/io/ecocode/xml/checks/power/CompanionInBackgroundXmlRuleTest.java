package io.ecocode.xml.checks.power;

import org.junit.Test;
import org.sonarsource.analyzer.commons.xml.checks.SonarXmlCheckVerifier;

public class CompanionInBackgroundXmlRuleTest {

    @Test
    public void attributePresentTrue() {
        SonarXmlCheckVerifier.verifyIssues("CompanionInBackgroundXmlCheck.xml", new CompanionInBackgroundXmlRule());
    }
}
