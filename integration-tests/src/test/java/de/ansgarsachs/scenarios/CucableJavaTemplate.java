package de.ansgarsachs.scenarios;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Configuration File for Cucumber Steps
 *
 * <p>
 *     This file can be used to run all cucumber tests.
 *     It is recommended to execute the tests via maven:
 *          mvn clean verify
 *     This will create an html report providing an
 *     awesome overview of each scenario
 * </p>
 *
 * @author Ansgar Sachs &lt;ansgar.sachs@cgm.com&gt;
 * @since 13.11.19
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "de.ansgarsachs.scenarios.steps",
        features = {"target/parallel/features/[CUCABLE:FEATURE].feature"},
        plugin = {"json:target/cucumber-report/[CUCABLE:RUNNER].json"}
)
public class CucableJavaTemplate {}