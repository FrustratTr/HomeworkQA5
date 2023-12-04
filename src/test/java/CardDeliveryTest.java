import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import java.time.Duration;

public class CardDeliveryTest {

    @Test
    public void shouldSubmitCardDeliveryForm() {
        Configuration.baseUrl = "http://localhost:9999";
        open("/");

        $("[data-test-id='city'] input").setValue("Москва");
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").doubleClick().sendKeys(date);
        $("[data-test-id='name'] input").setValue("Иван Иванов");
        $("[data-test-id='phone'] input").setValue("+79001234567");
        $("[data-test-id='agreement']").click();
        $(By.cssSelector("button.button_view_extra")).click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='notification'] .notification__content")
                .shouldHave(text("Встреча успешно забронирована на " + date));
    }
}
