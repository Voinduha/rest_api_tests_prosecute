package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;

public class LoginTests extends TestBase {

    @Test
    void loginWithUiTest() {
        // authorize
        // danvu@ya.ru 12345678
        open("/login");
        $("#Email").val("danvu@ya.ru");
        $("#Password").val("12345678").pressEnter();

        // verify successful authorization

        $(".account").shouldHave(text("danvu@ya.ru"));
    }

    @Test
    void loginWithCookieTest() {
        // authorize
        // danvu@ya.ru 12345678
        Map<String, String> cookiesMap =
                given()
                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .cookie("Nop.customer=17ed9454-2910-4448-8ae9-b1e58544ff2b;" +
                                " __utmz=78382081.1616663511.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none);" +
                                " nop.CompareProducts=; __utma=78382081.1910526156.1616663511.1617651642.1617704338.4;" +
                                " __utmc=78382081; NopCommerce.RecentlyViewedProducts=RecentlyViewedProductIds=" +
                                "2&RecentlyViewedProductIds=31&RecentlyViewedProductIds=74&RecentlyViewedProductIds=16;" +
                                " ARRAffinity=06e3c6706bb7098b5c9133287f2a8d510a64170f97e4ff5fa919999d67a34a46;" +
                                " __atuvc=1%7C12%2C0%7C13%2C27%7C14")
                        .formParam("Email", "danvu@ya.ru")
                        .formParam("Password", "12345678")
                        .when()
                        .post("/login")
                        .then()
                        .statusCode(302)
                        .log().body()
                        .extract().cookies();

        // verify successful authorization

        open("http://demowebshop.tricentis.com/Themes/DefaultClean/Content/images/logo.png");
        getWebDriver().manage().addCookie(new Cookie("Nop.customer", cookiesMap.get("Nop.customer")));
        getWebDriver().manage().addCookie(new Cookie("NOPCOMMERCE.AUTH", cookiesMap.get("NOPCOMMERCE.AUTH")));
        getWebDriver().manage().addCookie(new Cookie("ARRAffinity", cookiesMap.get("ARRAffinity")));
        System.out.println("Debug");
        open("");
          $(".account").shouldHave(text("danvu@ya.ru"));
    }
}
