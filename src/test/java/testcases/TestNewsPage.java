package testcases;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import pageObjects.HomePage;
import pageObjects.LandingPage;
import pageObjects.NewsPage;
import resources.Base;

public class TestNewsPage extends Base {
	String usrname = "aptcollie@gmail.com";
	String pw = "admin";
	private static Logger log = LogManager.getLogger(TestNewsPage.class.getName());
	HomePage homepg = new HomePage();
	LandingPage landpg = new LandingPage();
	NewsPage newspg = new NewsPage();

	@BeforeTest
	public void OpenBrowser() throws InterruptedException {
		driver = initializeDriver();
		WebDriverWait d = new WebDriverWait(driver, 20);

		log.info("Initialize Driver");
		driver.manage().window().maximize();
		driver.get(url);
		log.info("Open Browser");
		d.until(ExpectedConditions.visibilityOfElementLocated(landpg.usrname));
		landpg.inputUsrname().sendKeys(usrname);
		landpg.inputPw().sendKeys(pw);
		Thread.sleep(2000);
		landpg.clickSubmit().click();
		log.info("Successfully logged in");
		d.until(ExpectedConditions.visibilityOfElementLocated(homepg.dismisspopup));
		homepg.DissmissPopUp().click();
		d.until(ExpectedConditions.visibilityOfElementLocated(homepg.navdropdown));
		JavaExeClick(homepg.NavDropDown());
		d.until(ExpectedConditions.visibilityOfElementLocated(homepg.navdropdownitems));
		homepg.NavDropDownItems().get(4).click();
		log.info("Arrived at News Page");
	}

	@Test
	public void ClickOnNews() throws InterruptedException {
		WebDriverWait d = new WebDriverWait(driver, 20);
		Actions a = new Actions(driver);

		Thread.sleep(5000);
		d.until(ExpectedConditions.visibilityOf(newspg.NewsCount().get(0)));
		log.info("Total news: " + newspg.NewsCount().size());
		String parentwindow = driver.getWindowHandle();
		for (int i = 0; i < newspg.NewsCount().size(); i++) {

			ScrollToView(newspg.NewsCount().get(i));
			d.until(ExpectedConditions.visibilityOf(newspg.NewsCount().get(i)));

			a.keyDown(Keys.LEFT_CONTROL).click(newspg.NewsCount().get(i)).keyUp(Keys.LEFT_CONTROL).build()
					.perform();
			Thread.sleep(1000);
			log.info(i);
		}
		log.info("Successfully opened all the links");
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		while (it.hasNext()) {

			driver.switchTo().window(it.next());
			log.info(driver.getCurrentUrl());
			if (driver.getTitle().equals("Database Error")) {
				log.error("Page still has database error");
			} else {
				log.debug("Page successfully fixed");
			}
			if (!driver.getWindowHandle().equals(parentwindow)) {
				driver.close();
			}

		}
		driver.switchTo().window(parentwindow);
	}

	@AfterTest
	public void CloseBrowser() throws InterruptedException {
		WebDriverWait d = new WebDriverWait(driver, 20);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		ScrollToView(homepg.ProfileIcon());
		JavaExeClick(homepg.ProfileIcon());
		d.until(ExpectedConditions.visibilityOfElementLocated(homepg.logout));
		JavaExeClick(homepg.LogOut());
		log.info("Successfully logged out");
		driver.close();
		driver = null;
		log.info("Browser Closed");
	}

}
