package testcases;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import pageObjects.HomePage;
import pageObjects.LandingPage;
import pageObjects.MessengerPage;
import resources.Base;

public class TestMessengerPage extends Base {
	String usrname = "aptcollie@gmail.com";
	String pw = "admin";
	private static Logger log = LogManager.getLogger(TestMessengerPage.class.getName());
	HomePage homepg = new HomePage();
	LandingPage landpg = new LandingPage();
	MessengerPage mespg = new MessengerPage();

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
		d.until(ExpectedConditions.visibilityOfElementLocated(homepg.chaticon));
		JavaExeClick(homepg.ChatIcon());
		d.until(ExpectedConditions.visibilityOfElementLocated(homepg.chattab));
		JavaExeClick(homepg.ChatTab());
		log.info("Arrived at Messenger Page");
	}

	@Test
	public void SendMessage() throws InterruptedException {
		WebDriverWait d = new WebDriverWait(driver, 20);
		Actions act = new Actions(driver);

		d.until(ExpectedConditions.visibilityOfElementLocated(mespg.selectchat));
		for (int i = 0; i < mespg.SelectChat().size(); i++) {
			mespg.SelectChat().get(i).click();
			Thread.sleep(5000);
			d.until(ExpectedConditions.visibilityOfElementLocated(mespg.textarea));
			act.click(mespg.TextArea()).sendKeys("Test Message, Sorry to interrupt").sendKeys(Keys.ENTER).build()
					.perform();
			Thread.sleep(5000);
			mespg.Like().click();
			log.info("Message sent");
		}
	}

	@Test
	public void FilesAndPhotoVideosCount() throws InterruptedException {
		WebDriverWait d = new WebDriverWait(driver, 20);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		int chatindex = 0;
		
		mespg.SelectChat().get(chatindex).click();
		mespg.NumFiles().click();
		d.until(ExpectedConditions.visibilityOfElementLocated(mespg.filelist));
		log.info("Files count for chat index "+chatindex+" = " + mespg.FileList().size());
		mespg.NumFiles().click();
		JavaExeClick(mespg.NumPhotoVideo());
		d.until(ExpectedConditions.visibilityOfElementLocated(mespg.photovideolist));
		log.info("Photos and Videos count for chat index "+chatindex+" = "+mespg.PhotoVideoList().size());
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
