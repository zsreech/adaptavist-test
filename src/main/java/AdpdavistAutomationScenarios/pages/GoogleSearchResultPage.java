package AdpdavistAutomationScenarios.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import AdpdavistAutomationScenarios.base.Base;

public class GoogleSearchResultPage extends Base {
	
	//Setting the prefix and postfix part so that xpath can be generalized and can be dynamically reused for different inputs
	private String prefixSearchlinkXpath="//h3[contains(text(),'";
	private String postfixSearchLinkXpath="')]/parent::a";
	
	private By searchResultLink;
	private By allLinks = By.tagName("a");


	//Combining the prefix and postfix to dynamically create xpath based on searchtext
	public void constructLinkElement(String searchText)
	{
		searchResultLink = By.xpath(prefixSearchlinkXpath+searchText+postfixSearchLinkXpath);

	}
	
	//Return all the search result links as a List element
	public List<WebElement> returnSearchLinks(String searchText)
	{
	constructLinkElement(searchText);
	List<WebElement> links =	driver.findElements(searchResultLink);
	return links;
	}

	//To return all the links displayed in the page as a list element
	public List<WebElement> returnAllLinks()
	{
		List<WebElement> links =driver.findElements(allLinks);
		return links;
	}

}
