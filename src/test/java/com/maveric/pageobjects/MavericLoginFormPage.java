package com.maveric.pageobjects;


import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MavericLoginFormPage {


@FindBy(id="LoginForm_username") //page factory annotations
private WebElement userNameEle; //encapsulation - protecting the webElement without being used by other classes
@FindBy(id="LoginForm_password")
WebElement passwordEle;
@FindBy(name="yt0")
WebElement loginBtn;
@FindBy(how=How.ID,using="LoginForm_password_em_")
WebElement errorMsg;
@FindBy(tagName="a")
List<WebElement> linkEle;
WebDriver driver;
WebDriverWait wait;
public MavericLoginFormPage(WebDriver driver, WebDriverWait wait) {
this.driver = driver;
this.wait = wait;
PageFactory.initElements(driver, this); //connecting WebElements with driver
}


public void sendUserName(String userName) 
{
userNameEle.sendKeys(userName);
}
public void sendPassWord(String passWord)
{
passwordEle.sendKeys(passWord);
}
public void clickOnLogin()
{
loginBtn.click();
}
public String errorMsgExpected()
{
return errorMsg.getText();
}


public int getNoOfLinks()
{
int noOfLinks = linkEle.size();
return noOfLinks;
}
}




