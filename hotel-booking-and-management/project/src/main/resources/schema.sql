create table users (
    id VARCHAR2(255 CHAR) PRIMARY KEY,
    email VARCHAR2(255 CHAR),
    name VARCHAR2(255 CHAR),
    phone_number VARCHAR2(40 CHAR),
    password VARCHAR2(255 CHAR)
);

create table rooms (
    room_id VARCHAR2(255 CHAR) PRIMARY KEY,
    room_type VARCHAR2(255 CHAR),
    room_price numeric(19,2),
    room_description VARCHAR2(255 CHAR)
)

create table bookings (
    id VARCHAR2(255 CHAR) PRIMARY KEY,
    check_in_date timestamp,
    check_out_date timestamp,
    booking_cost numeric(19,2),
    total_num_of_guest INTEGER,
    user_id VARCHAR2(255 CHAR)  FOREIGN KEY REFERENCES users(id),
    room_id VARCHAR2(255 CHAR)  FOREIGN KEY REFERENCES rooms(room_id)
);
