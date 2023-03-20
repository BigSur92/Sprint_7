package model;
import client.base.ScooterRestClient;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class GetOrderList extends ScooterRestClient {
    public ValidatableResponse getLastOrders() {
        return given()
                .get()
                .then();
    }
}
