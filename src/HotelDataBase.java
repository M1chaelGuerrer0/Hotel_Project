import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
    /* Guest Methods: HotelDataBase
     * .addGuest(User guest) // add guest in db
     * .updateGuest(User guest) // update guest in db
     * .deleteGuest(String email) // delete guest and cards associated too
     * .getGuests() // list of all guest
     * .getGuest(String email) // copies a guest from the db into a User
     */

    /*
     * Add a new guest to guest table in the db
     * @param guest User object that gives information to be stored
     */
    public static void addGuest(User guest) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO guests (" +
                             "first_name, last_name, email, password, phone, " +
                             "address1, address2, city, country, state, zip_Code) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS)) {
            conn.setAutoCommit(false);
            stmt.setString(1, guest.getFirst_Name());
            stmt.setString(2, guest.getLast_Name());
            stmt.setString(3, guest.getEmail());
            stmt.setString(4, guest.getPassword());
            stmt.setString(5, guest.getPhone_Number());
            stmt.setString(6, guest.getAddress1());
            stmt.setString(7, guest.getAddress2());
            stmt.setString(8, guest.getCity());
            stmt.setString(9, guest.getCountry());
            stmt.setString(10, guest.getState());
            stmt.setString(11, guest.getZip_Code());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    guest.setUser_id(generatedKeys.getInt(1));
                }
            }
            conn.commit();

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.out.println("Error: This Guest is already added to the database.");
            } else {
                System.out.println("Database error: " + e.getMessage());
                e.printStackTrace();
            }        }
    }
    /*
     * update guest information to the guest table in the db
     * @param newGuest User object that gives information to be stored
     */
    public static void updateGuest(User newGuest) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("UPDATE guests SET " +
                     "first_name=?," +
                     "last_name=?," +
                     "email=?," +
                     "password=?," +
                     "phone=?," +
                     "address1=?," +
                     "address2=?," +
                     "city=?," +
                     "country=?," +
                     "state=?," +
                     "zip_Code=? WHERE email=?")) {
            conn.setAutoCommit(false);
            stmt.setString(1, newGuest.getFirst_Name());
            stmt.setString(2, newGuest.getLast_Name());
            stmt.setString(3, newGuest.getEmail());
            stmt.setString(4, newGuest.getPassword());
            stmt.setString(5, newGuest.getPhone_Number());
            stmt.setString(6, newGuest.getAddress1());
            stmt.setString(7, newGuest.getAddress2());
            stmt.setString(8, newGuest.getCity());
            stmt.setString(9, newGuest.getCountry());
            stmt.setString(10, newGuest.getState());
            stmt.setString(11, newGuest.getZip_Code());
            stmt.setString(12, newGuest.getEmail());
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
     * Delete a guest from the guest table in the db
     * @param guestId Integer that is in reference to the guestID
     * that is to be deleted from the guest table from in the db
     */
    public static void deleteGuest(String email) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                     "DELETE FROM guests WHERE email = ?")) {
            conn.setAutoCommit(false);
            stmt.setString(1,email);
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
     * Gets a List of all Guests from the guest table in the db
     * @return guests A list of all guests
     */
    public static List<User> getGuests() {
        List<User> guests = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM guests")) {
            while (rs.next()) {
                guests.add(new User(rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phone"),
                        rs.getString("address1"),
                        rs.getString("address2"),
                        rs.getString("zip_Code"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("country"),
                        rs.getInt("guest_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guests;
    }
    /*
     * Gets one guest from the guest table in the db
     * @param guestId Integer that is in reference to the guestID
     * that is to be used to grab from the db
     * @return guest User object
     */
    public static User getGuest(String email) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT * FROM guests WHERE email = '"+email+"'")) {
            while(rs.next()) {
                User guest = new User(rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phone"),
                        rs.getString("address1"),
                        rs.getString("address2"),
                        rs.getString("zip_Code"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("country"),
                        rs.getInt("guest_id"));
                return guest;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // End of Guest /////////////////////////////////////

    // Card /////////////////////////////////////////////

    /* Card Methods: HotelDataBase
     * .addCard(Card card) // add card in db
     * .updateCard(Card card) // update card in db
     * .deleteCard(int card_id) // delete card
     * .getCards() // list of all cards
     * .getCardsOfGuest(int guest_id) // list of all cards associated with the guest
     * .getCard(int card_id) // grabs a card class object
     */

    /*
     * Add a new card to card table in the db
     * @param card an object created by the card class to be put into the db
     */
    public static void addCard(Card card) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO card " +
                     "(guest_id, holder_Name, card_Number, expiration, " +
                     "cvc, address1, address2, city, country, state, zip_Code) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS)) {
            conn.setAutoCommit(false);
            stmt.setInt(1, card.getGuest_id());
            stmt.setString(2, card.getHolder_Name());
            stmt.setString(3, card.getCard_Number());
            stmt.setString(4, card.getExpiration());
            stmt.setString(5, card.getCvc());
            stmt.setString(6, card.getAddress1());
            stmt.setString(7, card.getAddress2());
            stmt.setString(8, card.getCity());
            stmt.setString(9, card.getCountry());
            stmt.setString(10, card.getState());
            stmt.setString(11, card.getZip_Code());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    card.setCard_id(generatedKeys.getInt(1));
                }
            }
            conn.commit();

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.out.println("Error: This Card is already added to the database.");
            } else {
                System.out.println("Database error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /*
     * Updates a card in the card table in the db
     * @param card an object created by the card class to be put into the db
     */
    public static void updateCard(Card newCard) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("UPDATE card SET " +
                     "holder_Name=?," +
                     "card_Number=?," +
                     "expiration=?," +
                     "cvc=?," +
                     "address1=?," +
                     "address2=?," +
                     "city=?," +
                     "country=?," +
                     "state=?," +
                     "zip_Code=? " +
                     "WHERE card_id="+newCard.getCard_id())) {
            conn.setAutoCommit(false);
            stmt.setString(1, newCard.getHolder_Name());
            stmt.setString(2, newCard.getCard_Number());
            stmt.setString(3, newCard.getExpiration());
            stmt.setString(4, newCard.getCvc());
            stmt.setString(5, newCard.getAddress1());
            stmt.setString(6, newCard.getAddress2());
            stmt.setString(7, newCard.getCity());
            stmt.setString(8, newCard.getCountry());
            stmt.setString(9, newCard.getState());
            stmt.setString(10, newCard.getZip_Code());
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
     * Deletes a card in the card table in the db
     * @param card_id the id associated with the card
     */
    public static void deleteCard(int card_id) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                     "DELETE FROM card WHERE card_id = "+card_id)) {
            conn.setAutoCommit(false);
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
     * Gets a List of all cards from the card table in the db
     * @return cards A list of card
     */
    public static List<Card> getCards() {
        List<Card> cards = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM card")) {
            while (rs.next()) {
                cards.add(new Card(rs.getString("holder_Name"),
                        rs.getString("card_Number"),
                        rs.getString("expiration"),
                        rs.getString("cvc"),
                        rs.getString("address1"),
                        rs.getString("address2"),
                        rs.getString("zip_Code"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("country"),
                        rs.getInt("card_id"),
                        rs.getInt("guest_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cards;
    }
    /*
     * Gets a List of all cards associated with a guest from the card table in the db
     * @return cards A list of cards
     */
    public static List<Card> getCardsOfGuest(int guest_id) {
        List<Card> cards = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM card Where guest_id = "+ guest_id)) {
            while (rs.next()) {
                cards.add(new Card(rs.getString("holder_Name"),
                        rs.getString("card_Number"),
                        rs.getString("expiration"),
                        rs.getString("cvc"),
                        rs.getString("address1"),
                        rs.getString("address2"),
                        rs.getString("zip_Code"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("country"),
                        rs.getInt("card_id"),
                        rs.getInt("guest_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cards;
    }
    /*
     * Gets a card in the card table in the db
     * @param card_id the id associated with the card
     * @return card an object created by the card class
     */
    public static Card getCard(int card_id) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT * FROM card WHERE card_id = " + card_id)) {
            while(rs.next()) {
                Card card = new Card(rs.getString("holder_Name"),
                        rs.getString("card_Number"),
                        rs.getString("expiration"),
                        rs.getString("cvc"),
                        rs.getString("address1"),
                        rs.getString("address2"),
                        rs.getString("zip_Code"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("country"),
                        rs.getInt("card_id"),
                        rs.getInt("guest_id")
                );
                return card;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // End of Card //////////////////////////////////////
    // Room /////////////////////////////////////////////
    /*
     * Add a new room to the room table in db
     * @param room an object created by the Room class to be put into the db
     */
    public static void addRoom(Room room) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO room " +
                     "(room_Type, price_Per_Night, room_Capacity, availability) " +
                     "VALUES ( ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            conn.setAutoCommit(false);
            stmt.setString(1, room.getRoom_Type());
            stmt.setDouble(2, room.getPrice_Per_Night());
            stmt.setInt(3, room.getRoom_Capacity());
            stmt.setString(4, room.getAvailability());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    room.setRoom_Number(generatedKeys.getInt(1));
                }
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
     * Updates a room in the room table in the db
     * @param room an object created by the Room class to be put into the db
     */
    public static void updateRoom(Room room) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("UPDATE room SET " +
                     "room_Type=?, price_Per_Night=?, room_Capacity=?, availability=? " +
                     "WHERE room_Number="+room.getRoom_Number())) {
            conn.setAutoCommit(false);
            stmt.setString(1, room.getRoom_Type());
            stmt.setDouble(2, room.getPrice_Per_Night());
            stmt.setInt(3, room.getRoom_Capacity());
            stmt.setString(4, room.getAvailability());
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
     * Deletes a room in the room table in the db
     * @param room_Number the number of the room
     */
    public static void deleteRoom(int room_Number) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                     "DELETE FROM room WHERE room_Number = "+room_Number)) {
            conn.setAutoCommit(false);
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
     * Display all rooms
     */
    public static void displayRoom() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT * FROM room")) {
            System.out.println("\nList of Rooms:" +
                    "\nroom_Number\troom_Type" +
                    "\tprice_Per_Night\troom_Capacity\tavailability");
            while (rs.next()) {
                System.out.println(rs.getInt("room_Number") + "\t" +
                        rs.getString("room_Type") + "\t" +
                        rs.getDouble("price_Per_Night") + "\t" +
                        rs.getInt("room_Capacity") + "\t"+
                        rs.getString("availability")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
     * Gets a room in the room table in the db
     * @param room_Number the number of the room
     * @return room an object created by the room class
     */
    public static Room getRoom(int room_Number) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT * FROM room WHERE room_Number = " + room_Number)) {
            Room room = new Room();
            while(rs.next()) {
                room.setRoom_Number(rs.getInt("room_Number"));
                room.setRoom_Type(rs.getString("room_Type"));
                room.setPrice_Per_Night(rs.getDouble("price_Per_Night"));
                room.setRoom_Capacity(rs.getInt("room_Capacity"));
                room.setAvailability(rs.getString("availability"));
            }
            return room;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // End of Room //////////////////////////////////////
    // Reservation //////////////////////////////////////
    /*
     * Add a new Reservation to the reservation table in db
     * @param reservation an object created by the Reservation class to be put into the db
     */
    public static void addReservation(Reservation reservation) {
        // checks if there already is a reservation under that same time for the same room
        int temp = 0;
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(
                     "SELECT COUNT(*) AS overlapping_reservations " +
                             "FROM reservation " +
                             "WHERE room_Number = ? " +
                             "AND ( " +
                             "    (? BETWEEN check_In_Date AND check_Out_Date) OR " +
                             "    (? BETWEEN check_In_Date AND check_Out_Date) OR " +
                             "    (check_In_Date BETWEEN ? AND ?))")) {
            pstmt.setInt(1, reservation.getRoom_Number());
            pstmt.setDate(2, new java.sql.Date(reservation.getCheck_In_Date().getTime()));
            pstmt.setDate(3, new java.sql.Date(reservation.getCheck_Out_Date().getTime()));
            pstmt.setDate(4, new java.sql.Date(reservation.getCheck_In_Date().getTime()));
            pstmt.setDate(5, new java.sql.Date(reservation.getCheck_Out_Date().getTime()));
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    temp = rs.getInt("overlapping_reservations");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(temp == 0) { // if room is available
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement("INSERT INTO reservation " +
                         "(room_Number, guest_id, card_id, name, check_In_Date, " +
                         "check_Out_Date, check_In_Time, check_Out_Time) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                conn.setAutoCommit(false);
                stmt.setInt(1, reservation.getRoom_Number());
                stmt.setInt(2, reservation.getGuest_id());
                stmt.setInt(3, reservation.getCard_id());
                stmt.setString(4, reservation.getName());
                stmt.setTimestamp(5, new Timestamp(reservation.getCheck_In_Date().getTime()));
                stmt.setTimestamp(6, new Timestamp(reservation.getCheck_Out_Date().getTime()));
                stmt.setTime(7, reservation.getCheck_In_Time());
                stmt.setTime(8, reservation.getCheck_Out_Time());
                stmt.executeUpdate();
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        reservation.setReserve_id(generatedKeys.getInt(1));
                    }
                }
                conn.commit();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{ // if not available
            System.out.println("ROOM TAKEN FOR THAT TIME");
        }
    }
    /*
     * Updates a reservation in the reservation table in the db
     * @param reservation an object created by the Reservation class to be put into the db
     */
    public static void updateReservation(Reservation reservation) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("UPDATE reservation SET " +
                     "room_Number=?, guest_id=?, card_id=?, name=?, check_In_Date=?, " +
                     "check_Out_Date=?, check_In_Time=?, check_Out_Time=?" +
                     "WHERE reserve_id="+reservation.getReserve_id())) {
            conn.setAutoCommit(false);
            stmt.setInt(1,reservation.getRoom_Number());
            stmt.setInt(2,reservation.getGuest_id());
            stmt.setInt(3,reservation.getCard_id());
            stmt.setString(4,reservation.getName());
            stmt.setDate(5,reservation.getCheck_In_Date());
            stmt.setDate(6,reservation.getCheck_Out_Date());
            stmt.setTime(7,reservation.getCheck_In_Time());
            stmt.setTime(8,reservation.getCheck_Out_Time());

            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
     * Deletes a reservation in the reservation table in the db
     * @param reserve_id the reservation ID number
     */
    public static void deleteReservation(int reserve_id) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                     "DELETE FROM reservation WHERE reserve_id = "+reserve_id)) {
            conn.setAutoCommit(false);
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
     * Display all reservation
     */
    public static void displayReservation() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT * FROM reservation")) {
            System.out.println("\nList of reservation:" +
                    "\nreserve_id\troom_Number\tguest_id\tname\t" +
                    "check_In_Date\tcheck_Out_Date\tcheck_In_Time\tcheck_Out_Time");
            while (rs.next()) {
                System.out.println(rs.getInt("reserve_id") + "\t" +
                        rs.getInt("room_Number") + "\t" +
                        rs.getInt("guest_id") + "\t" +
                        rs.getInt("card_id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getDate("check_In_Date") + "\t"+
                        rs.getDate("check_Out_Date") + "\t" +
                        rs.getTime("check_In_Time") + "\t" +
                        rs.getTime("check_Out_Time")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
     * Gets a reservation in the reservation table in the db
     * @param reserve_Id the ID of the reservation
     * @return reservation an object created by the Reservation class
     */
    public static Reservation getReservation(int room_Number) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT * FROM reservation WHERE room_Number = " + room_Number)) {
            Reservation reservation = new Reservation();
            while(rs.next()) {
                reservation.setReserve_id(rs.getInt("reserve_id"));
                reservation.setRoom_Number(rs.getInt("room_Number"));
                reservation.setGuest_id(rs.getInt("guest_id"));
                reservation.setName(rs.getString("name"));
                reservation.setCheck_In_Date(rs.getDate("check_In_Date"));
                reservation.setCheck_Out_Date(rs.getDate("check_Out_Date"));
                reservation.setCheck_In_Time(rs.getTime("check_In_Time"));
                reservation.setCheck_Out_Time(rs.getTime("check_In_Time"));
                reservation.setCard_id(rs.getInt("card_id"));
            }
            return reservation;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // End of Reservation ///////////////////////////////
}
