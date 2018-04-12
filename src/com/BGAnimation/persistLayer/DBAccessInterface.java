package com.BGAnimation.persistLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class DBAccessInterface {

	public static Connection connect() {
		 
		Connection con = DatabaseAccess.connect();
		return con;
	}
				
	public static ResultSet retrieve (String query) throws SQLException {
			
		Connection con = DatabaseAccess.connect();
		Statement stmt = null;
		stmt = con.createStatement();
			
		ResultSet rs = stmt.executeQuery(query);
		return rs;
	}

	public static int create (String query) throws SQLException {

	     java.sql.PreparedStatement preparedStmt = null;

	     try
	     {
	    	 Connection con = DatabaseAccess.connect();
	    	 preparedStmt = con.prepareStatement(query);
	    	 
	    	 
	    	 
	    	 preparedStmt.executeUpdate();
	     }
	     catch (SQLException e)
	     {
	    	 
	    	 System.out.println(e.getMessage());
	    	 
	     }
	     finally
	     {
	    	 if (preparedStmt != null)
	    	 {
	    		 preparedStmt.close();
	    	 }	
	    	 
	     }	
	     return 0;
		}	
		
		public static int update (String query) {
			return 0;
		}
		
		public static int delete (String query) throws SQLException {

        java.sql.PreparedStatement preparedStmt = null;

	        try
	        {
	            Connection con = DatabaseAccess.connect();
	            preparedStmt = con.prepareStatement(query);	            
	            preparedStmt.executeUpdate();
	        }
	        catch (SQLException e)
	        {	

	        	System.out.println(e.getMessage());

	        }
	        finally
	        {
	            if (preparedStmt != null)
	            {
	                preparedStmt.close();
	            }
	           
	        }
	        return 0;
		}
		
		public static void disconnect(Connection con) {
		}
	
	
	
}
