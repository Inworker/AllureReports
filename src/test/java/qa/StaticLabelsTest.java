package qa;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class StaticLabelsTest {


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
    @Feature("Issue в репозитории")
    @Story("Переход к Issue")
    @Owner("Inworker")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://testing.github.com")
    @DisplayName("Переход к Issue неавторизованного пользователя")
    public void testStaticLabels() {
        WebSteps steps = new WebSteps();
        steps.takeScreenshot();
        steps.openIssuesTab();
        steps.shouldSeeIsueWithNumber(ISSUE);
        steps.checkTabIssue(ISSUE);
    }

}