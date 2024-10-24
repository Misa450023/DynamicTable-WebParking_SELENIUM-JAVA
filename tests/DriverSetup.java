package tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class DriverSetup {
	
	
public static WebDriver getWebDriver() {
	
FirefoxProfile profile = new FirefoxProfile();
profile.setPreference("dom.webnotifications.enabled", false);
FirefoxOptions options = new FirefoxOptions();
options.setProfile(profile);
options.addArguments("--disable-popup-blocking");
WebDriver driver=new FirefoxDriver(options);
System.setProperty("webdriver.gecko.driver", "C:\\Users\\zikaz\\OneDrive\\Desktop\\projects\\PaypalApi\\geckodriver.exe");
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
return driver;
}

}
