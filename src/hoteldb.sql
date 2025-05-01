-- run each statement manually by highlighting them with your cursor and pressing execute statement

-- Drop tables in order of execution
DROP TABLE IF EXISTS reservation; -- first
DROP TABLE IF EXISTS room; -- second
DROP TABLE IF EXISTS card; -- third
DROP TABLE IF EXISTS guests; -- forth


-- creates guest table
create TABLE guests ( -- first
    guest_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    address1 VARCHAR(100) NOT NULL,
    address2 VARCHAR(100),
    city VARCHAR(20) NOT NULL,
    country VARCHAR(20) NOT NULL,
    state VARCHAR(2) NOT NULL,
    zip_Code VARCHAR(5) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- creates card table
create TABLE card ( -- second
    card_id INT AUTO_INCREMENT PRIMARY KEY,
    guest_id INT,
    FOREIGN KEY (guest_id) REFERENCES guests(guest_id) ON DELETE CASCADE,
    holder_Name VARCHAR(50) NOT NULL,
    card_Number VARCHAR(16) UNIQUE NOT NULL,
    expiration VARCHAR(5) NOT NULL,
    cvc VARCHAR(3) NOT NULL,
    address1 VARCHAR(100) NOT NULL,
    address2 VARCHAR(100),
    city VARCHAR(20) NOT NULL,
    country VARCHAR(20) NOT NULL,
    state VARCHAR(2) NOT NULL,
    zip_Code VARCHAR(5) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- creates room table
create TABLE room ( -- third
    room_Number INT AUTO_INCREMENT PRIMARY KEY,
    room_Type VARCHAR(10) NOT NULL,
    price_Per_Night DECIMAL(10,2) NOT NULL,
    room_Capacity INT NOT NULL,
    availability VARCHAR(10) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- creates reservation table
create TABLE reservation ( -- forth
    reserve_id INT AUTO_INCREMENT PRIMARY KEY,
    room_Number INT NOT NULL,
    guest_id INT NOT NULL,
    card_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    check_In_Date DATE NOT NULL,
    check_Out_Date DATE  NOT NULL,
    check_In_Time TIME,
    check_Out_Time TIME,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (room_Number) REFERENCES room(room_Number) ON DELETE CASCADE,
    FOREIGN KEY (guest_id) REFERENCES guests(guest_id) ON DELETE CASCADE,
    FOREIGN KEY (card_id) REFERENCES card(card_id) ON DELETE CASCADE
);
