package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.alfabank.alfatest.cucumber.api.AkitaPage;
import ru.alfabank.alfatest.cucumber.api.AkitaPage.Name;

@Name("LoginPage")
public class LoginPage extends AkitaPage {

    @FindBy(xpath = "//*[@name=\"username\"]")
    @Name("Логин")
    public SelenideElement loginField;

    @FindBy(xpath = "//*[@name=\"pass\"]")
    @Name("Пароль")
    public SelenideElement passField;

    @FindBy(xpath = "//*[@id=\"ext-gen81\"]")
    @Name("Вход")
    public SelenideElement logInButton;

}
