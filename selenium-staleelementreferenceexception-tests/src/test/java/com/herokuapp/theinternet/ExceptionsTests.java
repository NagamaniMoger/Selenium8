package com.herokuapp.theinternet;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ExceptionsTests {

	private WebDriver driver;

	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)
	private void setUp(String browser) {

//	     Create Driver
		switch (browser) {
		case "chrome":
			driver = new ChromeDriver();
			System.out.println("chrome browser started");
			break;

		case "firefox":
			driver = new FirefoxDriver();
			System.out.println("firefox browser started");
			break;

		default:
			System.out.println("Do not know how to start" + browser + "starting chrome instead");
			driver = new ChromeDriver();
			break;
		}

		driver.manage().window().maximize();

	}

	@Test
	public void staleElementReferenceExceptionTest() {

		// open webpage
		String url = "https://practicetestautomation.com/practice-test-exceptions/";
		driver.get(url);
		System.out.println("page opened");
		
		//Find the instructions text element
		WebElement instructionTextElement = driver.findElement(By.id("instructions"));
		
		//Push add button
		WebElement addButton = driver.findElement(By.id("add_btn"));
		addButton.click();
		
		//Verify instruction text element is no longer displayed
		
		//Assert.assertFalse(instructionTextElement.isDisplayed(), "Instructions are still displayed");
		//because the element stored in instructionTextElement is deleted so we cannot call isDisplayed
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("instructions"))), "Instructions are still displayed");
		
		
	}

	@AfterMethod(alwaysRun = true)
	private void tearDown() {
//      close browser
		driver.quit();
		System.out.println("Test Finished");
	}

}
