package com.crm.qa.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase{
	
	//Page Factory - OR:
	
	@FindBy(name="username")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement loginBtn;
	
	@FindBy(xpath="//a[contains(text(),'Sign Up')]")
	WebElement signUp;
	
	@FindBy(xpath="//a[@class='navbar-brand']")
	WebElement crmLogo;
	
	//Initializing the Page Objects
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Actions/features: (creating methods......kp)
	
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}
	
	public boolean validateCrmImage() {             ////REMEMBER THIS
		return crmLogo.isDisplayed();
	}
	
	public HomePage login(String un, String pwd) {  /////////This whole stuff how? see test class  
		username.sendKeys(un);
		password.sendKeys(pwd);
		loginBtn.sendKeys(Keys.ENTER);   // was initially .click(); which failed the test bcz was not clickable
		
		return new HomePage();
	}

}
