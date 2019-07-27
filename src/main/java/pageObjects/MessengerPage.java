package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import resources.Base;

public class MessengerPage extends Base {
	public By chattab = By.id("chat_tab");
	public By stafftab = By.id("staff_tab");
	public By grouptab = By.id("group_tab");
	public By selectchat = By.xpath("//div[@id='chatList']/ul/li");
	public By selectstaff = By.xpath("//div[@id='staffList']/ul/li");
	public By selectgroup = By.xpath("//div[@id='groupList']/ul/li");
	public By textarea = By.xpath("//*[@id=\"chat_box\"]");
	public By likebutton = By.cssSelector("button[onclick='buttonLike()']");
	public By numfiles = By.id("num_files");
	public By filelist = By.xpath("//*[@id=\"file_list\"]/tbody/tr");
	public By numphotovideo = By.id("num_photo_video");
	public By photovideolist = By.xpath("//*[@id=\"photo_list\"]/a");

	public WebElement ChatTab() {
		return driver.findElement(chattab);
	}

	public WebElement StaffTab() {
		return driver.findElement(stafftab);
	}

	public WebElement GroupTab() {
		return driver.findElement(grouptab);
	}

	public List<WebElement> SelectChat() {
		return driver.findElements(selectchat);
	}

	public List<WebElement> SelectStaff() {
		return driver.findElements(selectstaff);
	}

	public WebElement TextArea() {
		return driver.findElement(textarea);
	}

	public WebElement Like() {
		return driver.findElement(likebutton);
	}

	public WebElement NumFiles() {
		return driver.findElement(numfiles);
	}

	public List<WebElement> FileList() {
		return driver.findElements(filelist);
	}

	public WebElement NumPhotoVideo() {
		return driver.findElement(numphotovideo);
	}

	public List<WebElement> PhotoVideoList() {
		return driver.findElements(photovideolist);
	}
}
