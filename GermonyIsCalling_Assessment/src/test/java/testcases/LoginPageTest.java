package testcases;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utilities.ExcelUtils;
import base.BaseTest;

public class LoginPageTest extends BaseTest {

	@DataProvider(name="Login Data")
	public String[][] dataProviderMethod() {

		String [][] exceldata = ExcelUtils.readDataFromExcel();
		return exceldata;
	}
	
	@Test(dataProvider = "Login Data")
	public static void loginTest(String Username, String Password) {

		WebElement EmailField = driver.findElement(By.id("username"));
		EmailField.sendKeys(Username);

		WebElement PasswordField = driver.findElement(By.id("password"));
		PasswordField.sendKeys(Password);

		WebElement Loginbtn = driver.findElement(By.xpath("//button[@type='submit']"));
		Loginbtn.click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		try {
	        // If login successful, validate with expected page title
	        String titleofthepage = driver.getTitle();
	        String expectedTitle = "Upload your CV | Germany Is Calling";
	        Assert.assertEquals(titleofthepage, expectedTitle, "Login successful and test case passed");
	        
	    } catch (AssertionError e) {
	        // Handle invalid login scenario
	        WebElement errorMsg = driver.findElement(By.xpath("//div[@class='alert alert-danger']"));
	        String actualErrorMsg = errorMsg.getText();
	        String expectedErrorMsg = "Please enter a correct username and password. Note that both fields may be case-sensitive.";
	        
	        // Assert if the error message is as expected
	        Assert.assertTrue(actualErrorMsg.equalsIgnoreCase(expectedErrorMsg), "Invalid login detected, but error message doesn't match.");
	       captureScreenShot();
	    }
	}

}
