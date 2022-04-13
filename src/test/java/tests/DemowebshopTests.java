package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

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
                .extract().response().asString();
        System.out.println(response);
    }


}
