package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import resources.Base;

public class LandingPage extends Base {
	public By usrname = By.id("login_string");
	public By pw = By.id("login_pass");
	public By submit = By.id("btn-login");
	// remember usrname + pw
	By remember = By.id("remember");
	By newslinks = By.tagName("h4");
	By allnews = By.cssSelector("a[href='/cms-public']");
	public By alert = By.xpath("//*[@id=\"form-login\"]/div[1]/div");

	public WebElement inputUsrname() {
		return driver.findElement(usrname);
	}

	public WebElement inputPw() {
		return driver.findElement(pw);
	}

	public WebElement clickSubmit() {
		return driver.findElement(submit);
	}

	public WebElement clickRemember() {
		return driver.findElement(remember);
	}

	public List<WebElement> Newslinks() {
		return driver.findElements(newslinks);
	}

	public WebElement clickAllnews() {
		return driver.findElement(allnews);
	}

	public WebElement alertBox() {
		return driver.findElement(alert);
	}

}
