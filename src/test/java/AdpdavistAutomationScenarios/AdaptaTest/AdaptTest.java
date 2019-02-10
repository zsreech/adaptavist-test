package AdpdavistAutomationScenarios.AdaptaTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import AdpdavistAutomationScenarios.base.Base;
import AdpdavistAutomationScenarios.pages.GoogleHomePage;
import AdpdavistAutomationScenarios.pages.GoogleSearchResultPage;
import AdpdavistAutomationScenarios.utils.TestCaseUtils;

public class AdaptTest extends Base {


	//Creating the pageObjects to be used in the test case
	GoogleHomePage googlePageObj = new GoogleHomePage();
	GoogleSearchResultPage googleResultPageObj = new GoogleSearchResultPage();
	
	//This function does the common action of launching the google search page, and performing the search based on the search text
	public void doSearchAction(String searchtext)
	{
		googlePageObj.launchHomePage();
		if(googlePageObj.isPageDisplayed())
		{
			googlePageObj.enterSearchString(searchtext);
			googlePageObj.clickSearchBtn();
		}
	}
	
	
	//The test case to take screenshot of all the links in search results
	@Test
	public void testTakeLinkScreenshot()
	{
		
		doSearchAction("Adaptavist");
		List<WebElement> linkList = googleResultPageObj.returnSearchLinks("Adaptavist");
		TestCaseUtils.navigateAndtakeScreenshot(linkList);

	}
  
	//The test cast to validate all the search result links so that it doesn't contain the reference to the search text
	@Test
	public void testValdiateResultLinkForReferences()
	{
		doSearchAction("Atlassian");
		List<WebElement> allLinks = googleResultPageObj.returnAllLinks();
		int count = TestCaseUtils.validateResultLinks(allLinks,"Adaptavist");
	    //The count value would contain the number of references to the word adaptavist in the search result links
		
	    Assert.assertTrue(count==0,"There are "+count+" Links in the search result page with reference to Adaptavist");
	    
	}


}
