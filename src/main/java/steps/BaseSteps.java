package steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.Before;
import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import lombok.experimental.Delegate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import ru.alfabank.alfatest.cucumber.api.AkitaPage;
import ru.alfabank.alfatest.cucumber.api.AkitaScenario;
import ru.alfabank.steps.DefaultApiSteps;
import ru.alfabank.steps.DefaultSteps;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.Assert.*;
import static ru.alfabank.tests.core.helpers.PropertyLoader.loadProperty;

/**
 * Created by u_021d8 on 14.06.2017.
 */
@Slf4j
public class BaseSteps {

    @Delegate
    AkitaScenario alfaScenario = AkitaScenario.getInstance();
    private DefaultApiSteps defaultApiSteps = new DefaultApiSteps();

    private DefaultSteps defaultSteps = new DefaultSteps();


    @Before(order = 3)
    public static void saveSettings() {
        getWebDriver().manage().window().setSize(new Dimension(1280, 768));
    }



    @Дано("Выполнили скрипт увеличение масштаба до 30%")
    public void executeScript() {
        JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
        js.executeScript("document.body.style.zoom='30%'");
    }



//    @Тогда("^поле \"([^\"]*)\" содержит значение \"([^\"]*)\"$")
//    public void checkText(String fieldName, String text) {
//        SelenideElement field = alfaScenario.getCurrentPage().getElement(fieldName);
//        String result;
//        result = field.getText();
//
//        if(result.isEmpty()) {
//            assertEquals("Значение поля " + fieldName + " не содержит " + text, text, field.getValue());
//        } else {
//            assertEquals("Значение поля " + fieldName + " не содержит " + text, text, field.getText());
//        }
//    }

    @И("^popup поле \"([^\"]*)\" заполняется значением  \"([^\"]*)\"$")
    public void popupSetValue(String fieldName, String value) {
        SelenideElement field = alfaScenario.getCurrentPage().getElement(fieldName);
        SelenideElement popupValue = field.find(By.xpath(".//*[text()='" + value + "']"));
        popupValue.click();
    }

//    @И("^вызван метод \"([^\"]*)\" c URL \"([^\"]*)\", headers и parameters из таблицы")
//    public void request(String typeOfRequest, String urlName, List<RequestParam> table) throws Exception {
//        Random random = new Random();
//        String varName = "paramName"+random.nextInt(1000);
//        defaultApiSteps.sendRequest(typeOfRequest, urlName, varName, table);
//    }

    @И("^выбран (\\d+) элемент в списке \"([^\"]*)\"$")
    public void chooseElementFromPopup(String number, String fieldName)  {
        SelenideElement field = alfaScenario.getCurrentPage().getElement(fieldName);
        field.find(By.xpath(".//div[" + number +"]")).click();
    }

    @Тогда("^в поле \"([^\"]*)\" содержится ошибка бизнес валидации об обязательности поля$")
    public void fieldWithError(String fieldName)    {
        SelenideElement field = alfaScenario.getCurrentPage().getElement(fieldName);
        SelenideElement error = field.find(By.xpath("./ancestor::*[contains(@class, 'radio-group_invalid') or contains(@class, 'select_invalid') or contains(@class, 'input_invalid')]"));
        assertTrue(String.format("Поле [%s] не содержит ошибки", fieldName), error.exists());
    }

    @Тогда("^в поле \"([^\"]*)\" содержится ошибка с текстом \"([^\"]*)\"$")
    public void fieldWithErrorFillingText(String fieldName, String errorText) {
        SelenideElement field = alfaScenario.getCurrentPage().getElement(fieldName);
        SelenideElement error = field.find(By.xpath(".//following::*[contains(text(), (errorText))]"));
        assertTrue(String.format("Поле [%s] не содержит ошибки с заданным текстом", fieldName), error.exists());
    }

