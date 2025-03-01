import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Creator {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter the number of rows to create:");
        int rowCount = scnr.nextInt();
        System.out.println("Enter the name of the Workbook:\n(Don't include .xlsx)");
        String file = scnr.next();
        System.out.println("Enter the name of the Spreadsheet:");
        String spreadsheet = scnr.next();
        try {
            // Create a new Excel file with the specified number of rows and 6 columns
            createExcelFile(file+".xlsx",spreadsheet, rowCount);
            System.out.println("Excel file created successfully!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            scnr.close();
        }
    }
    // still need to make more dynamic
    public static void createExcelFile(String filePath, String spreadSheet, int rowCount) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(spreadSheet);
        // Create header row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Room#", "Availability", "Reserver ID", "Reserve Time", "Check In", "Check Out"};
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
                    cell.setCellValue(i); // Room number
                }
                else if(j == 1) {
                    cell.setCellValue("Open"); // Availability
                }
                else {
                    cell.setCellValue("N/A"); // Default value for other columns
                }
            }
        }

        // Write the workbook to the file
        try(FileOutputStream out = new FileOutputStream(new File(filePath))) {
            workbook.write(out);
        }
        workbook.close();
    }
    // int currentRowCount = sheet.getLastRowNum();

}