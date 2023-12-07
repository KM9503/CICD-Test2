package AutomationProject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class LaunchClass {

    public static WebDriver driver;
//    public static final String username = "kailasmore_Ey1d85";
//    public static final String accessKey = "9d9z2WezafunKPfRmSBh";
//    public static final String URL = "http://" + username + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub";

//    @BeforeMethod
//    public void LaunchDriver() throws MalformedURLException {
//        DesiredCapabilities dr = null;
//        dr = DesiredCapabilities.chrome();
//        dr.setBrowserName("chrome");
//        dr.setPlatform(Platform.ANY);
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver = new RemoteWebDriver(new URL("http://localhost:6900/wd/hub"), dr);
//        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//        driver.manage().window().fullscreen();
//    }

    @BeforeMethod
    public void launchDriver() throws MalformedURLException {
//        DesiredCapabilities dr = DesiredCapabilities.chrome();
        DesiredCapabilities capabilities = new DesiredCapabilities();
//        dr.setBrowserName("chrome");
//        dr.setPlatform(Platform.ANY);
//
//        // Setting up ChromeDriver using WebDriverManager
//        WebDriverManager.chromedriver().setup();
//
//        // Using RemoteWebDriver
//        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dr);
//
//        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//        driver.manage().window().fullscreen();

        try {
            Map<String, Object> chromePrefs = new HashMap<String, Object>();
            ChromeOptions options = new ChromeOptions();
//            chromePrefs.put("profile.content_settings.exceptions.clipboard", getClipBoardSettingsMap(1));
            chromePrefs.put("profile.default_content_settings.popups", 0);
//            chromePrefs.put("download.default_directory", downloadPath);
            chromePrefs.put("download.prompt_for_download", false);
            chromePrefs.put("credentials_enable_service", false);
            chromePrefs.put("profile.password_manager_enabled", false);
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
            options.addArguments("--no-sandbox"); // Bypass OS security model

//            if (isHeadless.equals("true"))
//                options.addArguments("--headless", "--window-size=1920,1200");

            options.setExperimentalOption("prefs", chromePrefs);
            options.setExperimentalOption("useAutomationExtension", false);
            options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
            capabilities.setPlatform(Platform.ANY);
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            capabilities.setCapability("browserName", "chrome");
            capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
//            options.merge(capabilities);

            driver = WebDriverManager.chromedriver().capabilities(capabilities).remoteAddress("http://localhost:4444/wd/hub").create();

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("WebDriver not initiated. " + e.getMessage());
        }
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
