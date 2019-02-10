package AdpdavistAutomationScenarios.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AdpdavistAutomationScenarios.base.Base;

public class GoogleHomePage extends Base{
	
	
	private By searchBox = By.name("q");
    private By searchbtn = By.name("btnK");
    

	//Launches the homepage
	public void launchHomePage()
	{
		driver.get(prop.getProperty("url"));
	}
	
	//checks if the searchbox is enabled
	public boolean isPageDisplayed()
	{
		
		return driver.findElement(searchBox).isEnabled();
		
	}
	
	//To Enter the search string in the searchbox
	public void enterSearchString(String searchText)
	{
		
		driver.findElement(searchBox).sendKeys(searchText);
	}

	//To click on the searchbutton
	public void clickSearchBtn()
	{
		wait.until(ExpectedConditions.elementToBeClickable(searchbtn)).click();;
		
	}
}
