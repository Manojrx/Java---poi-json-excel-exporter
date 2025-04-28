package com.example.demo.Service;


import java.io.File;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.demo.Constants.constantvalues;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@org.springframework.stereotype.Service
public class Service {
	
	 String[] array = {constantvalues.Name , constantvalues.Age , constantvalues.Occupation,constantvalues.Salary};

	 public  void createHeader(XSSFWorkbook workbook, XSSFSheet  Sheet) {	
		 XSSFRow row1  =  Sheet.createRow(0);
		 
		 XSSFCellStyle style = workbook.createCellStyle();
	        XSSFFont font = workbook.createFont();
	        font.setBold(true);
	        style.setFont(font);
	        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
	        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		 for (int i=0; i< array.length;i++) {
			 
			 Cell cellobj1 = row1.createCell(i);
			 cellobj1.setCellValue(array[i]); 
			 cellobj1.setCellStyle(style);

		 }
		
	 }

	 
	public void createbody(XSSFWorkbook workbook, XSSFSheet  Sheet) {
		// TODO Auto-generated method stub
		ObjectMapper om = new ObjectMapper();
		try {
			
			JsonNode node = om.readTree(new File("D:\\demo\\body.json"));    //Read through given  Location 
			JsonNode bodydetails = node.get(constantvalues.jsonBody);
			JsonNode  rowvalue;
			
			int a = 1;
			int colNum = 0;
			
			for (int k = 0 ;k<bodydetails.size() ;k++) {
				rowvalue = bodydetails.get(k);
				
				
				 XSSFRow row =Sheet.createRow(a++);  		//Creating row body after the header and adds 1 row while loopinig 
				 
				 

				 Cell name = row.createCell(colNum++);     //Creating cells in row  
				 Cell age = row.createCell(colNum++);
				 Cell Occupation = row.createCell(colNum++);
				 Cell salary = row.createCell(colNum++);
				 
				 name.setCellValue(rowvalue.get(constantvalues.jsonName).asText());
				 age.setCellValue(rowvalue.get(constantvalues.jsonAge).asInt());
				 Occupation.setCellValue(rowvalue.get(constantvalues.jsonDepartment).asText());
				 salary.setCellValue(rowvalue.get(constantvalues.jsonSalary).asInt());
				 
				 colNum = 0;                                                  // while loop gets in colnum value increases 1by1 for every row , hence making zero for nxt loop.

			}

		} 
		catch (IOException e) {
			
			System.out.println("Error while writing JSON Values");// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	} 
	 

	 
}
