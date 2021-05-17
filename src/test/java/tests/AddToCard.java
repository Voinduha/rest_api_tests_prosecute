package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class AddToCard extends TestBase{
    @Test
    void addProductToWishList() {

        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie("Nop.customer=17ed9454-2910-4448-8ae9-b1e58544ff2b;" +
                        " __utmz=78382081.1616663511.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none);" +
                        " nop.CompareProducts=; __utma=78382081.1910526156.1616663511.1617651642.1617704338.4;" +
                        " __utmc=78382081; NopCommerce.RecentlyViewedProducts=RecentlyViewedProductIds" +
                        "=2&RecentlyViewedProductIds=31&RecentlyViewedProductIds=74&RecentlyViewedProductIds=16;" +
                        " ARRAffinity=06e3c6706bb7098b5c9133287f2a8d510a64170f97e4ff5fa919999d67a34a46;" +
                        " __atuvc=1%7C12%2C0%7C13%2C27%7C14")
                .body("giftcard_2.RecipientName=dan&giftcard_2.RecipientEmail=danvu%40ya.ru&giftcard_2.SenderName=%D0%94%D0%B0%D0%BD%D0%B8%D0%B8%D0%BB+%D0%92%D1%83%D0%BB%D0%B8%D1%85&giftcard_2.SenderEmail=danvu%40ya.ru&giftcard_2.Message=gdfjkhh&addtocart_2.EnteredQuantity=1")
                .when()
                .post("addproducttocart/details/2/2")
                .then()
                .statusCode(200)
                .log().body()
                .body("success", is(true));
    }
}
