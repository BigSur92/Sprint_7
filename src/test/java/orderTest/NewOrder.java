package orderTest;
import client.base.ScooterRestClient;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class NewOrder extends ScooterRestClient{
    private static final String ORDER_URI = BASE_URI + "orders/";
    public ValidatableResponse createNewOrder(CreateOrdersTest createOrdersTest) {
        return given()
                .spec(getBaseReqSpec())
                .body(createOrdersTest)
                .when()
                .post(ORDER_URI)
                .then();
    }
}
