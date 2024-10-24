package pages;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class WebParking extends BaseClass {

	public WebParking(WebDriver driver) {
		super(driver);
	}

	public void openPage() {

		driver.get("https://practice.expandtesting.com/webpark");
	}

	public String setupParkingData(int index, String entryDate, String exitDate, String entryTime, String exitTime) {

		String script = "var ads = document.querySelectorAll('.ad-class'); for (var i = 0; i < ads.length; i++) { ads[i].style.display = 'none'; }";
		((JavascriptExecutor) driver).executeScript(script);
		waiting(By.xpath("//select[@id='parkingLot']"));
		scroolToView(By.xpath("//select[@id='parkingLot']"));
		Select select = new Select(driver.findElement(By.xpath("//select[@id='parkingLot']")));
		select.selectByIndex(index);
		writeTxt(By.xpath("//input[@id='entryDate']"), entryDate);
		writeTxt(By.xpath("//input[@id='exitDate']"), exitDate);
		writeTxt(By.xpath("//input[@id='entryTime']"), entryTime);
		writeTxt(By.xpath("//input[@id='exitTime']"), exitTime);
		driver.findElement(By.xpath("//button[@id='calculateCost']")).click();
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[@id='resultValue']"))).getText();
	}

	public String[] confirmParkingData() {

		String[] data = new String[3];
		driver.findElement(By.xpath("//a[@id='reserveOnline']")).click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@id='reservationDetails']/ul/li[2]/span")));
		data[0] = driver.findElement(By.xpath("//div[@id='reservationDetails']/ul/li[2]/span")).getText();
		data[1] = driver.findElement(By.xpath("//div[@id='reservationDetails']/ul/li[3]/span")).getText();
		data[2] = driver.findElement(By.xpath("//div[@id='reservationDetails']/ul/li[4]/strong")).getText();
		return data;
	}

	public void setupBookingData(String fname, String lname, String email, String phone, String lp)
			throws InterruptedException {

		writeTxt(By.xpath("//input[@id='firstName']"), fname);
		writeTxt(By.xpath("//input[@id='lastName']"), lname);
		writeTxt(By.xpath("//input[@id='email']"), email);
		writeTxt(By.xpath("//input[@id='phone']"), phone);
		scroolToView(By.xpath("//select[@id='vehicleSize']"));
		new Select(driver.findElement(By.xpath("//select[@id='vehicleSize']"))).selectByIndex(1);
		writeTxt(By.xpath("//input[@id='lpNumber']"), lp);
		driver.findElement(By.xpath("//button[@id='continue']")).click();
		Thread.sleep(500);

	}

	public String confirmBookingData() {

		return wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='completeReservation']")))
				.getText();
	}

	public void setupPaymentData(String cardNum, String expDate, String code) {

		writeTxt(By.xpath("//input[@id='cardNumber']"), cardNum);
		writeTxt(By.xpath("//input[@id='expirationDate']"), expDate);
		writeTxt(By.xpath("//input[@id='securityCode']"), code);
		driver.findElement(By.xpath("//button[@id='completeReservation']")).click();

	}

//METHOD RETURNS LIST OF DATA TO CONFIRM 
	public List<String> confirmPayment() {

		List<String> data = driver.findElements(By.xpath("//div[@id='reservationDetails']/ul/li/span")).stream()
				.map(e -> e.getText()).collect(Collectors.toList());
		data.add(driver.findElement(By.xpath("//div[@id='reservationDetails']/ul/li/strong")).getText());
		return data;
	}

	public void refresh() {
		driver.navigate().refresh();
	}

}
