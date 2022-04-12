package ru.anbn.reqres;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReqresinTests {

    String baseUrl = "https://reqres.in";

    @Test
    @DisplayName("01_GET_SINGLE USER")
    void checkGetSingleUser() {
        /*
        Request
        /api/users/2

        Response
        200

        {
            "data": {
                "id": 2,
                "email": "janet.weaver@reqres.in",
                "first_name": "Janet",
                "last_name": "Weaver",
                "avatar": "https://reqres.in/img/faces/2-image.jpg"
            },
            "support": {
                "url": "https://reqres.in/#support-heading",
                "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
            }
        }
         */
        String urlRequest = "/api/users/2";

        String dataAvatar = "https://reqres.in/img/faces/2-image.jpg";
        String supportUrl = "https://reqres.in/#support-heading";

        Response response = get(baseUrl + urlRequest)
                .then()
                .statusCode(200)
                .extract().response();
        assertEquals(dataAvatar, response.path("data.avatar"));
        assertEquals(supportUrl, response.path("support.url"));
    }

    @Test
    @DisplayName("02_GET_SINGLE USER NOT FOUND")
    void checkGetUserNotFound() {
        /*
        Request
        /api/users/23

        Response
        404

        {}
         */
        String urlRequest = "/api/users/23";

        // option one
        Response response = get(baseUrl + urlRequest)
                .then()
                .statusCode(404)
                .extract().response();

        // option two
        when()
                .get(baseUrl + urlRequest)
                .then()
                .statusCode(404);
    }

    @Test
    @DisplayName("03_POST_CREATE")
    void postCreate() {
        /*
        Request
        /api/users

        {
        "name": "morpheus",
        "job": "leader"
        }

        Response
        201

        {
            "name": "morpheus",
            "job": "leader",
            "id": "506",
            "createdAt": "2022-04-12T10:16:38.747Z"
        }
        */
        String urlRequest = "/api/users";
        String createUser = "{\"name\": \"morpheus\"," +
                "\"job\": \"leader\"}";

        String name = "morpheus";
        String job = "leader";

        // option one
        Response response = given()
                .body(createUser)
                .contentType(JSON)
                .when()
                .post(baseUrl + urlRequest)
                .then()
                .statusCode(201)
                .extract().response();

        assertEquals(name, response.path("name"));
        assertEquals(job, response.path("job"));

        // option two
        given()
                .body(createUser)
                .contentType(JSON)
                .when()
                .post(baseUrl + urlRequest)
                .then()
                .statusCode(201)
                .body("name", is(name))
                .body("job", is(job));
    }

    @Test
    @DisplayName("04_POST_REGISTER SUCCESSFUL")
    void postRegisterSuccessful() {
        /*
        Request
        /api/register

        {
            "email": "eve.holt@reqres.in",
            "password": "pistol"
        }

        Response
        200

        {
            "id": 4,
            "token": "QpwL5tke4Pnpja7X4"
        }
         */
        String urlRequest = "/api/register";
        String registerUser = "{\"email\": \"eve.holt@reqres.in\"," +
                "\"password\": \"pistol\"}";

        String token = "QpwL5tke4Pnpja7X4";

        Response response = given()
                .body(registerUser)
                .contentType(JSON)
                .when()
                .post(baseUrl + urlRequest)
                .then()
                .statusCode(200)
                .extract().response();

        assertEquals(token, response.path("token"));
    }

    @Test
    @DisplayName("05_PUT_UPDATE")
    void putUpdate() {
        /*
        Request
        /api/users/2

        {
            "name": "morpheus",
            "job": "zion resident"
        }

        Response
        200

        {
            "name": "morpheus",
            "job": "zion resident",
            "updatedAt": "2022-04-12T12:09:03.412Z"
        }
         */
        String urlRequest = "/api/users/2";
        String updateUser = "{\"name\": \"morpheus\"," +
                "\"job\": \"zion resident\"}";

        String name = "morpheus";
        String job = "zion resident";

        Response response = given()
                .body(updateUser)
                .contentType(JSON)
                .when()
                .post(baseUrl + urlRequest)
                .then()
                .statusCode(201)
                .extract().response();

        System.out.println("123");
        assertEquals(name, response.path("name"));
        assertEquals(job, response.path("job"));
    }

}
