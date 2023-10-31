package PageObjects;

import Utilities.SeleniumCommon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage extends SeleniumCommon {
	public static WebDriver driver;
	public SearchPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	private By shopByCategoryBtn = By.id("gh-shop-a");
	private By cellPhonesAndAccessories = By.xpath("//a[contains(text(),'Cell phones & accessories')]");
	private By cellPhonesAndSmartphones = By.xpath("//a[text()='Cell Phones & Smartphones']");
	private By allFilters = By.xpath("(//ul[@class='brm__list']//li//button)[last()]");
	private By pricedfrom = By.xpath("//input[contains(@aria-label,'Minimum Value')]");
	private By pricedTo = By.xpath("//input[contains(@aria-label,'Maximum Value')]");
	private By applyFilterBtn = By.cssSelector("button[aria-label='Apply']");
	private By appliedFilterbtn = By.xpath("//*[@class='x-flyout__button']//*[contains(.,'filters applied')]");
	private By searchLocator = By.xpath("//input[@id='gh-ac'  or @placeholder ='Search for anything']");
    private By categoryDropdownLocator  = By.id("gh-cat");
	private By searchBtnLocatoer = By.id("gh-btn");
	private By searchResultLocator = By.xpath("(//ul[contains(@class, 'srp-list')]/li//a/div/span/ancestor::a)[1]");
	

	
	
	public void applyConditionFilter(String filterOption ,String phoneType) {
		scrollClick(allFilters);
		waitFor(2);
		By element = By.xpath("//div[contains(@id,'c3-mainPanel')]//span[contains(., '" + filterOption + "')]");
		waitForElementClickable(element, 5);
		click(element);
		waitForElementDisplay(By.xpath("//span[@class='field']//label[.='" + phoneType + "']"), 5);
		click(By.xpath("//span[@class='field']//label[.='" + phoneType + "']"));
		waitFor(3);
		
    }
	
	public void applyPriceFilter(String filterOption, String minAmt, String maxAmt) {
	    
	        By element = By.xpath("//div[contains(@id,'c3-mainPanel')]//span[contains(., '" + filterOption + "')]");
	        waitForElementClickable(element, 5);
	        click(element);
	   try {
	        WebElement minPriceElement = driver.findElement(pricedfrom);
	        waitForElementDisplay(minPriceElement, 5);
	        minPriceElement.sendKeys(minAmt);
	        WebElement maxPriceElement = driver.findElement(pricedTo);
	        waitForElementDisplay(maxPriceElement, 5);
	        maxPriceElement.sendKeys(maxAmt);
	    } catch (Exception e) {
	        System.out.println("An exception occurred while applying the price filter: " + e.getMessage());
	    }
	}
	
	
	public void applyItemLocationFilter(String filterOption ,String itemLocation) {
		By element = By.xpath("//div[contains(@id,'c3-mainPanel')]//span[contains(., '" + filterOption + "')]");
		waitForElementClickable(element, 5);
		click(element);
		try {
			String itemLocationXPath = "//span[contains(text(),'" + itemLocation + "')]//ancestor::span//input";
			WebElement locationElement = driver.findElement(By.xpath(itemLocationXPath));
			locationElement.click();
			click(applyFilterBtn);
			waitFor(2);
		} catch (Exception e) {
			System.out.println("An Error occurred while applying item location filter: " + e.getMessage());
		}	
    }
	
	
	public void SerchItemInSearchbar(String item, String productCategory) {
		WebElement searchInputElement = driver.findElement(searchLocator); 
		waitForElementToBeClickable(driver, searchLocator, 3);
		searchInputElement.sendKeys(item);
		selectDropdownOptionByText(driver, categoryDropdownLocator, productCategory);
		click(searchBtnLocatoer);
	}
	
	public String getFirstResultValidation(){
			String  firstResultText = driver.findElement(searchResultLocator).getText();
			return firstResultText;
	}	
		
	public void clickingOnshopByCategoryBtn() {
		click(shopByCategoryBtn);
	}

	public void selectingPhonesAndSmartphones() {
		try {
			click(cellPhonesAndAccessories);
			waitForElementDisplay(cellPhonesAndSmartphones, 3);
			click(cellPhonesAndSmartphones);
		} catch (Exception e) {
			System.out.println("An exception occurred: " + e.getMessage());
		}
	}

	public void clickingOnAllFiltersBtn() {
		scrollClick(allFilters);
	}

	
	public String getPhoneCondtionTagText() {
		click(appliedFilterbtn);
		WebElement appliedFileterElement = driver.findElement(By.xpath("(//span[contains(@class,'brm__item-label')])[1]"));
		waitForElementDisplay(appliedFileterElement, 3);
		String text = appliedFileterElement.getText();
		String str []  = text.split(":");
		String phoneCondtionTagText  = str[0];
		return phoneCondtionTagText;
	}
	
	public String getPriceFilterTagText() {
		WebElement element = driver.findElement(By.xpath("(//span[contains(@class,'brm__item-label')])[2]"));
		waitForElementDisplay(element, 3);
		String text = element.getText();
		String str []  = text.split(":");
		String pricefilterTagText = str[0];
		return pricefilterTagText;
	}

	
	public String getItemLocationTagText() {
		WebElement element = driver.findElement(By.xpath("(//span[contains(@class,'brm__item-label')])[3]"));
		waitForElementDisplay(element, 3);
		String text = element.getText();
		String str []  = text.split(":");
		String locationText = str[0];
		return locationText;
	}
	
}
