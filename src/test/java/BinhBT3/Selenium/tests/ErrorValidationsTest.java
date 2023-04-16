package BinhBT3.Selenium.tests;

import BinhBT3.Selenium.PageObjects.CartPage;
import BinhBT3.Selenium.PageObjects.LandingPage;
import BinhBT3.Selenium.PageObjects.ProductCatalogs;
import BinhBT3.Selenium.PageObjects.VeriftOrder;
import BinhBT3.Selenium.TestComponents.BaseTest;
import BinhBT3.Selenium.TestComponents.Retry;

import java.io.IOException;
import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationsTest extends BaseTest {
	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void LoginErrorWrongPasswordValidation() throws IOException {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";

		String email = "anshika@gmail.com";
		String password = "Iamking@00";
		String country = "India";

		ProductCatalogs productCatalog = landingPage.loginApplication(email, password);
		String ErrorsMessage = landingPage.getErrorMessage();
		System.out.println(ErrorsMessage);
		Assert.assertEquals(ErrorsMessage, "ncorrect email or password.");

	}
	
	@Test
	public void LoginErrorWrongemailValidation() throws IOException {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";

		String email = "nshika@gmail.com";
		String password = "Iamking@00";
		String country = "India";

		ProductCatalogs productCatalog = landingPage.loginApplication(email, password);
		String ErrorsMessage = landingPage.getErrorMessage();
		System.out.println(ErrorsMessage);
		Assert.assertEquals(ErrorsMessage, "Incorrect email or password.");

	}

	@Test
	public void ProductErrorValidation() {
		String productName = "ADIDAS ORIGINAL";

		String email = "anshika@gmail.com";
		String password = "Iamking@000";
		String country = "India";

		ProductCatalogs productCatalog = landingPage.loginApplication(email, password);
		String loginMessage = driver.findElement(By.id("toast-container")).getText();
		System.out.println(loginMessage);

		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCart();

		Assert.assertFalse(cartPage.verifyProductsInCart(productName));
	
	}

}
