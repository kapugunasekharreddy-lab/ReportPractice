package reportgeneration;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;



public class rsa {

	public static void main(String[] args) throws Exception {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("--remote-allow-origins=*");

		
		

		
		WebDriver driver = new ChromeDriver(options);
		
		//WebDriver driver = new EdgeDriver(options);
		
		driver.manage().window().maximize();
			
			Thread.sleep(3000);
			
			String parentwindow=driver.getWindowHandle();
//			((JavascriptExecutor)driver).executeScript("window.open()");
//			
//					  String currentHandle= driver.getWindowHandle();
//					  Set<String> handles=driver.getWindowHandles();
//					  for(String actual: handles)
//					  {
//			
//						  if(!actual.equalsIgnoreCase(currentHandle))
//						  {
//							  //switching to the opened tab
//							  driver.switchTo().window(actual);
//			
//							  //opening the URL saved.
//							  driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
//							  //report.setReport("Launch", "Launched Google.com successfully",Status.PASS);
//							 // driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
//						  }
//					  }
			
					  driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
					  driver.manage().deleteAllCookies();
					  
					  driver.findElement(By.xpath("//input[@type='search']")).sendKeys("Tom");
					  Thread.sleep(2000);
					  
					  if(driver.findElement(By.xpath("//h4[contains(text(),'Tomato')]")).isDisplayed()) {
						  //report.setReport("", "Tomato is displayed", Status.PASS);
						  System.out.println("showing tomato");
					  }
					  
					  driver.findElement(By.xpath("//a[text()='Top Deals']")).click();
					  Thread.sleep(2000);
					  
			
					  //((JavascriptExecutor)driver).executeScript("window.open()");
				        ArrayList<String> tab1 = new ArrayList<>(driver.getWindowHandles());
				        driver.switchTo().window(tab1.get(1));
				        Thread.sleep(3000);
				        
				        if(driver.findElement(By.xpath("//input[@type='search']")).isDisplayed()) {
				        	 driver.findElement(By.xpath("//input[@type='search']")).sendKeys("Tom");
							  Thread.sleep(2000);
							  
							  System.out.println("showing tomato in deals page");
						  } 
				        
				        if(driver.findElement(By.xpath("//table[@class='table table-bordered']//tbody/tr/td[text()='Tomato']")).isDisplayed()) {
				        	//report.setReport("", "Tomato is displayed", Status.PASS);
				        	System.out.println("showing tomato in table");
				        }
				        
				        driver.quit();
			
		}

	}


