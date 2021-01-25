package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase{
	
	public static long PAGE_LOAD_TIMEOUT = 30;   // why long?
	public static long IMPLICIT_WAIT = 20;   // both public so that can be used anywhere
	
	public static String TESTDATA_SHEET_PATH = "C:/Selenium_Workspace/FreeCRMTestPractice/src/main/java/com/crm/qa/testdata/FreeCrmTestData.xlsx";                   // right click on excel sheet and path copy pasted
	
	
	static Workbook book;                       //  REAL is Workbook book = WorkbookFactory.create(file);
	static Sheet sheet;                         //  REAL is Sheet sheet = book.getSheet(sheetName);
	
	
	public void switchToFrame() {               
		driver.switchTo().frame("mainpanel");          
	}
	
	public static Object[][] getTestData(String sheetName){    //the sheet where you want to fetch the data from  // the data from the excel file is stored into the Object array all at once and the
		FileInputStream file = null;                           //data will be given one by one from that array // null bcz it showed to initialize variable  //Object bcz data can be anything string double, integer,float....
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);  //1
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);             //2
		} catch (EncryptedDocumentException e) {                             //was EncryptedDocumentException e but changed to invalid format
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);                    //3
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		 //System.out.println(sheet.getLastRowNum() + "----------" + sheet.getRow(0).getLastCellNum());
		for (int i=0; i<sheet.getLastRowNum(); i++) {
			for(int k=0; k<sheet.getRow(0).getLastCellNum();k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();      // i+1 bcz actual data is available from 2nd row
				 //System.out.println(data[i][k]);
			}
		} 
		return data;
	}
	
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		//String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File("C:/Users/Prajapati/Pictures/Error_Screenshots/Screenshot" + System.currentTimeMillis() + ".png"));
	}

}
