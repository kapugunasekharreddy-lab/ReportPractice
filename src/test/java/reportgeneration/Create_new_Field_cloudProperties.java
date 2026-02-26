package reportgeneration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;

public class Create_new_Field_cloudProperties {

	public static void main(String[] args) throws Exception {
		Properties cp=new Properties();
		
		FileInputStream prop=new FileInputStream("cloud.properties");
		
		cp.load(prop);
		prop.close();
		
		cp.setProperty("MyName", "Gunasekhar");
		cp.setProperty("My"+"first"+"company", "Cognizant");
		
		FileOutputStream out = new FileOutputStream("cloud.properties");
		
		cp.store(out, "updated cloud.properties");
		
		out.close();
		
		System.out.println("updates succesfully");

	}

}
