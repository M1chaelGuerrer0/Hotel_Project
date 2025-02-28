import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    public static void main(String[] args)
    {
        try {
            // Reading file from local directory
            FileInputStream file = new FileInputStream(new File("Hotel_Data.xlsx"));

            XSSFWorkbook workbook = new XSSFWorkbook(file);
            // Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            // Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();

            // Till there is an element condition holds true
            while(rowIterator.hasNext()) {
                Row row = rowIterator.next();
                // For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    // Checking the cell type and format accordingly
                    switch (cell.getCellType()) {
                        // Case 1
                        case NUMERIC:
                            System.out.print(
                                    cell.getNumericCellValue()
                                            + " ");
                            break;
                        // Case 2
                        case STRING:
                            System.out.print(
                                    cell.getStringCellValue()
                                            + " ");
                            break;
                    }
                }
                System.out.println("");
            }
            file.close();
        }
        catch (Exception e) { // to keep things working
            e.printStackTrace();
        }
    }
}
