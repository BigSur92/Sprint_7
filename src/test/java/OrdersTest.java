import io.qameta.allure.junit4.DisplayName;
import model.OrderClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.notNullValue;


@RunWith(Parameterized.class)
public class CreateOrdersTest {

    private OrderClient orderClient;
    @Before
    public void setUp(){
        orderClient = new OrderClient();
    }
    @Test
    @DisplayName("Проверка создания заказов")
    public void testCreateOrder() {

        orderClient.createNewOrder(Orders)
                .assertThat()
                .statusCode(201)
                .and()
                .assertThat()
                .body("track", notNullValue());
    }

}