package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public static WebDriver driver;
	static FileInputStream inputStream;
	public	static Properties prop;

	public static Properties loadProperty()  {


		try {
			inputStream=new FileInputStream("./config.properties");
			prop=new Properties();
			prop.load(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	} 




	@BeforeSuite
	public static void setup() {

		loadProperty();

		String Browser=prop.getProperty("browser");
		String BaseUrl=prop.getProperty("Url");

		if(Browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "./Drivers/geckodriver.exe");
			driver=new FirefoxDriver();
		}
		else if(Browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}

		driver.get(BaseUrl);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	public static void captureScreenShot() {

		Date date=new Date();

		//Using both Timestamp  format will be work perfectly based on our needs pick any one.
		//String Filename=date.toString().replace(":", "-").replace(" ", "-") + ".png";
		String Timestamp=new SimpleDateFormat("yyyy-MM-dd  HH-mm-ss").format(date);
		TakesScreenshot screenshot=(TakesScreenshot)driver;
		File source=screenshot.getScreenshotAs(OutputType.FILE);
		File Destination=new File("./Screenshots/"+ Timestamp+".png");
		try {
			FileHandler.copy(source, Destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Screenshot taken");

	}

	@AfterSuite
	public static void tearDown() {

		if(driver!=null) {
			driver.quit();
		}
	}





}
