import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;
    public class CheckOrdersListTest{
    @Before
    public void setUp(){
        RestAssured.baseURI ="http://qa-scooter.praktikum-services.ru/api/v1/orders?limit=10&page=0";
    }
    @Test
    @DisplayName("Проверка списка заказов")
    public void CheckOrdersList(){
        given()
                .get()
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .body("orders", notNullValue())
                .body("size()", greaterThan(0));
    }
}

