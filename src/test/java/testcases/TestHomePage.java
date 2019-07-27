package testcases;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import pageObjects.HomePage;
import pageObjects.LandingPage;
import resources.Base;

public class TestHomePage extends Base {
	String usrname = "aptcollie@gmail.com";
	String pw = "admin";
	private static Logger log = LogManager.getLogger(TestHomePage.class.getName());
	HomePage homepg = new HomePage();
	LandingPage landpg = new LandingPage();

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
	}

	@Test
	public void Post() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebDriverWait d = new WebDriverWait(driver, 20);

		((JavascriptExecutor) driver).executeScript("scroll(0,250);");
		Thread.sleep(1000);
		homepg.PostBox().click();
		log.info("Post box clicked");
		d.until(ExpectedConditions.visibilityOfElementLocated(homepg.postText));
		homepg.PostText().click();
		Thread.sleep(1200);
		homepg.PostText().sendKeys("Posted_by_Automated_Test");
		Thread.sleep(1200);
		homepg.BtnPost().click();
		log.info("Successfully posted");
	}

	@Test
	public void Comment() throws InterruptedException {
		WebDriverWait d = new WebDriverWait(driver, 20);

		ScrollToView(homepg.CommentBox());
		d.until(ExpectedConditions.visibilityOfElementLocated(homepg.commentbox));
		JavaExeClick(homepg.CommentBox());
		log.info("Clicked on Comment");
		homepg.WriteComment().sendKeys("Test Comment");
		d.until(ExpectedConditions.visibilityOfElementLocated(homepg.sendcomment));
		homepg.SendComment().click();
		log.info("Successfully commented");
	}

	@Test
	public void InspectNoti() throws IOException {
		WebDriverWait d = new WebDriverWait(driver, 10);

		d.until(ExpectedConditions.visibilityOfElementLocated(homepg.notiicon));
		JavaExeClick(homepg.NotiIcon());
		TakeScreenshot("Notifications");
		log.info("Successfully took a screenshot");
	}

	@Test
	public void InspectQuickAdd() throws IOException, InterruptedException {
		WebDriverWait d = new WebDriverWait(driver, 20);
		String picname = null;

		JavaExeClick(homepg.QuickAddIcon());
		log.info("Clicked on Quick add icon");
		d.until(ExpectedConditions.visibilityOfElementLocated(homepg.quickadditems));
		int n = homepg.QuickAddItems().size();
		log.info(homepg.QuickAddItems().size());
		JavaExeClick(homepg.QuickAddIcon());
		for (int i = 0; i < n; i++) {
			JavaExeClick(homepg.QuickAddIcon());
			d.until(ExpectedConditions.visibilityOfElementLocated(homepg.quickadditems));
			JavaExeClick(homepg.QuickAddItems().get(i));
			if (i == 0) {
				homepg.closepopup = By.xpath("//*[@id=\"frmPost\"]/div[3]/button[2]");
				picname = "frmPost";
			} else if (i == 1) {
				homepg.closepopup = By.xpath("//*[@id=\"frmTaskQadd\"]/div[4]/button[2]");
				picname = "frmTaskQadd";
			} else if (i == 2) {
				homepg.closepopup = By.xpath("//*[@id=\"frmCalendarForPost\"]/div[3]/button[3]");
				picname = "frmCalendarForPost";
			} else if (i == 3) {
				homepg.closepopup = By.xpath("//*[@id=\"frmWorkspace\"]/div[3]/button[2]");
				picname = "frmWorkspace";
			} else if (i == 4) {
				homepg.closepopup = By.xpath("//*[@id=\"frmInviteTeam\"]/div[3]/button[2]");
				picname = "frmInviteTeam";
			}
			d.until(ExpectedConditions.visibilityOfElementLocated(homepg.closepopup));
			Thread.sleep(2000);
			TakeScreenshot(picname);
			JavaExeClick(homepg.ClosePopUp());
			log.info("Successfully took the index" + i + " screenshot");
		}

	}

	@Test
	public void InspectNavDropDown() throws InterruptedException {
		String parentwindow = driver.getWindowHandle();
		Set<String> url = new HashSet<String>();
		Set<String> name = new HashSet<String>();
		WebDriverWait d = new WebDriverWait(driver, 20);
		Actions a = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		d.until(ExpectedConditions.visibilityOfElementLocated(homepg.navdropdown));
		JavaExeClick(homepg.NavDropDown());
		log.info("Clicked on Navigation Dropdown");
		d.until(ExpectedConditions.visibilityOfAllElements(homepg.NavDropDownItems()));
		int linksnumber = homepg.NavDropDownItems().size();
		for (int i = 0; i < homepg.NavDropDownItems().size(); i++) {
			name.add(homepg.NavDropDownItems().get(i).getText());
			a.keyDown(Keys.LEFT_CONTROL).click((homepg.NavDropDownItems().get(i))).keyUp(Keys.LEFT_CONTROL).build()
					.perform();

		}

		log.info("Successfully opened links on new tabs");
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		while (it.hasNext()) {
			driver.switchTo().window(it.next());
			url.add(driver.getCurrentUrl());
			if (!driver.getWindowHandle().equals(parentwindow)) {
				driver.close();
			}
		}
		log.info("Successfully retrieved the URLs");
		log.info(name);
		log.info(url);
		driver.switchTo().window(parentwindow);
	}
	@AfterMethod
	public void Pause() throws InterruptedException {
		Thread.sleep(3000);
	}

	@AfterTest
	public void CloseBrowser() throws InterruptedException {
		WebDriverWait d = new WebDriverWait(driver, 20);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -250)", "");
		JavaExeClick(homepg.ProfileIcon());
		d.until(ExpectedConditions.visibilityOfElementLocated(homepg.logout));
		JavaExeClick(homepg.LogOut());
		log.info("Successfully logged out");
		driver.close();
		driver = null;
		log.info("Browser Closed");
	}

}
