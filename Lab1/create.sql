/*
 Schema isolation 
*/

DROP SCHEMA Lab1 CASCADE;
CREATE SCHEMA Lab1;
/*
Tables for Drivers , Vehicles and Tickets

*/
CREATE TABLE Drivers(
LicenseID int,
Name varchar(30),
Address varchar(40),
BirthDate date,
EyeColor char(3),
HairColor char(3),
LicenseExpireDate date,
LicenseClass char(1),
PRIMARY KEY (LicenseID)
);

CREATE TABLE Vehicles(
VIN int,
Model varchar(30),   
Year int,
VehicleColor char(3),
OwnerLicenseID int,
RegExpireDate date,
InsuranceCo varchar(30),
InUse boolean,
PRIMARY KEY (VIN)
);

CREATE TABLE Tickets(
TicketID int,
VIN int,
LicenseID int,
TicketDate date,
Address varchar(40),
Infraction varchar(30),
Fee decimal(6,2),
Paid boolean,
PRIMARY KEY (TicketID)  
);  
