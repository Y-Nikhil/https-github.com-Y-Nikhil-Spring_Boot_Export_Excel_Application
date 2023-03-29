package com.ashokit;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

	@Autowired
	private BookRepository bookrepo;
	
	public void generateExcel(HttpServletResponse response) throws IOException {
		
		List<Book> books = bookrepo.findAll();
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Books info");
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("Book_Id");
		row.createCell(1).setCellValue("Book_name");
		row.createCell(2).setCellValue("Book_price");
		
		int dataRowIndex = 1;
		
		for(Book book : books) {
			HSSFRow dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(book.getBooksid());
			dataRow.createCell(1).setCellValue(book.getBooksname());
			dataRow.createCell(2).setCellValue(book.getBooksprice());
			dataRowIndex++;			
		}
		
		ServletOutputStream ops = response.getOutputStream();
		workbook.write(ops);
		workbook.close();
		ops.close();
	}
}
