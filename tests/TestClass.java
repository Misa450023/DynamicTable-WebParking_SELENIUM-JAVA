package tests;

import java.util.List;

import org.junit.Assert;
import org.testng.annotations.Test;

import dataProviders.DataProviders;
import pages.DynamicTablePage;
import pages.HomePage;
import pages.Tooltips_Page;
import pages.WebParking;
import utility.MyUtil;

public class TestClass extends BaseTest {

	String card = "5200828282828223";
	String name = "eki";
	String lname = "ekic";
	String mail = "eki21@by.com";
	String lp = "12312343";
	String phone = "01021023";

	DynamicTablePage dtp;

//TESTING DYNAMIC WEBTABLE 
	@Test(priority = 0)
	public void test_00_DynamicWebTable() {

		dtp = new DynamicTablePage(driver);
		List<String> list = dtp.handleDynamicTable();
		System.out.println(list);
		Assert.assertTrue(list.get(0).contains(list.get(1)));

		List<String> list1 = dtp.handleDynamicTable01();
		System.out.println(list1);
	}

//*THIS TEST RETURNS USER TO HOME PAGE AND CONFIRM IT
	@Test(priority = 1)
	public void test_01_HomePage() {

		HomePage home = dtp.getHomePage();
		Boolean inputpresent = home.searchInputValidation();
		System.out.println(inputpresent);
	}

//*TESTING THE TOOLTIPS LIST GIVES A VALUE FROM EVERY TOOLTIP
	@Test
	public void test_02_tooltips() {

		Tooltips_Page tp = new Tooltips_Page(driver);
		List<String> list = tp.getTooltipsData();
		System.out.println(list);
	}

// *** TEST FOR PARKING CALCULATOR SETTING DATA AND EXECUTE PAYMENT ***
	@Test(groups = "positive", dataProvider = "parkingData", dataProviderClass = DataProviders.class)
	public void parkingAppTest01(String index, String startDate, String endDate, String startTime, String endTime,
			String expectedCost) throws InterruptedException {

		WebParking webparking = new WebParking(driver);
		webparking.openPage();
		String result = webparking.setupParkingData(Integer.valueOf(index), startDate, endDate, startTime, endTime);

		Assert.assertEquals(result.replace("€", ""), expectedCost);

		Thread.sleep(900);
		String[] data = webparking.confirmParkingData();

		Assert.assertTrue(data[0].contains(startDate) && data[0].contains(startTime));
		Assert.assertTrue(data[1].contains(endDate) && data[1].contains(endTime));
		Assert.assertTrue(data[2].contains(expectedCost));

		webparking.setupBookingData(name, lname, mail, phone, lp);
		String buttonValue = webparking.confirmBookingData();

		Assert.assertEquals(buttonValue, "Complete Reservation");

		webparking.setupPaymentData(card, "1027", "123");
		List<String> paymentData = webparking.confirmPayment();

		Assert.assertNotNull(paymentData.get(0));
		Assert.assertTrue(paymentData.get(5).contains(expectedCost));
		Assert.assertTrue(paymentData.get(2).contains(startDate));
		Assert.assertTrue(paymentData.get(3).contains(endDate));
	}

}
