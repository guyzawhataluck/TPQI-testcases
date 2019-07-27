package pageObjects;

import org.openqa.selenium.*;

import resources.Base;

public class VehicleBookingPage extends Base {
	int carnumber = 3;

	public By searchforcar = By.xpath("/html/body/div[2]/div[1]/ul/li[2]/a");
	public By book = By.xpath("//div[@id='exampleTabsTwo']/table/tbody/tr[" + carnumber + "]/td[5]/button");
	public By checkcardata = By.xpath("//*[@data-id='bc-car_id']");
	By cardata = By.xpath("//*[@id=\"form-bookingcar\"]/div[1]/div/div/div/div/ul/li/a/span[1]");
	By purpose = By.id("bc-purpose");
	By departdate = By.id("bc-departure_date");
	By departtime = By.id("bc-departure_time");
	By arrivedate = By.id("bc-departure_date_end");
	By arrivetime = By.id("bc-departure_time_end");
	By passengername = By.id("bc-passenger_list");
	By start = By.xpath("//*[@id=\"form-bookingcar\"]/div[7]/div/textarea");
	By destination = By.xpath("//*[@id=\"form-bookingcar\"]/div[8]/div/textarea");
	By pickup = By.id("bc-pickup");
	By baggage = By.id("bc-baggage");
	By remark = By.id("bc-remark");
	By submit = By.xpath("//*[@id=\"form-bookingcar\"]/div[13]/div/button[1]");

	public WebElement SearchForCar() {
		return driver.findElement(searchforcar);
	}

	public WebElement Book() {
		return driver.findElement(book);
	}

	public WebElement CheckCar() {
		return driver.findElement(checkcardata);
	}

	public WebElement CarData() {
		return driver.findElement(cardata);
	}

	public WebElement Purpose() {
		return driver.findElement(purpose);
	}

	public WebElement DepartDate() {
		return driver.findElement(departdate);
	}

	public WebElement DepartTime() {
		return driver.findElement(departtime);
	}

	public WebElement ArriveDate() {
		return driver.findElement(arrivedate);
	}

	public WebElement ArriveTime() {
		return driver.findElement(arrivetime);
	}

	public WebElement PassengerName() {
		return driver.findElement(passengername);
	}

	public WebElement Start() {
		return driver.findElement(start);
	}

	public WebElement Destination() {
		return driver.findElement(destination);
	}

	public WebElement PickUp() {
		return driver.findElement(pickup);
	}

	public WebElement Baggage() {
		return driver.findElement(baggage);
	}

	public WebElement Remark() {
		return driver.findElement(remark);
	}

	public WebElement Submit() {
		return driver.findElement(submit);
	}
}