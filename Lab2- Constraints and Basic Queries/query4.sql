-- query4.sql
SELECT t.ticketid,v.model,v.year, t.fee
FROM Tickets t, Vehicles v
WHERE t.VIN = v.VIN AND insuranceco='GEICO' AND fee>50;
