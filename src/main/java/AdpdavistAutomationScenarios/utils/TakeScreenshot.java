package AdpdavistAutomationScenarios.utils;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.io.FileUtils;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


import AdpdavistAutomationScenarios.base.Base;

//This class is used to capture the screenshot of the webdriver
public class TakeScreenshot extends Base {
	
	
	public static void captureScreenshot(String url)
	{
	
		try
		{
		Date date = new Date();
		Timestamp stamp = new Timestamp(date.getTime());
		url= url.replace("https://","").replace("/","_");//Trimming the URL to make it part of the fileName
		String fileName = url+"_"+stamp.toString();//Adding the timestamp to the screenshot filename.
		
	
		TakesScreenshot t = (TakesScreenshot)driver;
		File source = t.getScreenshotAs(OutputType.FILE);
		//Setting the location for the screenshots
		FileUtils.copyFile(source, new File(prop.getProperty("ScreenShotPath")+"/"+fileName+".png"));
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}

}
