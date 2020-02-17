-- Edbel Basaldua
-- April 23 
-- Searches for driver with specif  attributes brown eye and black hair
--query1.sql
SELECT d.LicenseID, d.Name, d.BirthDate
FROM Drivers d
WHERE d.eyecolor='BRN' AND d.haircolor= 'BLK'; 
