package BinhBT3.Selenium.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import BinhBT3.Selenium.PageObjects.CartPage;
import BinhBT3.Selenium.PageObjects.LandingPage;
import BinhBT3.Selenium.PageObjects.OrderPage;
import BinhBT3.Selenium.PageObjects.ProductCatalogs;
import BinhBT3.Selenium.PageObjects.VeriftOrder;
import BinhBT3.Selenium.TestComponents.BaseTest;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
	@Test(dataProvider= "getData", groups={"purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException {
		// TODO Auto-generated method stub
//		String productName = "ZARA COAT 3";
//		String email = "anshika@gmail.com";
		String country = "India";

		ProductCatalogs productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));
		String loginMessage = driver.findElement(By.id("toast-container")).getText();
		System.out.println(loginMessage);

		productCatalog.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalog.goToCart();

		AssertJUnit.assertTrue(cartPage.verifyProductsInCart(input.get("productName")));
		cartPage.checkout();

		VeriftOrder verifyOrder = new VeriftOrder(driver);
		verifyOrder.selectCountry(country);
		verifyOrder.submitOrder();

	}
	
	
	@Test(dependsOnMethods= {"submitOrder"})
	// To verify ZARA COAT 3 is displaying in orders page
	public void OderHistoryTest() {
		String productName = "ZARA COAT 3";

		String email = "anshika@gmail.com";
		String password = "Iamking@000";
		String country = "India";

		ProductCatalogs productCatalog = landingPage.loginApplication(email, password);
		String loginMessage = driver.findElement(By.id("toast-container")).getText();
		System.out.println(loginMessage);
		
		OrderPage orderPage =  productCatalog.goToOrderPage();
		AssertJUnit.assertTrue(orderPage.verifyProductsInCart(productName));
		
	}
	
//	@DataProvider
//	public Object[][] getData() throws IOException {
//		
//		List <HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
//		
//		return new Object[][] {{data.get(0)}, {data.get(1)}};
//	}
	
	public String getSreenshot(String testcaseName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testcaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testcaseName + ".png";
	}
	
	@DataProvider
	public Object[][] getData() {
		
		HashMap<String,String> map= new HashMap<String, String>();
		map.put("email", "anshika@gmail.com");
		map.put("password", "Iamking@000");
		map.put("productName", "ZARA COAT 3");
		
		HashMap<String,String> map1= new HashMap<String, String>();
		map1.put("email", "anshika@gmail.com");
		map1.put("password", "Iamking@000");
		map1.put("productName", "ZARA COAT 3");
		
		return new Object[][] {{map}, {map1}};
	}

}
