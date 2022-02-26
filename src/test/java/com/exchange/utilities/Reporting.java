package com.exchange.utilities;

// This is the listener class used to generate extent reports
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
// adapter from testng TestListenerAdapter

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
public class Reporting extends TestListenerAdapter{

	
	public ExtentReports extent;
	public ExtentTest logger;
	public ExtentHtmlReporter htmlReporter;
	
	public void onStart(ITestContext testcontext) {
		String timestamp = 
				new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		String repName = "Test-Report-"+timestamp+".html";
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/"+repName);
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
		
		extent =  new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "rama");
		
		htmlReporter.config().setDocumentTitle("Exchange Test Project");
		htmlReporter.config().setReportName("Functional Test Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);		
	}
	
	public void onTestSuccess(ITestResult tr) {
		logger = extent.createTest(tr.getName());
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
	}
	
	public void onTestFailure(ITestResult tr) {
		logger = extent.createTest(tr.getName());
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		
		String screenShotPath = System.getProperty("user.dir") + "\\screenshots\\" + tr.getName()+".png";
		File file = new File(screenShotPath);
		
		if(!file.exists()) {
			try {
				logger.fail("Screen Shot is Below:"  + logger.addScreenCaptureFromPath(screenShotPath));
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}		
	}
	
	public void onTestSkipped(ITestResult tr) {
		logger = extent.createTest(tr.getName());
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}
	
}
