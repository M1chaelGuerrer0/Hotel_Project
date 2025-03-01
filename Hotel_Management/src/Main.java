import java.util.*;

/*
    There will be an ERROR that shows up called
            "ERROR StatusLogger Log4j2 could not find a logging implementation. Please add log4j-core to the classpath. Using SimpleLogger to log to the console..."
    (If it ain't broke don't fix it)


    Workbook: Hotel_Data
    Spreadsheet: Booking Data
        Format for Booking Data:
        Room# | Availability | ReserverID | ReserveTime | CheckIn | CheckOut
            1 |       Booked |      10001 |  12/24/2002 |    1000 |     1300
            2 |         Open |        N/A |         N/A |     N/A |      N/A

    Example input: As of Right Now you can't copy and paste it all in one go. SO ONE BY ONE
    1
    149
    Hotel_Data
    Booking_Data
    6
    Room#
    Availability
    ReserverID
    ReserveTime
    CheckIn
    CheckOut
    2
    150
    Hotel_Data
    150 Open N/A N/A N/A N/A
    3

    Still need to make creator and updater more customizable as they are still partially fixed on 6 columns and 1 spreadsheet.
*/

public class Main {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("REMEMBER TO CLOSE THE EXCEL WORKBOOK YOU ARE WORKING ON!\nWill you be creating a new workbook or updating one?\nEnter one of the following numbers:\n1) New Workbook\n2) Update Workbook\n3) Exit");
        int userResponse;
        while(true) {
            userResponse = scnr.nextInt();
            if(userResponse == 1) { // working
                createBook();
            }
            if(userResponse == 2) { // working
                updateBook();
            }
            if(userResponse == 3) { // working
                break;
            }
            System.out.println("Enter one of the following numbers:\n1) New Workbook\n2) Update Workbook\n3) Exit");
        }

    }

    private static void updateBook() { // updating row 1 at a time
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter the row number to edit:");
        int fixRowNum = scnr.nextInt();
        System.out.println("Enter the Workbook:\n(Don't include .xlsx)");
        String file = scnr.next();
        try { // important try catch or it will not run
            Updater update = new Updater();
            update.editRowInExcel(file+".xlsx", fixRowNum);
            System.out.println("Row updated successfully!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createBook() {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter the number of rows to create:");
        int rowCount = scnr.nextInt();
        System.out.println("Enter the name of the Workbook:\n(Don't include .xlsx)");
        String file = scnr.next();
        System.out.println("Enter the name of the Spreadsheet:");
        String spreadsheet = scnr.next();
        try { // important try catch or it will not run
            // Create a new Excel file with the specified number of rows and 6 columns
            Creator create = new Creator();
            create.createExcelFile(file+".xlsx",spreadsheet, rowCount);
            System.out.println("Excel file created successfully!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}



