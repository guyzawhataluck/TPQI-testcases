package resources;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Base {

	public static WebDriver driver;
	private String browser = "chrome";
	protected String url = "https://tpqi-demo.jigsawgroups.work/";
	String userdir = System.getProperty("user.dir");
	
	public WebDriver initializeDriver() {
		
		DesiredCapabilities ch = DesiredCapabilities.chrome();
		// ch.acceptInsecureCerts();
		ch.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		ch.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

		// Belows to your local browser
		ChromeOptions c = new ChromeOptions();
		c.merge(ch);

		if (browser == "chrome") {
			System.setProperty("webdriver.chrome.driver", userdir+"/chromedriver.exe");
			driver = new ChromeDriver(c);
		} else if (browser == "firefox") {

		} else if (browser == "IE") {

		}

		return driver;
	}

	public void TakeScreenshot(String result) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(src, new File(userdir+"/Pictures/" + result + ".png"));
	}

	public void JavaExeClick(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void ScrollToView(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(false);", element);
	}
}
