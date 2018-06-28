import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
public class BingTranslator{
	WebDriver driver;
	@BeforeClass
	public void init()
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\shivangigautam\\Downloads\\chromedriver_win32\\chromedriver.exe");  //driver,path
			driver=new ChromeDriver();             //launch the driver
			driver.manage().window().maximize();
		}
	@AfterClass
	public void close()
	{
		driver.close();        //end the test
	}
	@Test
	public void step01_LaunchURL()
	{
		driver.get("https://www.bing.com/translator");         //opens the page of bing translator
	}
	@Test
	public void step02_ClickOnText()
	{
		driver.findElement(By.cssSelector("a[title='Text']")).click();     //clicks on text button
		String expectedURL="https://www.bing.com/translator";
		Assert.assertEquals(expectedURL,  driver.getCurrentUrl(), "Page must not have redirected");     //actual,expected
	}
	@Test
	public void step03_ClickOnConversation()
	{
		driver.findElement(By.cssSelector("a[title='Conversation']")).click();       //clicks on conversation button
		String expectedURL="https://translator.microsoft.com/";
		Assert.assertEquals(expectedURL,driver.getCurrentUrl());
		driver.get("https://www.bing.com/translator");
	}
	@Test
	public void step04_ClickOnApps()
	{
		driver.findElement(By.cssSelector("a[title='Apps']")).click();      //clicks on app button
		String expectedURL="https://translator.microsoft.com/apps/";
		Assert.assertEquals(expectedURL,driver.getCurrentUrl());
		driver.get("https://www.bing.com/translator");
	}
	@Test
	public void step05_ClickOnForBusiness()
	{
		driver.findElement(By.cssSelector("a[title='For business']")).click();    //clicks on for business button
		String expectedURL="https://www.microsoft.com/en-us/translator/home.aspx";
		Assert.assertEquals(expectedURL,driver.getCurrentUrl());
		driver.get("https://www.bing.com/translator");
	}
	@Test
	public void step05_ClickOnHelp()
	{
		driver.findElement(By.cssSelector("a[title='Help']")).click();    //clicks on for business button
		String expectedURL="https://translator.microsoft.com/help/bing/";
		Assert.assertEquals(expectedURL,driver.getCurrentUrl());
		driver.get("https://www.bing.com/translator");
	}
	
	@Test
	public void step06_dropdown() throws InterruptedException
	{
		Select drpDwn=new Select(driver.findElement(By.id("t_sl")));
		drpDwn.selectByVisibleText("Danish");
		Thread.sleep(5000);
		driver.findElement(By.id("t_sl")).click();       //checks the functionality of dropdown button
	}
	@Test
	public void step07_TextBox() throws InterruptedException
	{
		driver.findElement(By.cssSelector("textarea#t_sv")).sendKeys("Kvalitet");
		Thread.sleep(5000);
	}
	@Test
	public void step08_SwapButton() throws InterruptedException
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;    //Creating the JavascriptExecutor interface object by Type casting
		String Text1=(String) js.executeScript("return document.getElementById('t_sv').value");  //return is used because we want to return a value
		String Text2=(String) js.executeScript("return document.getElementById('t_tv').value");  //return is used because we want to return a value
		Assert.assertEquals("Quality",Text2);
		System.out.println(Text1);
		System.out.println(Text2);
		driver.findElement(By.id("t_revIcon")).click();    //click on reverse button
		Thread.sleep(1000);
		System.out.println(Text1);
		System.out.println(Text2);
		if(Text1==Text2)
		{
			System.out.println("Reverse button not working");
		}
		
	}

}

