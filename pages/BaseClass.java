package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {

	public WebDriver driver;
	public WebDriverWait wait;

	public BaseClass(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(21));

	}

	public void waiting(By element) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}

	public void hoverOn(By element) {

		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(element)).build().perform();
	}

	public void scroolToView(By element) {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(element));
	}

	public void writeTxt(By element, String text) {

		driver.findElement(element).sendKeys(text);
	}

}
