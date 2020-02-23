package de.ansgarsachs.scenarios.steps;

import de.ansgarsachs.scenarios.utils.RestUtils;
import cucumber.runtime.CucumberException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.apache.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Steps that validates if the Playground is in the correct state
 *
 * @author Ansgar Sachs &lt;ansgar.sachs@cgm.com&gt;
 * @since 13.11.19
 */
public class ServiceSetupSteps {
    private static final int SLEEP_INTERVAL = 1000;

    @Given("Servers listed in the following table are up and running")
    public void serversAreRunning(DataTable dataTable) {
        dataTable.asList().forEach(RestUtils::assertAppIsRunning);
    }

    @Given("Databases listed in the following table purged any {string} data")
    public void databasesArePurged(String transferType, DataTable dataTable) {
        dataTable.asList().forEach(dbName -> {
            try {
                HttpResponse<JsonNode> response = Unirest.delete(RestUtils.resolveUrlPath(dbName, "movies/" + transferType)).asJson();
                assertThat(response.getStatus()).isEqualTo(HttpStatus.SC_OK);
            } catch (URISyntaxException | MalformedURLException e) {
                throw new CucumberException(e.getMessage());
            }
        });
    }
}
