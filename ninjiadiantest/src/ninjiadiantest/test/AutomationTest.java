package ninjiadiantest.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AutomationTest {

	public WebDriver driver;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AutomationTest njdtest=new AutomationTest();
		njdtest.logintest("18621666529", "123456");
	}
	
	//µÇÂ¼²âÊÔ
	public void logintest(String user_name,String user_passwd){
		
		WebElement loginbutton;
		WebElement username;
		WebElement passwd;
		WebElement loginsubmit;
		WebElement loginresult;
		
		driver=new FirefoxDriver();
		driver.get("http://www.ninjiadian.com");
		driver.manage().window().maximize();
		System.out.println("open homepage:"+driver.getTitle());
		
		loginbutton=driver.findElement(By.id("login"));
		loginbutton.click();
		
		username=driver.findElement(By.name("mobilePhone"));
		username.sendKeys(user_name);
		
		passwd=driver.findElement(By.name("loginPassword"));
		passwd.sendKeys(user_passwd);
		
		loginsubmit=driver.findElement(By.id("login_form"));
		loginsubmit.submit();
		
		loginresult=driver.findElement(By.xpath("//boday.text("));
		System.out.println("the result of login is: "+loginresult);
		
		
		
	}
	

}
