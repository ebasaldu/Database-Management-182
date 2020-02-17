-- Edbel Basaldua
-- ticket issued to driver name where licenseid not equal to licence id of vehcile owner
-- implements an alias for each instance of driver table
-- uses two instances of drivers table for comparison of attribute
-- query 5
SELECT t.ticketid, t.ticketdate,t.infraction, td1.name AS TicketedDriverName ,td2.name AS VehicleOwnerName
FROM tickets t,Drivers td1,Drivers td2, vehicles v
WHERE td1.licenseid=t.licenseid AND t.vin=v.vin AND v.ownerlicenseid=td2.licenseid AND 
td1.licenseid<>td2.licenseid ;
