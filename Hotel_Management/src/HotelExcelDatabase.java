
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
    There will be an ERROR that shows up called
        "ERROR StatusLogger Log4j2 could not find a logging implementation. Please add log4j-core to the classpath. Using SimpleLogger to log to the console..."
        (If it ain't broke don't fix it)

    Code:
        Reset:
            Full resets as in a full wipe.
            Default size for Rooms, Users, and Cards are 100.
            Uses classes.
            Creation of rows are repetitive. // could be fixed.
        Classes:
            Repetitive for now as it is unknown if they should change individually. // unknown if should fix
            Strings are safe but are shown as errors in Excel. // could be fixed
        Editor only edits one row at a time.
            Doesn't change classes. // unknown if should fix


    Workbook: HotelDatabase
    Spreadsheet: room_info
        Format:
        Room# | Availability | ID | Date        | CheckIn | CheckOut
            1 |       Taken  |  1 |  12/24/2002 |   10:00 |    13:00  // edited
            2 |    Available |    |             |         |           // default

    Spreadsheet: user_info
        Format:
        UserID | First  | Last | CardID |
            1  |   John |  Doe |      1 |   // edited
            2  |        |      |        |   // default

    Spreadsheet: card_info
        Format:
        CardID | Card#               | Expiration | CVC |
            1  | xxxx/xxxx/xxxx/xxxx |      xx/xx | xxx |  // x being some integers
            2  |                     |            |     |  // default

 */



    public class HotelExcelDatabase {

        private static final String FILE_PATH = "HotelDatabase.xlsx";

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to the Hotel Database Management System");
            while (true) {
                System.out.println("\nChoose an option:\n1. Reset all spreadsheets\n2. Edit a row in room_info\n3. Edit a row in user_info\n4. Edit a row in card_info\n5. Exit\nEnter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                switch (choice) {
                    case 1:
                        resetSpreadsheets();
                        System.out.println("All spreadsheets have been reset.");
                        break;
                    case 2:
                        editRow("room_info", scanner);
                        break;
                    case 3:
                        editRow("user_info", scanner);
                        break;
                    case 4:
                        editRow("card_info", scanner);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }

        private static void resetSpreadsheets() {
            try (Workbook workbook = new XSSFWorkbook()) {
                // Create room_info spreadsheet
                Sheet roomInfoSheet = workbook.createSheet("room_info");
                String[] roomInfoHeaders = {"Room#", "Availability", "ID", "Date", "CheckIn", "CheckOut"};
                createHeaderRow(roomInfoSheet, roomInfoHeaders);
                List<Room_Info> rooms = new ArrayList<>();
                for(int i = 1; i <= 100; i++) { // defaults
                    rooms.add(new Room_Info(i, "Available", "", "", "", ""));
                }
                createRoomRows(roomInfoSheet, rooms);

                // Create user_info spreadsheet
                Sheet userInfoSheet = workbook.createSheet("user_info");
                String[] userInfoHeaders = {"ID", "First", "Last", "CardID"};
                createHeaderRow(userInfoSheet, userInfoHeaders);
                List<User_Info> users = new ArrayList<>();
                for(int i = 1; i <= 100; i++) { // defaults
                    users.add(new User_Info(i, "","",""));
                }
                createUserRows(userInfoSheet, users);

                // Create card_info spreadsheet
                Sheet cardInfoSheet = workbook.createSheet("card_info");
                String[] cardInfoHeaders = {"CardID", "Card#", "Expiration", "CVC"};
                createHeaderRow(cardInfoSheet, cardInfoHeaders);
                List<Card_Info> cards = new ArrayList<>();
                for(int i = 1; i <= 100; i++) { // defaults
                    cards.add(new Card_Info(i, "","",""));
                }
                createCardRows(cardInfoSheet, cards);

                // Save the workbook to a file
                try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) { // will not work without a try catch
                    workbook.write(fileOut);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //////////////////// CREATORS ////////////////////
        private static void createHeaderRow(Sheet sheet, String[] headers) {
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }
        }
        private static void createRoomRows(Sheet sheet, List<Room_Info> rooms) {
            int rowNum = 1;
            for (Room_Info info : rooms) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(info.getRoomNum());
                row.createCell(1).setCellValue(info.getAvailability());
                row.createCell(2).setCellValue(info.getUserID());
                row.createCell(3).setCellValue(info.getDate());
                row.createCell(4).setCellValue(info.getCheckIn());
                row.createCell(5).setCellValue(info.getCheckOut());
            }
        }
        private static void createCardRows(Sheet sheet, List<Card_Info> cards) {
            int rowNum = 1;
            for (Card_Info info : cards) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(info.getCardID());
                row.createCell(1).setCellValue(info.getCardNumber());
                row.createCell(2).setCellValue(info.getExpiration());
                row.createCell(3).setCellValue(info.getCvc());
            }
        }
        private static void createUserRows(Sheet sheet, List<User_Info> users) {
            int rowNum = 1;
            for (User_Info info : users) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(info.getUserID());
                row.createCell(1).setCellValue(info.getFirst());
                row.createCell(2).setCellValue(info.getLast());
                row.createCell(3).setCellValue(info.getCardID());
            }
        }
        //////////////////// END OF CREATORS ////////////////////

        private static void editRow(String sheetName, Scanner scanner) {
            try (FileInputStream fileIn = new FileInputStream(FILE_PATH);
                 Workbook workbook = new XSSFWorkbook(fileIn)) {

                Sheet sheet = workbook.getSheet(sheetName);

                // Display current data
                System.out.println("\nCurrent data in " + sheetName + ":");
                for (Row row : sheet) {
                    for (Cell cell : row) {
                        System.out.print(cell.toString() + "\t");
                    }
                    System.out.println();
                }

                // Get row number to edit
                System.out.print("Enter the room/ID number: ");
                int rowNum = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                Row row = sheet.getRow(rowNum);
                // Edit each cell in the row
                for (int i = 1; i < row.getLastCellNum(); i++) {
                    Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    System.out.print("Enter new value for " + sheet.getRow(0).getCell(i).toString() + ": ");
                    String newValue = scanner.nextLine();
                    cell.setCellValue(newValue);
                }

                // Save changes
                try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH)) { // won't work without try catch
                    workbook.write(fileOut);
                    System.out.println("Row updated successfully!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
