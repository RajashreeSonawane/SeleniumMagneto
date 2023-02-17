package MagnetoTest;

import org.testng.annotations.Test;

import com.excel.Utility.Xls_Reader;
import com.utility.ExcelUtil;

import org.testng.Assert;

import java.io.FileReader;
import java.io.IOException;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class MagnetoApplicationTest {

	WebDriver driver;

	WebDriverWait wait;

	static Logger log = Logger.getLogger(MagnetoApplicationTest.class);

	public static Properties get_properties() throws Exception {

		FileReader reader = new FileReader("src\\test\\java\\ConfigProperties\\url.properties");
		Properties url = new Properties();
		try {
			url.load(reader);
		} catch (IOException e) {

			log.error("Ops!", e);

		}
		return url;

	}

	/**
	 * Code is Functional testing application
	 * 
	 * @param browser (Code is executed in cross browser like chrome,edge and
	 *                firefox)
	 * @return
	 * @throws Exception
	 */
	@Parameters("browser")
	@BeforeTest
	public void browser(String browser) throws Exception {
		PropertyConfigurator.configure("src\\main\\resources\\log4j.properties");
		Driver d = new Driver(browser);
		driver = d.setDriver();
		driver.manage().window().maximize();
		driver.get(get_properties().getProperty("url"));
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		log.info("Open the Magneto software Testing Url");

	}

	/**
	 * @param chooseProduct
	 * @param mouse         Hover(perform actions for women,tops and tees)
	 * @param tees
	 * @return
	 * @throws InterruptedException
	 */
	@Test(priority = 0)
	public void chooseProduct() throws InterruptedException {

		Actions act = new Actions(driver);

		By womenBy = By.xpath("//li[@class='level0 nav-2 category-item level-top parent ui-menu-item']/a");
		wait.until(ExpectedConditions.visibilityOfElementLocated(womenBy));
		WebElement women = driver.findElement(womenBy);
		act.moveToElement(women).build().perform();
		log.info("Move to Women");

		By topsBy = By.xpath("//*[@id='ui-id-9']/span[2]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(topsBy));
		WebElement tops = driver.findElement(topsBy);
		act.moveToElement(tops).perform();
		log.info("move to Tops");

		By teesBy = By.xpath("//*[@id='ui-id-13']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(teesBy)).click();
		log.info("move to tees and clicked tees product");
		log.info("choose Women Top,Tees product and clicked on product");

	}

	/**
	 * @param selectProduct
	 * @param Minerva       LumaTech™ V-Tee
	 * @return
	 */

	@Test(priority = 1)
	public void selectProduct() {
		By selectproductBy = By.xpath("//ol[@class='products list items product-items']/li[7]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(selectproductBy)).click();
		log.info("clicked on Minerva LumaTech™ V-Tee product");
	}

	/**
	 * @param Add        to cart selected product
	 * @param size,color and quantity
	 * @return
	 */
	@Test(priority = 2)
	public void AddToCart() {

		By sizeBy = By.xpath("//div[@aria-label='XS']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(sizeBy)).click();
		log.info("click on 'size' and select size");

		By colorBy = By.xpath("//div[@aria-label='Red']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(colorBy)).click();
		log.info("click on 'color' and select color");

		By AddedCartBy = By.xpath("//button[@class='action primary tocart']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddedCartBy)).click();
		log.info("clicked on Add to cart button");

		By msg = By.xpath("//div[@role='alert']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(msg));

	}

	/**
	 * @param show    selected product
	 * @param Add     to cart
	 * @param proceed to checkout
	 * @return
	 */
	@Test(priority = 3)
	public void Shippingcart() {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

		By showcartBy = By.xpath("//a[@class='action showcart']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(showcartBy)).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		log.info("click on show cart button");

		By checkoutBy = By.xpath("//button[@id='top-cart-btn-checkout']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutBy)).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		log.info("click on proceed to checkout");

	}

	/**
	 * @param filling    shipping data
	 * @param data(Email id,first name,last name,company,street address,city)
	 * @param data       (state,post code,country,telephone number and shipping
	 *                   methods)
	 * @return
	 * @throws Exception
	 */
	@DataProvider
	public Iterator<Object[]> getTestData() {
		ArrayList<Object[]> testData = ExcelUtil.getDataFromExcel();
		return testData.iterator();
	}

	@Test(dataProvider = "getTestData", priority = 4)
	public void FillShippingData(String email, String firstname, String lastname, String company, String street_address,
			String city, String postal_code, String phone) throws Exception {
		
		By emailBy = By.xpath("//fieldset[@id='customer-email-fieldset']//input[@type='email']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(emailBy)).sendKeys(email);
		log.info("Enter Email id");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

		By firstnameBy = By.xpath("//input[@name='firstname']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(firstnameBy)).sendKeys(firstname);
		log.info("enter FirstName");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

		By lastnameBy = By.xpath("//input[@name='lastname']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(lastnameBy)).sendKeys(lastname);
		log.info("enter lastname");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

		By companyBy = By.xpath("//input[@name='company']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(companyBy)).sendKeys(company);
		log.info("enter company name");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

		By streetBy = By.xpath("//input[@name='street[0]']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(streetBy)).sendKeys(street_address);
		log.info("enter street address");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

		By cityBy = By.xpath("//input[@name='city']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(cityBy)).sendKeys(city);
		log.info("enter city");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

		By postcodeBy = By.xpath("//input[@name='postcode']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(postcodeBy)).sendKeys(postal_code);
		log.info("enter postcode");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

		By countryBy = By.xpath("//select[@name='country_id']//option[@data-title='India']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(countryBy)).click();
		log.info("click on country");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

		By regionBy = By.xpath("//select[@name='region_id']//option[@data-title='Andhra Pradesh']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(regionBy)).click();
		log.info("click on state");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

		By phoneBy = By.xpath("//input[@name='telephone']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(phoneBy)).sendKeys(phone);
		log.info("enter mobile number");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

		By keymethodBy = By.xpath("//*[@id='label_carrier_flatrate_flatrate']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(keymethodBy)).click();
		log.info("click on shipping methods");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

		By nextbuttonBy = By.xpath("//button[@class='button action continue primary']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(nextbuttonBy)).click();
		log.info("click on next button");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

	}

	/**
	 * @param Verify                         order summary
	 * @param product_name
	 * @param product_quantity,product_color ,product_size and product_price
	 * @return
	 */

	@Test(priority = 5)
	public void VerifyOrderSummary() {
		WebElement ordersummary = driver.findElement(By.xpath("//div[@class='opc-block-summary']"));
		wait.until(ExpectedConditions.visibilityOf(ordersummary));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		By productdetailBy = By.xpath("//div[@class='block items-in-cart']/div");
		wait.until(ExpectedConditions.visibilityOfElementLocated(productdetailBy));
		WebElement product = driver.findElement(productdetailBy);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", product);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

		By nameBy = By.xpath("//div[@class='product-item-name-block']/strong[@class='product-item-name']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(nameBy));
		WebElement Product_name = driver.findElement(nameBy);
		String ExpectedText = "Minerva LumaTech™ V-Tee";
		log.info(Product_name.getText());
		Assert.assertEquals(ExpectedText, Product_name.getText());
		log.info("Product_name text is a expected – Assert passed");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

		By quantityBy = By.xpath("//div[@class='product-item-name-block']/div/span[2]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(quantityBy));
		WebElement Product_Quantity = driver.findElement(quantityBy);
		String ExpectedText1 = "1";
		Assert.assertEquals(ExpectedText1, Product_Quantity.getText());
		log.info(" Product_Quantity text is a expected – Assert passed");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

		By priceBy = By.xpath("//span[@class='cart-price']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(priceBy));
		WebElement Product_price = driver.findElement(priceBy);
		String ExpectedText2 = "$32.00";
		Assert.assertEquals(ExpectedText2, Product_price.getText());
		log.info("Product_price text is a expected – Assert passed");

	}

	/**
	 * automated all test cases and close the driver
	 */

	@AfterTest
	public void Close() {

		log.info("Broswer Close");
		driver.quit();

	}
}