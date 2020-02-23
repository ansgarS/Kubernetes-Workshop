package de.ansgarsachs.scenarios.steps;

import de.ansgarsachs.scenarios.utils.Patient;
import de.ansgarsachs.scenarios.utils.RestUtils;
import com.google.inject.Inject;
import cucumber.runtime.CucumberException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Condition;

import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * Steps that add a new movie
 *
 * @author Ansgar Sachs &lt;ansgar.sachs@cgm.com&gt;
 * @since 13.11.19
 */
public class AddPatientSteps {
    @Inject
    private World world;

    private Condition<Integer> statusCodeIs201 = new Condition<Integer>(code -> code == HttpStatus.SC_CREATED, "HttpStatus is 201");

    @When("I create a new patient")
    public void addPatient() throws MalformedURLException, URISyntaxException {
        Patient patient = new Patient();

        patient.setFirstName("Ansgar");
        patient.setLastName("Sachs");
        patient.setAge(29);

        HttpResponse<JsonNode> res = Unirest.post(RestUtils.resolveUrlPath("createpatient", "patients"))
                .header("Content-Type", "application/json")
                .body(new JSONObject(patient.toString()))
                .asJson();

        int status = res.getStatus();
        JsonNode body = res.getBody();
        String id = body.getObject().getString("id");

        assertThat(status)
                .as("Got the following status code instead of a success: {}", status)
                .is(anyOf(statusCodeIs201));

        assertThat(id)
                .as("Expected an id in response, but got the following payload", res.getBody())
                .isInstanceOf(String.class);

        patient.setId(id);
        world.addPatient(patient);
    }
}
