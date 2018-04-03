package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DataClass {
	
	@DataProvider(name = "dataProvider1")
	public static String[][] getData(){
		String [][] dataArray = null;
		try {
			//getting the input data file
			FileInputStream fs = new FileInputStream("data\\WPDataSheet.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			//get row count
			int rowCount = sheet.getLastRowNum();
			//get column count
			int columnCount = sheet.getRow(0).getLastCellNum();
			dataArray = new String[rowCount][columnCount];
			
			//loop through rows
			for(int i = 1; i<rowCount+1; i++) {
				try {
					XSSFRow row = sheet.getRow(i);
					for(int j=0; j<columnCount; j++) {
						try {
							String cellValue = "";
							cellValue = row.getCell(j).getStringCellValue();
							//add to the data array
							dataArray[i-1][j] = cellValue;
						} catch (NullPointerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			workbook.close();
			fs.close();

		} catch (FileNotFoundException e){
			System.err.println("File not available");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Invalid File name/ file is corrupted");
		}			
		return dataArray;
		
	}

}
