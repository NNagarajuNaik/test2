package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil {

	Workbook wb;
	public ExcelFileUtil(String excelpath) throws Throwable{
		
		FileInputStream fi = new FileInputStream(excelpath);
		wb = WorkbookFactory.create(fi);
	}
	
	public int rowCount(String sheetname){
		
		return wb.getSheet(sheetname).getLastRowNum();
		
	}
	
	public int cellCount(String sheetname){
		
		return wb.getSheet(sheetname).getRow(0).getLastCellNum();
		
	}
	
	public String getCellData(String sheetName,int row,int colum){
		String data = "";
		if (wb.getSheet(sheetName).getRow(row).getCell(colum).getCellType()==Cell.CELL_TYPE_NUMERIC) {
			
			int celldata = (int)wb.getSheet(sheetName).getRow(row).getCell(colum).getNumericCellValue();
			data = String.valueOf(celldata);	
		}
		else{
			data = wb.getSheet(sheetName).getRow(row).getCell(colum).getStringCellValue();
		}
		return data;
	}
	public void setCellData(String sheetName,int row,int colum,String status,String writeexcel) throws Throwable{
		
		Sheet ws = wb.getSheet(sheetName);
		Row rowNum = ws.getRow(row);
		Cell cell = rowNum.createCell(colum);
		cell.setCellValue(status);
		if (status.equalsIgnoreCase("pass")) {
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			font.setBoldweight(font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(colum).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("fail")){
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			font.setBoldweight(font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(colum).setCellStyle(style);
		}
		else{
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			font.setBoldweight(font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(colum).setCellStyle(style);
		}
		FileOutputStream fo = new FileOutputStream(writeexcel);
		wb.write(fo);
		
		
	}
}
