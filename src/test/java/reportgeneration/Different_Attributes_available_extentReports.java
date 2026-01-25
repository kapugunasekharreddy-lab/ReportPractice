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
import com.aventstack.extentreports.reporter.configuration.Theme;


public class Different_Attributes_available_extentReports {
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
		
		
		
//		spark.config().setTheme(Theme.DARK);
//		spark.config().setReportName("Testing different Attributes");
//		spark.config().setDocumentTitle("Attributes Report");
//		spark.config().setTimeStampFormat("dd-MM-yyyy hh:mm:ss");
//		spark.config().setCss(".badge-primary{background-color:#65df72}");
//		spark.config().setJs("document.getElementsByClassName('logo')[0].style.display='none';");
		
		
		//spark.loadJSONConfig(new File("./src/test/resources/extent_report.json"));
		spark.loadXMLConfig(new File("./src/test/resources/extent_report.xml"));
		extentsReports.attachReporter(spark);
		
		//fail,skip,warning,pass,info
		
		extentsReports.createTest("Test 1")
		.assignAuthor("Guna")
		.assignCategory("smoke")
		.assignDevice("chrome")
		.pass("test pass");
		
		extentsReports.createTest("Test 2")
		.assignAuthor("Sekhar")
		.assignCategory("Regression")
		.assignDevice("edge")
		.fail("test fail");
		
		
		

		extentsReports.flush();
		
		Desktop.getDesktop().browse(new File(filename).toURI());
}

		
		
public static String getReportfilename() {
	return reportfilename;
}

public static void setReportfilename(String reportfilename) {
	Different_Attributes_available_extentReports.reportfilename = reportfilename;
}

	
}


