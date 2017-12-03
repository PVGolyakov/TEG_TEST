package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.alfabank.alfatest.cucumber.api.AkitaPage;
import ru.alfabank.alfatest.cucumber.api.AkitaPage.Name;

@Name("Договор по лоту")

public class ConcludedContractPage extends AkitaPage {

    @FindBy(xpath = "//*[@id=\"ext-gen641\"]")
    @Name("Договор по лоту")
    public SelenideElement contractOfLot;

}
