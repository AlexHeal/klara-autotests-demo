package cloud.autotests.tests;

import cloud.autotests.helpers.DriverUtils;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;


public class HomePageTests extends TestBase {
    @Test
    @Description("Verify that home page have content blocks")
    @DisplayName("Verify HomePage")
    void verifyHomePageTest() {
        step("Open 'https://www.klara.com/'", () -> {
            open("https://www.klara.com/");
            $(".navbar-container").as("header").shouldBe(visible);
            $(".hero-video").as("background video").shouldBe(visible);
            $(".desktop-demo-text").as("free demo btn").shouldBe(visible, text("Free demo"));
            $(byText("Change the way you engage with patients")).shouldBe(visible);
        });

        step("Click 'Read more'", () -> {
            $(byText("Read more")).click();
            $(byText("Klara’s powerful patient communication platform unifies all patient workflows in one place")).shouldBe(visible);
        });

        step("Scroll to 'List slider'", () -> {
            $("#list-slider").scrollTo().scrollTo();
            $(".w-slide").shouldHave(text("Scheduling"));
        });

        step("Verify text on all slides", () -> {
            $$(".ls-nav-next").filterBy(visible).first().as("next slide btn").click();
            $$(".w-slide").filterBy(visible).first().shouldHave(text("Patient intake"));

            $$(".ls-nav-next").filterBy(visible).first().as("next slide btn").click();
            $$(".w-slide").filterBy(visible).first().shouldHave(text("Reminders & instructions"));

            $$(".ls-nav-next").filterBy(visible).first().as("next slide btn").click();
            $$(".w-slide").filterBy(visible).first().shouldHave(text("Video visits"));

            $$(".ls-nav-next").filterBy(visible).first().as("next slide btn").click();
            $$(".w-slide").filterBy(visible).first().shouldHave(text("No-show engagement"));

            $$(".ls-nav-next").filterBy(visible).first().as("next slide btn").click();
            $$(".w-slide").filterBy(visible).first().shouldHave(text("Feedback & reviews"));

            $$(".ls-nav-next").filterBy(visible).first().as("next slide btn").click();
            $$(".w-slide").filterBy(visible).first().shouldHave(text("Follow-up"));
        });

        step("Scroll to 'free-demo block'", () -> {
            $("#free-demo").scrollTo().scrollTo();
            $(byText("It’s easy to get started. Get a live demo today!")).shouldBe(visible);
        });

        step("Open Free Demo'", () -> {
            $(".klara-button-text").shouldBe(visible, text("Free demo")).click();
            $(byText("See how Klara can help improve practice efficiency and make patients happier.")).shouldBe(visible);
            String expectedUrl = "https://www.klara.com/free-demo/klara-consultation";
            String actualUrl = WebDriverRunner.url();

            assertThat(actualUrl).isEqualTo(expectedUrl);
        });


    }

    @Test
    @Description("Autogenerated test")
    @DisplayName("Page title should have header text")
    void titleTest() {
        step("Open url 'https://www.klara.com/'", () ->
            open("https://www.klara.com/"));

        step("Page title should have text 'Klara – Patient Communication'", () -> {
            String expectedTitle = "Klara – Patient Communication";
            String actualTitle = title();

            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }

    @Test
    @Description("Autogenerated test")
    @DisplayName("Page console log should not have errors")
    void consoleShouldNotHaveErrorsTest() {
        step("Open url 'https://www.klara.com/'", () ->
            open("https://www.klara.com/"));

        step("Console logs should not contain text 'SEVERE'", () -> {
            String consoleLogs = DriverUtils.getConsoleLogs();
            String errorText = "SEVERE";

            assertThat(consoleLogs).doesNotContain(errorText);
        });
    }
}