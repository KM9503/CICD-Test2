package AutomationProject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class LaunchClass {

    public static WebDriver driver;

    @BeforeMethod
    public void LaunchDriver() throws MalformedURLException {
        DesiredCapabilities dr = null;
        dr = DesiredCapabilities.chrome();
        dr.setBrowserName("chrome");
        dr.setPlatform(Platform.ANY);
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Kailas More\\Desktop\\Chrome driver\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
        driver = new RemoteWebDriver(new URL("http://192.168.84.1:4444/wd/hub"), dr);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();
    }

    @Test
    public void Test1() {
//		driver.get("https://www.facebook.com/");
        driver.navigate().to("https://www.facebook.com/");
        System.out.println("Test 1 Title : " + driver.getTitle());
    }

    @Test
    public void Test2() {
        driver.navigate().to("https://www.amazon.in/");
        System.out.println("Test 2 Title : " + driver.getTitle());
    }

    @Test
    public void Test3() {
        driver.navigate().to("https://www.myntra.com/");
        System.out.println("Test 3 Title : " + driver.getTitle());
    }

    @AfterMethod
    public void Quite() {
        driver.quit();
    }

}
