package qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

import io.github.bonigarcia.wdm.WebDriverManager;
import com.codeborne.selenide.Configuration;

public class StepTests {

    @BeforeAll
    public static void setup() {
        // Укажите путь к НОВОМУ ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:/ChromeTesting/chromedriver-win64/chromedriver.exe");

        Configuration.browser = "chrome";
        Configuration.browserBinary = "C:/ChromeTesting/chrome.exe";
        Configuration.timeout = 10000;
        // Отключить DevTools
        Configuration.headless = false;
        ChromeOptions options = new ChromeOptions();
        // Добавляем аргументы для стабильности и полного экрана
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized"); // Полный экран

        // Если хотим максимализировать окно, то используем "--start-maximized", но полный экран лучше через "--start-fullscreen"
    }

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE = 80;
    private static final String BUG_TYPE = "bug";


    @Test
    public void testLambdaStep() {
        System.out.println("ChromeDriver path: " + System.getenv("webdriver.chrome.driver"));

        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });
        step("кликаем по кнопке Issues ", () ->
                $("#issues-tab [data-content='Issues']").click());
        step("проверяем название Issue", () ->
                $("a[href='/taron19/Allure/issues/1']").shouldHave(Condition.exactText("FirstIssue")));
        step("проверяем что это тип ошибки" + BUG_TYPE,
                () -> $$(".prc-Text-Text-0ima0").findBy(Condition.text(BUG_TYPE)).shouldBe(Condition.visible));
    }

}