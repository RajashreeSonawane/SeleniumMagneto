package MagnetoTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.apache.log4j.Logger;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {

	static Logger log = Logger.getLogger(Driver.class);
	WebDriver driver;
	String browser;

	public Driver(String browser) {
		this.browser = browser;
	}

	public WebDriver setDriver() {
		if (browser.equalsIgnoreCase("firefox")) {
			log.info("Executing on FireFox");
			FirefoxOptions options = new FirefoxOptions();
			options.setHeadless(true);
			driver = new FirefoxDriver(options);

		} else if (browser.equalsIgnoreCase("chrome")) {
			log.info("Executing on CHROME");
			//ChromeOptions options = new ChromeOptions();
			//options.addArguments("headless");
			//driver = new ChromeDriver(options);
			 WebDriverManager.chromedriver().setup();
			 driver = new ChromeDriver();

		} else if (browser.equalsIgnoreCase("ie")) {
			log.info("Executing on IE");
			EdgeOptions options = new EdgeOptions();
			options.setHeadless(true);
			driver = new EdgeDriver(options);
		}
		return driver;
	}

}
