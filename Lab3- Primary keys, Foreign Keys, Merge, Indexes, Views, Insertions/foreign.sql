ALTER TABLE Tickets
ADD Foreign Key (VIN)
REFERENCES Vehicles(VIN);

ALTER TABLE Vehicles
ADD Foreign Key (OwnerLicenseID)
REFERENCES Drivers(LicenseID);
 
ALTER TABLE Tickets
ADD Foreign Key (LicenseID)
REFERENCES Drivers(LicenseID);
