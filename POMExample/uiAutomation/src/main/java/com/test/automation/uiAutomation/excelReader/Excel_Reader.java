package com.test.automation.uiAutomation.excelReader;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Reader {

	public FileOutputStream fileout = null;
	public String path;
	public FileInputStream fis;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;

	public Excel_Reader(String path) {
		this.path = path;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
		} catch (Exception e) {
			System.out.print("Error");
		}
	}

	@SuppressWarnings("deprecation")
	public String[][] getDataFromSheet(String sheetName, String ExcelName) {
		String dataSets[][] = null;
		try {
			// get sheet from excel Workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);
			// count total number of rows
			int totalRow = sheet.getLastRowNum() + 1;
			// count no of active columns in a row
			int totalColumn = sheet.getRow(0).getLastCellNum();
			// create array of rows and columns
			dataSets = new String[totalRow - 1][totalColumn];
			// Run for loop and store data in 2D array
			// this will run on rows
			for (int i = 1; i < totalRow; i++) {
				XSSFRow rows = sheet.getRow(i);
				// this loop will run for columns
				for (int j = 0; j < totalColumn; j++) {
					// get Cell method will get cell
					XSSFCell cell = rows.getCell(j);
					// if cell of type string, then this condition will work
					if (cell.getCellType() == Cell.CELL_TYPE_STRING)
						dataSets[i - 1][j] = cell.getStringCellValue();
					// if cell of type Number, then this condition will work
					else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
						String cellText = String.valueOf(cell.getNumericCellValue());
						dataSets[i - 1][j] = cellText;
					} else
						// if cell of type boolean, then this condition will
						// work
						dataSets[i - 1][j] = String.valueOf(cell.getBooleanCellValue());
				}
			}
			return dataSets;
		} catch (Exception e) {

			System.out.println("Exception reading excel" + e.getMessage());
			e.printStackTrace();

		}
		return dataSets;
	}

	@SuppressWarnings("deprecation")
	public String getCellData(String sheetName, String colName, int rowNum) {
		try {
			int col_Num = 0;
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			XSSFRow row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().equals(colName)) {
					col_Num = i;
					break;
				}
			}
			row = sheet.getRow(rowNum - 1);
			XSSFCell cell = row.getCell(col_Num);
			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				return cell.getStringCellValue();
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
				return "";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
