import java.util.*;

/*
    There will be an ERROR that shows up called
            "ERROR StatusLogger Log4j2 could not find a logging implementation. Please add log4j-core to the classpath. Using SimpleLogger to log to the console..."
    (If it ain't broke don't fix it)



    Format for Booking_Data:
    Room# Availability ReserverID ReserveTime CheckIn CheckOut
    1 Booked 10001 12/24/2002 1000 1300
    2 Open



*/

public class Main {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("REMEMBER TO CLOSE THE EXCEL WORKBOOK YOU ARE WORKING ON!\nWill you be creating a new workbook or updating one?\nEnter one of the following numbers:\n1) New Workbook\n2) Update Workbook\n3) Exit");
        int userResponse;
        while(true) {
            userResponse = scnr.nextInt();
            if(userResponse == 1) {
                Creator create = new Creator();
            }
            if(userResponse == 2) {
                Updater updater = new Updater();
            }
            if(userResponse == 3) {
                break;
            }
        }

    }
}



