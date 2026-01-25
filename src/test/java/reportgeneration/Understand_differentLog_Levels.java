package reportgeneration;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class Understand_differentLog_Levels {
	public static String reportfilename;
	

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
		 reportfilename = reporterDir+"\\Test_"+fileSuffix;
		//setReportfilename(reportfilename);
		String filename = reportfilename +"\\TestReport.html";
		File file = new File(filename);
		if (file.isDirectory()) {
            
        }
        else {
        	file.mkdir();
        }
		
		
		
		
		ExtentReports extentsReports = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter(filename);
		
		extentsReports.attachReporter(spark);
		
		//fail,skip,warning,pass,info
		
		extentsReports.createTest("Test 1")
		.log(Status.INFO, "Info 1")
		.log(Status.INFO, "Info 2")
		.log(Status.INFO, "Info 3")
		.log(Status.PASS, "pass")
		.log(Status.WARNING, "warning")
		.log(Status.PASS, "pass")
		
		.log(Status.SKIP, "skip");
		
		

		extentsReports.flush();
		
		Desktop.getDesktop().browse(new File(filename).toURI());
}

		
		
public static String getReportfilename() {
	return reportfilename;
}

public static void setReportfilename(String reportfilename) {
	Understand_differentLog_Levels.reportfilename = reportfilename;
}

	
}


