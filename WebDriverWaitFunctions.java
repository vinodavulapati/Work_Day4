package work_day4;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.google.common.base.Function;

import onlineSelenium.WebDriverFactory;

public class WebDriverWaitFunctions extends WebDriverFactory {

	// Implicity wait
	public void implicitWait() {
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		getDriver().get("http://url_that_delays_loading");
		WebElement myDynamicElement = getDriver().findElement(By.id("someElementID"));
	}

	// This is the most common wait function used in selenium
	public static WebElement webDriverFluentWait(final By locator) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver()).withTimeout(10, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class).ignoring(ElementNotFoundException.class)
				.ignoring(WebDriverException.class).withMessage(
						"Webdriver waited for 15 seconds but still could not find the element therefore Timeout Exception has been thrown: "
								+ locator.toString());

		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});

		return element;
	}

	// Expected wait
	public static void waitUntilElementClickable() {
		WebDriverWait wait = new WebDriverWait(getDriver(), 10);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("someID")));
	}

	// Page Load wait
	public static void pageLoadWait() {
		getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}

	// Script timeout
	public static void asynchronousScript() {
		getDriver().manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
	}
}