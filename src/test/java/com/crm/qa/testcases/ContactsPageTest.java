package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil; 
	ContactsPage contactsPage;
	String sheetName = "contacts";
	
	public ContactsPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();                                  // to reach at homepage we have to login first
		testUtil = new TestUtil();                        //KKKK
		contactsPage = new ContactsPage();
		loginPage = new LoginPage();                      // loginpage object created so that we can use LoginPage class methods next line
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));	
		testUtil.switchToFrame();                          // GOOOOOOOOOOOOD before clicking on contacts link
		contactsPage = homePage.clickOnContactsLink();    // new here and then if next page then this pages stuff will be new
	}                                                     // bcz earlier we had to login to reach homepage, same way we have to click on
	                                                      //contactslink on homepage to get contacts page
	
	@Test(priority=1)
	public void verifyContactsLabelTest() {
		Assert.assertTrue(contactsPage.verifyContactsLabel(), "contacts label is missing on the page");
	}
	
	@Test(priority=2)
	public void selectSingleContactsTest() {
		contactsPage.selectContactsByName("test 1 test 1");   // we should write assertion but lets ignore in this case
	}
	
	@Test(priority=3)
	public void selectMultipleContactsTest() {
		contactsPage.selectContactsByName("test 1 test 1");   // we should write assertion but lets ignore in this case
		contactsPage.selectContactsByName("util util");
	}
	
	@DataProvider
	public Object[][] getCRMTestData() {  // can also pass parameter that get data from particular SHEET NAME // String sheetName
		Object data[][] = TestUtil.getTestData(sheetName);    // Object data[][] bcz .get test data returns 2d object array
		return data;
	}
	
	@Test(priority=4, dataProvider="getCRMTestData")
	public void validateCreateNewContact(String title, String firstname, String lastname, String company) {
		homePage.clickOnNewContactLink();
			//contactsPage.createNewContact("Mr.", "Tom", "Peter", "Google");  // still these are hardcoded values lets say tomorrow we want to
		                                                    // create 100 contacts, so for that we will have to use data driven approach
	    contactsPage.createNewContact(title, firstname, lastname, company);
	}
	
	
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
