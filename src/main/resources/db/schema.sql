CREATE TABLE Doctors
(
    doctor_id   INT UNSIGNED      NOT NULL AUTO_INCREMENT,
    docname        VARCHAR(50)       NOT NULL,
    region      VARCHAR(255)      NOT NULL,
    spec        VARCHAR (255)     NOT NULL,
    PRIMARY KEY (doctor_id)
);
