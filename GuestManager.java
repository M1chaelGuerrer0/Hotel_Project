import java.sql.*;
// EVERYTHING YOU NEED TO RUN //
/*

    You need to install mySQL installer and install workbench, client, and shell.
    You need to make a password and remember it.
    You need to create a schema called hoteldb.
    You need to add the plugin called Database Navigator.
    You need to add a connection called hoteldb_connection and make sure the database is hoteldb.
    You need to put root as user and use the same password from earlier.
    You need to download the mysql-connector-j-9.2.0.zip and extract.
    Then you need to find mysql-connector-j-9.2.0.jar and put it under modules.
    Under console paste :
        CREATE TABLE guests (
        guest_id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        email VARCHAR(100) UNIQUE,
        phone VARCHAR(20),
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

*/

// Command explains //
/*

    Connection conn establishes a database session using DriverManager which acts as the connection broker.
    PrepareStatement is a precompiled sql command used to make changes to the tables.

*/

// Troubleshoot //
/*

    If having sync issues disconnect and reconnect.
    For more accuracy use the workbench for viewing the tables.

*/


public class GuestManager {
    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/hoteldb";
    private static final String USER = "root";
    private static final String PASSWORD = "113529";

    // Add a new guest
    public static void addGuest(String name, String email, String phone) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO guests (name, email, phone) VALUES (?, ?, ?)")) {
            conn.setAutoCommit(false);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, phone);
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateGuest(int guestId, String newName, String newEmail, String newPhone) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                     "UPDATE guests SET name=?, email=?, phone=? WHERE guest_id=?")) {
            conn.setAutoCommit(false);
            stmt.setString(1, newName);
            stmt.setString(2, newEmail);
            stmt.setString(3, newPhone);
            stmt.setInt(4, guestId);
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Delete a guest
    public static void deleteGuest(int guestId) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                     "DELETE FROM guests WHERE guest_id = ?")) {
            conn.setAutoCommit(false);
            stmt.setInt(1, guestId);
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all guests
    public static void displayAllGuests() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT * FROM guests")) {
            System.out.println("\nGuest List:");
            System.out.println("ID\tName\tEmail\tPhone");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("guest_id") + "\t" +
                                rs.getString("name") + "\t" +
                                rs.getString("email") + "\t" +
                                rs.getString("phone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
