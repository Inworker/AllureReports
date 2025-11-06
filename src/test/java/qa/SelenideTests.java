package qa;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.*;

public class SelenideTests {


    private static final String REPOSITORY = "Inworker/AllureReports";
    private static final String ISSUE = "1";

    @BeforeEach
    public void openPage() {
        System.out.println("ChromeDriver path: " + System.getenv("webdriver.chrome.driver"));

        SelenideLogger.addListener("allure", new AllureSelenide());

        step("открываем нужный репозиторий GitHub",
                () -> open("https://github.com/" + REPOSITORY));
    }

    @Test
    public void testLambdaAnnotatedStep() {
        WebSteps steps = new WebSteps();
        steps.takeScreenshot();
        steps.openIssuesTab();
        steps.shouldSeeIsueWithNumber(ISSUE);
        steps.checkTabIssue(ISSUE);
    }

}