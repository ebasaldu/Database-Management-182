ALTER TABLE Drivers
ADD CONSTRAINT LicenseClassCondtional
CHECK (LicenseClass='A' OR  LicenseClass='B' OR LicenseClass='C' OR LicenseClass= 
'M');

ALTER TABLE Tickets
ADD CONSTRAINT positive_fee
CHECK(Fee>0);

ALTER TABLE Vehicles
ADD CONSTRAINT NullCheck
CHECK (NOT(InUse=TRUE) OR RegExpireDate IS NOT NULL);
