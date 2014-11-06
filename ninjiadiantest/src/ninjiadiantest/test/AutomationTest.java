package ninjiadiantest.test;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


/**
* @author Frank_Xiong
*
*/
public class AutomationTest {
     public static void main(String[] args) {
          // TODO Auto-generated method stub
          AutomationTest njdTest = new AutomationTest();
          try {
               njdTest.loginUser("18621666529", "123456");
               njdTest.registUser(getPhoneNumber(), "111111", "111111");
          } catch (InterruptedException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
          }
     }

     /**
     * 用户登录testcase
     * @param username
     * @param passwd
     * @throws InterruptedException
     */
     public void loginUser(String username, String passwd)
               throws InterruptedException {
          System.out
                    .println("<---------------------------user login test case run--------------------------------->");
          WebDriver firefoxDriver = new FirefoxDriver();
          firefoxDriver.get("http://www.ninjiadian.com");
          Thread.sleep(3000);
          firefoxDriver.manage().window().maximize();
          //open homepage and take a screenshot
          screenshotTake((TakesScreenshot) firefoxDriver, "Homepage_photo.png");

          WebElement login_button = firefoxDriver.findElement(By.id("login"));
          login_button.click();
          Thread.sleep(3000);
          screenshotTake((TakesScreenshot) firefoxDriver, "Login_Photo.png");
          System.out.println("打开网页：" + firefoxDriver.getTitle());
          WebElement username_input = firefoxDriver.findElement(By
                    .name("mobilePhone"));
          username_input.sendKeys(username);
          WebElement passwd_input = firefoxDriver.findElement(By
                    .name("loginPassword"));
          passwd_input.sendKeys(passwd);
          // 提交登录form表单
          firefoxDriver.findElement(By.id("login_form")).submit();

          Thread.sleep(3000);
          String loginResultString = firefoxDriver
                    .findElement(By.xpath("//body")).getText();
          if ("success".equals(loginResultString)) {
               System.out.println("case result: pass");
               screenshotTake((TakesScreenshot) firefoxDriver,
                         "Pass_Login_Result_Photo.png");
          } else {
               System.out.println("case result: fail");
               screenshotTake((TakesScreenshot) firefoxDriver,
                         "Fail_Login_Result_Photo.png");
          }

          // 退出
          firefoxDriver.quit();
     }

     /**
     * 用户注册testcase
     * @param userName
     * @param passwd
     * @param cfmPasswd
     * @throws InterruptedException
     */
     public void registUser(String userName, String passwd, String cfmPasswd)
               throws InterruptedException {
          System.out
                    .println("<---------------------------regist new user test case run--------------------------------->");
          WebDriver firefoxDriver = new FirefoxDriver();
          firefoxDriver.get("http://www.ninjiadian.com");
          System.out.println("the title is: " + firefoxDriver.getTitle());
          Thread.sleep(3000);
          firefoxDriver.manage().window().maximize();

          firefoxDriver.findElement(By.id("regist")).click();
          firefoxDriver
                    .findElement(
                              By.xpath("//form[@id='regist_form']/div/input[@name='mobilePhone']"))
                    .sendKeys(userName);
          firefoxDriver
                    .findElement(
                              By.xpath("//form[@id='regist_form']/div[2]/input[@name='loginPassword']"))
                    .sendKeys(passwd);
          firefoxDriver.findElement(By.name("confirmLoginPassword")).sendKeys(
                    cfmPasswd);
          firefoxDriver.findElement(By.id("regist_form")).submit();

          Thread.sleep(3000);
          String registResultString = firefoxDriver.findElement(
                    By.xpath("//body")).getText();
          if ("success".equals(registResultString)) {
               System.out.println("case result: pass");
               screenshotTake((TakesScreenshot) firefoxDriver,
                         "Pass_Regist_Result_Photo.png");
          } else {
               System.out.println("case result: fail");
               screenshotTake((TakesScreenshot) firefoxDriver,
                         "Fail_Regist_Result_Photo.png");
          }

          firefoxDriver.quit();
     }

     /**
     * @return
     * 自动生成手机号码
     */
     public static String getPhoneNumber() {
          String phoneNumber = "1";		//phone number for register
          int number;		//the second number of phone number
          int tempArr[]={3,5,8};		//the second number of phone number must be 3 or 5 or 8
          int tempData;		//index
          for (int i = 0; i < 10; i++) {
        	  if (i==0) {
        		//get index
        		tempData=new Random().nextInt(tempArr.length);
        		//get second number of phone number
				number=tempArr[tempData];
			}else {
	            number = new Random().nextInt(10);
			}

               phoneNumber += number;
          }

          // System.out.println("random phonenumber is: "+phoneNumber);

          return phoneNumber;
     }

     /**
     * @param driverName
     * @param fileName
     * webdriver截图
     */
     public static void screenshotTake(TakesScreenshot driverName,
               String fileName) {
          //截屏
          File scsPhoto = driverName.getScreenshotAs(OutputType.FILE);
          try {
               System.out.println("save snapshot path is: E:/temp/" + fileName);
               //save screenshot
               FileUtils.copyFile(scsPhoto, new File("E:\\temp\\" + fileName));
          } catch (IOException e) {
               // TODO: handle exception
               System.out.println("can't save screenshot!");
               e.printStackTrace();
          }
     }

}