package TestComponents;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import PageObjects.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseTest {
	
	public WebDriver driver;
	protected HomePage homepage;
	
	public WebDriver Browser_setup() {
		
		
		//WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\VIDYANANTH M K\\Documents\\chromedriver-win64");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		return driver;
	}
	
	@BeforeClass
	public void Launch_website() {
		
		driver = Browser_setup();
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");	
		homepage = new HomePage(driver);
		
	}
	
	//public HomePage hp() {
	
	//return homepage;
	
	//}
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		
		
	}

	
	@AfterClass
	public void quit_browser() {
		driver.close();
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json to string
	String jsonContent = 	FileUtils.readFileToString(new File(filePath), 
			StandardCharsets.UTF_8);
	
	//String to HashMap- Jackson Databind
	
	ObjectMapper mapper = new ObjectMapper();
	  List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
      });
	  return data;

	}
	
}
