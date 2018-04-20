package com.learn.java;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ExcelReader {
	public static final String SAMPLE_XLSX_FILE_PATH = "D:\\Test.xlsx";
	public static Iterator<Sheet> sheetIterator;
	public static Sheet sheet;
	public static DataFormatter dataFormatter;
	public static Iterator<Row> rowIterator;
	public static Row row;
	public static Iterator<Cell> cellIterator;
	public static Cell cell;
	public static String cellValue;
	public static List<Map<String, String>> mapobj;
	public static List<String> Columns;

	public static void readExcelWorkbook() throws IOException, InvalidFormatException {
		// Creating a Workbook from an Excel file (.xls or .xlsx)
		Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));
		// Retrieving the number of sheets in the Workbook
		System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");
		// 1. You can obtain a sheetIterator and iterate over it
		sheetIterator = workbook.sheetIterator();
		System.out.println("Retrieving Sheets using Iterator");
		while (sheetIterator.hasNext()) {
			sheet = sheetIterator.next();
			System.out.println("=> " + sheet.getSheetName());
		}
		// Getting the Sheet at index zero
		sheet = workbook.getSheetAt(0);
		// Create a DataFormatter to format and get each cell's value as String
		dataFormatter = new DataFormatter();
		// 1. You can obtain a rowIterator and columnIterator and iterate over them
		System.out.println("\n\nIterating over Rows and Columns using Iterator\n");
		rowIterator = sheet.rowIterator();
		while (rowIterator.hasNext()) {
			row = rowIterator.next();
			// Now let's iterate over the columns of the current row
			cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				cell = cellIterator.next();
				cellValue = dataFormatter.formatCellValue(cell);
				System.out.print(cellValue + "\t");
			}
			System.out.println();
		}
		// Closing the workbook
		workbook.close();
	}

	public static List<Map<String, String>> MapColumnToData() throws IOException, InvalidFormatException {
		Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));
		sheetIterator = workbook.sheetIterator();
		sheet = workbook.getSheetAt(0);
		dataFormatter = new DataFormatter();
		rowIterator = sheet.rowIterator();
		mapobj = new ArrayList<Map<String, String>>();
		Columns = new ArrayList<String>();
		while (rowIterator.hasNext()) {
			Map<String, String> rowData = new HashMap<String, String>();
			row = rowIterator.next();
			cellIterator = row.cellIterator();
			if (row.getRowNum() == 0) {
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					String cellValue = dataFormatter.formatCellValue(cell);
					Columns.add(cellValue);
				}
			} else {
				for (int i = 0; i < row.getLastCellNum(); i++) {
					Cell cell = cellIterator.next();
					String cellValue = dataFormatter.formatCellValue(cell);
					rowData.put(Columns.get(i), cellValue);
				}
				mapobj.add(rowData);
			}
		}
		workbook.close();
		return mapobj;
	}

}