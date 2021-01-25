package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase{
	
	// Page Factory -- OR
	
	@FindBy(xpath="//td[contains(text(),'Contacts')]")
	WebElement contactsLabel;
	
	@FindBy(id = "first_name")
	WebElement firstName;
	
	@FindBy(id = "surname")
	WebElement lastName;
	
	@FindBy(name = "client_lookup")
	WebElement company;
	
	@FindBy(xpath = "//input[@type='submit' and @value='Save']")
	WebElement saveButton;
	
	
	//@FindBy(xpath="//a[contains(text(),'test 1 test 1')]//parent::td[@class='datalistrow']//preceding-sibling::td[@class='datalistrow']//input[@type='checkbox']")
	//WebElement kpTest1Test1Checkbox;  //try "//a[contains(text(),'test 1 test 1')]"
	
	//INSTEAD CREATED a method see below selectContactsByName so that we dont have to write 100 @FindBy if there are 100 contacts
	
	// Initializing the Page Objects
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyContactsLabel() {
		return contactsLabel.isDisplayed();
	}
	
	public void selectContactsByName(String name) {
		//driver.findElement(By.xpath("//a[text()='\"+name+\"']//parent::td[@class='datalistrow']//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id']\")).click();
				driver.findElement(By.xpath("//a[text()='"+name+"']//parent::td[@class='datalistrow']//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id']")).click();
				
	}
	
	public void createNewContact(String title, String ftName, String ltName, String comp) {            // passed string(for title) variable so that we dont have to hardcode the value 
		Select select = new Select(driver.findElement(By.name("title")));          // we can also create page factory for the select/dropdown
		select.selectByVisibleText(title);                                                          // so while calling this method from testNG class we will pass this parameter
		
		firstName.sendKeys(ftName);
		lastName.sendKeys(ltName);
		company.sendKeys(comp);
		saveButton.click();
		
	}
	
	
}
