INSERT INTO 
Tickets(TicketID,VIN,LicenseID,TicketDate,Address,Infraction,Fee,Paid)
VALUES(200014,111111,123456,'2017-05-18','fake','fake',45,'f');

INSERT INTO 
Vehicles(VIN,Model,Year,VehicleColor,OwnerLicenseID,RegExpireDate,InsuranceCo,InUse)
VALUES(111111,'ford',2008,'RED',12345,'2017-05-19','AllState','t');

INSERT INTO Tickets(TicketID,VIN,LicenseID,TicketDate,Address,Infraction,Fee,Paid)
VALUES(200014,200000,246810,'2017-05-18','fake','fake',45,'f');

UPDATE Drivers
SET licenseClass= 'A'
WHERE licenseClass= 'B'; 

UPDATE Drivers
SET licenseClass='Z'
WHERE licenseClass='A';

UPDATE Tickets
SET Fee=7
WHERE Fee>1000;

UPDATE Tickets
SET Fee=-8
WHERE Fee=47.14;

UPDATE Vehicles
SET InUse=True
WHERE RegExpireDate IS NOT NULL;

UPDATE Vehicles
SET RegExpireDate = Null
WHERE InUse=True AND RegExpireDate IS NOT NULL;
