package testcases;

import java.io.IOException;
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
import org.testng.Assert;
import org.testng.annotations.*;

import pageObjects.HomePage;
import pageObjects.LandingPage;
import pageObjects.NewsPage;
import resources.Base;

public class TestNewsPage extends Base {
	String usrname = "aptcollie@gmail.com";
	String usrnameAdmin = "qc_admin@synerry.com";
	String pw = "admin";
	int newsno = 1;
	String title = "test fix";
	String text = "test fix";
	private static Logger log = LogManager.getLogger(TestNewsPage.class.getName());
	HomePage homepg = new HomePage();
	LandingPage landpg = new LandingPage();
	NewsPage newspg = new NewsPage();

	@BeforeTest(groups = "admin")
	public void OpenBrowser() throws InterruptedException {
		driver = initializeDriver();
		WebDriverWait d = new WebDriverWait(driver, 20);

		log.info("Initialize Driver");
		driver.manage().window().maximize();
		driver.get(url);
		log.info("Open Browser");
		d.until(ExpectedConditions.visibilityOfElementLocated(landpg.usrname));
		landpg.inputUsrname().sendKeys(usrnameAdmin);
		landpg.inputPw().sendKeys(pw);
		Thread.sleep(2000);
		landpg.clickSubmit().click();
		log.info("Successfully logged in");
		d.until(ExpectedConditions.visibilityOfElementLocated(homepg.dismisspopup));
		homepg.DissmissPopUp().click();
		/*
		 * d.until(ExpectedConditions.visibilityOfElementLocated(homepg.navdropdown));
		 * JavaExeClick(homepg.NavDropDown());
		 * d.until(ExpectedConditions.visibilityOfElementLocated(homepg.navdropdownitems
		 * )); Thread.sleep(1000); homepg.NavDropDownItems().get(4).click();
		 */
		driver.get("https://tpqi-demo.jigsawgroups.work/intranet/news?");
		Assert.assertTrue(driver.getCurrentUrl().contains("tpqi-demo.jigsawgroups.work/intranet/news"));
		log.info("Arrived at News Page");
	}

