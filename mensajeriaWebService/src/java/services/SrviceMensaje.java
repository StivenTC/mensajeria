/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author Disneider
 */
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SrviceMensaje {
    
    public Connection conn = null;
    public Statement stmt = null; 
    services.Conexion connection = new services.Conexion();
    
    public SrviceMensaje(){
        connection.createConnection();
    }
    
    public beans.beansMensaje Buscar(String id){
        conn=Conexion.createConnection();
        String mensaje = null;
        try
        {
            stmt = (Statement) conn.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM mensaje WHERE id = '" + id+"'");
            while ( rs.next() ) {
                mensaje = rs.getString("mensaje");
            }
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
        return new beans.beansMensaje(id, mensaje);
    }
    
    public boolean guardar(String id,String msg)
    {
        conn=Conexion.createConnection();
        try
        {
            stmt = (Statement) conn.createStatement();
            stmt.execute("INSERT INTO mensaje VALUES ('"+ id +"','"+ msg +"')");
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
        return true;
    }
}
