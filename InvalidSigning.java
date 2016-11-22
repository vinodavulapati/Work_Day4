package work_day4;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InvalidSigning extends WebDriverFactory {
	@Test
	public void invalidsignincheck() {
		getDriver().findElement(By.id("hdr-account")).click();
		getDriver().findElement(By.id("hdr-signin")).click();
		getDriver().findElement(By.id("sign-in-email")).sendKeys("vinod");
		getDriver().findElement(By.id("sign-in-password")).sendKeys("1234567");
		getDriver().findElement(By.xpath("html/body/div[7]/div/div[1]/div/div/form/div[2]/button")).click();
		String actual = getDriver().findElement(By.xpath("html/body/div[7]/div/div[1]/div/div/p")).getText();
		String expected = "We don’t recognize the email and password combination you’ve entered. Please try again, or if you’ve forgotten your password we’ll help reset it";
		Assert.assertEquals(actual, expected);
	}

}