    @Тогда("^в поле \"([^\"]*)\" содержится подсказка с текстом \"([^\"]*)\"$")
    public void fieldWithHintFillingText(String fieldName, String hintText) {
        SelenideElement field = alfaScenario.getCurrentPage().getElement(fieldName);
        SelenideElement hint = field.find(By.xpath(".//following::*[contains(text(), (hintText))]"));
        assertTrue(String.format("Поле [%s] не содержит подсказку с заданным текстом", fieldName), hint.exists());
    }

    @Тогда ("^элемент \"([^\"]*)\" видим на экране$")
    public void ElemIsDisplayed(String elemName) {
        SelenideElement element = alfaScenario.getCurrentPage().getElement(elemName);
        element.isDisplayed();
    }

    @Тогда ("^элемент \"([^\"]*)\" невидим на экране$")
    public void ElemIsNotDisplayed(String elemName) {
        SelenideElement element = alfaScenario.getCurrentPage().getElement(elemName).shouldNotBe(visible);
    }

    @Тогда ("^(?:поле|элемент) \"([^\"]*)\" (?:нередактируемо|нередактируем)$")
    public void ElemIsDisabled(String elemName) {
        SelenideElement element = alfaScenario.getCurrentPage().getElement(elemName);
        SelenideElement disabled = element.find (By.xpath(".//ancestor::*[contains(@class, 'disabled')]"));
        assertTrue(String.format("Поле [%s] можно заполнить", elemName), disabled.exists());
    }

    @Тогда ("^элемент \"([^\"]*)\" доступен для заполнения$")
    public void ElemIsEnabled(String elemName) {
        SelenideElement element = alfaScenario.getCurrentPage().getElement(elemName);
        SelenideElement disabled = element.find (By.xpath(".//ancestor::*[contains(@class, 'disabled')]"));
        assertFalse(String.format("Поле [%s] нельзя заполнить", elemName), disabled.exists());
    }

    @Тогда ("^элемент \"([^\"]*)\" не видим на странице$")
    public void ElemIsNotVisible(String elemName) {
        SelenideElement element = alfaScenario.getCurrentPage().getElement(elemName);
        element.shouldNotBe(Condition.visible);
    }

    @Тогда("^в поле \"([^\"]*)\" отсутствует ошибка$")
    public void fieldWithoutError(String fieldName)    {
        SelenideElement field = alfaScenario.getCurrentPage().getElement(fieldName);
        SelenideElement error = field.find(By.xpath("./ancestor::span[contains(@class, 'radio-group_invalid') or contains(@class, 'input_invalid')]"));
        assertFalse(String.format("Поле [%s] содержит ошибку", fieldName), error.exists());
    }

    @И("^в поле \"([^\"]*)\" дописывается значение \"([^\"]*)\"$")
    public void addValue (String fieldName, String value) {
        SelenideElement field = alfaScenario.getCurrentPage().getElement(fieldName);
        String oldValue = field.getValue();
        field.setValue("");
        field.setValue(oldValue + value);
    }

    @И("^нажать на клавиатуре \"([^\"]*)\"$")
    public void pressButtonOnKeyboard(String buttonName) {
        Keys key = Keys.valueOf(buttonName.toUpperCase());
        WebElement curElement = getWebDriver().switchTo().activeElement();
        curElement.sendKeys(key);
    }

    @И("^нажатие на элемент с текстом \"([^\"]*)\"$")
    public void findElement(String textName) {
        $(By.xpath("//*[text()='" + textName + "']//ancestor::div[1]")).click();
    }

