package com.maveric.pageobjects;


import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.maveric.utilities.PropertiesHandler;




public class CorporatePointOfContact {


@FindBy(xpath="//a[text()='Corporate']")
WebElement corporateEle;
@FindBy(xpath="(//a[text()='Point of Contact'])[1]")
WebElement pointOfContact;
@FindBy(xpath="//span[text()='Administration Contact']")
WebElement adminContacts;
@FindBy(xpath="//table[@id='yw0-body-table']/tbody/tr")
List<WebElement> rowsEle;
WebDriver driver;
WebDriverWait wait;
public CorporatePointOfContact(WebDriver driver, WebDriverWait wait) {
this.driver = driver;
this.wait = wait;
PageFactory.initElements(driver, this); //connecting WebElements with driver
}


public List<String> getAdministrationContactEmailIds()
{
Actions action = new Actions(driver);
action.moveToElement(corporateEle).pause(Duration.ofSeconds(2)).build().perform();
pointOfContact.click();
adminContacts.click();
List<String> ListOfIds = new ArrayList<String>();
for (int i = 1; i <= rowsEle.size(); i++) 
{
String listOfEmailIds = driver.findElement(By.xpath("//table[@id='yw0-body-table']/tbody/tr["+i+"]/td[5]")).getText();
ListOfIds.add(listOfEmailIds);
}
return ListOfIds;
}
}
