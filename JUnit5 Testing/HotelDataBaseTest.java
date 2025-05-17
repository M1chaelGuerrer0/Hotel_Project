import Application.Card;
import Application.HotelDataBase;
import Application.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static Application.HotelDataBase.connection;
import static org.junit.jupiter.api.Assertions.*;

/* TO RUN:
 * need to be a maven project
 * update the pom.xml file // should be automatic when pressing on the red text (errors)
 * make sure it is maven 5.0.0 or higher // reject if told any version below
 * Files needed: HotelDataBase.java User.java Card.java hoteldb.sql
 */

class HotelDataBaseTest {

    private User testGuest;
    private Card testCard;
    private HotelDataBase db= new HotelDataBase();

    private User createGuest() {
        User guest = new User();
        guest.setFirst_Name("John");
        guest.setLast_Name("Doe");
        guest.setEmail("john.doe@example.com");
        guest.setPassword("123");
        guest.setPhone_Number("1234567890");
        guest.setAddress1("123 Main St");
        guest.setCity("Los Angeles");
        guest.setState("CA");
        guest.setZip_Code("90001");
        guest.setCountry("US");
        return guest;
    }

    private Card createCard() {
        Card card = new Card();
        card.setHolder_Name("John Doe");
        card.setCard_Number("1234567890");
        card.setExpiration("1030");
        card.setCvc("123");
        card.setAddress1("123 Main St");
        card.setCity("Los Angeles");
        card.setCountry("US");
        card.setState("CA");
        card.setZip_Code("90001");
        card.setGuest_id(1);
        return card;
    }

    @Nested
    class guestTest {
        @BeforeEach
        void setUp() {
            testGuest = createGuest(); // reset
        }

        @AfterEach
        void cleanup() throws SQLException {
            // Clear test data after each test
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("SET FOREIGN_KEY_CHECKS = 0");
                stmt.execute("TRUNCATE TABLE guests");
                stmt.execute("SET FOREIGN_KEY_CHECKS = 1");
            }
        }



        @Test
        void addGuest_ShouldAddGuestToDatabase() {
            db.addGuest(testGuest); // adds guest to db
            List<User> guests = db.getGuests(); // gets all guests
            assertEquals(1, guests.size(), "Database should contain 1 guest");
            User savedGuest = guests.get(0); // index 1 of the list
            assertAll("Verify all guest fields",
                    () -> assertEquals("John", savedGuest.getFirst_Name()),
                    () -> assertEquals("Doe", savedGuest.getLast_Name()),
                    () -> assertEquals("john.doe@example.com", savedGuest.getEmail()),
                    () -> assertEquals("123 Main St", savedGuest.getAddress1()),
                    () -> assertEquals("Los Angeles", savedGuest.getCity()),
                    () -> assertEquals("CA", savedGuest.getState()),
                    () -> assertEquals("90001", savedGuest.getZip_Code()),
                    () -> assertEquals("US", savedGuest.getCountry()),
                    () -> assertNotNull(savedGuest.getUser_id(), "User ID should be auto-generated")
            );
        }

        @Test
        void addGuest_ShouldRejectNullEmail() {
            User noEmail = testGuest;
            noEmail.setEmail(null); // email set to null
            db.addGuest(noEmail); // Add the guest
            assertEquals(0, db.getGuests().size());
        }

        @Test
        void addGuest_ShouldRejectNullAddressInfo() {
            User noAddressInfo = testGuest;
            noAddressInfo.setAddress1(null); // set to null
            noAddressInfo.setCity(null);
            noAddressInfo.setState(null);
            noAddressInfo.setCountry(null);
            noAddressInfo.setZip_Code(null);
            db.addGuest(noAddressInfo); // Add the guest
            assertEquals(0, db.getGuests().size());
        }

        @Test
        void addGuest_ShouldRejectDuplicateEmail() {
            db.addGuest(testGuest); // Add 1st guest
            User duplicateGuest = createGuest(); // A dupe of guest
            db.addGuest(testGuest); // Add the dupe
            assertEquals(1, db.getGuests().size());
        }

        @Test
        void addGuest_ShouldRejectNullPassword() {
            User noPassword = testGuest;
            noPassword.setPassword(null); // password set to null
            db.addGuest(noPassword); // Add the guest
            assertEquals(0, db.getGuests().size());
        }

        @Test
        void updateGuest_ShouldUpdate() {
            db.addGuest(testGuest);
            User updatedGuest = testGuest;
            updatedGuest.setEmail("doe.john@example.com");
            db.updateGuest(updatedGuest);
            List<User> guests = db.getGuests(); // gets all guests
            User savedGuest = guests.get(0); // index 1 of the list
            assertAll("Verify all guest fields",
                    () -> assertEquals("John", savedGuest.getFirst_Name()),
                    () -> assertEquals("Doe", savedGuest.getLast_Name()),
                    () -> assertEquals("doe.john@example.com", savedGuest.getEmail()),
                    () -> assertEquals("123 Main St", savedGuest.getAddress1()),
                    () -> assertEquals("Los Angeles", savedGuest.getCity()),
                    () -> assertEquals("CA", savedGuest.getState()),
                    () -> assertEquals("90001", savedGuest.getZip_Code()),
                    () -> assertEquals("US", savedGuest.getCountry()),
                    () -> assertEquals(1, savedGuest.getUser_id())
            );
        }

