import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Creator {
    public static void createExcelFile(String filePath, String spreadSheet, int rowCount) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(spreadSheet);
        // Create header row
        Row headerRow = sheet.createRow(0);
        /////////////////////////////////////// header row initialize
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter number of columns:");
        int size = scnr.nextInt();
        String temp;
        String[] headers = new String[size];
        for(int i = 0; i < size; i++) {
            System.out.println("Enter column #"+(i+1));
            temp = scnr.next();
            headers[i] = temp;
        }
        ///////////////////////////////////////
        for(int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }
        // Create rows with default data
        for(int i = 1; i <= rowCount; i++) {
            Row row = sheet.createRow(i);
            for(int j = 0; j < headers.length; j++) {
                Cell cell = row.createCell(j);
                if(j == 0) {
                    cell.setCellValue(i); // Room number // still need to make more customizable
                }
                else if(j == 1) {
                    cell.setCellValue("Open"); // Availability // still need to make more customizable
                }
                else {
                    cell.setCellValue("N/A"); // Default value for other columns // still need to make more customizable
                }
            }
        }

        // Write the workbook to the file
        try(FileOutputStream out = new FileOutputStream(new File(filePath))) {
            workbook.write(out);
        }
        workbook.close();
    }
}