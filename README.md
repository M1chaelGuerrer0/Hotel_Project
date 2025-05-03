# Hotel_Project
DEDSEC Hotel Project

## EVERYTHING YOU NEED TO RUN THE DATABASE
 * install intellij
 * install jdk 21
 * install mySQL installer and make a custom installation. (install workbench, client, and shell.)
 * Make a password and remember it.
 * Create a schema called hoteldb.
 * Add the plugin called Database Navigator on IntelliJ (Found under settings and plugins.)
 * install mysql-connectionector-j-9.2.0.jar and put it under modules (Under Project structure under dependencies)
 * Go to db navigator at the top of the screen and create a connection and called hoteldb_connection and change the database to hoteldb.
 * You need to put root as user and use the same password from earlier.
 * Test connection and it should be successful.
 * Run HotelDataBase.reset() once to create the tables
 * Input the password from earlier under PASSWORD

## EVERYTHING YOU NEED TO RUN THE TEST
 * need to be a maven project
 * update the pom.xml file // should be automatic when pressing on the red text (errors)
 * make sure it is JUnit 5.0.0 or higher // reject if told any version below
 * Files needed: HotelDataBase.java User.java Card.java hoteldb.sql

