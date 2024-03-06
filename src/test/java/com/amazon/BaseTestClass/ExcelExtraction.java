package com.amazon.BaseTestClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ExcelExtraction {

    @Test
    public void excelRun() throws IOException {

        FileInputStream fis = new FileInputStream(
                "C:\\Users\\904904\\OneDrive - Cognizant\\Desktop\\Java Project\\AmazonProject\\product\\src\\excel.xlsx");
        
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        int sheetCount = wb.getNumberOfSheets();
        System.out.println("The number of sheets are: " + sheetCount);

        ArrayList<String> userName = new ArrayList<>();
        ArrayList<String> passWord = new ArrayList<>();

        for (int i = 0; i < sheetCount; i++) {
            if (wb.getSheetAt(i).getSheetName().equalsIgnoreCase("TestData")) {
                XSSFSheet sheet = wb.getSheetAt(i);
                XSSFRow row = sheet.getRow(0);

                int userColumn = 0;
                for(int j=0; j<row.getLastCellNum(); i++) { 
                    XSSFCell cell = row.getCell(j);
                    if(cell.getStringCellValue().equalsIgnoreCase("Username")) {
                        userColumn = j;
                        break;
                    }
                }

                for (int j = 0; j < sheet.getLastRowNum(); j++) {
                    XSSFRow row1 = sheet.getRow(j + 1);
                    XSSFCell cell = row1.getCell(userColumn);
                    userName.add(cell.getStringCellValue());
                }

                int passColumn = 0;
                for(int j=0; j<row.getLastCellNum(); i++) {
                    XSSFCell cell = row.getCell(j);
                    if(cell.getStringCellValue().equalsIgnoreCase("Password")) {
                        passColumn = j;
                        break;
                    }
                }

                for (int l = 0; l < sheet.getLastRowNum(); l++) {
                    XSSFRow row2 = sheet.getRow(l + 1);
                    XSSFCell cell = row2.getCell(passColumn);
                    passWord.add(cell.getStringCellValue());
                }
            }
        }

        System.out.println(userName);
        System.out.println(passWord);
        wb.close();
    }
}
