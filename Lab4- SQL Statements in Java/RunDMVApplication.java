import java.sql.*;
import java.io.*;
import java.util.*;

/**
 * A class that connects to PostgreSQL and disconnects.
 * The name of your database in the server is the same as your username.
 * Executes three query functions which should return the appropiate info
*/


public class RunDMVApplication
{
	
	// Reuquires  user and password for Postgres account
	public static String USERNAME = "username_here";
	public static String PASSWORD = "password_here";
	 
    public static void main(String[] args) {
    	
    	Connection connection = null;
    	try {
    		//Register the driver
    		Class.forName("org.postgresql.Driver"); 
    		// Make the connection to postgres server
            connection = DriverManager.getConnection(
					 "jdbc:postgresql://cmps182-db.lt.ucsc.edu/" + RunDMVApplication.USERNAME + "?currentSchema=lab4",
					 RunDMVApplication.USERNAME,
					 RunDMVApplication.PASSWORD);
            
            if (connection != null)
                System.out.println("Connected to the database!");


			// construct the application with the connection to the database
			DMVApplication application = new DMVApplication(connection);

			// get drivers with many vehicles and print output
			System.out.println("getDriversWithManyVehicles(3): " + application.getDriversWithManyVehicles(3));
			/*
			* Output of getDriversWithManyVehicles when numberOfVehicles is 3:
			* getDriversWithManyVehicles(3): [10000]
			*/

			// call set ticketed driver (the print statement is not required)
			System.out.println("setTicketedDriver(\"Chao Xu\", \"1007 Broadway Ave\", 3000011)");
			application.setTicketedDriver("Chao Xu", "1007 Broadway Ave", 3000011);

			// calling and printing output for two calls to getSomeTicketFees
			System.out.println("getSomeTicketFees(2300): " + application.getSomeTicketFees(2300));
			System.out.println("getSomeTicketFees(-8): " + application.getSomeTicketFees(-8));

    	} catch (SQLException | ClassNotFoundException e) {
    		System.out.println("Error while connecting to database: " + e);
    		e.printStackTrace();
    	} finally {
    		if (connection != null) {
    			// Closes Connection
    			try {
					connection.close();
				} catch (SQLException e) {
					System.out.println("Failed to close connection: " + e);
					e.printStackTrace();
				}
    		}
    	}
    }
}
