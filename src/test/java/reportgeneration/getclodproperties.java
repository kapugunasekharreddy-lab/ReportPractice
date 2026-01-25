package reportgeneration;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class getclodproperties {

	protected static String getProperty(String property, Properties props) {
		if (System.getProperty(property) != null) {
			return System.getProperty(property);
		} else if (System.getenv().containsKey(property)) {
			return System.getenv(property);
		} else if (props != null) {
			return props.getProperty(property);
		}
		return null;
	}
	public static void main(String[] args) throws Exception {
		Properties cp=new Properties();
		
		FileReader fr = new FileReader("cloud.properties");
		cp.load(fr);
		
		String adopurl =cp.getProperty("ADOproject");
		
		String ExecutionSheetName =cp.getProperty("ExecutionSheetName");
		
		String url = getProperty("url", cp);
		
		String chrome_version = getProperty("chrome_version", cp);
		
		System.out.println(adopurl);
		System.out.println(ExecutionSheetName);
		System.out.println(url);
		System.out.println(chrome_version);
		fr.close();
	}
	
	

}
