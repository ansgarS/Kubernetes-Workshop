package de.ansgarsachs.scenarios.steps;

import de.ansgarsachs.scenarios.utils.RestUtils;
import com.google.inject.Inject;
import io.cucumber.java.en.Then;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Condition;

import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * GetPatientsSteps
 *
 * @author Ansgar Sachs &lt;ansgar.sachs@cgm.com&gt;
 * @since 27.11.19
 */
public class GetPatientsSteps {
    @Inject
    private World world;

    private Condition<Integer> statusCodeIs200 = new Condition<Integer>(code -> code == HttpStatus.SC_OK, "HttpStatus is 200");

    @Then("I should see the patients id in a list of all patients")
    public void patientInPatientList() throws MalformedURLException, URISyntaxException {
        String patientId = world.getCurrentPatient().getId();

        HttpResponse<JsonNode> res = Unirest.get(RestUtils.resolveUrlPath("listpatients", "patients")).asJson();

        int status = res.getStatus();

        assertThat(status)
                .as("Got the following status code instead of a success: {}", status)
                .is(anyOf(statusCodeIs200));

        JSONArray idList = res.getBody().getObject().getJSONArray("patients");
        List<String> ids = new ArrayList<>();
        for (int i=0; i<idList.length(); i++) {
            ids.add( idList.getString(i) );
        }

        assertThat(ids)
                .as("Expected the patients id in response, but got the following payload", res.getBody())
                .contains(patientId);
    }
}
