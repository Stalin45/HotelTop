CREATE TABLE user
(
  userId SERIAL,
  name VARCHAR(30) NOT NULL,
  surname VARCHAR(30) NOT NULL,
  bonusPoints BIGINT NOT NULL DEFAULT 0,
  totalPayments BIGINT NOT NULL DEFAULT 0,
  discountPerc TINYINT(2) NOT NULL DEFAULT 0,
  modDate TIMESTAMP,
  constraint pk_userId primary key (userId)
) ENGINE InnoDB CHARACTER SET utf8;

CREATE TABLE room
(
  roomNumber SERIAL,
  peopleCount TINYINT(4),
  price INT(6),
  description VARCHAR(255),
  modDate TIMESTAMP,
  constraint pk_roomNumber primary key (roomNumber)
)
  ENGINE InnoDB CHARACTER SET utf8;

CREATE TABLE roomStatusCalendar
(
  noteId SERIAL,
  roomNumber BIGINT UNSIGNED NOT NULL,
  calendarDate DATE NOT NULL,
  status VARCHAR(255) NOT NULL DEFAULT "booked",
  modDate TIMESTAMP,
  constraint pk_noteId primary key (noteId),
  constraint fk_roomStatus_room foreign key (roomNumber) references room (roomNumber)
  on delete cascade
  on update cascade,
  constraint uc_roomNumber_calendarDate UNIQUE (roomNumber, calendarDate)
)
  ENGINE InnoDB CHARACTER SET utf8;

CREATE TABLE booking
(
  orderId SERIAL,
  userId BIGINT UNSIGNED,
  roomNumber BIGINT UNSIGNED,
  days SMALLINT(3) NOT NULL,
  totalPrice BIGINT NOT NULL,
  bonusPoints BIGINT,
  orderDate DATE NOT NULL,
  status VARCHAR(255),
  modDate TIMESTAMP,
  constraint pk_orderId primary key (orderId),
  constraint fk_booking_user foreign key (userId) references user (userId)
    on delete cascade
    on update cascade,
  constraint fk_order_room foreign key (roomNumber) references room (roomNumber)
    on delete set null
    on update cascade
) ENGINE InnoDB CHARACTER SET utf8;
