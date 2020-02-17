-- Edbel Basaldua 
--Finds the model and year of vehcles owned by a specific driver
-- Also accounts duplication
-- query3.sql
SELECT DISTINCT v.model,v.year
FROM Vehicles v, Drivers d
WHERE v.ownerlicenseid=d.licenseid AND name='John Smith';
