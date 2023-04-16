package BinhBT3.Selenium.PageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.google.common.cache.AbstractCache;

import BinhBT3.Selenium.AbstractComponnents.AbstractComponents;

public class CartPage extends AbstractComponents {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/dashboard/cart");
	}

	@FindBy(xpath = "//div[@class='cartSection']/h3")
	private List<WebElement> productsInCart;

	public boolean verifyProductsInCart(String productName) {
		Boolean match = productsInCart.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		System.out.println(match);
		return match;
	}

	@FindBy(xpath = "//button[text()='Checkout']")
	WebElement checkoutButton;

	public void checkout() {
		checkoutButton.click();
	}
}
