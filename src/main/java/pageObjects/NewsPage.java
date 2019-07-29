package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import resources.Base;

public class NewsPage extends Base {
	By newscount = By.xpath("//h4[@class='card-title']");
	By title = By.id("title");
	By textarea = By.xpath("//*[@id=\"cms-item-form\"]/div[2]/div/div[3]/div[2]");
	By draftfixnews = By.id("btn-save-draft");
	By previewnews = By.id("btn-preview");
	By publish = By.id("btn-public");
	By attachpic = By.id("attachment");
	By coverphoto = By.id("cover-photo");
	By status = By.xpath(
			"/html/body/div[2]/div[2]/div/div/div[1]/div[2]/div[1]/div/section/div[3]/div[2]/div[1]/ul/li[2]/div/div/ins");
	By choose = By.xpath("//*[@id=\"permission_custom_select\"]/span/span[1]/span/ul");
	By allowcomment = By
			.xpath("/html/body/div[2]/div[2]/div/div/div[1]/div[2]/div[1]/div/section/div[4]/div[2]/div/span");
	By tag = By.id("tags-tokenfield");
	By activity = By.className("timeline-content");
	By activitycount = By
			.xpath("/html/body/div[2]/div[2]/div/div/div[1]/div[2]/div[1]/div/section/div[7]/div[2]/ul/li");
	By header = By.xpath("/html/body/div[2]/div[2]/div/div/div[2]/div[1]/div/div[1]/div/div/div/form/div[1]/div/h1");
	By content = By.id("summernote");
	By noofpicattached = By.xpath("/html/body/div[2]/div[2]/div/div/div[2]/div[2]/div/table/tbody/tr");
	By noofpicattachedpreview = By.xpath("/html/body/div[2]/div[2]/div/div/div[2]/div[1]/div/section/table/tbody/tr");
	By nooftag = By.xpath("/html/body/div[2]/div[2]/div/div/div[1]/div[2]/div[1]/div/section/div[6]/div[2]/div/div");
	By nooftagpreview = By
			.xpath("/html/body/div[2]/div[2]/div/div/div[1]/div[2]/div[1]/div/section/div[2]/div[2]/span");
	By delete = By.id("btn-delete");
	By deleteform = By.id("form-del-cms");
	By canceldelete = By.xpath("//*[@id=\"form-del-cms\"]/div/div[3]/button[1]");
	By confirmdelete = By.xpath("//*[@id=\"form-del-cms\"]/div/div[3]/button[2]");

	public WebElement DeleteNews() {
		return driver.findElement(delete);
	}

	public WebElement DeleteForm() {
		return driver.findElement(deleteform);
	}

	public WebElement CancelDelete() {
		return driver.findElement(canceldelete);
	}

	public WebElement ConfirmDelete() {
		return driver.findElement(confirmdelete);
	}

	public List<WebElement> newsById(String id) {
		return driver.findElements(By.xpath("//li[@data-id='" + id + "']"));
	}

	public List<WebElement> activity() {
		return driver.findElements(activity);
	}

	public List<WebElement> activitycount() {
		return driver.findElements(activitycount);
	}

	public List<WebElement> NoOfPicAttached() {
		return driver.findElements(noofpicattached);
	}

	public List<WebElement> NoOfPicAttachedPreview() {
		return driver.findElements(noofpicattachedpreview);
	}

	public List<WebElement> headercount() {
		return driver.findElements(header);
	}

	public WebElement NewsOption(int i) {
		return driver.findElement(By.xpath("//ul[@id='ul-items-warp']/li[" + i + "]/div/div[1]/button/i"));
	}

	public List<WebElement> NewsPublicTick(int i) {
		return driver.findElements(By.xpath("//ul[@id='ul-items-warp']/li[" + i + "]/div/div[1]/div/a[3]/i[2]"));
	}

	public List<WebElement> NewsDraftTick(int i) {
		return driver.findElements(By.xpath("//ul[@id='ul-items-warp']/li[" + i + "]/div/div[1]/div/a[4]/i[2]\""));
	}

	public WebElement FixNews(int i) {
		return driver.findElement(By.xpath("//ul[@id='ul-items-warp']/li[" + i + "]/div/div[1]/div/a[1]"));
	}

	public WebElement News(int i) {
		return driver
				.findElement(By.xpath("//ul[@id='ul-items-warp']/li[" + i + "]/div/div[@class='card-block']/div/a"));

	}

	public List<WebElement> NumberOfTag() {
		return driver.findElements(nooftag);
	}

	public List<WebElement> NumberOfTagPreview() {
		return driver.findElements(nooftagpreview);
	}

	public WebElement header() {
		return driver.findElement(header);
	}

	public WebElement content() {
		return driver.findElement(content);
	}

	public WebElement draftnews() {
		return driver.findElement(draftfixnews);
	}

	public WebElement previewnews() {
		return driver.findElement(previewnews);
	}

	public WebElement publishnews() {
		return driver.findElement(publish);
	}

	public WebElement title() {
		return driver.findElement(title);
	}

	public WebElement textarea() {
		return driver.findElement(textarea);
	}

	public WebElement attachpic() {
		return driver.findElement(attachpic);
	}

	public WebElement coverphoto() {
		return driver.findElement(coverphoto);
	}

	public WebElement status() {
		return driver.findElement(status);
	}

	public WebElement choose() {
		return driver.findElement(choose);
	}

	public WebElement allowcomment() {
		return driver.findElement(allowcomment);
	}

	public WebElement type(int i) {
		return driver.findElement(
				By.xpath("/html/body/div[2]/div[2]/div/div/div[1]/div[2]/div[1]/div/section/div[5]/div/div[2]/div[" + i
						+ "]/div/ins"));
	}

	public WebElement tag() {
		return driver.findElement(tag);
	}

	public List<WebElement> NewsCount() {
		return driver.findElements(newscount);

	}
}
