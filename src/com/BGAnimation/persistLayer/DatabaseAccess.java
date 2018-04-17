package com.BGAnimation.persistLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Sahil Patel
 *
 */
public class DatabaseAccess
{

    static final String DRIVE_NAME = "com.mysql.jdbc.Driver";
    static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/bganimation";
    static final String DB_CONNECTION_USERNAME = "root";
    static final String DB_CONNECTION_PASSWORD = "root";

    public static Connection connect()
    {
        Connection con = null;
        try
        {
            Class.forName(DRIVE_NAME);
            con = DriverManager.getConnection(CONNECTION_URL, DB_CONNECTION_USERNAME, DB_CONNECTION_PASSWORD);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return con;
    }

    public static ResultSet retrieve(Connection con, String query)
    {
        ResultSet rset = null;
        try
        {
            Statement stmt = con.createStatement();
            rset = stmt.executeQuery(query);
            return rset;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return rset;
    }

    public static void closeConnection(Connection con)
    {
        try
        {
            if (con != null)
            {
                con.close();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

}