package com.learn.java;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

import com.monitorjbl.xlsx.StreamingReader;
import com.monitorjbl.xlsx.impl.*;

/**
 * @author Sumit
 *
 */
public class ExcelStreamer{
	public static String  filename = "D:\\Data.xlsx";
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

	
	
	
	
	
	
	
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void readExcelWorkbook() throws IOException {
			BasicConfigurator.configure();
	InputStream is = new FileInputStream(new File(filename));
	Workbook workbook = StreamingReader.builder()
	        .rowCacheSize(100)    // number of rows to keep in memory (defaults to 10)
	        .bufferSize(4096)     // buffer size to use when reading InputStream to file (defaults to 1024)
	        .open(is);            // InputStream or File for XLSX file (required)
	
	System.out.println("The number of sheets present in the workbook: "+workbook.getNumberOfSheets());
	
//	for(Sheet sheet : workbook) {
//		System.out.println(sheet.getSheetName());
//		for(Row r: sheet) {
//			for(Cell c: r) {
//				System.out.println(c.getStringCellValue());
//			}
//		}
//	}
//	
	
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

	}
	
	public static List<Map<String, String>> MapColumnToData() throws IOException, InvalidFormatException {
		BasicConfigurator.configure();
		System.out.println("Inside Method");
	InputStream is = new FileInputStream(new File(filename));
	Workbook workbook = StreamingReader.builder()
	        .rowCacheSize(100)    // number of rows to keep in memory (defaults to 10)
	        .bufferSize(4096)     // buffer size to use when reading InputStream to file (defaults to 1024)
	        .open(is);            // InputStream or File for XLSX file (required)
	
	System.out.println("The number of sheets present in the workbook: "+workbook.getNumberOfSheets());
	
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
					//System.out.println(row.getLastCellNum()+" Column No : "+(i+1));
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
