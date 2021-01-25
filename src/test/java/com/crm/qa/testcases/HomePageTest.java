package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;   //KKKK
	ContactsPage contactsPage;
	
	public HomePageTest() {
		super();
	}
	
	// test cases should be separated -- independent with each other
	// before each test case --  launch the browser and login
	// @test -- execute test case
	// after each test case -- close the browser
	
	@BeforeMethod
	public void setUp() {
		initialization();             // to reach at homepage we have to login first
		testUtil = new TestUtil();    //KKKK
		contactsPage = new ContactsPage();
		loginPage = new LoginPage(); // loginpage object created so that we can use LoginPage class methods next line
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));	
	}
	
	@Test(priority=1)
	public void homePageTitleTest(){
		String title = homePage.verifyHomePageTitle();
		Assert.assertEquals(title, "CRMPRO", "home page title not match");  //if test fails the msg(home page not) willbe shownin report
	}
	
	@Test(priority=2)
	public void verifyUserNameTest() {
		testUtil.switchToFrame();
		Assert.assertTrue(homePage.verifyCorrectUserName());
	}
	
	@Test(priority=3)
	public void verifyContactsLinkTest() {
		testUtil.switchToFrame();
		contactsPage = homePage.clickOnContactsLink();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}


}
