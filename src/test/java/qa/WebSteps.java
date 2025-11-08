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

    @Step("кликаем по кнопке Issues")
    public void openIssuesTab()
    {
        $("#issues-tab [data-content='Issues']").click();
    }

    @Step("проверяем название Issue")
    public void shouldSeeIsueWithNumber(String issue)
    {
        $("a[href='/Inworker/AllureReports/issues/1']").shouldHave(Condition.exactText(issue));
    }

    @Step("проверяем что это тип ошибки")
    public void checkTabIssue(String issue)
    {
        $$(".prc-Text-Text-0ima0").findBy(Condition.text(issue)).shouldBe(Condition.visible));
    }
}
