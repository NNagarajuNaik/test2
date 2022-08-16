package driverFactory;

import java.util.concurrent.TimeUnit;

import org.testng.Reporter;
import org.testng.annotations.Test;

import commonFunctions.FunctionLibrary;
import constant.AppUtil;
import utilities.ExcelFileUtil;

public class Test_1 extends AppUtil{

	String inputpath = "D:\\Framework\\DDT_Framework\\TestInput\\NNNaik.xlsx";
	String outputpath = "D:\\Framework\\DDT_Framework\\TestOutput\\report_1.xlsx";
	@Test
	public void validatelogin() throws Throwable{
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		int rc = xl.rowCount("Login");
		int cc = xl.cellCount("Login");
		Reporter.log("Rows : "+rc+"\n"+"cells : "+cc);
		for (int i = 1; i<= rc; i++) {
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			String user = xl.getCellData("Login", i, 0);
			String pass = xl.getCellData("Login", i, 1);
			Thread.sleep(1000);
			boolean res =FunctionLibrary.verifyLogin(user, pass);
			if (res==true) {
				xl.setCellData("Login", i, 2, "Login fail", outputpath);
				xl.setCellData("Login", i, 3, "pass", outputpath);
			} 
			else {

				xl.setCellData("Login", i, 2, "Invalid detail", outputpath);
				xl.setCellData("Login", i, 3, "fail", outputpath);
			}
		}
	}
}