	@Test(groups = "admin")
	public void PreviewNews() throws IOException, InterruptedException {
		WebDriverWait d = new WebDriverWait(driver, 30);
		int type = 2;
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		
		Thread.sleep(10000);
		d.until(ExpectedConditions.visibilityOf(newspg.NewsOption(newsno)));
		newspg.NewsOption(newsno).click();
		newspg.FixNews(newsno).click();
		String url = driver.getCurrentUrl();
		int i = url.indexOf("form/");
		i += 5;
		String formno = url.substring(i, i + 4);
		TakeScreenshot(formno + "_Before_above_" + nameofCurrMethod);
		newspg.title().clear();
		log.info("Clear input");
		newspg.previewnews().click();
		log.info("Clicked on preview news");
		Set<String> windows = driver.getWindowHandles();
		if (windows.size() == 1) {
			log.debug("Unable to proceed to the next page (Expected result)");
			TakeScreenshot("Require_field_alert");
		} else if (windows.size() > 1) {
			log.error("Proceed to new page (Unexpected result)");
		}
		newspg.title().sendKeys(title);
		log.info("Title changed");
		newspg.textarea().clear();
		newspg.textarea().sendKeys(text);
		log.info("Text changed");
		// newspg.coverphoto().click();
		// Thread.sleep(3000);
		// autoIT
		// Runtime.getRuntime().exec(System.getProperty("user.dir") +
		// "/autoIT_AttachPicture.exe");
		// log.info("Cover photo uploaded");
		TakeScreenshot(formno + "_After_above_" + nameofCurrMethod);
		ScrollToView(newspg.tag());
		TakeScreenshot(formno + "_Before_bottom_" + nameofCurrMethod);
		/*
		 * newspg.status().click(); Thread.sleep(2000);
		 * a.moveToElement(newspg.choose()).click().sendKeys(Keys.ENTER);
		 * Thread.sleep(2000); log.info("Status changed");
		 */
		newspg.allowcomment().click();
		Thread.sleep(2000);
		log.info("Comment switch clicked");
		newspg.type(type).click();
		log.info("Switched news category");
		Thread.sleep(2000);
		//newspg.attachpic().click();
		//Thread.sleep(3000);
		// autoIT
		// Runtime.getRuntime().exec(System.getProperty("user.dir") +
		// "/autoIT_AttachPicture.exe");
		// log.info("Image uploaded");
		newspg.tag().click();
		newspg.tag().sendKeys(title);
		newspg.tag().sendKeys(Keys.ENTER);
		log.info("Tag created");
		TakeScreenshot(formno + "_After_bottom_" + nameofCurrMethod);

		String parentwindow = driver.getWindowHandle();
		int updatedtrial = newspg.activitycount().size();
		log.info(updatedtrial);
		int numberoftags = newspg.NumberOfTag().size();
		int numberofpicsattached = newspg.NoOfPicAttached().size();

		ScrollToView(newspg.previewnews());
		newspg.previewnews().click();
		log.info("Preview news clicked");
		Thread.sleep(5000);
		Set<String> windows1 = driver.getWindowHandles();
		Iterator<String> it = windows1.iterator();
		log.info(windows1);

		String window = null;
		while (it.hasNext()) {
			window = it.next();
			if (!parentwindow.equals(driver.getWindowHandle())) {
				break;
			}
		}
		driver.switchTo().window(window);
		String previewurl = driver.getCurrentUrl();
		log.info("Preview URL: " + previewurl);
		TakeScreenshot("Preview_" + formno);
		d.until(ExpectedConditions.visibilityOf(newspg.header()));
		Assert.assertTrue(newspg.header().getText().equals(title));
		log.info("Check title");
		Assert.assertTrue(newspg.content().getText().equals(text));
		log.info("Check content");
		Assert.assertTrue(numberoftags == newspg.NumberOfTagPreview().size());
		log.info("Check tag");
		// Assert.assertTrue(numberofpicsattached ==
		// newspg.NoOfPicAttachedPreview().size());
		// log.info("Check image attached");
		driver.switchTo().window(parentwindow);
		driver.navigate().refresh();
		int latestupdatedtrial = newspg.activitycount().size();
		log.info(latestupdatedtrial);
		for (int i1 = 0; i1 < latestupdatedtrial; i1++) {
			log.info(newspg.activity().get(i1).getText());
		}
		if (updatedtrial == latestupdatedtrial) {
			log.debug("System didn't save the data (Expected result)");
		} else {
			log.error("System saved the data (Unexpected result)");
		}
		// Try different account to see if the other account can access the preview page
		ScrollToView(homepg.ProfileIcon());
		JavaExeClick(homepg.ProfileIcon());
		d.until(ExpectedConditions.visibilityOfElementLocated(homepg.logout));
		JavaExeClick(homepg.LogOut());
		log.info("Successfully logged out");
		d.until(ExpectedConditions.visibilityOfElementLocated(landpg.usrname));
		landpg.inputUsrname().sendKeys(usrname);
		landpg.inputPw().sendKeys(pw);
		Thread.sleep(2000);
		landpg.clickSubmit().click();
		log.info("Successfully logged in with user account");
		d.until(ExpectedConditions.visibilityOfElementLocated(homepg.dismisspopup));
		homepg.DissmissPopUp().click();
		driver.switchTo().window(window);
		driver.navigate().refresh();
		if (newspg.headercount().size() == 0) {
			log.debug("Could not access to the preview page (Expected result)");
		} else {
			log.error("Could access to the preview page (Unexpected result)");
		}
		driver.close();
		driver.switchTo().window(parentwindow);
		ScrollToView(homepg.ProfileIcon());
		JavaExeClick(homepg.ProfileIcon());
		d.until(ExpectedConditions.visibilityOfElementLocated(homepg.logout));
		JavaExeClick(homepg.LogOut());
		log.info("Successfully logged out from user account");
		d.until(ExpectedConditions.visibilityOfElementLocated(landpg.usrname));
		landpg.inputUsrname().sendKeys(usrnameAdmin);
		landpg.inputPw().sendKeys(pw);
		Thread.sleep(2000);
		landpg.clickSubmit().click();
		log.info("Successfully logged in with Admin account");
		d.until(ExpectedConditions.visibilityOfElementLocated(homepg.dismisspopup));
		homepg.DissmissPopUp().click();
		/*
		 * ScrollToView(newspg.draftnews()); newspg.draftnews().click();
		 * log.info("News successfully fixed"); Thread.sleep(5000);
		 * TakeScreenshot(formno+"_After_above"); ScrollToView(newspg.tag());
		 * TakeScreenshot(formno+"_After_bottom"); for (int
		 * i1=0;i1<newspg.activity().size();i1++) {
		 * log.info(newspg.activity().get(i1).getText()); }
		 */
	}

