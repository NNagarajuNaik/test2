package constant;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AppUtil {
public static WebDriver driver;
public static Properties config;
@BeforeTest
public void setUp() throws Throwable{
	config = new Properties();
	config.load(new FileInputStream("D:\\Framework\\DDT_Framework\\PropertyFiles\\OrangeHRM.properties"));
	if (config.getProperty("Browser").equalsIgnoreCase("chrome")) {
		System.setProperty("webdriver.chrome.driver", "D://chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(config.getProperty("url"));
	}
	else if (config.getProperty("Browser").equalsIgnoreCase("firefox")){
		
		System.setProperty("webdriver.firefox.driver", "D://geckodriver.exe");
		driver = new FirefoxDriver();
	}
	else{
		System.out.println("select a browser");
	}
}
@AfterTest
public void tearDown(){
	driver.close();
}
}
