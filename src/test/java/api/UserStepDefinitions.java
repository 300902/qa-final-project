package api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserStepDefinitions {
    private Response response;
    private final String BASE_URL = "https://dummyapi.io/data/v1/user/";
    private final String APP_ID = "6112dc7c3f812e0d9b6679dd"; // APP ID dari dummyapi.io

    @Given("user API is available")
    public void userApiIsAvailable() {
        // Optionally, check API health here
    }

    @When("I request user with id {string}")
    public void iRequestUserWithId(String userId) {
        response = RestAssured.given()
            .header("app-id", APP_ID)
            .get(BASE_URL + userId);
    }

    @When("I create a new user with name \"{string}\" and email \"{string}\"")
    public void iCreateNewUser(String name, String email) {
        response = RestAssured.given()
            .header("app-id", APP_ID)
            .contentType("application/json")
            .body("{\"firstName\": \"" + name + "\", \"email\": \"" + email + "\"}")
            .post(BASE_URL);
    }

    @When("I update user with id \"{string}\" to name \"{string}\"")
    public void iUpdateUser(String userId, String newName) {
        response = RestAssured.given()
            .header("app-id", APP_ID)
            .contentType("application/json")
            .body("{\"firstName\": \"" + newName + "\"}")
            .put(BASE_URL + userId);
    }

    @When("I delete user with id \"{string}\"")
    public void iDeleteUser(String userId) {
        response = RestAssured.given()
            .header("app-id", APP_ID)
            .delete(BASE_URL + userId);
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @Then("the response should contain user data")
    public void theResponseShouldContainUserData() {
        assertTrue(response.getBody().asString().contains("id"));
        assertTrue(response.getBody().asString().contains("firstName"));
    }

    @Then("the response should contain created user data")
    public void theResponseShouldContainCreatedUserData() {
        assertTrue(response.getBody().asString().contains("id"));
        assertTrue(response.getBody().asString().contains("firstName"));
    }

    @Then("the response should contain updated user data")
    public void theResponseShouldContainUpdatedUserData() {
        assertTrue(response.getBody().asString().contains("id"));
        assertTrue(response.getBody().asString().contains("firstName"));
    }

    @Then("the response should confirm user deletion")
    public void theResponseShouldConfirmUserDeletion() {
        assertTrue(response.getBody().asString().toLowerCase().contains("success"));
    }

    @Then("the response should contain error message")
    public void theResponseShouldContainErrorMessage() {
        assertTrue(response.getBody().asString().toLowerCase().contains("error"));
    }
}
