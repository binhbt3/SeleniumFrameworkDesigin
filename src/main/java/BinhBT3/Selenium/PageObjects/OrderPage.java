package BinhBT3.Selenium.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import BinhBT3.Selenium.AbstractComponnents.AbstractComponents;

public class OrderPage extends AbstractComponents {
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	
	@FindBy(xpath = "//tbody/tr/td[2]")
	private List<WebElement> productsName;

	public boolean verifyProductsInCart(String productName) {
		Boolean match = productsName.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		System.out.println(match);
		return match;
	}

}
