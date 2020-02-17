UPDATE Tickets
SET Paid=true
WHERE TicketID IN ( SELECT t2.TicketID
		   FROM TicketPayments t2 ,Tickets t1
		 WHERE t1.TicketID=t2.TicketID AND t1.fee=t2.amount);

