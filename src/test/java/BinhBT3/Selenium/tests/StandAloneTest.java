package BinhBT3.Selenium.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import BinhBT3.Selenium.PageObjects.CartPage;
import BinhBT3.Selenium.PageObjects.LandingPage;
import BinhBT3.Selenium.PageObjects.OrderPage;
import BinhBT3.Selenium.PageObjects.ProductCatalogs;
import BinhBT3.Selenium.PageObjects.VeriftOrder;
import BinhBT3.Selenium.TestComponents.BaseTest;

import java.io.IOException;
import java.time.Duration;
import java.util.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest extends BaseTest {
	@Test(dataProvider= "getData")
	public void submitOrder(String email, String password, String productName) throws IOException {
		// TODO Auto-generated method stub
//		String productName = "ZARA COAT 3";
//		String email = "anshika@gmail.com";
//		String password = "Iamking@000";
		String country = "India";

		ProductCatalogs productCatalog = landingPage.loginApplication(email, password);
		String loginMessage = driver.findElement(By.id("toast-container")).getText();
		System.out.println(loginMessage);

		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCart();

		AssertJUnit.assertTrue(cartPage.verifyProductsInCart(productName));
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
	
	@DataProvider
	public Object[][] getData() {
		return new Object[][] {{"anshika@gmail.com", "Iamking@000", "ZARA COAT 3"}, {"anshika@gmail.com", "Iamking@000", "ZARA COAT 3"}, {"anshika@gmail.com", "Iamking@000", "ADIDAS ORIGINAL"}};
		
		
		
	}

}
