package Tests;

import java.io.IOException;
import TestComponents.Retry;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import TestComponents.BaseTest;

public class ErrorValidation extends BaseTest {
	
	
	@Test(dataProvider = "Data", retryAnalyzer= Retry.class)
	public void validate_country_name(HashMap<String, String> input) {
	    String retrieved_value = homepage.select_country(input.get("Country"));
	    Assert.assertEquals(input.get("Country"), retrieved_value);
	    //Assert.assertTrue(false);
	}

	@DataProvider
	public Object[][] Data() throws IOException {
	    HashMap<String, String> valData1 = new HashMap<>();
	    valData1.put("Country", "Indian");
	    valData1.put("Tickets", "3");

	    HashMap<String, String> valData2 = new HashMap<>();
	    valData2.put("Country", "USA");
	    valData2.put("Tickets", "5");

	    return new Object[][] {{valData1}, {valData2}};
	}
	

}
