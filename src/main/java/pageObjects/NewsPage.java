package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import resources.Base;

public class NewsPage extends Base {
	By newscount = By.xpath("//li[@class='masonry-item']");

	public WebElement News(int i) {
		return driver.findElement(By.xpath("//ul[@id='ul-items-warp']/li[" + i + "]/div/div[@class='card-block']/div/a"));

	}

	public List<WebElement> NewsCount() {
		return driver.findElements(newscount);

	}
}
