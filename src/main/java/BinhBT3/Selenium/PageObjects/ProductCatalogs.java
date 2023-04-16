package BinhBT3.Selenium.PageObjects;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import BinhBT3.Selenium.AbstractComponnents.AbstractComponents;


public class ProductCatalogs extends AbstractComponents {
	WebDriver driver;

	public ProductCatalogs(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	By productsBy = By.cssSelector(".mb-3");

	public List<WebElement> getProductsList() {

		waitForElementToApper(productsBy);
		return products;
	}

	public WebElement getProductByName(String productName) {
		{
			WebElement prod =	getProductsList().stream().filter(product->
			product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
			return prod;
		}

	}

	By addToCart = By.xpath("//div/button[2]");
	By toastContainer = By.id("toast-container");
	@FindBy(css=".ng-aminating")
	WebElement bgAminating;
	public void addProductToCart(String productName) {
		WebElement product = getProductByName(productName);
		product.findElement(addToCart).click();
		waitForElementToApper(toastContainer);
		waitForElementToDisappear(bgAminating);
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}


}
