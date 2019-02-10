package AdpdavistAutomationScenarios.base;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import AdpdavistAutomationScenarios.utils.WebEventListener;

//This is the base class of the project which handles all the necessary configuration and settings
public class Base {

	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Properties prop;
	public static EventFiringWebDriver eventFiring_driver;
	public static WebEventListener eventListener;
	public static Logger log = LogManager.getLogger(AdpdavistAutomationScenarios.base.Base.class);
	
	//This gets called before the execution of each of the test cases
	@BeforeMethod
	public void startMethod()
	{
		try {
		String projectPath = System.getProperty("user.dir");
		prop = new Properties();
		FileInputStream ip = new FileInputStream(projectPath+"/src/main/resources/config.properties");//Loading the config.properties file
		prop.load(ip);
		
		String browserName = prop.getProperty("browser");
		
		//Instantiating  the webdriver to appropriate driver based on the config file
		if(browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", projectPath+prop.getProperty("firefoxDriverPath"));
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("chrome"))
		{
			
			System.setProperty("webdriver.chrome.driver", projectPath+prop.getProperty("chromeDriverPath"));
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("dockerchrome"))
		{
			//Change the localhostURL depending on the running environment
			String localHostUrl = "http://localhost";
			ChromeOptions options = new ChromeOptions();
			driver = new RemoteWebDriver(new URL(localHostUrl+":4444/wd/hub"), options);
			
		}
		
		//Configuring the WebEventListener to the driver object
		eventFiring_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		eventFiring_driver.register(eventListener);
		driver = eventFiring_driver;
		
		//Setting the implicit timeouts
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		//Creating the wait object for handling explicit wait
		wait = new WebDriverWait(driver, 30);
		
		driver.manage().window().maximize();
		
		}
		catch(Exception e)
		{
			System.out.println("Error:"+e.getMessage());
		}
	}

	//This gets called after the execution of each of the test cases
	@AfterMethod
	public void endMethod()
	{
		
		driver.quit();
	}
	
}
