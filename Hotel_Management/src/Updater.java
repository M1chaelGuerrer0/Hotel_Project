import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Updater {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        System.out.println("Enter the row number to edit:");
        int fixRowNum = scnr.nextInt();
        System.out.println("Enter the Workbook:\n(Don't include .xlsx)");
        String file = scnr.next();
        try {
            editRowInExcel(file+".xlsx", fixRowNum);
            System.out.println("Row updated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void editRowInExcel(String filePath, int fixRowNum) throws Exception {
        FileInputStream file = new FileInputStream(new File(filePath));
        XSSFWorkbook wb = new XSSFWorkbook(file);
        XSSFSheet ws = wb.getSheetAt(0); // Assuming you want to edit the first sheet
        Row row = ws.getRow(fixRowNum);
        if (row == null) {
            row = ws.createRow(fixRowNum);
        }

        System.out.println("Enter the new data for the row:\n(Format for 'booking view': x Booked xxxxx xx/xx/xxxx xxxx xxxx)");
        Scanner scnr = new Scanner(System.in);
        String temp;
        for (int i = 0; i < 6; i++) {
            Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            temp = scnr.next();
            if(intOrString(temp)) {
                cell.setCellValue(Integer.parseInt(temp));
            }
            else{
                cell.setCellValue(temp);
            }
        }

        file.close();

        FileOutputStream outFile = new FileOutputStream(new File(filePath));
        wb.write(outFile);
        outFile.close();
        wb.close();
    }

    private static boolean intOrString(String temp) {
        try {
            Integer.parseInt(temp);
            return true;
        }
        catch(NumberFormatException e) {
            return false;
        }
    }
}