        @Test
        void updateGuest_ShouldFailWithoutUser_id() {
            db.addGuest(testGuest);
            User updatedGuest = testGuest;
            updatedGuest.setEmail("doe.john@example.com");
            updatedGuest.setUser_id(0);
            db.updateGuest(updatedGuest);
            List<User> guests = db.getGuests(); // gets all guests
            User savedGuest = guests.get(0); // index 1 of the list
            assertAll("Verify all guest fields",
                    () -> assertEquals("John", savedGuest.getFirst_Name()),
                    () -> assertEquals("Doe", savedGuest.getLast_Name()),
                    () -> assertEquals("john.doe@example.com", savedGuest.getEmail()),
                    () -> assertEquals("123 Main St", savedGuest.getAddress1()),
                    () -> assertEquals("Los Angeles", savedGuest.getCity()),
                    () -> assertEquals("CA", savedGuest.getState()),
                    () -> assertEquals("90001", savedGuest.getZip_Code()),
                    () -> assertEquals("US", savedGuest.getCountry()),
                    () -> assertEquals(1, savedGuest.getUser_id())
            );
        }
    }

    @Nested
    class cardTest {
        @BeforeEach
        void setUp() {
            testGuest = createGuest();
            db.addGuest(testGuest); // adds guest to db
            testCard = createCard();
        }

        @AfterEach
        void cleanup() throws SQLException {
            // Clear test data after each test
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("SET FOREIGN_KEY_CHECKS = 0");
                stmt.execute("TRUNCATE TABLE guests");
                stmt.execute("TRUNCATE TABLE card");
                stmt.execute("SET FOREIGN_KEY_CHECKS = 1");
            }
        }

        @Test
        void addCard_ShouldAddCard() {
            db.addCard(testCard); // adds card to db
            List<Card> cards = db.getCards(); // gets all cards
            assertEquals(1, cards.size(), "Database should contain 1 card");
            Card savedCard = cards.get(0); // index 1 of the list
            assertAll("Verify all card fields",
                    () -> assertEquals("John Doe", savedCard.getHolder_Name()),
                    () -> assertEquals("1234567890", savedCard.getCard_Number()),
                    () -> assertEquals("1030", savedCard.getExpiration()),
                    () -> assertEquals("123", savedCard.getCvc()),
                    () -> assertEquals("123 Main St", savedCard.getAddress1()),
                    () -> assertEquals("Los Angeles", savedCard.getCity()),
                    () -> assertEquals("CA", savedCard.getState()),
                    () -> assertEquals("90001", savedCard.getZip_Code()),
                    () -> assertEquals("US", savedCard.getCountry()),
                    () -> assertNotNull(savedCard.getCard_id(), "User ID should be auto-generated")
            );
        }

        @Test
        void addCard_ShouldRejectNullFields() {
            Card card = new Card(); // empty
            db.addCard(card); // adding empty card
            List<Card> cards = db.getCards(); // gets all cards
            assertEquals(0, cards.size(), "Database should contain 0 card");
        }

        @Test
        void addCard_ShouldFailWithoutGuest_id() {
            Card nullGuest_id = testCard;
            nullGuest_id.setGuest_id(0); // 0 is default
            db.addCard(nullGuest_id);
            List<Card> cards = db.getCards(); // gets all cards
            assertEquals(0, cards.size(), "Database should contain 0 card");
        }

        @Test
        void updateCard_ShouldUpdateCard() {
            db.addCard(testCard); // original card
            List<Card> cards = db.getCards(); // list of all cards
            Card updatedCard = cards.get(0); // gets card_id for search
            updatedCard.setHolder_Name("Jane Doe");
            db.updateCard(updatedCard); // updated card
            List<Card> newCards = db.getCards(); // gets all cards
            Card savedCard = newCards.get(0); // index 1 of the list
            assertAll("Verify all card fields",
                    () -> assertEquals("Jane Doe", savedCard.getHolder_Name()),
                    () -> assertEquals("1234567890", savedCard.getCard_Number()),
                    () -> assertEquals("1030", savedCard.getExpiration()),
                    () -> assertEquals("123", savedCard.getCvc()),
                    () -> assertEquals("123 Main St", savedCard.getAddress1()),
                    () -> assertEquals("Los Angeles", savedCard.getCity()),
                    () -> assertEquals("CA", savedCard.getState()),
                    () -> assertEquals("90001", savedCard.getZip_Code()),
                    () -> assertEquals("US", savedCard.getCountry()),
                    () -> assertNotNull(savedCard.getCard_id(), "User ID should be auto-generated")
            );
        }

        @Test
        void updateCard_ShouldFailWithoutCard_id() {
            db.addCard(testCard); // original card
            Card updatedCard = testCard;
            updatedCard.setHolder_Name("Jane Doe");
            updatedCard.setCard_id(0); // default is 0
            db.updateCard(updatedCard); // updated card
            List<Card> newCards = db.getCards(); // gets all cards
            Card savedCard = newCards.get(0); // index 1 of the list
            assertAll("Verify all card fields",
                    () -> assertEquals("John Doe", savedCard.getHolder_Name()),
                    () -> assertEquals("1234567890", savedCard.getCard_Number()),
                    () -> assertEquals("1030", savedCard.getExpiration()),
                    () -> assertEquals("123", savedCard.getCvc()),
                    () -> assertEquals("123 Main St", savedCard.getAddress1()),
                    () -> assertEquals("Los Angeles", savedCard.getCity()),
                    () -> assertEquals("CA", savedCard.getState()),
                    () -> assertEquals("90001", savedCard.getZip_Code()),
                    () -> assertEquals("US", savedCard.getCountry()),
                    () -> assertNotNull(savedCard.getCard_id(), "User ID should be auto-generated")
            );
        }
    }
}