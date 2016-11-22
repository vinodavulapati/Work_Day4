package work_day4;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HotelSearch extends WebDriverFactory {

	public static String tomorrowdate() {
		Calendar cal1 = Calendar.getInstance();
		Date today = cal1.getTime();

		cal1.add(Calendar.DATE, 1);
		Date tomorrow = cal1.getTime();
		DateFormat formatter = new SimpleDateFormat("MM/dd/YY");
		String tomorrowstr = formatter.format(tomorrow);
		System.out.println(tomorrowstr);
		return tomorrowstr;
	}

	public static String afterOneWeekDate() {
		Calendar cal = Calendar.getInstance();
		Date today1 = cal.getTime();
		cal.add(Calendar.DATE, 8);
		Date afterOneWeekDay = cal.getTime();
		DateFormat formatter = new SimpleDateFormat("MM/dd/YY");
		String seven = formatter.format(afterOneWeekDay);
		System.out.println(seven);
		return seven;
	}

	@Test
	public void headerCheck() throws InterruptedException {

		getDriver().findElement(By.xpath(".//*[@id='menu-bar']/div/nav/div/a")).click();
		getDriver().findElement(By.xpath(".//*[@id='hdr-deals']")).click();

		// London united kingdom selection from suggestions

		getDriver().findElement(By.xpath(".//*[@id='qf-1q-destination']")).sendKeys("London");
		List<WebElement> orginSuggestionList = getDriver()
				.findElements(By.xpath(".//div[@class='autosuggest-category-result']"));
		Thread.sleep(4000);

		for (WebElement el : orginSuggestionList) {
			if (el.getText().contains("London, United Kingdom")) {
				el.click();
				break;
			}
		}

		/*
		 * Calendar cal = Calendar.getInstance(); int todayDate =
		 * cal.get(Calendar.DAY_OF_MONTH);
		 * 
		 * int tomorrowDate = todayDate + 1; int dayAfterTomorrowDate =
		 * todayDate + 7;
		 * 
		 * String todayStr = String.valueOf(todayDate); String tomorrowStr =
		 * String.valueOf(tomorrowDate); String dayAfterTomorrowStr =
		 * String.valueOf(dayAfterTomorrowDate);
		 * 
		 * // *********Origin Date PICKING from Calendar******
		 * 
		 * // selecting the required next day date by selecting available all
		 * dates // from month box
		 * 
		 * List<WebElement> myList3 = getDriver().findElements(
		 * By.xpath("html/body/div[9]/div[1]/div[2]/table/tbody/tr/td/a")); int
		 * total = myList3.size(); for (int i = 0; i < total; i++) { String date
		 * = myList3.get(i).getText(); if (date.equals(tomorrowStr)) {
		 * System.out.println("vinorrbhrbrbrbbrbrbrbbrb"); ((By)
		 * myList3).findElement((SearchContext)
		 * By.linkText(tomorrowStr)).click(); // myList3.get(i).click();
		 * //Thread.sleep(4000);
		 * 
		 * //continue; break; } }
		 */

		String s1 = tomorrowdate();
		String s3 = afterOneWeekDate();

		WebElement checkin = getDriver().findElement(By.id("qf-1q-localised-check-in"));
		checkin.sendKeys(s1);
		WebElement checkout = getDriver().findElement(By.id("qf-1q-localised-check-out"));
		checkout.clear();
		checkout.sendKeys(s3);

		WebElement NumOfRooms = getDriver().findElement(By.className("query-rooms"));
		Select dropdownRooms = new Select(NumOfRooms);
		String s = dropdownRooms.getFirstSelectedOption().getText();
		System.out.println(s);
		if (s == "1") {
			System.out.println("default value is 1");
		} else {
			dropdownRooms.selectByVisibleText("2");
			Thread.sleep(3000);
		}

		WebElement NumOfAdultsRoom1 = getDriver().findElement(By.id("qf-1q-room-0-adults"));
		Select dropdownRoom1Adults = new Select(NumOfAdultsRoom1);
		String s2 = dropdownRoom1Adults.getFirstSelectedOption().getText();
		if (s2 == "1") {
			System.out.println("default value is 2");

		} else {
			dropdownRoom1Adults.selectByVisibleText("2");
			Thread.sleep(3000);
		}

		WebElement NumOfChildsRoom1 = getDriver().findElement(By.id("qf-1q-room-0-children"));
		Select dropdownRoom1Childs = new Select(NumOfChildsRoom1);
		dropdownRoom1Childs.selectByVisibleText("1");
		Thread.sleep(3000);

		WebElement room1ChildAge = getDriver().findElement(By.id("qf-1q-room-0-child-0-age"));
		Select dropdownRoom1ChildAge = new Select(room1ChildAge);
		dropdownRoom1ChildAge.selectByVisibleText("7");
		Thread.sleep(3000);

		WebElement NumOfAdultsRoom2 = getDriver().findElement(By.id("qf-1q-room-1-adults"));
		Select dropdownRoom2Adults = new Select(NumOfAdultsRoom2);
		dropdownRoom2Adults.selectByVisibleText("3");
		Thread.sleep(3000);

		WebElement NumOfChildsRoom2 = getDriver().findElement(By.id("qf-1q-room-1-children"));
		Select dropdownRoom2Childs = new Select(NumOfChildsRoom2);
		dropdownRoom2Childs.selectByVisibleText("0");
		Thread.sleep(3000);

		getDriver()
				.findElement(By
						.xpath(".//*[@id='main-content']/main/div/div/div[1]/div/div[1]/div[2]/div/div/form/fieldset[4]/button"))
				.click();
		String actual = getDriver().findElement(By.xpath(".//*[@id='search']/div/div/h1")).getText();
		String expected = "London, England, United Kingdom";
		Assert.assertEquals(actual, expected);

	}

}