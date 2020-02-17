SELECT v.LicenseID,SUM(t.Fee) AS TicketFeeSum,MAX(t.Fee) AS TicketFeeMax 
FROM MultiTicketedDrivers v, Tickets t
WHERE v.LicenseID=t.LicenseID
GROUP BY v.LicenseID;
/* Output Before Deletions
ebasaldu=> \i queryview.sql 

ebasaldu=> \i queryview.sql 
 licenseid | ticketfeesum | ticketfeemax 
-----------+--------------+--------------
     10003 |       477.27 |       348.63
     10008 |      2061.73 |      1004.73
     10004 |       197.36 |       123.18
     10001 |        94.28 |        47.14
     10006 |       488.08 |       322.44
(5 rows)

DELETE 2
	Output After Deletions
ebasaldu=> \i queryview.sql 
 licenseid | ticketfeesum | ticketfeemax 
-----------+--------------+--------------
     10003 |       477.27 |       348.63
     10008 |      1057.00 |       623.18
     10004 |       197.36 |       123.18
     10006 |       488.08 |       322.44
(4 rows)

DELETE 0
	*/
DELETE FROM Tickets
WHERE TicketID=3000013 OR TicketID=3000016;

