CREATE TABLE airport
(
    id      INT AUTO_INCREMENT,
    country VARCHAR(50) NULL,
    city    VARCHAR(50) NULL,
    name    VARCHAR(50) NULL,
    CONSTRAINT airport_id_uindex
        UNIQUE (id)
);

ALTER TABLE airport
    ADD PRIMARY KEY (id);

CREATE TABLE plane
(
    id           INT AUTO_INCREMENT,
    type         VARCHAR(20) NULL,
    status       INT         NULL,
    first_flight DATE        NULL,
    CONSTRAINT plane_id_uindex
        UNIQUE (id)
);

ALTER TABLE plane
    ADD PRIMARY KEY (id);

CREATE TABLE brigade
(
    id       INT AUTO_INCREMENT,
    plane_id INT         NULL,
    type     VARCHAR(20) NULL,
    CONSTRAINT brigade_id_uindex
        UNIQUE (id),
    CONSTRAINT brigade_plane_id_fk
        FOREIGN KEY (plane_id) REFERENCES plane (id)
);

ALTER TABLE brigade
    ADD PRIMARY KEY (id);

CREATE TABLE plane_log
(
    id         INT AUTO_INCREMENT,
    plane_id   INT         NULL,
    airport_id INT         NULL,
    state      VARCHAR(50) NULL,
    time       DATETIME    NULL,
    CONSTRAINT plane_log_id_uindex
        UNIQUE (id),
    CONSTRAINT plane_log_airport_id_fk
        FOREIGN KEY (airport_id) REFERENCES airport (id),
    CONSTRAINT plane_log_plane_id_fk
        FOREIGN KEY (plane_id) REFERENCES plane (id)
);

ALTER TABLE plane_log
    ADD PRIMARY KEY (id);

ALTER TABLE plane
    ADD CONSTRAINT plane_plane_log_id_fk
        FOREIGN KEY (status) REFERENCES plane_log (id);

CREATE TABLE technical_inspection
(
    id              INT AUTO_INCREMENT,
    plane_id        INT         NULL,
    verdict         VARCHAR(20) NULL,
    inspection_date DATE        NULL,
    CONSTRAINT technical_inspection_id_uindex
        UNIQUE (id),
    CONSTRAINT technical_inspection_plane_id_fk
        FOREIGN KEY (plane_id) REFERENCES plane (id)
);

ALTER TABLE technical_inspection
    ADD PRIMARY KEY (id);

CREATE TABLE user
(
    id       INT AUTO_INCREMENT,
    username VARCHAR(30)  NULL,
    password VARCHAR(50)  NULL,
    roles    VARCHAR(100) NULL,
    CONSTRAINT user_id_uindex
        UNIQUE (id)
);

ALTER TABLE user
    ADD PRIMARY KEY (id);

CREATE TABLE passenger
(
    id            INT AUTO_INCREMENT,
    name          VARCHAR(50) NULL,
    surname       VARCHAR(50) NULL,
    date_of_birth DATE        NULL,
    gender        CHAR        NULL,
    user_id       INT         NULL,
    CONSTRAINT passenger_id_uindex
        UNIQUE (id),
    CONSTRAINT passenger_user_id_uindex
        UNIQUE (user_id),
    CONSTRAINT passenger_user_id_fk
        FOREIGN KEY (user_id) REFERENCES user (id)
);

ALTER TABLE passenger
    ADD PRIMARY KEY (id);

CREATE TABLE passenger_check
(
    id           INT AUTO_INCREMENT,
    passenger_id INT         NULL,
    check_name   VARCHAR(30) NULL,
    check_result TINYINT(1)  NULL,
    time         DATETIME    NULL,
    CONSTRAINT passenger_check_id_uindex
        UNIQUE (id),
    CONSTRAINT passenger_check_passenger_id_fk
        FOREIGN KEY (passenger_id) REFERENCES passenger (id)
);

ALTER TABLE passenger_check
    ADD PRIMARY KEY (id);

-- Cyclic dependencies found

