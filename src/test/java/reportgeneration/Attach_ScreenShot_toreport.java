package reportgeneration;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class Attach_ScreenShot_toreport {
	public static String reportfilename;
	
	static WebDriver driver;

	public static void main(String[] args) throws Exception {
		File reporterDir = new File(System.getProperty("user.dir"), "reports");
		//reporterDir.mkdir();
if (reporterDir.isDirectory()) {
            
        }
        else {
        	reporterDir.mkdir();
        }
		
String fileSuffix = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date());
		
		fileSuffix = fileSuffix.replace(" ", "_");
		fileSuffix = fileSuffix.replace(":", "_");
		 reportfilename = reporterDir+"\\Testguna_"+fileSuffix;
		setReportfilename(reportfilename);
		String filename = reportfilename +"\\TestReport.html";
		File file = new File(filename);
		if (file.isDirectory()) {
            
        }
        else {
        	file.mkdir();
        }
		
		
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		
		ExtentReports extentsReports = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter(filename);
		
		extentsReports.attachReporter(spark);
		
		
		String base64code = captureScreenShot();
		String path= captureScreenShot(driver);
		
		
		
		//fail,skip,warning,pass,info
		
		extentsReports.createTest("Screenshot testing Testcase 1","This is attaching for screenshot 1")
		.info("This is a info msg ")
		.addScreenCaptureFromBase64String(base64code);
		
		
		
		extentsReports.createTest("Screenshot Test 2","This is attaching for screenshot 2")
		.info("This is a info msg 2")
		.addScreenCaptureFromBase64String(base64code,"Google homepage");
		
		extentsReports.createTest("Screenshot Test 3","This is attaching for screenshot 3")
		.info("This is a info msg ")
		.addScreenCaptureFromPath(path);
		
		extentsReports.createTest("Screenshot Test 4","This is attaching for screenshot 4")
		.info("This is a info msg ")
		.addScreenCaptureFromPath(path,"google homepage");
		
		extentsReports.createTest("Screenshot Test 5","This is attaching for screenshot 5")
		.log(Status.PASS, "info pass", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		
		
		Thread.sleep(5000);
		driver.get("https://www.amazon.com/");
		Thread.sleep(4000);
		
		String path1= captureScreenShot(driver);
		extentsReports.createTest("Screenshot Test 6","This is attaching for screenshot 6")
		.pass("pass at log level", MediaEntityBuilder.createScreenCaptureFromPath(path1).build())
		.log(Status.INFO, "info pass", MediaEntityBuilder.createScreenCaptureFromPath(path1).build());
		
		extentsReports.createTest("Screenshot Test 7","This is attaching for screenshot 7")
		.log(Status.INFO, "info pass", MediaEntityBuilder.createScreenCaptureFromPath(path).build())
		.addScreenCaptureFromPath(path)
		.addScreenCaptureFromPath(path)
		.pass("chacking for another screenshot", MediaEntityBuilder.createScreenCaptureFromPath(path1).build())
		.pass(MediaEntityBuilder.createScreenCaptureFromBase64String(base64code, "base 64").build())
		.addScreenCaptureFromBase64String(base64code);
		
		driver.get("https://www.flipkart.com/");
		Thread.sleep(4000);
		String path2= captureScreenShot(driver);
		extentsReports.createTest("Screenshot Test 8","This is attaching for screenshot 8")
		.pass("pass at log level", MediaEntityBuilder.createScreenCaptureFromPath(path2).build())
		.log(Status.PASS, "info pass", MediaEntityBuilder.createScreenCaptureFromPath(path1).build());

		extentsReports.flush();
		driver.close();
		
		Desktop.getDesktop().browse(new File(filename).toURI());
}

		
		
public static String getReportfilename() {
	return reportfilename;
}

public static void setReportfilename(String reportfilename) {
	Attach_ScreenShot_toreport.reportfilename = reportfilename;
}


public static String captureScreenShot() {
	TakesScreenshot ts=(TakesScreenshot)driver;
	String base64code = ts.getScreenshotAs(OutputType.BASE64);
	System.out.println("screenshot saved succesfully");
	//File destFile = new File("./Screenshots/img1.jpg");
	return base64code;
}



public static String captureScreenShot(WebDriver driver) throws Exception {
//	TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
//	File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
//	File destFile = new File("./Screenshots/"+ filename);
//	try {
//		FileUtils.copyFile(sourceFile, destFile);
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	System.out.println("Screenshot saved successfully");
//	return destFile.getAbsolutePath();
	
	
	TakesScreenshot screenshot = (TakesScreenshot) driver;
	File  SourceFile = screenshot.getScreenshotAs(OutputType.FILE);
	
	File file = new File(getReportfilename(), "Screenshots");
	if (file.isDirectory()) {
        
    }
    else {
    	file.mkdir();
    }
	
//	System.out.println(file);
	String fileSuffix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	String filename = file+ "\\sample_"+ fileSuffix + ".png";
//	System.out.println(filename);
	File DestinationFile = new File(filename);
	FileHandler.copy(SourceFile, DestinationFile);
	
	System.out.println("dest file path "+DestinationFile.toString());
	
	String[] relatvePath = DestinationFile.toString().split("Screenshots");
	
	System.out.println("relative path "+relatvePath.toString());
	String screenshotPath = "./Screenshots" + relatvePath[1];
	
	System.out.println("ss path "+screenshotPath);

	return screenshotPath;
}

	
}


