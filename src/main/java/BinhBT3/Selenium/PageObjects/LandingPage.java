package BinhBT3.Selenium.PageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import BinhBT3.Selenium.AbstractComponnents.AbstractComponents;


public class LandingPage extends AbstractComponents{
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		
	}
	
	//PageFactory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userpassword;

	@FindBy(id="login")
	WebElement submit;

	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	public ProductCatalogs loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userpassword.sendKeys(password);
		submit.click();
		ProductCatalogs productCatalog = new ProductCatalogs(driver);
		return productCatalog;
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}