CREATE TABLE employee
(
    id             INT AUTO_INCREMENT,
    brigade        INT         NULL,
    supervisor_id  INT         NULL,
    department     VARCHAR(20) NULL,
    name           VARCHAR(30) NOT NULL,
    surname        VARCHAR(30) NOT NULL,
    date_of_birth  DATE        NOT NULL,
    gender         CHAR        NOT NULL,
    kids           INT         NULL,
    sal_hour       DOUBLE      NULL,
    employed_since DATE        NULL,
    user_id        INT         NULL,
    CONSTRAINT employee_id_uindex
        UNIQUE (id),
    CONSTRAINT employee_user_id_uindex
        UNIQUE (user_id),
    CONSTRAINT employee_brigade_id_fk
        FOREIGN KEY (brigade) REFERENCES brigade (id),
    CONSTRAINT employee_user_id_fk
        FOREIGN KEY (user_id) REFERENCES user (id),
    CONSTRAINT staff_staff_id_fk
        FOREIGN KEY (supervisor_id) REFERENCES employee (id)
);

ALTER TABLE employee
    ADD PRIMARY KEY (id);

CREATE TABLE medical_inspection
(
    id              INT AUTO_INCREMENT,
    inspection_date DATE       NOT NULL,
    passed          TINYINT(1) NULL,
    pilot_id        INT        NOT NULL,
    CONSTRAINT medical_inspection_id_uindex
        UNIQUE (id),
    CONSTRAINT medical_inspection_pilot_id_fk
        FOREIGN KEY (pilot_id) REFERENCES employee (id)
);

ALTER TABLE medical_inspection
    ADD PRIMARY KEY (id);

-- Cyclic dependencies found

ALTER TABLE flight
    ADD PRIMARY KEY (id);

-- Cyclic dependencies found

-- Cyclic dependencies found

CREATE TABLE flight_log
(
    id        INT AUTO_INCREMENT,
    flight_id INT         NULL,
    state     VARCHAR(50) NULL,
    time      DATETIME    NULL,
    CONSTRAINT flight_log_id_uindex
        UNIQUE (id),
    CONSTRAINT flight_log_flight_id_fk
        FOREIGN KEY (flight_id) REFERENCES flight (id)
);

ALTER TABLE flight_log
    ADD PRIMARY KEY (id);

CREATE TABLE flight
(
    id                     INT AUTO_INCREMENT,
    plane_id               INT         NULL,
    takeoff                DATETIME    NULL,
    landing                DATETIME    NULL,
    type                   VARCHAR(50) NULL,
    departure_airport_id   INT         NULL,
    transfer_airport_id    INT         NULL,
    destination_airport_id INT         NULL,
    ticket_price           INT         NULL,
    max_passengers         INT         NULL,
    min_passengers         INT         NULL,
    status                 INT         NULL,
    CONSTRAINT flight_id_uindex
        UNIQUE (id),
    CONSTRAINT flight_airport_id_fk_dep
        FOREIGN KEY (departure_airport_id) REFERENCES airport (id),
    CONSTRAINT flight_airport_id_fk_des
        FOREIGN KEY (destination_airport_id) REFERENCES airport (id),
    CONSTRAINT flight_airport_id_fk_tra
        FOREIGN KEY (transfer_airport_id) REFERENCES airport (id),
    CONSTRAINT flight_flight_log_id_fk
        FOREIGN KEY (status) REFERENCES flight_log (id),
    CONSTRAINT flight_plane_id_fk
        FOREIGN KEY (plane_id) REFERENCES plane (id)
);

-- Cyclic dependencies found

ALTER TABLE ticket
    ADD PRIMARY KEY (id);

-- Cyclic dependencies found

-- Cyclic dependencies found

CREATE TABLE ticket_log
(
    id        INT AUTO_INCREMENT,
    ticket_id INT         NULL,
    state     VARCHAR(50) NULL,
    time      DATETIME    NULL,
    CONSTRAINT ticket_log_id_uindex
        UNIQUE (id),
    CONSTRAINT ticket_log_ticket_id_fk
        FOREIGN KEY (ticket_id) REFERENCES ticket (id)
);

ALTER TABLE ticket_log
    ADD PRIMARY KEY (id);

CREATE TABLE ticket
(
    id           INT AUTO_INCREMENT,
    passenger_id INT NULL,
    flight_id    INT NULL,
    seat         INT NULL,
    status       INT NULL,
    CONSTRAINT ticket_id_uindex
        UNIQUE (id),
    CONSTRAINT ticket_flight_id_fk
        FOREIGN KEY (flight_id) REFERENCES flight (id),
    CONSTRAINT ticket_passenger_id_fk
        FOREIGN KEY (passenger_id) REFERENCES passenger (id),
    CONSTRAINT ticket_ticket_log_id_fk
        FOREIGN KEY (status) REFERENCES ticket_log (id)
);

