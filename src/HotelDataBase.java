import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/*
 EVERYTHING YOU NEED TO RUN
 * install intellij
 * install jdk 21
 * install mySQL installer and make a custom installation. (install workbench, client, and shell.)
 * Make a password and remember it.
 * Create a schema called hoteldb.
 * Add the plugin called Database Navigator on IntelliJ (Found under settings and plugins.)
 * install mysql-connectionector-j-9.2.0.jar and put it under modules (Under Project structure under dependencies)
 * Go to db navigator at the top of the screen and create a connection and called hoteldb_connection
   and change the database to hoteldb.
 * You need to put root as user and use the same password from earlier.
 * Test connection and it should be successful.
 * Run HotelDataBase.reset() once to create the tables
 * Input the password from earlier under PASSWORD
 */


public class HotelDataBase {
    public static final Connection connection;
    static {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hoteldb",
                    "root",
                    "" // PASSWORD
            );
        } catch (SQLException e) {
            throw new RuntimeException("DB connection failed", e);
        }
    }

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
     * @param guest User class object that gives information to be stored
     */
    public static void addGuest(User guest) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO guests (" +
                        "first_name, last_name, email, password, phone, " +
                        "address1, address2, city, country, state, zip_Code) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
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
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) { // auto assign the key to guest // happens once
                if (generatedKeys.next()) {
                    guest.setUser_id(generatedKeys.getInt(1));
                }
            }
            connection.commit();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) { // if a dupe is caught
                System.out.println("Error: This Guest is already added to the database.\n"
                        + e.getMessage());
            } else {
                System.out.println("Database error: " + e.getMessage());
            }
        }
    }
    /*
     * update guest information to the guest table in the db using guest_id
     * @param newGuest User object that gives information to be stored
     */
    public static void updateGuest(User newGuest) {
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE guests SET " +
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
                "zip_Code=? WHERE guest_id=?")) {
            connection.setAutoCommit(false);
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
            stmt.setInt(12, newGuest.getUser_id());
            stmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
     * Delete a guest from the guest table in the db
     * @param email the email associated with the guest
     */
    public static void deleteGuest(String email) { // deletes all cards associated with guest as well
        try (Statement stmt = connection.createStatement()) {
            connection.setAutoCommit(false);
            stmt.execute("SET FOREIGN_KEY_CHECKS = 0");
            stmt.execute("DELETE c FROM card c JOIN guests g ON " +
                    "c.guest_id = g.guest_id WHERE g.email = '" + email + "'");
            stmt.execute("DELETE FROM guests WHERE email = '"+email+"'");
            stmt.execute("SET FOREIGN_KEY_CHECKS = 1");
            connection.commit();
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
        try (Statement stmt = connection.createStatement();
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
            return guests;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /*
     * Gets one guest from the guest table in the db
     * @param email An email associated with the guest
     * @return guest User Class Object
     */
    public static User getGuest(String email) {
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM guests WHERE email = '"+email+"'")) {
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
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO card " +
                "(guest_id, holder_Name, card_Number, expiration, " +
                "cvc, address1, address2, city, country, state, zip_Code) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
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
            connection.commit();

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) { // finds dupes
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
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE card SET " +
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
            connection.setAutoCommit(false);
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
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
     * Deletes a card in the card table in the db
     * @param card_id the id associated with the card
     */
    public static void deleteCard(int card_id) {
        try (Statement stmt = connection.createStatement()) {
            connection.setAutoCommit(false);
            stmt.execute("SET FOREIGN_KEY_CHECKS = 0");
            stmt.execute("DELETE FROM card WHERE card_id = "+card_id);
            stmt.execute("SET FOREIGN_KEY_CHECKS = 1");
            connection.commit();
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
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM card")) {
            while (rs.next()) {
                cards.add(new Card(rs.getString("holder_Name"),
                        rs.getString("card_Number"),
                        rs.getString("expiration"),
                        rs.getString("cvc"),
                        rs.getString("address1"),
                        rs.getString("address2"),
                        rs.getString("city"),
                        rs.getString("country"),
                        rs.getString("state"),
                        rs.getString("zip_code"),
                        rs.getInt("card_id"),
                        rs.getInt("guest_id")
                ));
            }
            return cards;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /*
     * Gets a List of all cards associated with a guest from the card table in the db
     * @return cards A list of cards
     */
    public static List<Card> getCardsOfGuest(int guest_id) {
        List<Card> cards = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
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
            return cards;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /*
     * Gets a card in the card table in the db
     * @param card_id the id associated with the card
     * @return card an object created by the card class
     */
    public static Card getCard(int card_id) {
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM card WHERE card_id = " + card_id)) {
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

    /* Room Methods: HotelDataBase
     * .addRoom(Room room) // add room in db
     * .updateRoom(Room room) // update room in db
     * .deleteRoom(int room_Number) // delete room
     * .getRooms() // list of all rooms
     * .getRoom(int room_Number) // grabs a room class object
     */

    /*
     * Add a new room to the room table in db
     * @param room an object created by the Room class to be put into the db
     */
    public static void addRoom(Room room) {
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO room " +
                "(room_Type, price_Per_Night, room_Capacity, availability) " +
                "VALUES ( ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
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
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
     * Updates a room in the room table in the db
     * @param room an object created by the Room class to be put into the db
     */
    public static void updateRoom(Room room) {
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE room SET " +
                "room_Type=?, price_Per_Night=?, room_Capacity=?, availability=? " +
                "WHERE room_Number="+room.getRoom_Number())) {
            connection.setAutoCommit(false);
            stmt.setString(1, room.getRoom_Type());
            stmt.setDouble(2, room.getPrice_Per_Night());
            stmt.setInt(3, room.getRoom_Capacity());
            stmt.setString(4, room.getAvailability());
            stmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
     * Deletes a room in the room table in the db
     * @param room_Number the number of the room
     */
    public static void deleteRoom(int room_Number) {
        try (Statement stmt = connection.createStatement()) {
            connection.setAutoCommit(false);
            stmt.execute("SET FOREIGN_KEY_CHECKS = 0");
            stmt.execute("DELETE FROM room WHERE room_Number = "+room_Number);
            stmt.execute("SET FOREIGN_KEY_CHECKS = 1");
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
     * Gets a List of all rooms from the room table in the db
     * @return rooms A list of rooms
     */
    public static List<Room> getRooms() {
        List<Room> rooms = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM room")) {
            while (rs.next()) {
                rooms.add(new Room(rs.getInt("room_Number"),
                        rs.getString("room_Type"),
                        rs.getDouble("price_Per_Night"),
                        rs.getInt("room_Capacity"),
                        rs.getString("availability")
                ));
            }
            return rooms;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /*
     * Gets a room in the room table in the db
     * @param room_Number the number of the room
     * @return room an object created by the room class
     */
    public static Room getRoom(int room_Number) {
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM room WHERE room_Number = " + room_Number)) {
            while(rs.next()) {
                Room room = new Room(rs.getInt("room_Number"),
                        rs.getString("room_Type"),
                        rs.getDouble("price_Per_Night"),
                        rs.getInt("room_Capacity"),
                        rs.getString("availability")
                );
                return room;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * Gets a List of all rooms of a certain type from the room table in the db
     * @return rooms A list of rooms
     */
    public static List<Room> getRoomOfType(String room_Type) {
        List<Room> rooms = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM room WHERE room_Type = '" +room_Type+"'")) {
            while (rs.next()) {
                rooms.add(new Room(rs.getInt("room_Number"),
                        rs.getString("room_Type"),
                        rs.getDouble("price_Per_Night"),
                        rs.getInt("room_Capacity"),
                        rs.getString("availability")
                ));
            }
            return rooms;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // End of Room //////////////////////////////////////
    // Reservation //////////////////////////////////////
    /*
     * .addReservation(Reservation reservation) // add res
     * .updateReservation(Reservation reservation) // update res
     * .deleteReservation(int reserve_id) // delete res
     * .getReservations() // get all current res
     * .getReservationHistory() // get all res
     * .getReservation(int room_Number) // get a single specific res
     */

    /*
     * Add a new Reservation to the reservation table in db
     * @param reservation an object created by the Reservation class to be put into the db
     */
    public static void addReservation(Reservation reservation) {
        // checks if there already is a reservation under that same time for the same room
        int temp = 0;
        try (PreparedStatement pstmt = connection.prepareStatement("SELECT COUNT(*) AS overlapping_reservations " +
                "FROM reservation " +
                "WHERE room_Number = ? " +
                "AND ( " +
                "(? BETWEEN check_In_Date AND check_Out_Date) OR " +
                "(? BETWEEN check_In_Date AND check_Out_Date) OR " +
                "(check_In_Date BETWEEN ? AND ?))")) {
            pstmt.setInt(1, reservation.getRoom_Number());
            pstmt.setDate(2, new Date(reservation.getCheck_In_Date().getTime()));
            pstmt.setDate(3, new Date(reservation.getCheck_Out_Date().getTime()));
            pstmt.setDate(4, new Date(reservation.getCheck_In_Date().getTime()));
            pstmt.setDate(5, new Date(reservation.getCheck_Out_Date().getTime()));
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    temp = rs.getInt("overlapping_reservations");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(temp == 0) { // if room is available
            try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO reservation " +
                    "(room_Number, guest_id, card_id, name, check_In_Date, " +
                    "check_Out_Date, check_In_Time, check_Out_Time) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                connection.setAutoCommit(false);
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
                connection.commit();

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
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE reservation SET " +
                "room_Number=?, guest_id=?, card_id=?, name=?, check_In_Date=?, " +
                "check_Out_Date=?, check_In_Time=?, check_Out_Time=?" +
                "WHERE reserve_id="+reservation.getReserve_id())) {
            connection.setAutoCommit(false);
            stmt.setInt(1,reservation.getRoom_Number());
            stmt.setInt(2,reservation.getGuest_id());
            stmt.setInt(3,reservation.getCard_id());
            stmt.setString(4,reservation.getName());
            stmt.setDate(5,reservation.getCheck_In_Date());
            stmt.setDate(6,reservation.getCheck_Out_Date());
            stmt.setTime(7,reservation.getCheck_In_Time());
            stmt.setTime(8,reservation.getCheck_Out_Time());

            stmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
     * Deletes a reservation in the reservation table in the db
     * @param reserve_id the reservation ID number
     */
    public static void deleteReservation(int reserve_id) {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM reservation " +
                "WHERE reserve_id = "+reserve_id)) {
            connection.setAutoCommit(false);
            stmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
     * Gets a List of all Reservation from the room table in the db
     * @return reservations A list of Reservations
     */

    public static List<Reservation> getReservations() {
        List<Reservation> reservations = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM reservation WHERE check_Out_Time = '00:00:00'")) {
            while (rs.next()) {
                reservations.add(new Reservation(rs.getInt("reserve_id"),
                        rs.getInt("room_Number"),
                        rs.getInt("guest_id"),
                        rs.getString("name"),
                        rs.getDate("check_In_Date"),
                        rs.getDate("check_Out_Date"),
                        rs.getTime("check_In_Time"),
                        rs.getTime("check_Out_Time"),
                        rs.getInt("card_id")
                ));
            }
            return reservations;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /*
     * Gets a List of all Reservation from the room table in the db
     * @return reservations A list of Reservations
     */
    public static List<Reservation> getReservationHistory() {
        List<Reservation> reservations = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM reservation")) {
            while (rs.next()) {
                reservations.add(new Reservation(rs.getInt("reserve_id"),
                        rs.getInt("room_Number"),
                        rs.getInt("guest_id"),
                        rs.getString("name"),
                        rs.getDate("check_In_Date"),
                        rs.getDate("check_Out_Date"),
                        rs.getTime("check_In_Time"),
                        rs.getTime("check_Out_Time"),
                        rs.getInt("card_id")
                ));
            }
            return reservations;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /*
     * Gets a reservation in the reservation table in the db
     * @param reserve_Id the ID of the reservation
     * @return reservation an object created by the Reservation class
     */

    public static Reservation getReservation(int room_Number) {
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM reservation " +
                     "WHERE room_Number = " + room_Number)) {
            while(rs.next()) {
                Reservation reservation = new Reservation(rs.getInt("reserve_id"),
                        rs.getInt("room_Number"),
                        rs.getInt("guest_id"),
                        rs.getString("name"),
                        rs.getDate("check_In_Date"),
                        rs.getDate("check_Out_Date"),
                        rs.getTime("check_In_Time"),
                        rs.getTime("check_Out_Time"),
                        rs.getInt("card_id")
                );
                return reservation;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // End of Reservation ///////////////////////////////

    public static int total_revenue() {
        int total_revenue = 0;
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT SUM(price_Per_Night) " +
                     "AS total_revenue FROM reservation res JOIN room r ON res.room_Number = r.room_Number;")) {
            while(rs.next()) {
                return total_revenue = rs.getInt("total_revenue");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total_revenue;
    }
    public static void reset() { // NEEDS TO BE UPDATE WHEN CHANGES ARE MADE
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS workers");
            stmt.execute("DROP TABLE IF EXISTS reservation");
            stmt.execute("DROP TABLE IF EXISTS room");
            stmt.execute("DROP TABLE IF EXISTS card");
            stmt.execute("DROP TABLE IF EXISTS guests");
            stmt.execute("create TABLE guests (\n" +
                    "    guest_id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                    "    first_name VARCHAR(50) NOT NULL,\n" +
                    "    last_name VARCHAR(50) NOT NULL,\n" +
                    "    email VARCHAR(100) UNIQUE NOT NULL,\n" +
                    "    password VARCHAR(100) NOT NULL,\n" +
                    "    phone VARCHAR(20) NOT NULL,\n" +
                    "    address1 VARCHAR(100) NOT NULL,\n" +
                    "    address2 VARCHAR(100),\n" +
                    "    city VARCHAR(20) NOT NULL,\n" +
                    "    country VARCHAR(20) NOT NULL,\n" +
                    "    state VARCHAR(2) NOT NULL,\n" +
                    "    zip_Code VARCHAR(5) NOT NULL,\n" +
                    "    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP\n" +
                    ")");
            stmt.execute("create TABLE card (\n" +
                    "    card_id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                    "    guest_id INT,\n" +
                    "    FOREIGN KEY (guest_id) REFERENCES guests(guest_id),\n" +
                    "    holder_Name VARCHAR(50) NOT NULL,\n" +
                    "    card_Number VARCHAR(16) UNIQUE NOT NULL,\n" +
                    "    expiration VARCHAR(5) NOT NULL,\n" +
                    "    cvc VARCHAR(3) NOT NULL,\n" +
                    "    address1 VARCHAR(100) NOT NULL,\n" +
                    "    address2 VARCHAR(100),\n" +
                    "    city VARCHAR(20) NOT NULL,\n" +
                    "    country VARCHAR(20) NOT NULL,\n" +
                    "    state VARCHAR(2) NOT NULL,\n" +
                    "    zip_Code VARCHAR(5) NOT NULL,\n" +
                    "    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP\n" +
                    ")");
            stmt.execute("create TABLE room (\n" +
                    "    room_Number INT AUTO_INCREMENT PRIMARY KEY,\n" +
                    "    room_Type VARCHAR(10) NOT NULL,\n" +
                    "    price_Per_Night DECIMAL(10,2) NOT NULL,\n" +
                    "    room_Capacity INT NOT NULL,\n" +
                    "    availability VARCHAR(10) NOT NULL,\n" +
                    "    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP\n" +
                    ")");
            stmt.execute("create TABLE reservation (\n" +
                    "    reserve_id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                    "    room_Number INT NOT NULL,\n" +
                    "    guest_id INT NOT NULL,\n" +
                    "    card_id INT NOT NULL,\n" +
                    "    name VARCHAR(100) NOT NULL,\n" +
                    "    check_In_Date DATE NOT NULL,\n" +
                    "    check_Out_Date DATE  NOT NULL,\n" +
                    "    check_In_Time TIME,\n" +
                    "    check_Out_Time TIME,\n" +
                    "    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,\n" +
                    "    FOREIGN KEY (room_Number) REFERENCES room(room_Number),\n" +
                    "    FOREIGN KEY (guest_id) REFERENCES guests(guest_id),\n" +
                    "    FOREIGN KEY (card_id) REFERENCES card(card_id)\n" +
                    ")");
            stmt.execute("CREATE TABLE workers (\n" +
                    "    worker_id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                    "    first_name VARCHAR(50) NOT NULL,\n" +
                    "    last_name VARCHAR(50) NOT NULL,\n" +
                    "    email VARCHAR(100) UNIQUE NOT NULL,\n" +
                    "    password VARCHAR(100) NOT NULL,\n" +
                    "    phone VARCHAR(20) NOT NULL,\n" +
                    "    address1 VARCHAR(100) NOT NULL,\n" +
                    "    address2 VARCHAR(100),\n" +
                    "    city VARCHAR(20) NOT NULL,\n" +
                    "    country VARCHAR(20) NOT NULL,\n" +
                    "    state VARCHAR(2) NOT NULL,\n" +
                    "    zip_Code VARCHAR(5) NOT NULL,\n" +
                    "    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP\n" +
                    ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Worker ///////////////////////////////////////////
    /* worker Methods: HotelDataBase
     * .addWorker(User worker) // add worker in db
     * .updateWorker(User worker) // update worker in db
     * .deleteWorker(String email) // delete worker
     * .getWorker() // list of all worker
     * .getWorker(String email) // copies a worker from the db into a User
     */

    /*
     * Add a new worker to worker table in the db
     * @param worker User class object that gives information to be stored
     */
    public static void addWorker(User worker) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO workers (" +
                        "first_name, last_name, email, password, phone, " +
                        "address1, address2, city, country, state, zip_Code) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            stmt.setString(1, worker.getFirst_Name());
            stmt.setString(2, worker.getLast_Name());
            stmt.setString(3, worker.getEmail());
            stmt.setString(4, worker.getPassword());
            stmt.setString(5, worker.getPhone_Number());
            stmt.setString(6, worker.getAddress1());
            stmt.setString(7, worker.getAddress2());
            stmt.setString(8, worker.getCity());
            stmt.setString(9, worker.getCountry());
            stmt.setString(10, worker.getState());
            stmt.setString(11, worker.getZip_Code());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    worker.setUser_id(generatedKeys.getInt(1));
                }
            }
            connection.commit();

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.out.println("Error: This worker is already added to the database.\n"
                        + e.getMessage());
            } else {
                System.out.println("Database error: " + e.getMessage());
            }
        }
    }
    /*
     * update guest information to the guest table in the db
     * @param newGuest User object that gives information to be stored
     */
    public static void updateWorker(User worker) {
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE workers SET " +
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
                "zip_Code=? WHERE worker_id=?")) {
            connection.setAutoCommit(false);
            stmt.setString(1, worker.getFirst_Name());
            stmt.setString(2, worker.getLast_Name());
            stmt.setString(3, worker.getEmail());
            stmt.setString(4, worker.getPassword());
            stmt.setString(5, worker.getPhone_Number());
            stmt.setString(6, worker.getAddress1());
            stmt.setString(7, worker.getAddress2());
            stmt.setString(8, worker.getCity());
            stmt.setString(9, worker.getCountry());
            stmt.setString(10, worker.getState());
            stmt.setString(11, worker.getZip_Code());
            stmt.setInt(12, worker.getUser_id());
            stmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
     * Delete a guest from the guest table in the db
     * @param guestId Integer that is in reference to the guestID
     * that is to be deleted from the guest table from in the db
     */
    public static void deleteWorker(String email) { // deletes all cards associated with guest as well
        try (Statement stmt = connection.createStatement()) {
            connection.setAutoCommit(false);
            stmt.execute("DELETE FROM workers WHERE email = '"+email+"'");
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
     * Gets a List of all Guests from the guest table in the db
     * @return guests A list of all guests
     */
    public static List<User> getWorkers() {
        List<User> workers = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM workers")) {
            while (rs.next()) {
                workers.add(new User(rs.getString("first_name"),
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
                        rs.getInt("worker_id")
                ));
            }
            return workers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /*
     * Gets one guest from the guest table in the db
     * @param guestId Integer that is in reference to the guestID
     * that is to be used to grab from the db
     * @return guest User object
     */
    public static User getWorker(String email) {
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM workers WHERE email = '"+email+"'")) {
            while(rs.next()) {
                User worker = new User(rs.getString("first_name"),
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
                        rs.getInt("worker_id"));
                return worker;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // End of Worker ////////////////////////////////////
}
