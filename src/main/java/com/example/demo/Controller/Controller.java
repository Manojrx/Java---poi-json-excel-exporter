package com.example.demo.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Constants.constantvalues;
import com.example.demo.Service.Service;

@RestController
public class Controller {

	@Autowired
	Service service;
	
	@GetMapping("/Excel")
	    public void writeExcel()  {
		 try {
			 XSSFWorkbook workbook = new XSSFWorkbook(); 
			 XSSFSheet  Sheet= workbook.createSheet();
			 service.createHeader(workbook,Sheet);     // Header Content
			 service.createbody(workbook,Sheet);		//Json Body
		      //Create file system using specific name
		      FileOutputStream out = new FileOutputStream(new File(constantvalues.fileName));
		      //write operation workbook using file out object 
		      workbook.write(out);
		      out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        return ;
	    }
	
}

