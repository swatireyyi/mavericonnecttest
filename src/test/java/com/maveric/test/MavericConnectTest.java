package com.maveric.test;


import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.maveric.pageobjects.CorporatePointOfContact;
import com.maveric.pageobjects.MavericHomePage;
import com.maveric.pageobjects.MavericLoginFormPage;

import com.maveric.utilities.PropertiesHandler;


public class MavericConnectTest {
//@Test(priority=2)
public void positiveCredentialCheck() throws IOException {
System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
Properties prop = PropertiesHandler.getPropertiesDetails("Utilities/data.properties");
String url = prop.getProperty("url");
String userName = prop.getProperty("username");
String passWord = prop.getProperty("password");
String expectedTitle = prop.getProperty("expectedtitle");
WebDriver driver = new ChromeDriver(); //opening the browser by constructor //runtime polymorphism
WebDriverWait wait = new WebDriverWait(driver, 50); // explicit wait time
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //implicit wait time
//driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS); //controlling page load time , here if it doesnt load within 5 sec. it will abort the execution
driver.manage().window().maximize();
System.setProperty("webdriver.gecko.driver","Driver/geckodriver.exe");
driver.get(url); 


MavericLoginFormPage login = new MavericLoginFormPage(driver, wait);
System.out.println(login.getNoOfLinks());
login.sendUserName(userName);
login.sendPassWord(passWord);
login.clickOnLogin();
WebElement loginBtn = driver.findElement(By.name("yt0"));
loginBtn.click();
MavericHomePage portal = new MavericHomePage(driver, wait);
String actualTitle = portal.waitForQmsDisplayAndGetTitle();
if (actualTitle.equals(expectedTitle))
{
System.out.println("Page title matched");
}
else
{
System.out.println("Page title not matched");
} 
}


@Test(dataProvider="testCaseForNegativeCredentials")
public void NegativeCredentialCheck(String userName, String password) throws IOException, InterruptedException
{
System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
Properties prop = PropertiesHandler.getPropertiesDetails("Utilities/data.properties");
WebDriver driver = new ChromeDriver(); //opening the browser by constructor //runtime polymorphism
WebDriverWait wait = new WebDriverWait(driver, 50); // explicit wait time
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //implicit wait time
//driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS); //controlling page load time , here if it doesnt load within 5 sec. it will abort the execution
driver.manage().window().maximize();
String url = prop.getProperty("url");
//String userName = prop.getProperty("nusername");
//String passWord = prop.getProperty("npassword");
String expectedText = prop.getProperty("expectedtext");
driver.get(url);
MavericLoginFormPage login = new MavericLoginFormPage(driver, wait);
login.sendUserName(userName);
login.sendPassWord(password);
login.clickOnLogin();
Thread.sleep(5000);
Assert.assertEquals(login.errorMsgExpected(), expectedText, "Validation on NegativeCredentialCheck");
}
}
