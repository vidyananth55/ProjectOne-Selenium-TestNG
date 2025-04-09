package Tests;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import TestComponents.BaseTest;



public class Regression extends BaseTest {
	
	
	//HomePage homepage = hp();
	
	
	
	@Test(priority=2)
	public void validate_date() {
		
		Assert.assertTrue(homepage.Date_is_displayed());  
				
	}

	@Test(priority=3)
	public void alert_handling()
	{
		homepage.alert_click();
				
	}
	
	

	@Test(priority=5)
	public void switch_window()
	{
		
		System.out.println(homepage.windowHandling("child"));
		System.out.println(homepage.windowHandling("parent"));
		
	}
	
	
}
