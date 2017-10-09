package services;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    private static String dbURL = "jdbc:mysql://localhost:3306/mensaje?user=root&password=root";
    private static String tableName = "mensaje";
    // jdbc Connection
    public static Connection conn = null;
    private static Statement stmt = null;
    
    public static Connection createConnection()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection(dbURL); 
            return conn;
        }
        catch (Exception except)
        {
            except.printStackTrace();
            return null;
        }
    }
    
}