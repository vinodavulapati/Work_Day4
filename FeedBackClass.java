package work_day4;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class FeedBackClass extends WebDriverFactory {
	@Test
	public void Feedback() throws InterruptedException {

		getDriver().findElement(By.xpath(".//*[@id='hdr-feedback']")).click();

		switchToWindow(1);
		// getDriver().manage().window().maximize();
		getDriver().findElement(By.xpath(".//*[@id='comment-textarea']")).sendKeys("good good good");
		getDriver()
				.findElement(By.xpath(".//*[@id='row-head']/div[2]/section/div/section[1]/fieldset/div/div[2]/div[1]"))
				.click();
		Thread.sleep(5000);
		getDriver().findElement(By.xpath(".//*[@id='content-1']")).click();
		getDriver().findElement(By.xpath(".//*[@id='design-1']")).click();
		getDriver().findElement(By.xpath(".//*[@id='usability-1']")).click();

		Select dropdown = new Select(getDriver().findElement(By.id("4293415")));

		dropdown.selectByVisibleText("Highly likely");

		getDriver().findElement(By.id("answer_4293414-2")).click();
		getDriver().findElement(By.id("answer_4293413-1")).click();
		getDriver().findElement(By.id("email_address")).sendKeys("vinodKumar@gmail.com");

		Thread.sleep(5000);
		getDriver().findElement(By.xpath("html/body/form/footer/div/div/div[1]/input")).click();
		switchToRootWindowAndCloseCurrentBrowser();
	}

	public static void switchToWindow(int index) {

		List<String> listOfWindows = new ArrayList<String>(getDriver().getWindowHandles());
		getDriver().switchTo().window(listOfWindows.get(index));
	}

	public static void switchToRootWindowAndCloseCurrentBrowser() {
		List<String> listOfWindows = new ArrayList<String>(getDriver().getWindowHandles());

		for (int i = 1; i < listOfWindows.size(); i++) {
			getDriver().switchTo().window(listOfWindows.get(i));
			getDriver().close();
		}
		getDriver().switchTo().window(listOfWindows.get(0));

	}

}
