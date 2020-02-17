CREATE VIEW MultiTicketedDrivers AS
SELECT t1.LicenseID,t1.Name,t1.Address
FROM Drivers t1, Tickets t2
WHERE t1.LicenseID=t2.LicenseID
GROUP BY t1.LicenseID
HAVING COUNT(t2.TicketID)>1;


