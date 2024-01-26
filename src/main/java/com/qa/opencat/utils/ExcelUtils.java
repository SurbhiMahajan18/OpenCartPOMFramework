package com.qa.opencat.utils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {

	private static final String TEST_DATA_SHEET_PATH ="./src/test/resources/testdata/POMSeriesTestData.xlsx";
	private static Workbook book;
	private static Sheet sheet;

	public static Object[][] getTestData(String sheetName)
	{
		System.out.println("Reading test data from sheet: " +sheetName);
		Object data[][]= null;

		try {
			FileInputStream fis = new FileInputStream(TEST_DATA_SHEET_PATH);
			book=WorkbookFactory.create(fis);
			sheet = book.getSheet(sheetName);

			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			for(int rows = 0;rows<sheet.getLastRowNum();rows++)
			{
				for(int cols=0;cols<sheet.getRow(0).getLastCellNum();cols++)
				{
					data[rows][cols] = sheet.getRow(rows+1).getCell(cols).toString();
				}
			}




		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return data;
	}
}