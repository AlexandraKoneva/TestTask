package mailRuPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PersonalAreaPage {
    public WebDriver driver;

    public PersonalAreaPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//span[text()='Написать письмо']//parent::span")
    private WebElement writeLetter;

    public void clickOnButtonWriteLetter() {
        writeLetter.click();
    }
}
