package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BaseClass {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public Boolean searchInputValidation() {

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='search-input']")));
		return driver.findElement(By.xpath("//input[@id='search-input']")).isDisplayed();
	}

}
