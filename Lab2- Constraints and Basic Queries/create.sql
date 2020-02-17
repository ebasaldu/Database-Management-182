-- Uses Lab1 soltuion with new constraints
-- Edbel Basaldua
-- Lab 2 
-- April 23 2016
-- For management of the schema.
--

DROP SCHEMA lab2 CASCADE;
CREATE SCHEMA lab2;
SET search_path TO lab2;

--
-- The three tables now with new constraints as per lab2 description.
-- Implements use of not  null and unqiue pairing

CREATE TABLE Drivers (
    LicenseID INT NOT NULL PRIMARY KEY,
    Name VARCHAR(30) NOT NULL,
    Address VARCHAR(40),
    BirthDate DATE,
    EyeColor CHAR(3),
    HairColor CHAR(3),
    LicenseExpireDate DATE,
    LicenseClass CHAR(1),
    UNIQUE(Name, Address)
);

CREATE TABLE Vehicles (
    VIN INT NOT NULL PRIMARY KEY,
    Model VARCHAR(30) NOT NULL,
    Year INT NOT NULL,
    VehicleColor CHAR(3),
    OwnerLicenseID INT NOT NULL,
    RegExpireDate DATE,
    InsuranceCo VARCHAR(30),
    InUse BOOLEAN
);

CREATE TABLE Tickets (
    TicketID INT NOT NULL PRIMARY KEY,
    VIN INT NOT NULL,
    LicenseID INT,
    TicketDate DATE NOT NULL,
    Address VARCHAR(40),
    Infraction VARCHAR(30),
    Fee DECIMAL(6,2),
    Paid BOOLEAN
);
