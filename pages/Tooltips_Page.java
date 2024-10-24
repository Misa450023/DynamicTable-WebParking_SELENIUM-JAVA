package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Tooltips_Page extends BaseClass {

	public Tooltips_Page(WebDriver driver) {
		super(driver);
	}

	public List<String> getTooltipsData() {

		List<String> list = new ArrayList<>();
		openPage();
		waitForTooltipsToBeDisplayed();
		scroolToView(By.xpath("//button[@id='btn1']"));
		for (int a = 1; a <= 5; a++) {
			String xpath = "//button[@id='btn" + String.valueOf(a) + "']";
			list.add(getTooltipTxt(xpath, "//div[@role='tooltip']"));
		}

		return list;
	}

	public void openPage() {
		driver.get("https://practice.expandtesting.com/tooltips");
	}

	public void waitForTooltipsToBeDisplayed() {
		waiting(By.xpath("//button[@id='btn1']"));
	}

	public String getTooltipTxt(String buttonXP, String tooltipXP) {
		hoverOn(By.xpath(buttonXP));
		waiting(By.xpath(tooltipXP));
		return driver.findElement(By.xpath(tooltipXP)).getText();

	}

}
