package tests;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.w3c.dom.ls.LSOutput;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class DemowebshopTests {

    /*
    Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
          .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
          .header("Cookie", "ARRAffinity=1818b4c81d905377ced20e7ae987703a674897394db6e97dc1316168f754a687; Nop.customer=3d26c045-d2eb-4a90-ae9f-fae62387cda0")
          .body("product_attribute_72_5_18=53&product_attribute_72_6_19=54&product_attribute_72_3_20=57&addtocart_72.EnteredQuantity=1")
          .asString();
     */

    @Test
    @DisplayName("01_addToCartTest")
    void addToCartAsNewUserTest() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("product_attribute_72_5_18=53" +
                        "&product_attribute_72_6_19=54" +
                        "&product_attribute_72_3_20=57" +
                        "&addtocart_72.EnteredQuantity=1")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                .then()
                .log().all()  // команда выводит весь контент в чат
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your \u003ca href=\"/cart\"\u003eshopping cart\u003c/a\u003e"))
                .body("updatetopcartsectionhtml", is("(1)"));

    }

    @Test
    @DisplayName("02_addToCartTest")
    void addToCartTest() {

        String response = given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie("Nop.customer=3d26c045-d2eb-4a90-ae9f-fae62387cda0;")
                .body("product_attribute_72_5_18=53" +
                        "&product_attribute_72_6_19=54" +
                        "&product_attribute_72_3_20=57" +
                        "&addtocart_72.EnteredQuantity=1")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                .then()
                .log().all()  // команда выводит весь контент в чат
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your \u003ca href=\"/cart\"\u003eshopping cart\u003c/a\u003e"))
                .body("updatetopcartsectionhtml", is("(27)"))
                .extract().response().asString();

        System.out.println();
        System.out.println("Full answer:");
        System.out.println(response);

    }

    @Test
    @DisplayName("03_addToCartTest33")
    void addToCartTest33() {
        Integer cartSize = 0;

        ValidatableResponse response = given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie("Nop.customer=3d26c045-d2eb-4a90-ae9f-fae62387cda0;")
                .body("product_attribute_72_5_18=53" +
                        "&product_attribute_72_6_19=54" +
                        "&product_attribute_72_3_20=57" +
                        "&addtocart_72.EnteredQuantity=1")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                .then()
                .log().all()  // команда выводит весь контент в чат
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your \u003ca href=\"/cart\"\u003eshopping cart\u003c/a\u003e"));

        // assertThat(response.extract().path("updatetopcartsectionhtml").toString())
//                .body("updatetopcartsectionhtml", is("(24)"))
//                .extract().response().asString();

        System.out.println();
        System.out.println("Full answer:");
        System.out.println(response);
    }

/*
    Пример рабочего теста:

 */

}
