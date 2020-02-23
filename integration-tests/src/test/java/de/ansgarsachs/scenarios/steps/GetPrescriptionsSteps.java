package de.ansgarsachs.scenarios.steps;

import com.google.inject.Inject;
import de.ansgarsachs.scenarios.utils.DockerUtils;
import de.ansgarsachs.scenarios.utils.Prescription;
import de.ansgarsachs.scenarios.utils.RestUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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
 * GetPrescriptionsSteps
 *
 * @author Rouven Himmelstein &lt;rouven.himmelstein@cgm.com&gt;
 * @since 06.12.2019
 */
public class GetPrescriptionsSteps {
    @Inject
    private World world;

    private Condition<Integer> statusCodeIs200 = new Condition<Integer>(code -> code == HttpStatus.SC_OK, "HttpStatus is 200");

    @Then("I should see the cumulated costs of prescriptions for that patient")
    public void patientInPatientList() throws MalformedURLException, URISyntaxException {
        String patientId = world.getCurrentPatient().getId();
        double prescriptionCosts = world.getAddedPrescriptions().stream()
                .filter(element -> element.getPatientId().equals(patientId))
                .map(Prescription::getPrice)
                .reduce(0.0, Double::sum);

        HttpResponse<JsonNode> res = Unirest.get(RestUtils.resolveUrlPath("listcosts", "patients/" + patientId + "/costs")).asJson();

        int status = res.getStatus();

        assertThat(status)
                .as("Got the following status code instead of a success: {}", status)
                .is(anyOf(statusCodeIs200));

        double actualCosts = res.getBody().getObject().getDouble("costs");

        assertThat(actualCosts)
                .as("Expected the patients costs in response, but got the following payload", res.getBody())
                .isEqualTo(prescriptionCosts);
    }
}
