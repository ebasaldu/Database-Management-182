import java.sql.*;
import java.util.*;

/**
 * The class implements methods of the DMV database interface.
 *
 * All methods of the class receive a Connection object through which all
 * communication to the database should be performed. The
 * Connection object should not be closed by any method.
 * In case an error occurs in the database, then a method should print an
 * error message and call System.exit(-1);
 */
public class DMVApplication {

    private Connection connection;
    
    /*
     * Constructor
     */
    public DMVApplication(Connection connection) {
        this.connection = connection;
    }
    
    public Connection getConnection()
    {
        return connection;
    }
    
     /**
     * Return list of DriverID values for drivers who own at least numberOfVehicles vehicles.
     */
    public List<Integer> getDriversWithManyVehicles(int numberOfVehicles) {
        List<Integer> result = new ArrayList<Integer>();

        PreparedStatement query = null;
        ResultSet rs = null;
        try {

            // prepare the SQL statement
            Connection conn = getConnection();
            query = conn.prepareStatement(
                    "SELECT OwnerLicenseID " +
                    "FROM Vehicles  " +
                    "GROUP BY OwnerLicenseID " +
                    "HAVING COUNT(*) >= ?"
            );
        
            // set parameters and run
            query.setInt(1, numberOfVehicles);
            rs = query.executeQuery();

            // iterate over what's returned and add them to the results
            while (rs.next()) {
                Integer i = rs.getInt(1);
                result.add(i);
            }
        } catch (SQLException e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
            System.exit(-1);
        } finally {
            // the shorthand way to close an object
            if (query != null) try { query.close(); } catch (SQLException e) {}
            // the longhand way to close an object
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println("Exception closing ResultSet: " + e);
                    e.printStackTrace();
                }
            }
        }


        // end of your code
        return result;
    }
    
    
    /**
     * Takes as input a name and address, and a TicketID, and changes the DriverID on the ticket to the DriverId 
     * of the driver who has the specified name and address.  Since (name, address) is UNIQUE in Drivers, 
     * there can’t be multiple Drivers with that name.  
     * If there are no people with that name and address,setTicketedDriver should do nothing. 
     * If there is no ticket with the specified TicketId, setTicketedDriver should also do nothing.
     * setTicketedDriver should be performed as a single SQL statement.
     */
    public void setTicketedDriver(String name, String address, int ticketID) {

        PreparedStatement query2 = null;
        ResultSet rs = null;
        try {
            // get the prepared statement
            query2 = getConnection().prepareStatement(
                    "UPDATE Tickets " +
                    "SET LicenseID = " +
                            "(SELECT LicenseID FROM Drivers WHERE Name = ? AND Address = ?)  " +
                    "WHERE TicketId = ? AND EXISTS " +
                            "(SELECT LicenseID FROM Drivers WHERE Name = ? AND Address = ?) "
            );

            // set parameters in the statement.  note that because name and address are used twice,
            // they both need to be set twice (and at the appropriate index)
            query2.setString(1, name);
            query2.setString(2, address);
            query2.setInt(3, ticketID);
            query2.setString(4, name);
            query2.setString(5, address);
            query2.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
            System.exit(-1);
        } finally {
            // the shorthand way to close an object (both methods are fine)
            if (query2 != null) try { query2.close(); } catch (SQLException e) {}
            // the longhand way to close an object (both methods are fine)
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println("Exception closing ResultSet: " + e);
                    e.printStackTrace();
                }
            }
        }
    }
    
    
    /**
     * The getSomeTicketFees method takes as input an integer, stopTotal. getSomeTicketFees should
     * iterate through all the tickets whose Fee isn’t NULL in ascending TicketDate order, gathering their
     * TicketID.  getSomeTicketFees should total the fees on those tickets as it goes.  When
     * the total of the fees is more than stopTotal, then the method is finished; it should not 
     * look at or gather any more tickets.  If the total of the fees isn’t more than stopTotal 
     * but there are no more tickets to gather, then the method also should finish.
     * getSomeTicketFees should return all the TicketID values that it found,
     *
     * If stopTotal is not positive, then getSomeTicketFees should do nothing.  
     * Note that the Fee on a ticket can be NULL, but TicketDate can’t be NULL.
     */
    public List<Integer> getSomeTicketFees(int stopTotal) {
        List<Integer> result = new ArrayList<Integer>();
        float totalFee =  0;

        //validate input
        if (stopTotal <= 0) {
            return result;
        }

        Statement query3 = null;
        ResultSet rs = null;
        try {
            // get connection create a statement
            Connection conn = getConnection();
            query3 = conn.createStatement();

            // these commented lines below are not required, but fine if included
            //conn.setAutoCommit(false);
            //st.setFetchSize(50);

            rs = query3.executeQuery("SELECT TicketID, Fee FROM Tickets ORDER BY TicketDate ASC");
            /*
            The above SQL query could also be written to prevent NULL values from appearing in the results (and the
            "rs.wasNull" check below would not be required):
                SELECT TicketID, Fee FROM Tickets WHERE Fee IS NOT NULL ORDER BY TicketDate ASC
             */

            // iterate over the returned values
            while (rs.next())
            {
                // get the id and fee
                Integer id = rs.getInt(1);
                Float fee = rs.getFloat(2);

                // check for null (not required if NULLs are prevented in the SQL)
                if (!rs.wasNull()) {
                    // update the fee and save the TicketID
                    result.add(id);
                    totalFee += fee;
                }

                // check to see if we've exceeded totalFee, and break if so
                if (totalFee > stopTotal) break;
            }
        } catch (SQLException e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
            System.exit(-1);
        } finally {
            // the shorthand way to close an object (both methods are fine)
            if (query3 != null) try { query3.close(); } catch (SQLException e) {}
            // the longhand way to close an object (both methods are fine)
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println("Exception closing ResultSet: " + e);
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

};

