package work_day4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PriceDrag extends HotelSearch {

	@Test

	public void Pricechecking() throws InterruptedException {

		// getDriver().findElement(By.xpath(".//*[@id='filter-price']/div[2]/div/div[2]/div[3]")).click();

		WebElement source = getDriver().findElement(By.xpath(".//*[@id='filter-price']/div[2]/div/div[2]/div[3]"));
		WebElement target = getDriver().findElement(By.xpath(".//*[@id='filter-price']/div[2]/div/div[2]/div[2]"));
		Actions action = new Actions(getDriver());
		action.dragAndDropBy(source, -92, 0).release().build().perform();
		Thread.sleep(4000);
		String actual = getDriver()
				.findElement(By.xpath(".//*[@id='listings']/ol/li[1]/article/div/div[3]/div[1]/a/span/ins")).getText();
		String expected = "first hotel price is less then 200";
		Assert.assertEquals(actual, expected);
		Thread.sleep(10000);
	}

}
