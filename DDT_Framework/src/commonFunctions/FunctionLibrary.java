package commonFunctions;

import org.openqa.selenium.By;
import org.testng.Reporter;

import constant.AppUtil;

public class FunctionLibrary extends AppUtil{

	public static boolean verifyLogin(String userName,String password){
		driver.findElement(By.xpath(config.getProperty("objUser"))).clear();
		driver.findElement(By.xpath(config.getProperty("objUser"))).sendKeys(userName);
		driver.findElement(By.xpath(config.getProperty("objPass"))).clear();
		driver.findElement(By.xpath(config.getProperty("objPass"))).sendKeys(password);
		driver.findElement(By.xpath(config.getProperty("objlogin"))).click();
		String expected = "dashboard";
		String actual = driver.getCurrentUrl();
		if (actual.contains(expected)) {
			Reporter.log("Login success",true);
			return true;
		}
		else {
			String error = driver.findElement(By.xpath("objmsg")).getText();

			Reporter.log(error,true);
			return false;
		}
		
	}
}
