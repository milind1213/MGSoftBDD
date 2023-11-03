package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import Utilities.SeleniumCommon;

public class DashboardPage extends SeleniumCommon {

	private WebDriver driver;

	public DashboardPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	private By societyLogin = By.xpath("//div[@class='nb__GGkUH' or contains(text(), 'Society Login')]");
	private By emailField = By.xpath("(//input[@placeholder='Email'])[1]");
	private By passField = By.xpath("//input[@placeholder='Password']");
	private By loginBtn = By.xpath("//div[@class='nb__1Zwxx' and contains(text(),'Login')]");
	private By userName = By.cssSelector(".nb__19WIg:first-of-type");
	private By title = By.cssSelector(".nb__mLFkD:nth-of-type(2)");
	private By loginError = By.id("alertMessageBox");
	private By activePagelocator = By.xpath("//li//a[contains(@aria-label,'your current page')]");
	private By lastPagelocator = By.xpath("//a[@aria-label='Page 73']//parent::li");

	
	public void getSocietystafandSecurityDetails() {
	    WebElement lastEle = driver.findElement(lastPagelocator);
	    scrollIntoView(lastEle);
	    String lastEleText = lastEle.getText();
	    int totalPages = Integer.parseInt(lastEleText);
        System.out.println("Total Pages : "+totalPages);
        
	    for (int i = 1; i <=totalPages; i++) {
	       	 click(activePagelocator); // Active Element
	         int rows = driver.findElements(By.xpath("//table[@class='table-striped table-bordered table-hover table']//tbody//tr")).size();
	         System.out.println("Number of Rows : " + rows);
	         String pageNum = Integer.toString(i + 1); 
	         // Read all the rows from each Page 
	         for (int j=1;j<rows;j++) {
	         String name  = driver.findElement(By.xpath("//table[@class='table-striped table-bordered table-hover table']//tbody//tr["+j+"]//td[1]")).getText();
	         String profession  = driver.findElement(By.xpath("//table[@class='table-striped table-bordered table-hover table']//tbody//tr["+j+"]//td[3]")).getText();
	         String createdOn  = driver.findElement(By.xpath("//table[@class='table-striped table-bordered table-hover table']//tbody//tr["+j+"]//td[6]")).getText();
	         String approvedBy  = driver.findElement(By.xpath("//table[@class='table-striped table-bordered table-hover table']//tbody//tr["+j+"]//td[7]")).getText();

	        if(approvedBy.equals("Test")) {
	         System.out.println(String.format("%-25s %-25s %-19s %s", name, profession, createdOn, approvedBy));
	           }    	
	         }
	         
	         if (i == totalPages) {
	             break;
	         }
	         
	         WebElement nextPage = driver.findElement(By.xpath("//a[contains(@aria-label,'Page "+pageNum+"')]"));
	         waitForElementToBeClickable(driver, nextPage,2);
	         nextPage.click();  
	        
	    }
	}


	public void clickDashbpardElement(String mainModuleTitle, String subModuleTetle) {
		String xpath = "//div[@class='nb__3PXXE']//following::div[contains(text(),'" + mainModuleTitle + "')]";
		WebElement mainModuleEle = waitForElementClickable(driver.findElement(By.xpath(xpath)), 5);
		mainModuleEle.click();
		String xpathsub = "//div[contains(@class,'3CnSO')]//following::a[@title='Manage Staff/Security']"
				.replace("Manage Staff/Security", subModuleTetle);
		click(By.xpath(xpathsub));
		waitFor(2);
	}

	public void societyAdminLogin(String email, String password) {
		try {
			click(driver.findElement(societyLogin));
			waitForElementDisplay(emailField, 3);
			sendKeys(emailField, email);
			sendKeys(passField, password);
			click(driver.findElement(loginBtn));
		} catch (Exception e) {
			System.err.println("An error occurred during societyAdminLogin: " + e.getMessage());
		}
	}

	public String getUserNameText() {
		try {
			WebElement userNameEle = driver.findElement(userName);
			waitForElementDisplay(userNameEle, 3);
			String userNameText = userNameEle.getText();
			return userNameText;
		} catch (Exception e) {
			System.err.println("An error occurred during getUserNameText: " + e.getMessage());
			return "";
		}
	}

	public String getPageTitle() {
		try {
			WebElement titleEle = driver.findElement(title);
			waitForElementDisplay(titleEle, 5);
			String dashboardTitle = titleEle.getText();
			return dashboardTitle;
		} catch (Exception e) {
			System.err.println("An error occurred during getPageTitle: " + e.getMessage());
			return "";
		}
	}

	public String getLoginErrortext() {
		WebElement errorEle = driver.findElement(loginError);
		waitForElementDisplay(errorEle, 2);
		String loginErrorText = errorEle.getText();
		return loginErrorText;
	}

}
