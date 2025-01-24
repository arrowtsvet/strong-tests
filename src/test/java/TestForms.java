import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class TestForms {
    @Test
    void completionForm() {
        open("https://demoqa.com/automation-practice-form");
    }
}
