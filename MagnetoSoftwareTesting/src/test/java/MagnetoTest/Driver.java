package MagnetoTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {
	
	WebDriver driver;
	String browser;
	public Driver(String browser)
	{
		this.browser=browser;
	}
	public WebDriver setDriver() 
	{
		if(browser.equalsIgnoreCase("firefox"))
		{
			System.out.println("Executing on FireFox");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			}
		else if (browser.equalsIgnoreCase("chrome")) 
		{System.out.println("Executing on CHROME");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();}
		else if (browser.equalsIgnoreCase("ie")) 
		{
			System.out.println("Executing on IE");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			}
		return driver;
			}

}
