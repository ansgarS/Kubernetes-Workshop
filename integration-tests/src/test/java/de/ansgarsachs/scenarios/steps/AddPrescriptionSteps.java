package de.ansgarsachs.scenarios.steps;

import de.ansgarsachs.scenarios.utils.Patient;
import de.ansgarsachs.scenarios.utils.Prescription;
import de.ansgarsachs.scenarios.utils.RestUtils;
import com.google.inject.Inject;
import cucumber.runtime.CucumberException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Condition;

import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Steps that retrieve existing movies
 *
 * @author Ansgar Sachs &lt;ansgar.sachs@cgm.com&gt;
 * @since 13.11.19
 */
public class AddPrescriptionSteps {
    @Inject
    private World world;

    private Condition<Integer> statusCodeIs200 = new Condition<Integer>(code -> code == HttpStatus.SC_OK, "HttpStatus is 200");
    private Condition<Integer> statusCodeIs201 = new Condition<Integer>(code -> code == HttpStatus.SC_CREATED, "HttpStatus is 201");
    private Condition<Integer> statusCodeIs304 = new Condition<Integer>(code -> code == HttpStatus.SC_NOT_MODIFIED, "HttpStatus is 304");

    @When("I add multiple prescriptions")
    public void addPrescriptions() throws MalformedURLException, URISyntaxException {
        Prescription prescription1 = new Prescription();

        prescription1.setName("Ibuprofen");
        prescription1.setPatientId(world.getCurrentPatient().getId());
        prescription1.setPrice((float) 4.99);

        Prescription prescription2 = new Prescription();

        prescription2.setName("Aspirin");
        prescription2.setPatientId(world.getCurrentPatient().getId());
        prescription2.setPrice((float) 6.99);

        HttpResponse<JsonNode> res1 = Unirest.post(RestUtils.resolveUrlPath("createprescription", "prescriptions"))
                .header("Content-Type", "application/json")
                .body(new JSONObject(prescription1.toString()))
                .asJson();

        HttpResponse<JsonNode> res2 = Unirest.post(RestUtils.resolveUrlPath("createprescription", "prescriptions"))
                .header("Content-Type", "application/json")
                .body(new JSONObject(prescription2.toString()))
                .asJson();

        int status1 = res1.getStatus();
        int status2 = res2.getStatus();

        JsonNode body1 = res1.getBody();
        String id1 = body1.getObject().getString("id");

        JsonNode body2 = res2.getBody();
        String id2 = body2.getObject().getString("id");

        assertThat(status1)
                .as("Got the following status code instead of a success: {}", status1)
                .is(anyOf(statusCodeIs201));

        assertThat(status2)
                .as("Got the following status code instead of a success: {}", status2)
                .is(anyOf(statusCodeIs201));

        assertThat(id1)
                .as("Expected an id in response, but got the following payload", res1.getBody())
                .isInstanceOf(String.class);

        assertThat(id2)
                .as("Expected an id in response, but got the following payload", res2.getBody())
                .isInstanceOf(String.class);

        prescription1.setId(id1);
        prescription2.setId(id2);

        world.addPrescription(prescription1);
        world.addPrescription(prescription2);
    }
}
