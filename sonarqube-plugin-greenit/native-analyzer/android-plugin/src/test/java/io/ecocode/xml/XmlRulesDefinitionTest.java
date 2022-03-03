package io.ecocode.xml;

import org.junit.Test;
import org.sonar.api.server.rule.RulesDefinition;

import static org.assertj.core.api.Assertions.assertThat;

public class XmlRulesDefinitionTest {

    @Test
    public void test() {
        XmlRulesDefinition rulesDefinition = new XmlRulesDefinition();
        RulesDefinition.Context context = new RulesDefinition.Context();
        rulesDefinition.define(context);
        RulesDefinition.Repository repository = context.repository(Xml.REPOSITORY_KEY);

        assertThat(repository.name()).isEqualTo(Xml.REPOSITORY_NAME);
        assertThat(repository.language()).isEqualTo(Xml.KEY);
        assertThat(repository.rules()).hasSize(XmlCheckList.getXmlChecks().size());

        RulesDefinition.Rule serviceBootTimeXml = repository.rule("EBAT001");
        assertThat(serviceBootTimeXml).isNotNull();
        assertThat(serviceBootTimeXml.name()).isEqualTo("Batch: Service Boot Time");

        RulesDefinition.Rule keepScreenOnXmlRule = repository.rule("EIDL003");
        assertThat(keepScreenOnXmlRule).isNotNull();
        assertThat(keepScreenOnXmlRule.name()).isEqualTo("Idleness: Keep Screen On");

        RulesDefinition.Rule keepCpuOnXmlRule = repository.rule("EIDL005");
        assertThat(keepCpuOnXmlRule).isNotNull();
        assertThat(keepCpuOnXmlRule.name()).isEqualTo("Idleness: Keep CPU On");

        RulesDefinition.Rule companionInBackgroundXmlRule = repository.rule("EPOW002");
        assertThat(companionInBackgroundXmlRule).isNotNull();
        assertThat(companionInBackgroundXmlRule.name()).isEqualTo("Power: Companion In Background");

        RulesDefinition.Rule ignoreBatteryOptimizationsXmlRule = repository.rule("EPOW003");
        assertThat(ignoreBatteryOptimizationsXmlRule).isNotNull();
        assertThat(ignoreBatteryOptimizationsXmlRule.name()).isEqualTo("Power: Ignore Battery Optimizations");

        RulesDefinition.Rule chargeAwarenessXmlRule = repository.rule("EPOW005");
        assertThat(chargeAwarenessXmlRule).isNotNull();
        assertThat(chargeAwarenessXmlRule.name()).isEqualTo("Power: Charge Awareness");

        RulesDefinition.Rule xmlBrightnessRule = repository.rule("ESOB003");
        assertThat(xmlBrightnessRule).isNotNull();
        assertThat(xmlBrightnessRule.name()).isEqualTo("Sobriety: Dark UI (Bright Colors)");

        RulesDefinition.Rule DarkUIThemeXmlRule = repository.rule("ESOB004");
        assertThat(DarkUIThemeXmlRule).isNotNull();
        assertThat(DarkUIThemeXmlRule.name()).isEqualTo("Sobriety: Dark UI (Theme)");

        for (RulesDefinition.Rule rule : repository.rules()) {
            for (RulesDefinition.Param param : rule.params()) {
                assertThat(param.description()).as("description for " + param.key()).isNotEmpty();
            }
        }
    }
}
