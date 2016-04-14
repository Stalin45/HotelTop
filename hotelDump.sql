CREATE TABLE user
(
userId SERIAL,
name VARCHAR(30) NOT NULL,
surname VARCHAR(30) NOT NULL,
bonusPoints INT NOT NULL DEFAULT 0,
totalPayments INT NOT NULL DEFAULT 0,
discountPerc INT(2) NOT NULL DEFAULT 0,
modDate TIMESTAMP,
constraint pk_userId primary key (userId)
) ENGINE InnoDB CHARACTER SET utf8;

CREATE TABLE room
(
roomNumber SERIAL,
peopleCount INT(2),
price INT(6),
description TEXT,
modDate TIMESTAMP,
constraint pk_roomNumber primary key (roomNumber)
)
ENGINE InnoDB CHARACTER SET utf8;

CREATE TABLE roomStatusCalendar
(
noteId SERIAL,
roomNumber SERIAL,
calendarDate DATE NOT NULL,
status ENUM('free', 'booked', 'inactive') NOT NULL DEFAULT “booked”,
modDate TIMESTAMP,
constraint pk_noteId primary key (roomNumber)
constraint fk_roomStatus_room foreign key (roomNumber) references room (roomNumber)
on delete cascade
on update cascade,
constraint uc_roomNumber_calendarDate UNIQUE (roomNumber, calendarDate)
)
ENGINE InnoDB CHARACTER SET utf8;


CREATE TABLE booking
(
orderId SERIAL,
userId SERIAL,
roomNumber SERIAL,
days INT(3) NOT NULL,
totalPrice INT NOT NULL,
bonusPoints INT,
orderDate DATETIME NOT NULL,
status ENUM('waiting', 'successfull', 'cancelled'),
modDate TIMESTAMP,
constraint pk_orderId primary key (orderId),
constraint fk_booking_user foreign key (userId) references user (userId)
on delete cascade 
on update cascade,
constraint fk_order_room foreign key (roomNumber) references room (roomNumber)
on delete set null
on update cascade
) ENGINE InnoDB CHARACTER SET utf8;
