package PageObjects;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;
import TestComponents.BaseTest;

public class HomePage extends BaseTest {
	WebDriver driver;
	AbstractComponent abt = new AbstractComponent();
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input[type=\"text\"][id=\"autosuggest\"]")
	WebElement Autosuggest;
	
	@FindBy(xpath="//label[text()='Round Trip']")
	WebElement Roundtrip;
	
	@FindBy(xpath="//*[@id='ctl00_mainContent_ddl_originStation1']/option[@value='MAA']")
	WebElement drpdn;
	
	@FindBy(xpath="//a[@class='popup-close2']")
	WebElement popup;
	
	@FindBy(xpath="//span[text()='Book Cab']")
	WebElement BookCab;
	
	public String select_country(String Country_name) {
	
	driver.navigate().refresh();
	Autosuggest.sendKeys(Country_name);
	
	
	By emt = (By.xpath("//a[contains(@id, 'ui-id')]"));
	abt.element_present(driver, emt);
	List<WebElement> countries = driver.findElements(emt);
	System.out.println(countries.stream()
	       .map(c->c.getText()).collect(Collectors.toList()));
	String retrieved_value = countries.stream()
	       .map(c->c.getText()) // Extract text from WebElements
	       .filter(text -> text.equalsIgnoreCase(Country_name)) // Filter by expected value
	       .findFirst() // Find the first match
	       .orElse(null); // Return null if no match is found

	//String retrieved_value = driver.findElement(By.cssSelector("input[type=\"text\"][id=\"autosuggest\"]")).getAttribute("value");
	return retrieved_value;
}
	
	public boolean Date_is_displayed() {
		
		Roundtrip.click();
		JavascriptExecutor j = (JavascriptExecutor)driver;
		j.executeScript("arguments[0].click()", drpdn);
		                           
		if (driver.findElement(By.id("ctl00_mainContent_view_date2")).isEnabled())
		{
			System.out.println("Date is enabled");
			return true;
		}
		else
		{
			System.out.println("not enabled");
			return false;
		
		}
}
	
	public void alert_click() {
		driver.findElement(By.linkText("Special Assistance")).click();
		popup.click();
		driver.findElement(By.xpath("//label[text()='Multicity']")).click();
		//driver.switchTo().alert().accept();
		driver.findElement(By.cssSelector("#MultiCityModelAlert")).click();
	}
	
	public void entries(int passenger_count) {
		
		
		By info = By.cssSelector("#divpaxinfo");
		abt.element_clickable(driver, info);
		driver.findElement(info).click();
		int pass_count = Integer.parseInt(driver.findElement(By.cssSelector("#divpaxinfo")).getText().split(" ")[0]);
		//System.out.println(pass_count);
		while(pass_count < passenger_count)	
			{
			driver.findElement(By.cssSelector("#hrefIncAdt")).click();
			pass_count = Integer.parseInt(driver.findElement(By.cssSelector("#spanAudlt")).getText());
		}
		while(pass_count > passenger_count)	
		{
		driver.findElement(By.cssSelector("#hrefDecAdt")).click();
		pass_count = Integer.parseInt(driver.findElement(By.cssSelector("#spanAudlt")).getText());
	}
		By close = By.cssSelector("#btnclosepaxoption");
		abt.element_clickable(driver, close);		
		
		
		driver.findElement(close).click();	
		
	}
	
	public String windowHandling(String win) {
		
		
		
		if (win.equalsIgnoreCase("child")){
			BookCab.click();
			Set<String> S1 = driver.getWindowHandles();
			Iterator<String> I1 = S1.iterator();
			String paren_win = I1.next();
			String child_win = I1.next();
			driver.switchTo().window(child_win);
			String title = driver.getTitle();
			driver.close();
			driver.switchTo().window(paren_win);		
			return title;
			
		}
		else {
			String title1 = driver.getTitle();
			return title1;
			
		}
		
		
	}
}
