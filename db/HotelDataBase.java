import java.sql.*;
/*
 EVERYTHING YOU NEED TO RUN
 * You need to install mySQL installer and make a custom installation
   install workbench, client, and shell.
 * You need to make a password and remember it.
 * You need to create a schema called hoteldb.
 * You need to add the plugin called Database Navigator on IntelliJ.
   Found under settings and plugins.
 * Then you need to find mysql-connector-j-9.2.0.jar and put it under modules.
    Under Project structure under dependencies.
 * You need to add a connection called hoteldb_connection and make sure the database is hoteldb.
 * You need to put root as user and use the same password from earlier.
 * You need to download the mysql-connector-j-9.2.0.zip and extract.

 * Create the database tables in "hoteldb.sql"
 * Default USER is root but if you changed it on your end
 * You have to change the variable in this code.
 * Change the PASSWORD variable to your password in this code.

 Troubleshoot
 * If having sync issues disconnect and reconnect.
 * For more accuracy use the workbench for viewing the tables.

 Test
 4/24/25 video
 */


public class HotelDataBase {
    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/hoteldb";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Guest ////////////////////////////////////////////
    /*
     * Add a new guest to guest table in the database
     * @param guest User object that gives information to be stored
     */
    public static void addGuest(User guest) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO guests (" +
                             "firstname, lastname, email, password, phone, " +
                             "address1, address2, city, country, state, zipCode) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            conn.setAutoCommit(false);
            stmt.setString(1, guest.getFirst());
            stmt.setString(2, guest.getLast());
            stmt.setString(3, guest.getEmail());
            stmt.setString(4, guest.getPassword());
            stmt.setString(5, guest.getPhoneNumber());
            stmt.setString(6, guest.getAddress1());
            stmt.setString(7, guest.getAddress2());
            stmt.setString(8, guest.getCity());
            stmt.setString(9, guest.getCountry());
            stmt.setString(10, guest.getState());
            stmt.setString(11, guest.getZipCode());
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
     * update guest information to the guest table in the database
     * @param newGuest User object that gives information to be stored
     */
    public static void updateGuest(User newGuest) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("UPDATE guests SET " +
                     "firstname=?," +
                     "lastname=?," +
                     "email=?," +
                     "password=?," +
                     "phone=?," +
                     "address1=?," +
                     "address2=?," +
                     "city=?," +
                     "country=?," +
                     "state=?," +
                     "zipCode=? WHERE email=?")) {
            conn.setAutoCommit(false);
            stmt.setString(1, newGuest.getFirst());
            stmt.setString(2, newGuest.getLast());
            stmt.setString(3, newGuest.getEmail());
            stmt.setString(4, newGuest.getPassword());
            stmt.setString(5, newGuest.getPhoneNumber());
            stmt.setString(6, newGuest.getAddress1());
            stmt.setString(7, newGuest.getAddress2());
            stmt.setString(8, newGuest.getCity());
            stmt.setString(9, newGuest.getCountry());
            stmt.setString(10, newGuest.getState());
            stmt.setString(11, newGuest.getZipCode());
            stmt.setString(12, newGuest.getEmail());
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
     * Delete a guest from the guest table in the database
     * @param guestId Integer that is in reference to the guestID
     * that is to be deleted from the guest table from in the database
     */
    public static void deleteGuest(String email) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                     "DELETE FROM guests WHERE email = ?")) {
            conn.setAutoCommit(false);
            stmt.setString(1, email);
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
     * Gets one guest from the guest table in the database
     * @param guestId Integer that is in reference to the guestID
     * that is to be used to grab from the database
     * @return guest User object
     */
    public static User getGuest(String email) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT * FROM guests WHERE email = '"+email+"'")) {
            User user = new User();
            while(rs.next()) {
                user.setUser_id(rs.getInt("guest_id"));
                user.setFirst(rs.getString("firstname"));
                user.setLast(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhoneNumber(rs.getString("phone"));
                user.setAddress1(rs.getString("address1"));
                user.setAddress2(rs.getString("address2"));
                user.setCity(rs.getString("city"));
                user.setCountry(rs.getString("country"));
                user.setState(rs.getString("state"));
                user.setZipCode(rs.getString("zipCode"));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // End of Guest /////////////////////////////////////

    // Card /////////////////////////////////////////////
    /*
     * Add a new card to card table in the database
     * @param card an object created by the card class to be put into the database
     */
    public static void addCard(Card card) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO card (guest_id, holderName, cardNumber, expiration, cvc, address1, address2, city, country, state, zipCode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            conn.setAutoCommit(false);
            stmt.setInt(1, card.getGuest_id());
            stmt.setString(2, card.getHolderName());
            stmt.setString(3, card.getCardNumber());
            stmt.setString(4, card.getExpiration());
            stmt.setString(5, card.getCvc());
            stmt.setString(6, card.getAddress1());
            stmt.setString(7, card.getAddress2());
            stmt.setString(8, card.getCity());
            stmt.setString(9, card.getCountry());
            stmt.setString(10, card.getState());
            stmt.setString(11, card.getZipCode());
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * Updates a card in the card table in the database
     * @param card an object created by the card class to be put into the database
     */
    public static void updateCard(Card newCard) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("UPDATE card SET " +
                     "holderName=?," +
                     "cardNumber=?," +
                     "expiration=?," +
                     "cvc=?," +
                     "address1=?," +
                     "address2=?," +
                     "city=?," +
                     "country=?," +
                     "state=?," +
                     "zipCode=? WHERE card_id="+newCard.getCard_id())) {
            conn.setAutoCommit(false);
            stmt.setString(1, newCard.getHolderName());
            stmt.setString(2, newCard.getCardNumber());
            stmt.setString(3, newCard.getExpiration());
            stmt.setString(4, newCard.getCvc());
            stmt.setString(5, newCard.getAddress1());
            stmt.setString(6, newCard.getAddress2());
            stmt.setString(7, newCard.getCity());
            stmt.setString(8, newCard.getCountry());
            stmt.setString(9, newCard.getState());
            stmt.setString(10, newCard.getZipCode());
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
     * Deletes a card in the card table in the database
     * @param card_id the id associated with the card
     */
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
    /*
     * Display all cards that are associated with a guest
     * @param guest_id the id of the guest
     */
    public static void displayCards(int guest_id) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM card Where guest_id = "+guest_id)) {
            System.out.println("\nList of Cards connected to guest_id = "+guest_id+
                    "\nCard_id\tholderName\tcardNumber\texpiration\tcvc" +
                    "\taddress1\taddress2\tcity\tcountry\tstate\tzipCode");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("card_id") + "\t" +
                        rs.getString("holderName") + "\t" +
                        rs.getString("cardNumber") + "\t" +
                        rs.getString("expiration") + "\t"+
                        rs.getString("cvc") + "\t" +
                        rs.getString("address1") + "\t" +
                        rs.getString("address2") + "\t" +
                        rs.getString("city") + "\t" +
                        rs.getString("country") + "\t" +
                        rs.getString("state") + "\t" +
                        rs.getString("zipCode") + "\t"
                );

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
     * Gets a card in the card table in the database
     * @param card_id the id associated with the card
     * @return card an object created by the card class
     */
    public static Card getCard(int card_id) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT * FROM card WHERE card_id = " + card_id)) {
            Card card = new Card();
            while(rs.next()) {
                card.setCard_id(rs.getInt("card_id"));
                card.setHolderName(rs.getString("holderName"));
                card.setCardNumber(rs.getString("cardNumber"));
                card.setExpiration(rs.getString("expiration"));
                card.setCvc(rs.getString("cvc"));
                card.setAddress1(rs.getString("address1"));
                card.setAddress2(rs.getString("address2"));
                card.setCity(rs.getString("city"));
                card.setCountry(rs.getString("country"));
                card.setState(rs.getString("state"));
                card.setZipCode(rs.getString("zipCode"));
            }
            return card;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // End of Card //////////////////////////////////////
}
