package qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;

public class WebSteps {

    @Step
    public void openMainPage()
    {
        step("открываем нужный репозиторий GitHub",
                () -> open("https://github.com/"));
        attachment("Source", webdriver().driver().source());
    }

    @Step
    public void searcForRepository(String repo)
    {
        // Открытие страницы перед каждым тестом
        step("открываем нужный репозиторий GitHub",
                () -> open("https://github.com/" + repo));
    }


    @Step
    public void openIssuesTab()
    {
        step("кликаем по кнопке Issues ", () ->
                $("#issues-tab [data-content='Issues']").click());
    }

    @Step
    public void shouldSeeIsueWithNumber(String issue)
    {
        step("проверяем название Issue", () ->
                $("a[href='/Inworker/AllureReports/issues/1']").shouldHave(Condition.exactText(issue)));
    }

    @Step
    public void checkTabIssue(String issue)
    {
        step("проверяем что это тип ошибки" + issue,
                () -> $$(".prc-Text-Text-0ima0").findBy(Condition.text(issue)).shouldBe(Condition.visible));

    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot()
    {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
