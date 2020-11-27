package mailRuPages;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WriteLetterPage {
    public WebDriver driver;

    public WriteLetterPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//div[@class=\"contactsContainer--3RMuQ\"]//input[@class=\"container--H9L5q size_s--3_M-_\"]")
    private WebElement toWhom;

    @FindBy(xpath = "//div[@class=\"subject__container--HWnat\"]//input[@class=\"container--H9L5q size_s--3_M-_\"]")
    private WebElement theme;

    @FindBy(xpath = "//div[@role=\"textbox\"]/div[1]")
    private WebElement message;

    @FindBy(xpath = "//div[contains(@class,'compose-app')]//span[text()='Отправить']//parent::span[1]")
    private WebElement buttonSend;

    @FindBy(xpath = "//*[text()='Письмо отправлено']")
    private WebElement thisLetterSend;

    public void inputToWhom(String value) {
        toWhom.sendKeys(value);
        toWhom.sendKeys(Keys.ENTER);
    }

    public void inputTheme(String value) {
        theme.sendKeys(value);
    }

    public void inputMessage(String value) {
        message.sendKeys(value);
    }

    public void clickSend() {
        buttonSend.click();
    }

    public String getAttributeFromTheme() {
        return theme.getAttribute("value");
    }

    public String getTextMessage() {
        return message.getText();
    }
    public void theLetterSendIsSuccesful(){
        Assert.assertTrue(thisLetterSend.isDisplayed());
    }
}
