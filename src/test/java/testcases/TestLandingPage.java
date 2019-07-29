package testcases;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import pageObjects.HomePage;
import pageObjects.LandingPage;
import resources.Base;

public class TestLandingPage extends Base {
	private static Logger log = LogManager.getLogger(TestLandingPage.class.getName());
	LandingPage landpg = new LandingPage();
	HomePage homepg = new HomePage();

	@DataProvider
	public Object[][] LoginData() {
		Object[][] data = new Object[2][3];
		data[0][0] = "unregister@gmail.com";
		data[0][1] = "unregistered";
		data[0][2] = "first data tested";
		data[1][0] = "aptcollie@gmail.com";
		data[1][1] = "admin";
		data[1][2] = "second data tested";
		return data;
	}

	@BeforeTest
	public void OpenBrowser() {
		driver = initializeDriver();
		log.info("Initialize Driver");
		driver.manage().window().maximize();
		driver.get(url);
		log.info("Open Browser");

	}

	@Test(dataProvider = "LoginData")
	public void FillLoginForm(String usrname, String pw, String text) throws InterruptedException {
		WebDriverWait d = new WebDriverWait(driver, 20);
		
		driver.get(url);
		String LandingUrl = driver.getCurrentUrl();
		landpg.inputUsrname().sendKeys(usrname);
		landpg.inputPw().sendKeys(pw);
		landpg.clickSubmit().click();
		log.info(text);
		Thread.sleep(10000);
		if (!driver.getCurrentUrl().equals(LandingUrl)) {
			log.debug("Successful logged in");
			Assert.assertFalse(driver.getCurrentUrl().equals(LandingUrl));
			d.until(ExpectedConditions.visibilityOfElementLocated(homepg.dismisspopup));
			homepg.DissmissPopUp().click();
			JavaExeClick(homepg.ProfileIcon());
			d.until(ExpectedConditions.visibilityOfElementLocated(homepg.logout));
			JavaExeClick(homepg.LogOut());
			log.info("Successfully logged out");
			
		} else {
			log.error("Invalid");
			Assert.assertTrue(driver.getCurrentUrl().equals(LandingUrl));
		}
	}

	@Test
	public void ClickNews() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Actions act = new Actions(driver);
		for (int i = 0; i < landpg.Newslinks().size(); i++) {
			act.moveToElement(landpg.Newslinks().get(i)).click().build().perform();
			log.info("Click on news links");
			if (driver.getTitle().equals("Database Error")) {
				log.error("Page still has database error");
			} else {
				log.debug("Page successfully fixed");
			}
			driver.navigate().back();
		}
	}

	@AfterTest
	public void CloseBrowser() {
		driver.close();
		driver = null;
		log.info("Browser Closed");
	}

}
