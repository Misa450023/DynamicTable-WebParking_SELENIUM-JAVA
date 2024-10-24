package pages;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DynamicTablePage extends BaseClass {

	public DynamicTablePage(WebDriver driver) {
		super(driver);		
	}
	
	
	public void openPage() {
		driver.get("https://practice.expandtesting.com/dynamic-table");
	}
	
	
	public int getIndexOfChrome() {
		
		return
		driver.findElements(By.xpath("//tbody/tr/td[1]")).stream().map(e->e.getText())
		.collect(Collectors.toList()).indexOf("Chrome");
	}
	
	public int getIndexOfCPU() {
		
		return
		driver.findElements(By.xpath("//th")).stream().map(e->e.getText())
		.collect(Collectors.toList()).indexOf("CPU");
	}
	
	public String getChromeCPUdata() {
		
		String row=String.valueOf(getIndexOfChrome()+1);
		String col=String.valueOf(getIndexOfCPU()+1);
		String xp= "//tbody/tr["+row+"]/td["+col+"]";
		return
		driver.findElement(By.xpath(xp)).getText();
		
	}
	
	public String getCheckerData() {
		
		return
		driver.findElement(By.xpath("//p[@id='chrome-cpu']")).getText();
	}
	
	
	public List handleDynamicTable() {
		openPage();
		return
		Arrays.asList(getCheckerData(),getChromeCPUdata());
	}
	
	
// OTHER VAY TO HANDLE DYNAMIC WEB TABLE 
	
	public String getChromeCPU2() {
		
		String xp="//table[@class='table table-striped']/tbody/tr/td[normalize-space()='Chrome']/following-sibling::td";
		return
		driver.findElements(By.xpath(xp)).stream().map(e->e.getText())
		.filter(e->e.contains("%")).collect(Collectors.toList()).get(0);
	}
		
	
	public List handleDynamicTable01() {
		openPage();
		
		return
		Arrays.asList(getCheckerData(),getChromeCPU2());
	}

	
	
	
	
	public HomePage getHomePage() {
		driver.navigate().to("https://practice.expandtesting.com/");
		return new HomePage(driver);
	}
	
	
	
	
	
	
	
}
