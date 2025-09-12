package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public static String[][] readData() {
		String[][] data = null;

		String fileName = "src\\test\\resources\\testData\\ixigoInputdata.xlsx";

		try {
			FileInputStream fis = new FileInputStream(fileName);

			XSSFWorkbook workbook = new XSSFWorkbook(fis);

			XSSFSheet sheet = workbook.getSheet("Sheet1");

//				int rowCount = sheet.getLastRowNum();
//				int colCount = sheet.getRow(0).getLastCellNum(); 
//				System.out.println("Row Count: " + rowCount);
//				DataFormatter df = new DataFormatter();
//				XSSFCell cell;
//				data = new String[rowCount][colCount];
//				for(int rowNo=1; rowNo<=rowCount;rowNo++) {
//					int cellCount = sheet.getRow(rowNo).getLastCellNum();
//					
//					for(int cellNo=0;cellNo<cellCount;cellNo++) {
//						cell = sheet.getRow(rowNo).getCell(cellNo);
//						data[rowNo-1][cellNo] = df.formatCellValue(cell);
//					}
//				}
			int rowCount = sheet.getLastRowNum(); // last row index
			int colCount = sheet.getRow(0).getLastCellNum(); // total columns in header

			System.out.println("Row Count: " + rowCount);

			DataFormatter df = new DataFormatter();
			data = new String[rowCount][colCount]; // we skip header row (row 0)

			for (int rowNo = 1; rowNo <= rowCount; rowNo++) {
				Row row = sheet.getRow(rowNo);
				if (row == null)
					continue; // skip empty row

				for (int cellNo = 0; cellNo < colCount; cellNo++) {
					Cell cell = row.getCell(cellNo);
					if (cell != null) {
						data[rowNo - 1][cellNo] = df.formatCellValue(cell);
					} else {
						data[rowNo - 1][cellNo] = ""; // if column is missing, put blank
					}
				}
			}
			workbook.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

}
