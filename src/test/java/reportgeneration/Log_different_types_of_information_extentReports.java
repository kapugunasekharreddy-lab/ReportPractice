package reportgeneration;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class Log_different_types_of_information_extentReports {
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
		.log(Status.INFO, "<b><i>Info 2</i></b>")
		.log(Status.INFO, "<i>Info 3</i>");
		
	
		String jsondata= "{\"menu\": {\r\n"
				+ "  \"id\": \"file\",\r\n"
				+ "  \"value\": \"File\",\r\n"
				+ "  \"popup\": {\r\n"
				+ "    \"menuitem\": [\r\n"
				+ "      {\"value\": \"New\", \"onclick\": \"CreateNewDoc()\"},\r\n"
				+ "      {\"value\": \"Open\", \"onclick\": \"OpenDoc()\"},\r\n"
				+ "      {\"value\": \"Close\", \"onclick\": \"CloseDoc()\"}\r\n"
				+ "    ]\r\n"
				+ "  }\r\n"
				+ "}}";
		
		String xmldata= "<menu id=\"file\" value=\"File\">\r\n"
				+ "  <popup>\r\n"
				+ "    <menuitem value=\"New\" onclick=\"CreateNewDoc()\" />\r\n"
				+ "    <menuitem value=\"Open\" onclick=\"OpenDoc()\" />\r\n"
				+ "    <menuitem value=\"Close\" onclick=\"CloseDoc()\" />\r\n"
				+ "  </popup>\r\n"
				+ "</menu>";
		
		
		
		extentsReports.createTest("XML Test")
		.pass(MarkupHelper.createCodeBlock(xmldata, CodeLanguage.XML));
		
		extentsReports.createTest("JSON Test")
		.pass(MarkupHelper.createCodeBlock(jsondata, CodeLanguage.JSON));
		
		String collapsejson= "<details><summary>click to view JSON</summary><pre>"+jsondata+"</pre></details>";
		
		extentsReports.createTest("JSON Test")
		.pass(MarkupHelper.createLabel(collapsejson,null));
		
		
		List<String> languages = new ArrayList<>();
	    languages.add("Java");
	    languages.add("Python");
	    languages.add("Swift");
	    
	    Set<String> set1 = new LinkedHashSet<>();
        set1.add("guna");
        set1.add("sekhar");
        set1.add("reddy");
        
        Map<String, Integer> numbers = new LinkedHashMap<>();
        numbers.put("One", 1);
        numbers.put("Two", 2);
        numbers.put("Three", 3);
        
        extentsReports.createTest("List Test")
		.pass(MarkupHelper.createOrderedList(languages))
		.pass(MarkupHelper.createUnorderedList(languages));
        
        extentsReports.createTest("Set Test")
		.pass(MarkupHelper.createOrderedList(set1))
		.pass(MarkupHelper.createUnorderedList(set1));
        
        extentsReports.createTest("Map Test")
		.pass(MarkupHelper.createOrderedList(numbers))
		.pass(MarkupHelper.createUnorderedList(numbers));
        
        extentsReports.createTest("Highlight Test")
        .info("not highlight")
		.pass(MarkupHelper.createLabel("Highlight text",ExtentColor.BLACK));
		
        
        try {
        	int a=5/0;
        }catch(Exception e) {
        	extentsReports.createTest("Exception Test")
            .fail(e);
        }
        
        Throwable t=new RuntimeException("Customized exception");
        extentsReports.createTest("Custom exception Test")
        .fail(t);
        extentsReports.createTest("Custom exception Test2")
        .fail(t);
        
        

		extentsReports.flush();
		
		Desktop.getDesktop().browse(new File(filename).toURI());
}

		
		
public static String getReportfilename() {
	return reportfilename;
}

public static void setReportfilename(String reportfilename) {
	Log_different_types_of_information_extentReports.reportfilename = reportfilename;
}

	
}


