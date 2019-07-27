package testcases;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import pageObjects.HomePage;
import pageObjects.LandingPage;
import pageObjects.VehicleBookingPage;
import resources.Base;

public class TestBookingPage extends Base {
	String usrname = "aptcollie@gmail.com";
	String pw = "admin";
	private static Logger log = LogManager.getLogger(TestHomePage.class.getName());
	HomePage homepg = new HomePage();
	LandingPage landpg = new LandingPage();
	VehicleBookingPage bookpg = new VehicleBookingPage();

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
		JavaExeClick(homepg.NavDropDown());
		Thread.sleep(1000);
		homepg.NavDropDownItems().get(7).click();
		log.info("Arrived at Vehicle Booking Page");
	}

	@Test
	public void BookVehicle() throws InterruptedException {
		WebDriverWait d = new WebDriverWait(driver, 20);
		String purpose = "Automated Test";
		String departdate = "29/06/19";
		String departtime = "13:00";
		String arrivedate = "30/06/19";
		String arrivetime = "13:00";
		String passengernames = "Guy QC";
		String start = "Rangsit";
		String destination = "Sathorn";
		String baggage = "2 backpacks";
		Actions a = new Actions(driver);
		
		d.until(ExpectedConditions.visibilityOfElementLocated(bookpg.searchforcar));
		bookpg.SearchForCar().click();
		d.until(ExpectedConditions.visibilityOfElementLocated(bookpg.book));
		JavaExeClick(bookpg.Book());
		bookpg.CheckCar().click();
		log.info("Car info: " + bookpg.CarData().getText());
		bookpg.Purpose().sendKeys(purpose);
		bookpg.DepartDate().click();
		Thread.sleep(1000);
		bookpg.DepartDate().sendKeys(departdate);
		bookpg.DepartDate().sendKeys(Keys.ENTER);
		bookpg.DepartTime().click();
		Thread.sleep(1000);
		bookpg.DepartTime().sendKeys(departtime);
		bookpg.DepartTime().sendKeys(Keys.ENTER);
		bookpg.PassengerName().sendKeys(passengernames);
		bookpg.Start().sendKeys(start);
		bookpg.Destination().sendKeys(destination);
		bookpg.PickUp().sendKeys(start);
		bookpg.Baggage().sendKeys(baggage);
		ScrollToView(bookpg.Submit());
		Thread.sleep(800);
		bookpg.Submit().click();
		log.info("Successfully booked a Car");
		Thread.sleep(10000);

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
