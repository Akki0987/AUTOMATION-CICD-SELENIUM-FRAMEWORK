package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import pages.Home_Page;

public class BaseTest {
	
	protected Home_Page hp;
	 public WebDriver driver;
	 public WebDriverWait wait;
	    
	    public WebDriver setup() throws IOException {
	       
	    	Properties prop = new Properties();
	    	FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\GlobalData.properties");
	    	prop.load(fis);
	    	
	    	String browser = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
	    	
	  
	    	
	    	if(browser.equalsIgnoreCase("chrome"))
	    	{
//	    		Chrome code 
	    		ChromeOptions options = new ChromeOptions();
	    		
	    		if(System.getProperty("mode").equalsIgnoreCase("headless"))
	    		{
	    			options.addArguments("--headless");
	    		}
	    		// ChromeDriver path
		        System.setProperty("webdriver.chrome.driver",
		        		System.getProperty("user.dir")+"\\Driver\\chromedriver.exe");

		        driver = new ChromeDriver(options);
		        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    	}
	    	else if(browser.equalsIgnoreCase("Edge"))
	    	{
//	    		Edge Code
	    		EdgeOptions options = new EdgeOptions();

		        // EdgeDriver path
		        System.setProperty("webdriver.edge.driver",
		        		System.getProperty("user.dir")+"\\Driver\\msedgedriver.exe");

		        driver = new EdgeDriver(options);
		        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	}
	    	else if(browser.equalsIgnoreCase("brave"))
	    	{
//	    		Brave code 
	    		ChromeOptions options = new ChromeOptions();
		        // Brave browser binary path
		        options.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
	    		
	    		if(System.getProperty("mode").equalsIgnoreCase("headless"))
	    		{
	    			options.addArguments("--headless");
	    		}
		        // ChromeDriver path
		        System.setProperty("webdriver.chrome.driver",
		        		System.getProperty("user.dir")+"\\Driver\\chromedriver.exe");

		        driver = new ChromeDriver(options);
		        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
	    	}
	    	else
	    	{
	    		System.out.println("Wrong browser configuration, Please send a valid browser to execute tests");
	    	}
	    	
	    	driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	        
	        return driver;
	    }
	    
	    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	    {
	    	TakesScreenshot ss = (TakesScreenshot)driver;
	    	File screenshot = ss.getScreenshotAs(OutputType.FILE);
	    	FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir")+"//reports//"+ testCaseName + ".png"));
	    	
	    	return System.getProperty("user.dir")+"//reports//"+ testCaseName + ".png";
	    }

	    @BeforeMethod(alwaysRun=true)
	    public Home_Page launchApplication() throws IOException
	    {
	    	driver = setup();
	    	hp = new Home_Page(driver);
	        hp.goToLandingPage();
	        return hp;
	    }

	    @AfterMethod(alwaysRun=true)
	    public void closeUp() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
}
