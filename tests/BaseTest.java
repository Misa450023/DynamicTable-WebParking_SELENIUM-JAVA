package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

	public WebDriver driver;

	@BeforeTest
	public void setup() {
		driver = DriverSetup.getWebDriver();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
