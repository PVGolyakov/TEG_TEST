package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.alfabank.alfatest.cucumber.api.AkitaPage;
import ru.alfabank.alfatest.cucumber.api.AkitaPage.Name;

@Name("Главноая страница оператора")

public class FirstOperatorPage extends AkitaPage {

    @FindBy(xpath = "//*[@id=\"ext-comp-1033\"]/tbody/tr[2]/td[2]/em")
    @Name("Финансы")
    public SelenideElement finance;

    @FindBy(xpath = "//*[@id=\"ext-gen168\"]")
    @Name("Закупочные процедуры")
    public SelenideElement procurementProcedures;

    @FindBy(xpath = "//*[@id=\"ext-comp-1528\"]/tbody/tr[2]/td[2]/em")
    @Name("Выход")
    public SelenideElement logOut;

    @FindBy(xpath = "//*[@id=\"ext-gen153\"]")
    @Name("Договоры")
    public SelenideElement contract;

    @FindBy(xpath = "//*[@id=\"ext-gen253\"]")
    @Name("Заключенные договоры")
    public SelenideElement concludedСontracts;

    @FindBy(xpath = "//*[@id=\"ext-gen199\"]/div[1]/table/tbody/tr/td[17]/div/a/img")
    @Name("Операции")
    public SelenideElement contractOperation;



}