    @И("^скролл в конец страницы$")
    public void scrollDown() {
        Actions actions = new Actions(getWebDriver());
        actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).build().perform();
    }

    @Когда("^поместили значение \"([^\"]*)\" в поле \"([^\"]*)\"$")
    public void setValToField(String value, String nameOfField) {
        SelenideElement valueInput = alfaScenario.getCurrentPage().getElement(nameOfField);
        valueInput.sendKeys(Keys.chord(Keys.CONTROL, value));
        valueInput.should(not(Condition.empty));
    }

    @Когда("^установлено значение \"([^\"]*)\" в поле \"([^\"]*)\" без проверки заполнения$")
    public void setValueToField(String amount, String nameOfField) {
        SelenideElement valueInput = alfaScenario.getCurrentPage().getElement(nameOfField);
        valueInput.clear();
        valueInput.setValue(String.valueOf(amount));
    }

    @Когда ("^добавили внешний кредит$")
    public void addCredit () {
        stepCreateUniqueWordValue("Название банка", 10);
        defaultSteps.clickOnElement("Тип кредита");
        popupSetValue("Выпадающий список тип кредита", "Кредит наличными");
        defaultSteps.setFieldValue("Сумма задолженности", "50000");
        defaultSteps.setFieldValue("Дата открытия", "10.10.2010");
        defaultSteps.clickOnElement("Добавить");
    }

    @Когда("^поле \"([^\"]*)\" заполняется текущей датой&")
    public void currentDate(String fieldName) {
        long date = System.currentTimeMillis();
        String curStringDate = new SimpleDateFormat("dd.MM.yyyy").format(date);
        SelenideElement valueInput = alfaScenario.getCurrentPage().getElement(fieldName);
        valueInput.setValue("");
        valueInput.setValue(curStringDate);
    }


    @Когда("^правильно очищено поле \"([^\"]*)\"$")
    public void cleanField(String nameOfField) {
        SelenideElement valueInput = alfaScenario.getCurrentPage().getElement(nameOfField);
        valueInput.click();
        valueInput.clear();
        valueInput.setValue("");
    }

    @Когда("^очищено поле \"([^\"]*)\" и введено значение \"(.*)\"$")
    public void setFieldValue(String elementName, String value) {
        sleep(500);
        SelenideElement valueInput = alfaScenario.getCurrentPage().getElement(elementName);
        valueInput.scrollTo();
        valueInput.click();
        valueInput.clear();
        valueInput.setValue(String.valueOf(value));
    }

    @Когда("^выполен переход в поле \"([^\"]*)\"$")
    public void clickField(String elementName) {
        sleep(500);
        SelenideElement valueInput = alfaScenario.getCurrentPage().getElement(elementName);
        valueInput.scrollTo();
        valueInput.click();
    }

    @Когда ("^выполнен запрос \"$select\" к базе$")
    public void requestToDB (String select){
    }

    @Когда("^поле \"([^\"]*)\" заполняется уникальным \"([^\"]*)\"-значным словом$")
    public void stepCreateUniqueWordValue(String var, Integer length) {
        String uniqueWord = "Б" + RandomStringUtils.random(length,"йцукенгшщзхэждлорпавыфячсмитбю");
        SelenideElement valueInput = alfaScenario.getCurrentPage().getElement(var);
        valueInput.setValue(uniqueWord);
    }

    @Когда("^поле \"([^\"]*)\" заполняется уникальным \"([^\"]*)\"-значным цифро-буквенным словом$")
    public void stepCreateUniqueWordNValue(String var, Integer length) {
        String uniqueWord = RandomStringUtils.random(length-2, "1323у4к5е6н7г8ш9щ0з1х2ф3ы4в5а6п7р8о9л041ж2э3я4ч5с6м7и8т9ь0б1ю");
        SelenideElement valueInput = alfaScenario.getCurrentPage().getElement(var);
        valueInput.setValue(uniqueWord);
    }

    @Тогда("^выбран радиобаттон \"([^\"]*)\"$")
    public void selectedRadioButton(String elemName) {
        alfaScenario.getCurrentPage().getElement(elemName).setSelected(true);
    }

    //PDF на всякий случай
//    @И("^делаем из PDF конфету$")
//    public void pdf() throws IOException, COSVisitorException, COSLoadException {
//        serviceSteps.extractText("конфета");
//    }


    @И("^элемента \"([^\"]*)\" нет на странице$")
    public void elemIsNotPresentedOnPage(String elemName) {
        alfaScenario.getCurrentPage().getElement(elemName).is(Condition.disappears);
    }

}
