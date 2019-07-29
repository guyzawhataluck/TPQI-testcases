package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import resources.Base;

public class HomePage extends Base {
	private int postnumber = 3;
	private int emojiorder = 2;
	public By dismisspopup = By.id("btn-dismiss-welcome-msg");
	public By navdropdown = By.xpath("//*[@id=\"toggleMenubar\"]/a/i");
	public By navdropdownitems = By.cssSelector("li[class*='site-menu-item']");
	public By searchicon = By.cssSelector("a[href='/search']");
	public By navhome = By.cssSelector("a[href*='/home']");
	public By quickaddicon = By.xpath("//*[@id=\"site-navbar-collapse\"]/ul[2]/li[2]/a/i");
	public By quickadditems = By.xpath("//*[@id=\"site-navbar-collapse\"]/ul[2]/li[2]/div/div[2]/div[1]/div/div/a");
	public By closepopup = null;
	public By profileicon = By.xpath("//*[@id=\"site-navbar-collapse\"]/ul[2]/li[3]/a/span");
	public By bookmarkicon = By.cssSelector("a[data-url*='bookmarks']");
	public By chaticon = By.cssSelector("a[data-url*='chat']");
	public By chattab = By.xpath("//*[@id=\"notiChat_chat_tab_container\"]/li[1]");
	public By notiicon = By.xpath("//*[@id=\"site-navbar-collapse\"]/ul[2]/li[6]/a");
	public By sitemapicon = By.xpath("//*[@id=\"site-navbar-collapse\"]/ul[2]/li[6]/a");
	public By sidenavcontent = By.cssSelector("li[class*='scrollable-content']");
	public By postbox = By.xpath("/html/body/div[2]/div[3]/div/div[1]/div/section[1]");
	public By postText = By.id("postFormArea");
	public By privacyradiobtn = By.cssSelector("[type='radio']");
	public By commentswitch = By.xpath("/html/body/div[9]/div/form/div[2]/div[8]/div/div/span/small");
	public By btnpost = By.id("btnpost");
	public By commentbox = By
			.xpath("/html/body/div[2]/div[3]/div/div[1]/div/div/div[" + postnumber + "]/div[2]/ul/li[1]/a");
	public By writecomment = By
			.xpath("/html/body/div[2]/div[3]/div/div[1]/div/div/div[" + postnumber + "]/div[3]/div/div[2]/textarea");
	public By emoji = By.xpath(
			"/html/body/div[2]/div[3]/div/div[1]/div/div/div[" + postnumber + "]/div[3]/div/div[2]/div/button[1]");
	public By chooseemoji = By.xpath("/html/body/div[22]/div[1]/section[3]/div/em[" + emojiorder + "]/span");
	public By sendcomment = By.xpath(
			"/html/body/div[2]/div[3]/div/div[1]/div/div/div[" + postnumber + "]/div[3]/div/div[2]/div/button[3]");
	public By logout = By.cssSelector("a[href*='logout']");

	public WebElement DissmissPopUp() {
		return driver.findElement(dismisspopup);
	}

	public WebElement NavDropDown() {
		return driver.findElement(navdropdown);
	}

	public List<WebElement> NavDropDownItems() {
		return driver.findElements(navdropdownitems);
	}

	public WebElement SearchIcon() {
		return driver.findElement(searchicon);
	}

	public WebElement NavHome() {
		return driver.findElement(navhome);
	}

	public WebElement QuickAddIcon() {
		return driver.findElement(quickaddicon);
	}

	public List<WebElement> QuickAddItems() {
		return driver.findElements(quickadditems);
	}

	public WebElement ClosePopUp() {
		return driver.findElement(closepopup);
	}

	public WebElement ProfileIcon() {
		return driver.findElement(profileicon);
	}

	public WebElement BookMarkIcon() {
		return driver.findElement(bookmarkicon);
	}

	public WebElement ChatIcon() {
		return driver.findElement(chaticon);
	}
	
	public WebElement ChatTab() {
		return driver.findElement(chattab);
	}
	
	public WebElement NotiIcon() {
		return driver.findElement(notiicon);
	}

	public WebElement SiteMapIcon() {
		return driver.findElement(sitemapicon);
	}

	public WebElement SideNavContent() {
		return driver.findElement(sidenavcontent);
	}

	public WebElement PostBox() {
		return driver.findElement(postbox);
	}

	public WebElement PostText() {
		return driver.findElement(postText);
	}

	public List<WebElement> RadioBtn() {
		return driver.findElements(privacyradiobtn);
	}

	public WebElement CommentBox() {
		return driver.findElement(commentbox);
	}

	public WebElement WriteComment() {
		return driver.findElement(writecomment);
	}

	public WebElement Emoji() {
		return driver.findElement(emoji);
	}

	public WebElement ChooseEmoji() {
		return driver.findElement(chooseemoji);
	}

	public WebElement SendComment() {
		return driver.findElement(sendcomment);
	}

	public WebElement CommentSwitch() {
		return driver.findElement(commentswitch);
	}

	public WebElement BtnPost() {
		return driver.findElement(btnpost);
	}

	public WebElement LogOut() {
		return driver.findElement(logout);
	}

}
