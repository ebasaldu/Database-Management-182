-- Will return the VIN where the LicenceId has Value of Null
-- Distinct used to prevent duplication
--query2.sql
SELECT DISTINCT t.VIN
FROM Tickets t
WHERE t.LicenseID IS NULL;
