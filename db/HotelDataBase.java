import java.sql.*;
// EVERYTHING YOU NEED TO RUN //
/*
    You need to install mySQL installer and install workbench, client, and shell.
    You need to make a password and remember it.
    You need to create a schema called hoteldb.
    You need to add the plugin called Database Navigator on IntelliJ.
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

// Troubleshoot //
/*

    If having sync issues disconnect and reconnect.
    For more accuracy use the workbench for viewing the tables.

*/


public class HotelDataBase {
    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/hoteldb";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Guest // ////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Add a new guest to database directly
    public static void addGuest(User user) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO guests (firstname, lastname, email, password, phone, address1, address2, city, country, state, zipCode, card_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            conn.setAutoCommit(false);
            stmt.setString(1, user.getFirst());
            stmt.setString(2, user.getLast());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getPhoneNumber());
            stmt.setString(6, user.getAddress1());
            stmt.setString(7, user.getAddress2());
            stmt.setString(8, user.getCity());
            stmt.setString(9, user.getCountry());
            stmt.setString(10, user.getState());
            stmt.setString(11, user.getZipCode());
            stmt.setInt(12, user.getCardId());
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // update guest information to the database
    public static void updateGuest(User newUser) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("UPDATE guests SET firstname=?, " +
                     "lastname=?, email=?, password=?, phone=?, address1=?, address2=?, city=?, country=?, " +
                     "state=?, zipCode=?, card_id=? WHERE guest_id=?")) {
            conn.setAutoCommit(false);
            stmt.setString(1, newUser.getFirst());
            stmt.setString(2, newUser.getLast());
            stmt.setString(3, newUser.getEmail());
            stmt.setString(4, newUser.getPassword());
            stmt.setString(5, newUser.getPhoneNumber());
            stmt.setString(6, newUser.getAddress1());
            stmt.setString(7, newUser.getAddress2());
            stmt.setString(8, newUser.getCity());
            stmt.setString(9, newUser.getCountry());
            stmt.setString(10, newUser.getState());
            stmt.setString(11, newUser.getZipCode());
            stmt.setInt(12, newUser.getCardId());
            stmt.setInt(13, newUser.getUserId());
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a guest from database directly
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

    // object user individual getter
    public static User getUser(int guestId) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT * FROM guests WHERE guest_id = "+Integer.toString(guestId))) {
            User user = new User();
            user.setUserId(rs.getInt("guest_id"));
            user.setFirst(rs.getString("firstname"));
            user.setLast(rs.getString("lastname"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setPhoneNumber(rs.getString("phone"));
            user.setAddress1(rs.getString("address1"));
            user.setAddress2(rs.getString("address2"));
            user.setCity(rs.getString("city"));
            user.setState(rs.getString("state"));
            user.setZipCode(rs.getString("zipCode"));
            user.setCardId(rs.getInt("card_id"));
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // display all guest from database directly
    /*
    public static void displayAllGuests() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT * FROM guests")) {
            System.out.println("\nGuest List:");
            System.out.println("guest_id\tFirstName\tLastName\tEmail\tPassword\tPhone\tAddress1\tAddress2\tCity\tState\tZipCode\tCard_id");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("guest_id") + "\t" +
                                rs.getString("firstname") + "\t" +
                                rs.getString("lastname") + "\t" +
                                rs.getString("email") + "\t" +
                                rs.getString("password") + "\t" +
                                rs.getString("phone") + "\t" +
                                rs.getString("address1") + "\t" +
                                rs.getString("address2") + "\t" +
                                rs.getString("city") + "\t" +
                                rs.getString("state") + "\t" +
                                rs.getString("zipCode") + "\t" +
                                rs.getString("card_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     */
    // End of User /////////////////////////////////////////////////////////////////////////////////////////////////////
    // Card ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Add a new card
    public static void addCard(String holderName, String cardNumber, String expiration, String cvc,
                               String address1, String address2, String city,
                               String country, String state, String zipCode) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO card (holderName, cardNumber, expiration, cvc, address1, address2, city, country, state, zipCode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            conn.setAutoCommit(false);
            stmt.setString(1, holderName);
            stmt.setString(2, cardNumber);
            stmt.setString(3, expiration);
            stmt.setString(4, cvc);
            stmt.setString(5, address1);
            stmt.setString(6, address2);
            stmt.setString(7, city);
            stmt.setString(8, country);
            stmt.setString(9, state);
            stmt.setString(10, zipCode);
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateCard(int card_id, String newHolderName, String newCardNumber, String newExpiration, String newCvc,
                                  String newAddress1, String newAddress2, String newCity,
                                  String newCountry, String newState, String newZipCode) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                     "UPDATE card SET holderName=?, cardNumber=?, expiration=?, cvc=?, address1=?, address2=?, city=?, country=?, state=?, zipCode=? WHERE card_id=?")) {
            conn.setAutoCommit(false);
            stmt.setString(1, newHolderName);
            stmt.setString(2, newCardNumber);
            stmt.setString(3, newExpiration);
            stmt.setString(4, newCvc);
            stmt.setString(5, newAddress1);
            stmt.setString(6, newAddress2);
            stmt.setString(7, newCity);
            stmt.setString(8, newCountry);
            stmt.setString(9, newState);
            stmt.setString(10, newZipCode);
            stmt.setString(11, String.valueOf(card_id));
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Delete a card
    public static void deleteCard(int card_id) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                     "DELETE FROM card WHERE card_id = ?")) {
            conn.setAutoCommit(false);
            stmt.setInt(1, card_id);
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all cards
    public static void displayAllCards() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT * FROM card")) {
            System.out.println("\ncard List:");
            System.out.println("card_id\tholderName\tcardNumber\texpiration\tcvc\taddress1\taddress2\tcity\tcountry\tstate\tzipCode");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("card_id") + "\t" +
                                rs.getString("holderName") + "\t"+
                                rs.getString("cardNumber") + "\t" +
                                rs.getString("expiration") + "\t" +
                                rs.getString("cvc") + "\t" +
                                rs.getString("address1") + "\t" +
                                rs.getString("address2") + "\t" +
                                rs.getString("city") + "\t" +
                                rs.getString("state") + "\t" +
                                rs.getString("zipCode") + "\t"
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // End of Card /////////////////////////////////////////////////////////////////////////////////////////////////////
}
