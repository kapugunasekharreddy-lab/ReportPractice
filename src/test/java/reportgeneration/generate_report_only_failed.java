package reportgeneration;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class generate_report_only_failed {

	public static void main(String[] args) throws Exception {
		ExtentReports extentsReports = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("Alltests.html");
		
		ExtentSparkReporter sparkfail = new ExtentSparkReporter("failed.html");
		sparkfail.filter().statusFilter().as(new Status[] {Status.FAIL}).apply();
		
		ExtentSparkReporter sparkskipwarning = new ExtentSparkReporter("skipwarning.html");
		sparkskipwarning.filter().statusFilter().as(new Status[] {Status.SKIP,Status.WARNING}).apply();
		
		extentsReports.attachReporter(spark,sparkfail,sparkskipwarning);
		
		//fail,skip,warning,pass,info
		
		//shortcut pass-p, fail=f, skipped-s, w-warning ,esc-clear filters ,l=theme, down arrow-scroll down, up arrow- scrollup , t-test , c-tag, d-dashboard ,x-exception/bug
		
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
		
		extentsReports.createTest("Test 3")
		.assignAuthor("Guna")
		.assignCategory("smoke")
		.assignDevice("chrome")
		.skip("test skip");
		
		extentsReports.createTest("Test 4")
		.assignAuthor("Guna")
		.assignCategory("smoke")
		.assignDevice("chrome")
		.warning("test warning");
		

		extentsReports.flush();
		Desktop.getDesktop().browse(new File("Alltests.html").toURI());
		
		Desktop.getDesktop().browse(new File("failed.html").toURI());
		Desktop.getDesktop().browse(new File("skipwarning.html").toURI());

	}

}
