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

public class DataValidation extends BaseTest{

	
	@Test(priority=1,dataProvider="getData")
	public void validate_country_name(HashMap<String,String> input) {
		
	
	String retrieved_value = homepage.select_country(input.get("Country"));
	Assert.assertEquals(input.get("Country"), retrieved_value);
		
	}
	
	@Test(priority=2,dataProvider="getData")
	public void add_entries(HashMap<String,String> input)
	{
		homepage.entries(Integer.parseInt(input.get("Tickets")));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{

		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//data//User_data.json");
		return new Object[][]  {{data.get(0)}, {data.get(1) }, {data.get(2)}};
		
		
		
		
	}
	
	
}
