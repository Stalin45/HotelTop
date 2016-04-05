CREATE TABLE user 
(
userId SERIAL,
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
status ENUM('free', 'booked', 'inactive'),
modDate TIMESTAMP,
constraint pk_roomNumber primary key (roomNumber)
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
