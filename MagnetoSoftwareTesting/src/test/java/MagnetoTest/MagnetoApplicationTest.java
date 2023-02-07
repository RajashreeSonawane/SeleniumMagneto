package MagnetoTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



public class MagnetoApplicationTest {
public String Url ="https://magento.softwaretestingboard.com";  
//String driverpath ="C:\\Users\\rajashrees\\Desktop\\Seljars\\chromedriver_win32 (4)\\chromedriver.exe"; 
//String driverName = "webdriver.chrome.driver";  
public WebDriver driver; 

@Parameters("browser")
@BeforeTest  
public void browser(String browser)  
{	  
//System.setProperty(driverName,driverpath);  
//driver= new ChromeDriver();	
Driver d=new Driver(browser);
driver=d.setDriver();
driver.manage().window().maximize();     
driver.get(Url);  
driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);  
}	  

@Test(priority = 0)  
public void chooseProduct() throws InterruptedException 
{  
    Actions act = new Actions(driver);
    
	WebElement Women =driver.findElement(By.xpath("//*[@id=\"ui-id-4\"]/span[2]"));
	act.moveToElement(Women).perform();
	Thread.sleep(1000);
	WebElement Tops =driver.findElement(By.xpath("//*[@id=\"ui-id-9\"]/span[2]"));
	act.moveToElement(Tops).perform();
	Thread.sleep(1000);
	WebElement Tees =driver.findElement(By.xpath("//*[@id=\"ui-id-13\"]"));
	Tees.click();
	System.out.println("choose Women Top,Tees product and clicked");
	
}  

@Test(priority = 1)  
public void selectProduct() 
{  
   driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/ol/li[7]/div/a/span/span/img")).click();
}  

@Test(priority = 2)  
public void AddToCart() throws InterruptedException 
{  
   
	driver.findElement(By.xpath("//*[@id=\"option-label-size-143-item-166\"]")).click(); //select size
	driver.findElement(By.xpath("//*[@id=\"option-label-color-93-item-58\"]")).click(); //select color
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[@id=\"product-addtocart-button\"]/span")).click(); //click on add to cart button
	boolean addedcart= driver.findElement(By.xpath("//*[@id=\"product-addtocart-button\"]/span")).isDisplayed();
    Assert.assertTrue(addedcart);
    
} 

@Test(priority = 3)  
public void Shippingcart() throws InterruptedException
{  
  Thread.sleep(2000); 
  driver.findElement(By.xpath("//header/div[2]/div[1]/a[1]")).click(); //click on shipping cart
  Thread.sleep(2000);
  driver.findElement(By.xpath("//button[@title='Proceed to Checkout']")).click(); //click on proceed to checkout
} 

@Test(priority = 4)  
public void FillShippingData() throws InterruptedException
{  
  Thread.sleep(1000); 
  driver.findElement(By.xpath("//body/div[2]/main[1]/div[2]/div[1]/div[2]/div[4]/ol[1]/li[1]/div[2]/form[1]/fieldset[1]/div[1]/div[1]/input[1]")).sendKeys("Rajashree.sonawane@gmail.com");
  Thread.sleep(1000); 
  driver.findElement(By.xpath("//input[@name=\"firstname\"]")).sendKeys("Rajashree");
  Thread.sleep(1000); 
  driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Sonawane");
  Thread.sleep(1000); 
  driver.findElement(By.xpath("//input[@name='company']")).sendKeys("Excellarate");
  Thread.sleep(1000); 
  driver.findElement(By.xpath("//input[@name='street[0]']")).sendKeys("Handewadi road");
  Thread.sleep(1000); 
  driver.findElement(By.xpath("//input[@name='city']")).sendKeys("Pune");
  Thread.sleep(2000);
  driver.findElement(By.xpath("/html[1]/body[1]/div[2]/main[1]/div[2]/div[1]/div[2]/div[4]/ol[1]/li[1]/div[2]/form[2]/div[1]/div[5]/div[1]/select[1]")).click();
  Thread.sleep(2000); 
  driver.findElement(By.xpath("//*[@data-title=\'Alabama\']")).click();
  Thread.sleep(1000);
  driver.findElement(By.xpath("//input[@name='postcode']")).sendKeys("24365-1587");
  Thread.sleep(1000); 
   driver.findElement(By.xpath("/html[1]/body[1]/div[2]/main[1]/div[2]/div[1]/div[2]/div[4]/ol[1]/li[1]/div[2]/form[2]/div[1]/div[8]/div[1]/select[1]")).click();
   Thread.sleep(1000);
   driver.findElement(By.xpath("//*[@data-title=\'Afghanistan\']")).click();
   Thread.sleep(1000);
  driver.findElement(By.xpath("//input[@name='telephone']")).sendKeys("9657344065");
  Thread.sleep(1000);
  driver.findElement(By.xpath("//input[@name='ko_unique_5']")).click();
  Thread.sleep(1000);
  driver.findElement(By.xpath("//*[@id=\"shipping-method-buttons-container\"]/div/button")).click();
  
} 

@Test(priority = 5)  
public void VerifyOrderSummary() throws InterruptedException
{  
  Thread.sleep(5000); 
  driver.findElement(By.xpath("//*[@id='opc-sidebar']/div[1]/div/div[1]")).click();
  WebElement Product_name =driver.findElement(By.xpath("//strong[contains(text(),'Minerva LumaTech™ V-Tee')]"));
  String ExpectedText = "Minerva LumaTech™ V-Tee";
  System.out.println(Product_name.getText());
  Assert.assertEquals(ExpectedText, Product_name.getText());
  System.out.println("Product_name text is a expected – Assert passed");
  
  WebElement Product_Quantity =driver.findElement(By.xpath("//div[@class='product-item-name-block']/div/span[2]"));
  String ExpectedText1 = "1";
  System.out.println(Product_Quantity.getText());
  Assert.assertEquals(ExpectedText1, Product_Quantity.getText());
  System.out.println(" Product_Quantity text is a expected – Assert passed");
  
  WebElement Product_price =driver.findElement(By.xpath("//span[contains(text(),'$32.00')]"));
  String ExpectedText2 = "$32.00";
  System.out.println(Product_price.getText());
  Assert.assertEquals(ExpectedText2, Product_price.getText());
  System.out.println("Product_price text is a expected – Assert passed");
  
  driver.findElement(By.xpath("//*[@id=\"opc-sidebar\"]/div[1]/div/div[2]/div/ol/li/div/div/div[2]/span/span")).click();
  
  		  
  
  	WebElement Product_size =driver.findElement(By.xpath("//dd[contains(text(),'XS')]"));
  	String ExpectedText3 = "XS";
  	System.out.println(Product_size.getText());
  	Assert.assertEquals(ExpectedText3, Product_size.getText());
  System.out.println(" Product_size text is a expected – Assert passed");
  		  
  
  WebElement Product_color =driver.findElement(By.xpath("//dd[contains(text(),'Red')]"));
  String ExpectedText4 = "Red";
  System.out.println(Product_color.getText());
  Assert.assertEquals(ExpectedText4, Product_color.getText());
  System.out.println("Product_color text is a expected – Assert passed");
  		  
  		  		  
  
} 

@AfterTest  
public void Close() 
{  
driver.quit();  
}  
}