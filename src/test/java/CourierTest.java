import client.CourierClient;
import model.Courier;
import model.CourierCredentials;
import model.CourierGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.CoreMatchers.*;

public class CourierTest {
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
    public void courierCanBeCreatedWithValidData(){
        Courier courier = CourierGenerator.getRandom();

        courierClient.create(courier)
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
    public void courierCanNotBeCreatedWithTheSameName() {
        Courier courier = CourierGenerator.getRandom();

        courierClient.create(courier)
                .assertThat()
                .statusCode(SC_CREATED)
                .and()
                .assertThat()
                .body("ok", is(true));
        courierClient.create(courier)
                .assertThat()
                .statusCode(409)
                .and()
                .assertThat()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    public void courierCanNotBeCreatedWithOutPassword(){
        Courier courier = CourierGenerator.getWithOutPassword();

        courierClient.create(courier)
                .assertThat()
                .statusCode(400).and()
                .assertThat()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));

        courierClient.login(CourierCredentials.from(courier))
                .assertThat()
                .statusCode(400)
                 .assertThat()
                .body("message", equalTo("Недостаточно данных для входа"));
    }
    @Test
    public void courierCanNotBeCreatedWithOutLogin(){
        Courier courier = CourierGenerator.getWithOutLogin();

        courierClient.create(courier)
                .assertThat()
                .statusCode(400).and()
                .assertThat()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));

        courierClient.login(CourierCredentials.from(courier))
                .assertThat()
                .statusCode(400)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для входа"));
    }
    @Test
    public void courierCanNotBeCreatedWithOutFirsName(){
        Courier courier = CourierGenerator.getWithOutFirstName();

        courierClient.create(courier)
                .assertThat()
                .statusCode(201).and()
                .assertThat()
                .body("ok", is(true));

        courierClient.login(CourierCredentials.from(courier))
                .assertThat()
                .statusCode(200)
                .and()
                .body("id", notNullValue());
    }

}
