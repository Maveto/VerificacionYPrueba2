package runner;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MyStepdefs {
    @Given("I have access to Todo.ly")
    public void iHaveAccessToTodoLy() {
        
    }

    @When("I sent a Post Request to {string} with json")
    public void iSentAPostRequestToWithJson(String url, String json) {
    }

    @Then("I expect a response code {int}")
    public void iExpectAResponseCode(int expRes) {
    }

    @And("I expect the project's name to be {string}")
    public void iExpectTheProjectSNameToBe(String name) {
    }
}
