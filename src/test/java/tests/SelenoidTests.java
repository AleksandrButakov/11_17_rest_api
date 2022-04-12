package tests;

import io.restassured.response.Response;
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

    // хорошая практика
    @Test
    void checkTotalGoodPractice() {
        // запрос вернет ответ в виде String
        Integer response = get("https://selenoid.autotests.cloud/status")
                .then()
                .extract().path("total");

        System.out.println("Response: " + response);

        Integer expectedResponse = 20;
        assertEquals(expectedResponse, response);
    }

    @Test
    void responseExamples() {
        Response response = get("https://selenoid.autotests.cloud/status")
                .then()
                .extract().response();
        System.out.println("1. Response: " + response);
        System.out.println("2. Response .toString(): " + response.toString());
        System.out.println("3. Response .asString(): " + response.asString());
        System.out.println("4. Response .path(\"total\"): " + response.path("total"));
        System.out.println("5. Response .path(\"browsers.chrome\": " + response.path("browsers.chrome"));
    }

    // запрос на сервис с указанием имени и пароля в ссылке
    @Test
    void checkStatus200() {
        get("https://user1:1234@selenoid.autotests.cloud/wd/hub/status")
                .then()
                .statusCode(200);
    }

    @Test
    void checkStatus401() {
        get("https://selenoid.autotests.cloud/wd/hub/status")
                .then()
                .statusCode(401);
    }

    // запрос на сервис с указанием имени и пароля через given()
    @Test
    void checkStatus200WithAuth() {
        given()
                .auth().basic("user1","1234")
                .get("https://selenoid.autotests.cloud/wd/hub/status")
                .then()
                .statusCode(200);
    }

    // таким методом в режиме дебага можем посмотреть ответ
    @Test
    void checkStatus200WithAuth1() {
        Response response = given()
                .auth().basic("user1","1234")
                .get("https://selenoid.autotests.cloud/wd/hub/status")
                .then()
                .statusCode(200)
                .extract().response();
        System.out.println("123");
    }

}
