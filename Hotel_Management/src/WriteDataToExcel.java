import java.io.File;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/*
Important Documentation:

Excel Spreadsheet MUST NOT BE OPEN.
ONE line of ERROR will always be there. That ERROR is "ERROR StatusLogger Log4j2 could not find a logging implementation. Please add log4j-core to the classpath. Using SimpleLogger to log to the console...".
(if it aint broke dont fix it)

MUST CHANGE FILE ADDRESS EVERYTIME AS OF RIGHT NOW
*/

public class WriteDataToExcel {

    public static void main(String[] args)
    {
        // workbook object - One file
        XSSFWorkbook workbook = new XSSFWorkbook();

        // spreadsheet object - data in file
        XSSFSheet spreadsheet = workbook.createSheet
                (" Booking View ");

        // creating a row object 
        XSSFRow row;

        // This data needs to be written (Object[]) 
        Map<String, Object[]> bookingView = new TreeMap<String, Object[]>();

        bookingView.put(
                "1", // row index
                new Object[]
                        { "Room #", "Availability", "Reserver ID" , "Reserve Time", "Check In", "Check Out"} // Columns
        );

        // to add rows follow the format below
        bookingView.put("2", new Object[]{ "1", "Open", "" , "", "", ""});

        Set<String> keyid = bookingView.keySet(); // set of row index

        int rowid = 0;

        // writing the data into the sheets...
        for (String key : keyid) {
            row = spreadsheet.createRow(rowid++);
            Object[] objectArr = bookingView.get(key);
            int cellid = 0;
            for (Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String)obj);
            }
        }

        // .xlsx is the format for Excel Sheets... 
        // writing the workbook into the file...
        /////////////////////////////////////////////////////////////////////////////////
        try { // have to change file address every time so we must find a way around that
            FileOutputStream out = new FileOutputStream(new File("C:/Users/lords/Desktop/code/Java/Hotel_Management/src/Hotel_Data.xlsx"));
            workbook.write(out);
            out.close();
        }
        catch(Exception e) { // keeps things working
            System.out.println("catch block");
            e.printStackTrace();
        }
        /////////////////////////////////////////////////////////////////////////////////
    }
}