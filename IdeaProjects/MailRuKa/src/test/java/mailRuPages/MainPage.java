package mailRuPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    public WebDriver driver;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//input[@placeholder=\"Имя ящика\"]")
    private WebElement nameMail;

    @FindBy(xpath = "//button[text()=\"Ввести пароль\"]")
    private WebElement buttonInputPassword;

    @FindBy(xpath = "//input[@placeholder=\"Пароль\"]")
    private WebElement password;

    @FindBy(xpath = "//button[text()='Войти']")
    private WebElement buttonComeIn;

    public void inputMail(String mail) {
        nameMail.sendKeys(mail);
    }

    public void inputPassword(String valuePassword) {
        password.sendKeys(valuePassword);
    }

    public void clickOnButtonInputPassword() {
        buttonInputPassword.click();
    }

    public void clickOnButtonComeIn() {
        buttonComeIn.click();
    }

}