	@Test(groups = "admin")
	public void DraftNews() throws IOException, InterruptedException {
		WebDriverWait d = new WebDriverWait(driver, 10);
		int type = 2;
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		Thread.sleep(10000);
		d.until(ExpectedConditions.visibilityOf(newspg.NewsOption(newsno)));
		newspg.NewsOption(newsno).click();
		newspg.FixNews(newsno).click();
		String url = driver.getCurrentUrl();
		int i = url.indexOf("form/");
		i += 5;
		String formno = url.substring(i, i + 4);
		TakeScreenshot(formno + "_Before_above_" + nameofCurrMethod);
		newspg.title().clear();
		log.info("Clear input");
		newspg.draftnews().click();
		log.info("Clicked on draft news");
		Set<String> windows = driver.getWindowHandles();
		if (windows.size() == 1) {
			log.debug("Unable to proceed to the next page (Expected result)");
			TakeScreenshot("Require_field_alert");
		} else if (windows.size() > 1) {
			log.error("Proceed to new page (Unexpected result)");
		}
		newspg.title().sendKeys(title);
		log.info("Title changed");
		newspg.textarea().clear();
		newspg.textarea().sendKeys(text);
		log.info("Text changed");
		//newspg.coverphoto().click();
		//Thread.sleep(3000);
		// autoIT
		// Runtime.getRuntime().exec(System.getProperty("user.dir") +
		// "/autoIT_AttachPicture.exe");
		// log.info("Cover photo uploaded");
		TakeScreenshot(formno + "_After_above_" + nameofCurrMethod);
		ScrollToView(newspg.tag());
		TakeScreenshot(formno + "_Before_bottom_" + nameofCurrMethod);
		/*
		 * newspg.status().click(); Thread.sleep(2000);
		 * a.moveToElement(newspg.choose()).click().sendKeys(Keys.ENTER);
		 * Thread.sleep(2000); log.info("Status changed");
		 */
		newspg.allowcomment().click();
		Thread.sleep(2000);
		log.info("Comment switch clicked");
		newspg.type(type).click();
		log.info("Switched news category");
		Thread.sleep(2000);
		//newspg.attachpic().click();
		//Thread.sleep(3000);
		// autoIT
		// Runtime.getRuntime().exec(System.getProperty("user.dir") +
		// "/autoIT_AttachPicture.exe");
		// log.info("Image uploaded");
		newspg.tag().click();
		newspg.tag().sendKeys(title);
		newspg.tag().sendKeys(Keys.ENTER);
		log.info("Tag created");
		TakeScreenshot(formno + "_After_bottom_" + nameofCurrMethod);
		int numberoftags = newspg.NumberOfTag().size();
		int numberofpicsattached = newspg.NoOfPicAttached().size();

		ScrollToView(newspg.draftnews());
		newspg.draftnews().click();
		String url2 = driver.getCurrentUrl();

		if (url2.contains("form")) {
			log.error("System doesn't navigate to News collection page (Unexpected result)");
			ScrollToView(homepg.ProfileIcon());
			JavaExeClick(homepg.ProfileIcon());
			d.until(ExpectedConditions.visibilityOfElementLocated(homepg.logout));
			JavaExeClick(homepg.LogOut());
			log.info("Successfully logged out");
			d.until(ExpectedConditions.visibilityOfElementLocated(landpg.usrname));
			landpg.inputUsrname().sendKeys(usrnameAdmin);
			landpg.inputPw().sendKeys(pw);
			Thread.sleep(2000);
			landpg.clickSubmit().click();
			log.info("Successfully logged in");
			d.until(ExpectedConditions.visibilityOfElementLocated(homepg.dismisspopup));
			homepg.DissmissPopUp().click();
		} else {
			log.debug("System navigates to News collection page (Expected result)");
			if (newspg.NewsDraftTick(newsno).size() > 0) {
				log.debug("System successfully saved as a draft");
			} else {
				log.error("System did not save as a draft");
			}
			ScrollToView(newspg.NewsOption(newsno));
			d.until(ExpectedConditions.visibilityOf(newspg.NewsCount().get(newsno - 1)));
			TakeScreenshot("NewsColletionPage_" + formno + "_" + nameofCurrMethod);
			newspg.NewsCount().get(newsno - 1).click();
			Assert.assertTrue(newspg.header().getText().equals(title));
			log.info("Check title");
			Assert.assertTrue(newspg.content().getText().equals(text));
			log.info("Check content");
			Assert.assertTrue(numberoftags == newspg.NumberOfTagPreview().size());
			log.info("Check tag");
			// Assert.assertTrue(numberofpicsattached ==
			// newspg.NoOfPicAttachedPreview().size());
			// log.info("Check image attached");
			ScrollToView(homepg.ProfileIcon());
			JavaExeClick(homepg.ProfileIcon());
			d.until(ExpectedConditions.visibilityOfElementLocated(homepg.logout));
			JavaExeClick(homepg.LogOut());
			log.info("Successfully logged out");
			d.until(ExpectedConditions.visibilityOfElementLocated(landpg.usrname));
			landpg.inputUsrname().sendKeys(usrname);
			landpg.inputPw().sendKeys(pw);
			Thread.sleep(2000);
			landpg.clickSubmit().click();
			log.info("Successfully logged in with user account");
			d.until(ExpectedConditions.visibilityOfElementLocated(homepg.dismisspopup));
			homepg.DissmissPopUp().click();
			d.until(ExpectedConditions.visibilityOfElementLocated(homepg.navdropdown));
			JavaExeClick(homepg.NavDropDown());
			d.until(ExpectedConditions.visibilityOfElementLocated(homepg.navdropdownitems));
			homepg.NavDropDownItems().get(4).click();
			Assert.assertTrue(driver.getCurrentUrl().contains("tpqi-demo.jigsawgroups.work/intranet/news"));
			log.info("Arrived at News Page");
			if (newspg.newsById(formno).size() > 0) {
				log.error("User account can view draft news");
			} else {
				log.debug("User account cannot view draft news");
			}
			ScrollToView(homepg.ProfileIcon());
			JavaExeClick(homepg.ProfileIcon());
			d.until(ExpectedConditions.visibilityOfElementLocated(homepg.logout));
			JavaExeClick(homepg.LogOut());
			log.info("Successfully logged out from user account");
			d.until(ExpectedConditions.visibilityOfElementLocated(landpg.usrname));
			landpg.inputUsrname().sendKeys(usrnameAdmin);
			landpg.inputPw().sendKeys(pw);
			Thread.sleep(2000);
			landpg.clickSubmit().click();
			log.info("Successfully logged in with Admin account");
			d.until(ExpectedConditions.visibilityOfElementLocated(homepg.dismisspopup));
			homepg.DissmissPopUp().click();
		}

	}

