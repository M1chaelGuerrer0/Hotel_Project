import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Updater {
    public static void editRowInExcel(String filePath, int fixRowNum) throws Exception {
        FileInputStream file = new FileInputStream(new File(filePath)); // open file
        XSSFWorkbook wb = new XSSFWorkbook(file); // open spreadsheet
        XSSFSheet ws = wb.getSheetAt(0); // Assuming you want to edit the first sheet // still need to make more customizable
        Row row = ws.getRow(fixRowNum);
        if (row == null) {
            row = ws.createRow(fixRowNum);
        }
        System.out.println("Enter the new data for the row:\n(Format for 'Booking_View': x Booked xxxxx xx/xx/xxxx xxxx xxxx)...with x being an Integer");
        Scanner scnr = new Scanner(System.in);
        String temp; // temp = new value
        for (int i = 0; i < 6; i++) { // still fixed on 6 columns
            Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            temp = scnr.next();
            if(intOrString(temp)) { // if Integer
                cell.setCellValue(Integer.parseInt(temp));
            }
            else{ // if String
                cell.setCellValue(temp);
            }
        }
        file.close();
        FileOutputStream outFile = new FileOutputStream(new File(filePath));
        wb.write(outFile);
        outFile.close();
        wb.close();
    }

    private static boolean intOrString(String temp) { // check if string is really an integer or string
        try { // if integer
            Integer.parseInt(temp);
            return true;
        } // if string
        catch(NumberFormatException e) {
            return false;
        }
    }
}