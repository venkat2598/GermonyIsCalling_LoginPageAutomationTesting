package listeners;

import org.testng.ITestListener;

import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base.BaseTest;

public class TestListener  extends BaseTest implements ITestListener {

	ExtentTest test;
	ExtentSparkReporter reporter;
	ExtentReports extent;

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getName());
		String Timestamp=new SimpleDateFormat("yyyy-MM-dd  HH-mm-ss").format(new Date()); //timestamp
		String repName="Test-Report-"+Timestamp+".html";
		reporter=new ExtentSparkReporter("./GermonyIsCalling_Assessment"+repName); //specify the location to store the extent reports
		extent= new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("HostName","localhost");
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("User","K.K.Venkatachalapathy");
		reporter.config().setReportName("Login Page Automation Testing "); //name  of the report
		reporter.config().setDocumentTitle("GermonyIs Calling Website Login Page Test Cases Report"); //Title of the report
		reporter.config().setTheme(Theme.DARK);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName()); //Create a new entry in the report
		test.log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Test Failed");
		//Add Screenshot and attach to report

		String ScreenshotPath = "./Screenshots/"+result.getName()+".png";
		test.addScreenCaptureFromPath(ScreenshotPath);
		captureScreenShot();

	}

	public void onTestSkipped(ITestResult result) {

		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test skipped");
	}

	@Override
	public void onFinish(ITestContext context) {

		extent.flush();
	}

}
