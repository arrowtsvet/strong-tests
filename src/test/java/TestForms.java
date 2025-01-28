import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

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
        $(".text-center").shouldHave(exactText("Text Box"));
        $("#userName-label").shouldHave(exactText("Full Name"));
        $("#userEmail-label").shouldHave(exactText("Email"));
        $("#currentAddress-label").shouldHave(exactText("Current Address"));
        $("#permanentAddress-label").shouldHave(exactText("Permanent Address"));

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
        //assertTrue($(".field-error").isDisplayed());
        $(".field-error").should(appear);

        //получить значение свойства border
        /*$(".field-error").getCssValue("border");*/

        //$(".field-error").should(Condition.cssValue("border", $(".field-error").getCssValue("border").toString()));
        $(".field-error").should(cssValue("border", "1px solid rgb(255, 0, 0)"));
    }

    @AfterEach
    void clearFields() {

        /* $("#userName").clear();
        $("#userEmail").clear();
        $("#currentAddress").clear();
        $("#permanentAddress").clear();*/

        //очищаем поля видимых элементов, оптимизация 4-ёх строк выше, желательно использовать очищение по каждому элементу в отдельности
        $$(".form-control").filter(visible).forEach(e -> e.clear());
    }
}