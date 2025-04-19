/*
    HotelDataBase
    4/16/25
    @author Michael Guerrero  
    Hoteldb is a collection of sql commands to be run, such as the creation
    of the tables for the hoteldb schema.

    To Run: Highlight each statement and run indivdually.
*/

-- run each statement manually by highlighting them with your cursor.

-- Drop tables if they exist
DROP TABLE IF EXISTS reservation;
DROP TABLE IF EXISTS card;
DROP TABLE IF EXISTS guests;
DROP TABLE IF EXISTS room;

-- creates guest table
create TABLE guests (
    guest_id INT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    address1 VARCHAR(100) NOT NULL,
    address2 VARCHAR(100),
    city VARCHAR(20) NOT NULL,
    country VARCHAR(20) NOT NULL,
    state VARCHAR(2) NOT NULL,
    zipCode VARCHAR(5) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- creates card table
create TABLE card (
    card_id INT AUTO_INCREMENT PRIMARY KEY,
    holderName VARCHAR(50) NOT NULL,
    cardNumber VARCHAR(16) UNIQUE NOT NULL,
    expiration VARCHAR(5) NOT NULL,
    cvc VARCHAR(3) NOT NULL,
    address1 VARCHAR(100) NOT NULL,
    address2 VARCHAR(100),
    city VARCHAR(20) NOT NULL,
    country VARCHAR(20) NOT NULL,
    state VARCHAR(2) NOT NULL,
    zipCode VARCHAR(5) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- creates room table
create TABLE room (
    room_id INT AUTO_INCREMENT PRIMARY KEY,
    roomType VARCHAR(10) NOT NULL,
    pricePerNight DECIMAL(10,2) NOT NULL,
    roomCapacity INT NOT NULL,
    availability VARCHAR(10) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- creates reservation table
create TABLE reservation (
    reserve_id INT AUTO_INCREMENT PRIMARY KEY,
    room_id INT NOT NULL,
    guest_id INT NOT NULL,
    check_in_date DATETIME  NOT NULL,
    check_out_date DATETIME  NOT NULL,
    check_in_time TIME,
    check_out_time TIME,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);