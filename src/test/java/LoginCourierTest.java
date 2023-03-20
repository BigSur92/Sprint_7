import client.CourierClient;
import model.Courier;
import model.CourierCredentials;
import model.CourierGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.CoreMatchers.*;

public class LoginCourierTest {
    private CourierClient courierClient;
    private int courierId;
    @Before
    public void setUp(){
        courierClient = new CourierClient();
    }
    @After
    public void clearData(){
        courierClient.delete(courierId);
    }
    @Test
    public void courierCanBeLoginWithValidData(){
        Courier courier = CourierGenerator.getRandom();

        courierClient.createCourier(courier)
                .assertThat()
                .statusCode(SC_CREATED)
                .and()
                .assertThat()
                .body("ok", is(true));


        courierClient.login(CourierCredentials.from(courier))
                .assertThat()
                .statusCode(200)
                .and()
                .body("id", notNullValue());
    }
    @Test
    public void courierCanNotBeLoginWithOutPasword(){
        CourierCredentials courierCredentials = new CourierCredentials("alex123123", "");
        courierClient.login(courierCredentials)
                .assertThat()
                .statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для входа"));

    }
    @Test
    public void courierCanNotBeLoginWithOutLogin(){
        CourierCredentials courierCredentials = new CourierCredentials("", "pasalex");
        courierClient.login(courierCredentials)
                .assertThat()
                .statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для входа"));
    }
    @Test
    public void courierCanNotBeLoginWithwithWrongLogIn(){
        CourierCredentials courierCredentials = new CourierCredentials("alex123123", "pasalex");
        CourierCredentials courierCredentialsWrongData = new CourierCredentials("alex1231234", "pasalex");
        courierClient.login(courierCredentials)
                .assertThat()
                .statusCode(200)
                .and()
                .body("id", notNullValue());
        courierClient.login(courierCredentialsWrongData)
                .assertThat()
                .statusCode(404)
                .and()
                .body("message", equalTo("Учетная запись не найдена"));
    }
    @Test
    public void courierCanNotBeLoginWithwithWrongPasword() {
        CourierCredentials courierCredentials = new CourierCredentials("alex123123", "pasalex");
        CourierCredentials courierCredentialsWrongData = new CourierCredentials("alex123123", "pasalexa");
        courierClient.login(courierCredentials)
                .assertThat()
                .statusCode(200)
                .and()
                .body("id", notNullValue());
        courierClient.login(courierCredentialsWrongData)
                .assertThat()
                .statusCode(404)
                .and()
                .body("message", equalTo("Учетная запись не найдена"));
    }
}
