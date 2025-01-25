import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestForms {
    @BeforeAll
    static void configuration() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
        open("https://demoqa.com/text-box");
    }

    @Test
    void checkStateFields() {
        //Проверка labels
        $(".text-center").shouldHave(text("Text Box"));
        $("#userName-label").shouldHave(text("Full Name"));
        $("#userEmail-label").shouldHave(text("Email"));
        $("#currentAddress-label").shouldHave(text("Current Address"));
        $("#permanentAddress-label").shouldHave(text("Permanent Address"));

        //Проверка на пустоту
        $("#userName").shouldHave(value(""));
        $("#userEmail").shouldHave(value(""));
        $(".form-control").shouldHave(value(""));

        //Проверка hint
        $("#userName").shouldHave(attribute("placeholder", "Full Name"));
        $("#userEmail").shouldHave(attribute("placeholder", "name@example.com"));
        $("#currentAddress").shouldHave(attribute("placeholder", "Current Address"));
    }

    @Test
    void validFillFields() {
        $("#userName").setValue("Anatoly");
        $("#userEmail").setValue("strong@strongovich.com");
        $("#currentAddress").setValue("street 1");
        $("#permanentAddress").setValue("street 2");
        $("#submit").click();
    }

    @Test
    void invalidFillFields() {
        $("#userName").setValue("Anatoly");
        $("#userEmail").setValue("ts");
        $("#currentAddress").setValue("street 1");
        $("#permanentAddress").setValue("street 2");
        $("#submit").click();
        $(".field-error").isDisplayed();
    }

    @AfterEach
    void clearFields() {
        $("#userName").clear();
        $("#userEmail").clear();
        $("#currentAddress").clear();
        $("#permanentAddress").clear();
    }
}
