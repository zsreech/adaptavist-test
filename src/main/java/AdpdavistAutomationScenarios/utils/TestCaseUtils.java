package AdpdavistAutomationScenarios.utils;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AdpdavistAutomationScenarios.base.Base;
import net.bytebuddy.asm.Advice.This;


//This class is used to provide the utility functions needed to execute our test case and validation scenarios
public class TestCaseUtils extends Base {
	
	
	/*This function gets the List of URLs, navigates to each of them, then waits for the page to load and calls the screenshot function*/
	public static void navigatetoUrlAndTakeScreenShot(List<String> linkUrls)
	{
		Iterator<String> urlIterator = linkUrls.iterator();
		String url = "";
		while(urlIterator.hasNext())
		{
		   
			try {
			url = urlIterator.next();
			driver.get(url);
            wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
			TakeScreenshot.captureScreenshot(url);
			}
			catch(Exception e)
			{
				
			}
			
		}
	}
	
	/*This function gets the list of link webelemetns fetches the href of each of them and populates them into a list.
	 * Then it calls the function to perform the naviation and take screenshots */
	public static void navigateAndtakeScreenshot(List<WebElement> linkList)
	{
		Iterator<WebElement> itr = linkList.iterator();
		List<String> linkUrls = new ArrayList<String>();
		while(itr.hasNext())
		{
			WebElement linkElement = itr.next();
			linkUrls.add(linkElement.getAttribute("href"));
			
		}	
      navigatetoUrlAndTakeScreenShot(linkUrls);
	}

	/*This function is to validate if any of the link of the results contains the reference to the searchtext parameter passed*/
	public static int validateResultLinks(List<WebElement> allLinks,String searchText)
	{
		Iterator<WebElement> itr = allLinks.iterator();
		int count =0;
		while(itr.hasNext())
		{
			WebElement linkElement = itr.next();
			String linkText = linkElement.getText();
			if(!linkText.isEmpty() && linkText.equalsIgnoreCase(searchText))
			{
				count++;
			}
		}
	 return count;
	}
}
