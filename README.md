## Tools
* Intellij
* MySQL
* Jira
* Java 21
* JavaFX
* SceneBuilder


## Operation Procedure
* On IntelliJ, create a JavaFX project called “gooncentral”
* Click Next and select FormsFX
* Override everything within the project with our files
* On MySQL, create a schema called “hoteldb”.
* Add the Database Navigator plugin on IntelliJ (Found under settings and plugins.)
* Extract mysql-connector-j-9.2.0.jar and put it under modules (Under Project structure under dependencies)
* Go to the DB Navigator at the top of the screen and create a connection called hoteldb_connection and change the database to hoteldb.
* You need to put your MySQL user (default is root) and use your MySQL password.
* Test the connection, and it should be successful.
* Highlight with mouse cursor on each code segment on the .sql file and run them to create table,s or create a separate file and run HotelDataBase.reset();
* In the file HotelDataBase, there is a connection that you need to put your MySQL password in.

We goated
