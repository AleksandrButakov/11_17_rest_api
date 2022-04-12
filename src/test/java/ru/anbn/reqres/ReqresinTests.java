package ru.anbn.reqres;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReqresinTests {

    String baseUrl = "https://reqres.in";

    @Test
    @DisplayName("01_GET_SINGLE USER")
    public void checkGetSingleUser() {
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
    public void checkGetUserNotFound() {
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
    

}
