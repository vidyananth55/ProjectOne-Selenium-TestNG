package StepDefinitions;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class Step_definition_1 {

WebDriver driver;
WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		
@Given("user is on website home page")
public void launch_website() 
{
	driver = new ChromeDriver();
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\VIDYANANTH M K\\Documents\\chromedriver-win64");
	driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
}


@When("^I search with (.*) and enter details$")
public void enter_details(String Country_name) throws InterruptedException 
{
	driver.findElement(By.cssSelector("input[type=\"text\"][id=\"autosuggest\"]")).sendKeys(Country_name);
	String retrieved_value = driver.findElement(By.cssSelector("input[type=\"text\"][id=\"autosuggest\"]")).getAttribute("placeholder");
	Country_name.equals(retrieved_value);
	driver.findElement(By.xpath("//label[text()='Round Trip']")).click();
	
	
	//driver.findElement(By.xpath("//*[@id='ctl00_mainContent_ddl_originStation1']/option[@value='MAA']")).click();
	//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='ctl00_mainContent_ddl_originStation1']")));
	//WebElement drpdn = driver.findElement(By.xpath("//select[@name='ctl00$mainContent$ddl_originStation1']"));
	WebElement drpdn = driver.findElement(By.xpath("//*[@id='ctl00_mainContent_ddl_originStation1']/option[@value='MAA']"));
	JavascriptExecutor j = (JavascriptExecutor)driver;
	j.executeScript("arguments[0].click()", drpdn);
	//Actions actions = new Actions(driver);
	//actions.moveToElement(drpdn);
	//actions.click().build().perform();
	//Select dropdown1 = new Select(drpdn);
	//dropdown1.selectByValue("MAA");
	//Select dropdown2 = new Select(driver.findElement(By.xpath("(//select[@name='ctl00$mainContent$ddl_destinationStation1'])[1]")));
	//dropdown2.selectByValue("DEL");                             
	if (driver.findElement(By.id("ctl00_mainContent_view_date2")).isEnabled())
	{
		System.out.println("Date is enabled");
	}
	else
	{
		System.out.println("not enabled");
	}
	
}

@Then("Handle alert")
public void alert_handling()
{
	driver.findElement(By.linkText("Special Assistance")).click();
	driver.findElement(By.xpath("//a[@class='popup-close2']")).click();
	driver.quit();
	
}

@Then("handle pop up and enter {int} members")
public void validate_entries(int passenger_count)
{
	driver.findElement(By.xpath("//label[text()='Multicity']")).click();
	//driver.switchTo().alert().accept();
	driver.findElement(By.cssSelector("#MultiCityModelAlert")).click();
	driver.findElement(By.cssSelector("#divpaxinfo")).click();
	int pass_count = Integer.parseInt(driver.findElement(By.cssSelector("#divpaxinfo")).getText().split(" ")[0]);
	System.out.println(pass_count);
	while(pass_count != passenger_count)	
		{
		driver.findElement(By.cssSelector("#hrefIncAdt")).click();
		pass_count = Integer.parseInt(driver.findElement(By.cssSelector("#spanAudlt")).getText());
	}
	driver.findElement(By.cssSelector("#btnclosepaxoption")).click();	
	driver.findElement(By.xpath("//*[@id='ctl00_mainContent_ddl_originStation2_CTXT']")).click();
}

@And("switch between handles")
public void switch_window()
{
	
	driver.findElement(By.xpath("//span[text()='Book Cab']")).click();
	Set<String> S1 = driver.getWindowHandles();
	Iterator<String> I1 = S1.iterator();
	I1.next();
	String child_win = I1.next();
	driver.switchTo().window(child_win);
	
	System.out.println(driver.getTitle());
	driver.switchTo().defaultContent();
	System.out.println(driver.getTitle());
	driver.quit();
}

	}


