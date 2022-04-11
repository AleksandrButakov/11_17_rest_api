package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SelenoidTests {

    // make request to https://selenoid.autotests.cloud/status
    // total is 20

    @Test
    void checkTotal() {
        given()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .body("total", is(20));
    }

    @Test
    void checkTotalWithoutGiven() {
                get("https://selenoid.autotests.cloud/status")
                .then()
                .body("total", is(20));
    }

    @Test
    void checkChromeVersion() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .body("browsers.chrome", hasKey("100.0"));
    }

    // плохая практика, так не делаем
    @Test
    void checkTotalBadPractice() {
        // запрос вернет ответ в виде String
        String response = get("https://selenoid.autotests.cloud/status")
                .then()
                .extract().response().asString();

        System.out.println("Response: " + response);

        String expectedResponse = "";  // сюда можно подставить ожидаемое значение ответа
        assertEquals(expectedResponse, response);
    }

}
