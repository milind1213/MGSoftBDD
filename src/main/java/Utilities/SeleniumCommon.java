package Utilities;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SeleniumCommon {

	WebDriver driver;

	public SeleniumCommon(WebDriver driver) {
		this.driver = driver;
	}

	public static WebElement waitForElementToBeClickable(WebDriver driver, By locator, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static WebElement waitForElementToBeClickable(WebDriver driver, WebElement element, int seconds) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
	    return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForElementToAppear(WebElement ele, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public static void selectDropdownOptionByText(WebDriver driver, By dropdownLocator, String optionText) {
		WebElement dropdown = driver.findElement(dropdownLocator);
		Select select = new Select(dropdown);
		select.selectByVisibleText(optionText);
	}

	protected void waitForElementDisplay(WebElement ele, int seconds) {
		try {
			new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.visibilityOf(ele));
		} catch (Exception e) {
			System.err.println("Waited for element [" + ele + "] for " + seconds + " seconds");
		}
	}
	
	protected void waitForElementDisplay(By locator, int seconds) {
	    try {
	        new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.visibilityOfElementLocated(locator));
	    } catch (Exception e) {
	        System.err.println("Waited for element located by [" + locator + "] for " + seconds + " seconds");
	    }
	}

	public void waitFor(int i) {
		try {
			Thread.sleep(1000 * i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	

	public void sendKeys(By locator, String text) {
		try {
			waitFor(2);
			WebElement element = driver.findElement(locator);
			drawBorder(driver, element);
			element.sendKeys(text);
			waitFor(1);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed to send keys to element [" + locator + "]");
		}
	}
	
	public void sendKeys(WebElement element, String text) {
	    try {
	        waitFor(2);
	        drawBorder(driver, element);
	        element.sendKeys(text);
	        waitFor(1);
	    } catch (Exception e) {
	        e.printStackTrace();
	        Assert.fail("Failed to send keys to element");
	    }
	}

	public void click(By locator) {
		try {
			waitFor(2);
			WebElement element = driver.findElement(locator);
			drawBorder(driver, element);
			element.click();
			waitFor(1);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed to click on element [" + locator + "]");
		}
	}


	public void click(WebElement element) {
	    try {
	        waitFor(2);
	        drawBorder(driver, element);
	        element.click();
	        waitFor(1);
	    } catch (Exception e) {
	        e.printStackTrace();
	        Assert.fail("Failed to click on element");
	    }
	}

	public static void drawBorder(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}

	
	 public void scrollIntoView(WebElement element) {
	        try {
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", element);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            System.err.println("Failed to scroll to element.");
	        }
	    }
	 
	 
	 public void scrollDown() {
         JavascriptExecutor js = (JavascriptExecutor) driver;
         js.executeScript("window.scrollBy(0,750)");
     }

	public void scrollUpto(By elementLocator) {
		try {
			WebElement element = driver.findElement(elementLocator);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", element);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail("Element not found: " + elementLocator);
		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.fail("Failed to scroll to element: " + elementLocator);
		}
	}

	protected void scrollClick(By locator) {
		WebElement element = driver.findElement(locator);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].scrollIntoView(true);", element);
		waitFor(1);
		js.executeScript("arguments[0].click();", element);
	}

	protected WebElement waitForElementClickable(WebElement webElement, int timeoutSeconds) {
		try {
			new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
					.until(ExpectedConditions.elementToBeClickable(webElement));
		} catch (Exception e) {
			System.err
					.println("Waited for element [" + webElement + "] to be clickable for " + timeoutSeconds + " seconds");
		}
		return webElement;
	}

	public void waitForOverlayToDisappear(By overlayElement, int timeoutSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(overlayElement));
		} catch (Exception e) {
			System.err.println("Overlay did not disappear within " + timeoutSeconds + " seconds.");
		}
	}
}
