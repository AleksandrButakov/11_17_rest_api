package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.*;
import static org.hamcrest.Matchers.is;

public class ReqresinTests {

    // позитивный автотест на авторизацию
    @Test
    void successfulLogin() {
        /*
        request: https://reqres.in//api/login

        data:
        {
        "email": "eve.holt@reqres.in",
        "password": "cityslicka"
        }

        response:
        {
        "token": "QpwL5tke4Pnpja7X4"
        }
         */

        String authorizedData = "{\"email\": \"eve.holt@reqres.in\"," +
                "\"password\": \"cityslicka\"}";

        given()
                .body(authorizedData)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }


    // негативный автотест на авторизацию
    @Test
    void missingPasswordLogin() {
        /*
        request: https://reqres.in//api/login

        data:
        {
        "email": "eve.holt@reqres.in",
        "password": "cityslicka"
        }

        response:
        {
        "error": "Missing password"
        }
         */

        String authorizedData = "{\"email\": \"eve.holt@reqres.in\"}";

        given()
                .body(authorizedData)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

}
