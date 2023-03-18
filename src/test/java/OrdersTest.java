import io.restassured.response.Response;
import client.base.ScooterRestClient;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
@RunWith(Parameterized.class)
public class OrdersTest  extends ScooterRestClient {
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private String[] color;

    public OrdersTest(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }
    @Parameterized.Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"John", "Smith", "Voronez, Lenina st.,1", "5", "555-1234", 3, "2022-12-31", "Special request", new String[]{""}},
                {"Alex", "Surkov", "Zelenograd, 1", "210", "555-5678", 5, "2023-06-30", "", new String[]{"Black"}},
                {"Bob", "Johnson", "Moscow,red sq, 1", "20", "555-9012", 1, "2023-04-15", "", new String[]{"Black", "Gray"}}
        });
    }
    private static final String ORDER_URI = BASE_URI + "orders/";

    @Test
    @DisplayName("Проверка создания заказов")
    public void testCreateOrder() {
        OrdersTest ordersTest = new OrdersTest(firstName, lastName, address, metroStation, phone, rentTime,
                deliveryDate, comment, color);
        Response response = given()
                .spec(getBaseReqSpec())
                .body(ordersTest)
                .when()
                .post(ORDER_URI);
        response.then().statusCode(201)
                .body("track", notNullValue());
    }

    @Override
    public String toString() {
        return "Orders{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", metroStation='" + metroStation + '\'' +
                ", phone='" + phone + '\'' +
                ", rentTime=" + rentTime +
                ", deliveryDate='" + deliveryDate + '\'' +
                ", comment='" + comment + '\'' +
                ", color=" + Arrays.toString(color) +
                '}';
    }
}