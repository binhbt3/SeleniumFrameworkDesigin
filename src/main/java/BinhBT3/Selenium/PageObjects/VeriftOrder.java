package BinhBT3.Selenium.PageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import BinhBT3.Selenium.AbstractComponnents.AbstractComponents;

public class VeriftOrder extends AbstractComponents {
	WebDriver driver;

	public VeriftOrder(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "div[class='form-group'] input")
	WebElement country;
	@FindBy(css = ".ta-item:nth-child(3)")
	WebElement selectedCountry;
	By countrySuggestList = By.cssSelector(".ta-results");

	public void selectCountry(String countryName) {
		Actions act = new Actions(driver);
		act.sendKeys(country, countryName).build().perform();
		waitForElementToApper(countrySuggestList);
		selectedCountry.click();
	}

	@FindBy(css = ".action__submit")
	WebElement submitButton;
	@FindBy(css = ".hero-primary")
	WebElement confirmMessage;

	public void submitOrder() {
		submitButton.click();
		String message = confirmMessage.getText();
		System.out.println(message);

	}

}
