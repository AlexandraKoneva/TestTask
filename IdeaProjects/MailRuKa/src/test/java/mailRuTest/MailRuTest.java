package mailRuTest;

import mailRuPages.MainPage;
import mailRuPages.PersonalAreaPage;
import mailRuPages.WriteLetterPage;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MailRuTest {

    private static ThreadLocal<HashMap<String, String>> testData = new ThreadLocal<>();
    public static WebDriver driver;
    public static PersonalAreaPage personalAreaPage;
    public static MainPage mainPage;
    public static WriteLetterPage writeLetterPage;

    @BeforeClass
    public static void settingsForBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\webdrivers\\chromedriver85.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://mail.ru/");

        personalAreaPage = new PersonalAreaPage(driver);
        mainPage = new MainPage(driver);
        writeLetterPage = new WriteLetterPage(driver);
    }

    @Test
    public void test1() {
        mainPage.inputMail("test-testov-1983");
        mainPage.clickOnButtonInputPassword();
        mainPage.inputPassword(")TYribRaDr54");
        mainPage.clickOnButtonComeIn();
        personalAreaPage.clickOnButtonWriteLetter();

        writeLetterPage.inputToWhom("test-testov-1983@mail.ru");
        Assert.assertEquals("Ошибка! Значение в поле Кому не соответствует ожидаемому!", "test-testov-1983@mail.ru", driver.findElement(By.xpath("//div[@class=\"contactsContainer--3RMuQ\"]//span[1]")).getText());

        writeLetterPage.inputTheme(randomMail("qwertyuiopasdfghjklzxcvbnm", 10, "рандомное_значение_1"));
        String value1 = getTestDataValue("рандомное_значение_1");
        Assert.assertEquals("Ошибка! Значение в поле Тема не соответствует ожидаемому!", value1, writeLetterPage.getAttributeFromTheme());

        writeLetterPage.inputMessage(randomMail("qwertyuiopasdfghjklzxcvbnm", 20, "рандомное_значение_2"));
        String value2 = getTestDataValue("рандомное_значение_2");
        Assert.assertEquals("Ошибка! Значение в поле Сообщение не соответствует ожидаемому!", value2, writeLetterPage.getTextMessage());

        writeLetterPage.clickSend();

        writeLetterPage.theLetterSendIsSuccesful();
    }


    public String randomMail(String chars, int length, String key) {
        Random rand = new Random();
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < length; i++) {
            buf.append(chars.charAt(rand.nextInt(chars.length())));
        }
        setTestData(key, buf.toString());
        return buf.toString();
    }

    public static HashMap<String, String> getTestData() {
        if (MailRuTest.testData.get() == null) {
            MailRuTest.testData.set(new HashMap<>());
        }
        return testData.get();
    }

    public static boolean isContainsInTestData(String key) {
        return getTestData().containsKey(key);
    }

    public static String getTestDataValue(String key) {
        String value = "";
        if (isContainsInTestData(key)) {
            return getTestData().get(key);
        }
        return value;
    }

    public void setTestData(String key, String value) {
        getTestData().put(key, value);
    }


    @AfterClass
    public static void killBrowser() {
        driver.quit();
    }
}
