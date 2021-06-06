import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.util.NoSuchElementException;

public class baseTest {
    static Actions action;
    static WebDriver driver;
    final static Logger logger = Logger.getLogger(baseTest.class);

    @Before
    public void setUp() throws Exception{
        logger.info("Test Başladı.");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        action = new Actions(driver);
        driver.get("https://www.gittigidiyor.com");
        driver.manage().window().maximize();
        Thread.sleep(2000);
    }

    public static WebElement findElement (By by){
        return driver.findElement(by);
    }

    public static void clickElement(By by){
        findElement(by).click();
        logger.info(by + " elementine tiklandi.");
    }

    public static void sendElement(By by, String text){
        findElement(by).sendKeys(text);
        logger.info(by + " elementine" + text + " gönderildi.");
    }

    @After
    public void quit(){
        logger.info("Test Bitti.");
        driver.quit();
    }

    public void assertControl(By assertName, String expectedName){
        String assertName1 = findElement(assertName).getText();
        Assert.assertEquals(assertName1,expectedName);
        logger.info(assertName + " === " + expectedName);
    }

    public void assertName(String actualName, String expectedName){
        Assert.assertEquals(actualName,expectedName);
        logger.info("Gerçek isim: "+ actualName + " = " + "Beklenen isim: " + expectedName);
    }

    public boolean displayElement(By by) {
        try {
            return findElement(by).isDisplayed();

        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String getText(By by){
        return findElement(by).getText();
    }

    public void scrollElement(By by) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();",findElement(by) );
    }

    public void selectByValue (By by, String key){
        getSelect(by).selectByValue(key);
    }

    public Select getSelect(By by){
        return new Select(findElement(by));
    }
}