	@Test(groups = "admin")
	public void SaveAndPublishNews() throws IOException, InterruptedException {
		WebDriverWait d = new WebDriverWait(driver, 40);
		int type = 2;
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		
		Thread.sleep(10000);
		d.until(ExpectedConditions.visibilityOf(newspg.NewsOption(newsno)));
		newspg.NewsOption(newsno).click();
		newspg.FixNews(newsno).click();
		String url = driver.getCurrentUrl();
		int i = url.indexOf("form/");
		i += 5;
		String formno = url.substring(i, i + 4);
		TakeScreenshot(formno + "_Before_above_" + nameofCurrMethod);
		newspg.title().clear();
		log.info("Clear input");
		newspg.publishnews().click();
		log.info("Clicked on publish news");
		Set<String> windows = driver.getWindowHandles();
		if (windows.size() == 1) {
			log.debug("Unable to proceed to the next page (Expected result)");
			TakeScreenshot("Require_field_alert");
		} else if (windows.size() > 1) {
			log.error("Proceed to new page (Unexpected result)");
		}
		newspg.title().sendKeys(title);
		log.info("Title changed");
		newspg.textarea().clear();
		newspg.textarea().sendKeys(text);
		log.info("Text changed");
		/*
		 * newspg.coverphoto().click(); Thread.sleep(3000); // autoIT
		 * Runtime.getRuntime().exec(System.getProperty("user.dir") +
		 * "/autoIT_AttachPicture.exe"); log.info("Cover photo uploaded");
		 */
		TakeScreenshot(formno + "_After_above_" + nameofCurrMethod);
		ScrollToView(newspg.tag());
		TakeScreenshot(formno + "_Before_bottom_" + nameofCurrMethod);
		/*
		 * newspg.status().click(); Thread.sleep(2000);
		 * a.moveToElement(newspg.choose()).click().sendKeys(Keys.ENTER);
		 * Thread.sleep(2000); log.info("Status changed");
		 */
		newspg.allowcomment().click();
		Thread.sleep(2000);
		log.info("Comment switch clicked");
		newspg.type(type).click();
		log.info("Switched news category");
		Thread.sleep(2000);
		/*
		 * newspg.attachpic().click(); Thread.sleep(3000); // autoIT
		 * Runtime.getRuntime().exec(System.getProperty("user.dir") +
		 * "/autoIT_AttachPicture.exe"); log.info("Image uploaded");
		 */
		newspg.tag().click();
		newspg.tag().sendKeys(title);
		newspg.tag().sendKeys(Keys.ENTER);
		log.info("Tag created");
		TakeScreenshot(formno + "_After_bottom_" + nameofCurrMethod);
		int numberoftags = newspg.NumberOfTag().size();
		int numberofpicsattached = newspg.NoOfPicAttached().size();

		ScrollToView(newspg.publishnews());
		newspg.publishnews().click();
		Thread.sleep(20000);
		String url2 = driver.getCurrentUrl();

		if (url2.contains("form")) {
			log.error("System doesn't navigate to News collection page (Unexpected result)");
			ScrollToView(homepg.ProfileIcon());
			JavaExeClick(homepg.ProfileIcon());
			d.until(ExpectedConditions.visibilityOfElementLocated(homepg.logout));
			JavaExeClick(homepg.LogOut());
			log.info("Successfully logged out");
			d.until(ExpectedConditions.visibilityOfElementLocated(landpg.usrname));
			landpg.inputUsrname().sendKeys(usrnameAdmin);
			landpg.inputPw().sendKeys(pw);
			Thread.sleep(2000);
			landpg.clickSubmit().click();
			log.info("Successfully logged in with Admin account");
			d.until(ExpectedConditions.visibilityOfElementLocated(homepg.dismisspopup));
			homepg.DissmissPopUp().click();
		} else {
			log.debug("System navigates to News collection page (Expected result)");
			if (newspg.NewsPublicTick(newsno).size() > 0) {
				log.debug("System successfully saved as public");
			} else {
				log.error("System did not save as public");
			}
			ScrollToView(newspg.NewsOption(newsno));
			TakeScreenshot("NewsColletionPage_" + formno + "_" + nameofCurrMethod);
			JavaExeClick(newspg.NewsCount().get(newsno - 1));
			d.until(ExpectedConditions.visibilityOf(newspg.header()));
			Assert.assertTrue(newspg.header().getText().equals(title));
			log.info("Check title");
			Assert.assertTrue(newspg.content().getText().equals(text));
			log.info("Check content");
			Assert.assertTrue(numberoftags == newspg.NumberOfTagPreview().size());
			log.info("Check tag");
			// Assert.assertTrue(numberofpicsattached ==
			// newspg.NoOfPicAttachedPreview().size());
			log.info("Check image attached");
			ScrollToView(homepg.ProfileIcon());
			JavaExeClick(homepg.ProfileIcon());
			d.until(ExpectedConditions.visibilityOfElementLocated(homepg.logout));
			JavaExeClick(homepg.LogOut());
			log.info("Successfully logged out");
			d.until(ExpectedConditions.visibilityOfElementLocated(landpg.usrname));
			landpg.inputUsrname().sendKeys(usrname);
			landpg.inputPw().sendKeys(pw);
			Thread.sleep(2000);
			landpg.clickSubmit().click();
			log.info("Successfully logged in with user account");
			d.until(ExpectedConditions.visibilityOfElementLocated(homepg.dismisspopup));
			homepg.DissmissPopUp().click();
			d.until(ExpectedConditions.visibilityOfElementLocated(homepg.navdropdown));
			JavaExeClick(homepg.NavDropDown());
			d.until(ExpectedConditions.visibilityOfElementLocated(homepg.navdropdownitems));
			homepg.NavDropDownItems().get(4).click();
			Assert.assertTrue(driver.getCurrentUrl().contains("tpqi-demo.jigsawgroups.work/intranet/news"));
			log.info("Arrived at News Page");
			Thread.sleep(10000);
			if (newspg.newsById(formno).size() > 0) {
				log.debug("User account can view public news");
			} else {
				log.error("User account cannot view public news");
			}
			ScrollToView(homepg.ProfileIcon());
			JavaExeClick(homepg.ProfileIcon());
			d.until(ExpectedConditions.visibilityOfElementLocated(homepg.logout));
			JavaExeClick(homepg.LogOut());
			log.info("Successfully logged out from user account");
			d.until(ExpectedConditions.visibilityOfElementLocated(landpg.usrname));
			landpg.inputUsrname().sendKeys(usrnameAdmin);
			landpg.inputPw().sendKeys(pw);
			Thread.sleep(2000);
			landpg.clickSubmit().click();
			log.info("Successfully logged in with Admin account");
			d.until(ExpectedConditions.visibilityOfElementLocated(homepg.dismisspopup));
			homepg.DissmissPopUp().click();
		}
	}

