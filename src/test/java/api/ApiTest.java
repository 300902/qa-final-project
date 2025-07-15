package api;

import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.*;

public class ApiTest {
    @Test
    public void testGetRequest() {
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/posts/1");
        assertEquals(200, response.getStatusCode());
        assertTrue(response.getBody().asString().contains("userId"));
    }
}
