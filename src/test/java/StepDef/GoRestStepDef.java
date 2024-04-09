package StepDef;

import Pages.GoRestPage;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.*;

public class GoRestStepDef {
    GoRestPage getPage = new GoRestPage();
    @Given("I have a valid API endpoint {string}")
    public void i_have_a_valid_api_endpoint(String endpoint) {
        getPage.createGetEndpoint(endpoint);
    }
    @When("I send a GET request")
    public void i_send_a_get_request() {
        getPage.sendGetCall();
    }
    @Then("I should receive a valid response")
    public void i_should_receive_a_valid_response() {
        getPage.verifyResponse();
    }


    @When("I send a POST request")
    public void i_send_a_post_request() {
        getPage.postRequest();
    }

    @Then("verify status code")
    public void verify_status_code() throws JsonProcessingException {
        getPage.verifyPostResponse();
    }


}