	@Test(groups = "admin")
	public void DeleteNews() throws InterruptedException {
		WebDriverWait d = new WebDriverWait(driver, 20);

		d.until(ExpectedConditions.visibilityOf(newspg.NewsOption(newsno)));
		newspg.NewsOption(newsno).click();
		newspg.FixNews(newsno).click();
		String url = driver.getCurrentUrl();
		int i = url.indexOf("form/");
		i += 5;
		String formno = url.substring(i, i + 4);

		newspg.DeleteNews().click();
		d.until(ExpectedConditions.visibilityOf(newspg.DeleteForm()));
		// Try cancel deletion
		newspg.CancelDelete().click();
		if (url.equals(driver.getCurrentUrl())) {
			log.debug("Sucessfully cancelled news deletion");
			Thread.sleep(2000);
			newspg.DeleteNews().click();
			d.until(ExpectedConditions.visibilityOf(newspg.DeleteForm()));
			newspg.ConfirmDelete().click();
			if (driver.getCurrentUrl().contains("https://tpqi-demo.jigsawgroups.work/intranet/news/index")) {
				log.debug("Arrived at News Collection page");
				if (newspg.newsById(formno).size() > 0) {
					log.error("News still appear at News Collection page");
				} else {
					log.debug("News was successfully deleted");
				}
			} else {
				log.error("Didn't arrive at News Collection page");
			}
		} else {
			log.error("Not able to cancel news deletion");
		}

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

			a.keyDown(Keys.LEFT_CONTROL).click(newspg.NewsCount().get(i)).keyUp(Keys.LEFT_CONTROL).build().perform();
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
	@AfterMethod(groups = "admin")
	public void RedirectToNewsPage() throws InterruptedException {
		/*WebDriverWait d = new WebDriverWait(driver, 20);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		ScrollToView(homepg.ProfileIcon());
		JavaExeClick(homepg.ProfileIcon());
		d.until(ExpectedConditions.visibilityOfElementLocated(homepg.logout));
		JavaExeClick(homepg.LogOut());
		log.info("Successfully logged out");
		driver.close();
		driver = null;
		log.info("Browser Closed");*/
		driver.get("https://tpqi-demo.jigsawgroups.work/intranet/news?");
		Assert.assertTrue(driver.getCurrentUrl().contains("tpqi-demo.jigsawgroups.work/intranet/news"));
		log.info("Arrived at News Page");
	}
	
	@AfterTest(groups = "admin")
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

//*[@id="item-4190"]/div/div[1]/button/i