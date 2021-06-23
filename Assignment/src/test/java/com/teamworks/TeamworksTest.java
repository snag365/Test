package com.teamworks;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TeamworksTest {

	configProperty prop = new configProperty();
	WebDriver driver;
	WebDriverWait wait;

	@BeforeMethod
	public void BeforeMethod() {
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+prop.get("chromeDriverPath"));
		driver=new ChromeDriver(); 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver,30);
	}

	/*
	 * @AfterMethod public void AfterMethod() { driver.quit();
	 * //Assert.assertTrue(driver.getWindowHandles().isEmpty());
	 * System.out.println("WebDriver:" + driver); }
	 */

	@Test
	public void test_001() {

		//Step 1: Open the URL “https://home.teamworks.wellsfargo.net/technology” and 
		//verify the title of the page is “Technology”
		System.out.println("Step 1: Open the URL Teamwork URL and VerifyError title");
		driver.get(prop.get("teamWorkURL"));
		assertEquals(driver.getTitle(), "Google", "Validate teamwork page title");

		//Step 2: On the right side of the page verify the people select button text is available or not. 
		//Verify the textbox “search by name or profile info“ is available or not
		assertTrue(driver.findElement(By.xpath(ObjectRepo.btnPeopleSelect)).isDisplayed(), "Verify people select button is displayed");
		assertTrue(driver.findElement(By.xpath(ObjectRepo.inputBoxSearchByName)).isDisplayed(), "Verify Search by name textbox is displayed");

		//Step 3: Enter “Dean Jones "  and hit enter then the page will be navigated to the search results page 
		//and then verify the title of the page is “People Search Results - Teamworks"
		driver.findElement(By.xpath(ObjectRepo.inputBoxSearchByName)).sendKeys("Dean Jones");
		driver.findElement(By.xpath(ObjectRepo.btnPeopleSelect)).click();
		//if required
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("")));
		assertEquals(driver.getTitle(), "People Search Results - Teamworks", "Verify page Title");


		//Step 4: Verify the text displayed dynamically based on the search results For E.g. “About 5 results for Dean Jones” 
		//and verify the search name is exactly the same that we have searched in the step 3.

		//TODO

		//Step 5: Verify the links “Download in Excel” and “Email Results” are displayed and enabled
		assertTrue(driver.findElement(By.xpath(ObjectRepo.linkDownloadInExcel)).isDisplayed(), "Verify Download in excel link is displayed");
		assertTrue(driver.findElement(By.xpath(ObjectRepo.linkDownloadInExcel)).isEnabled(), "Verify Download in excel link is enabled");

		assertTrue(driver.findElement(By.xpath(ObjectRepo.linkEmailResult)).isDisplayed(), "Verify Email result link is displayed");
		assertTrue(driver.findElement(By.xpath(ObjectRepo.linkEmailResult)).isEnabled(), "Verify Email result link is enabled");

		//Step 6: Verify the web elements of the search results first candidate full name, Designation and email id are displayed and enabled
		//TODO
		//Step 7: Print the full name, designation and email id on the console then click on the full name link then the page will be navigated to a new page
		//TODO

		//Step 8: Verify the title of the page “Dean Jones”
		assertEquals(driver.getTitle(), "Dean Jones", "Verify page Title");

		//Step 9: Navigate back to the previous URL page using navigate methods
		driver.navigate().back();

		//step 10: Navigate to the URL “https://stamp.wellsfargo.com/psp/V92PROD/EMPLOYEE/ERP/c/ADMINISTER_EXPENSE_FUNCTIONS.TE_TIME_ENTRY_INQ.GBL?PAGE=TE_TIME_LINES&Action=U” 
		//and verify the title of the page is “Review Report"
		driver.get(prop.get("StampURL"));
		assertEquals(driver.getTitle(), "Review Report", "Verify page Title");

		//Step 11: Get the count of the iframe tags in the page then print on the console 
		//and verify the title text “Time Report” is displayed or not
		int iframeCount = driver.findElements(By.tagName("iframe")).size();
		System.out.println("Number of iframe tags on the page is "+iframeCount);

		assertEquals(driver.findElement(By.xpath("")).getText(), "Time Report", "Verify page Header");

		//Step 12: Verify the text “Enter any information you have and click Search. 
		//Leave fields blank for a list of all values”

		assertEquals(driver.findElement(By.xpath("")).getText().trim(), "Enter any information you have and click Search. \r\n" + 
				"		//Leave fields blank for a list of all values");

		//Step 13: Verify the search button is displayed and enabled then click on search button
		WebElement searchButtonElement = driver.findElement(By.xpath(""));
		assertTrue(searchButtonElement.isDisplayed(), "Verify Search Button is displayed" );
		assertTrue(searchButtonElement.isEnabled(), "Verify Search Button is enabled" );
		searchButtonElement.click();

		//Step 14: Print the current system date in the console with the format “MM/dd/yyyy hh:mm:ss” 
		//Add 10 days to the current date and print on the console. Subtract 30 days from the current date and print on the console
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		Calendar cal = Calendar.getInstance();
		System.out.println("Current Date: "+sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, 10);
		System.out.println("Current Date + 10 days: "+sdf.format(cal.getTime()));
		cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -30);
		System.out.println("Current Date - 30 days: "+sdf.format(cal.getTime()));

		//Step 15: Quit all the browsers and verify the same are closed
		driver.quit();
		try {
			driver.getWindowHandles();
		}catch (NoSuchSessionException e) {
			System.out.println("All browsers were succussfully closed");
		}
	}



}
