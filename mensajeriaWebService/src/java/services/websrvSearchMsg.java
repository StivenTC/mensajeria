/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author stiventc
 */
@WebService(serviceName = "websrvSearch")
public class websrvSearchMsg {
    private Connection conn = null;
    private Statement stmt = null; 
    services.Conexion connection = new services.Conexion();
    /**
     * This is a sample web service operation
     *
     * @param txt
     * @return
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    @WebMethod(operationName = "searchMsg")
    public String searchMsg(@WebParam(name = "id") String id) {
        String mensaje = null;
        services.Conexion connection = new services.Conexion();
        connection.createConnection();
        conn = Conexion.createConnection();
        try
        {
            stmt = (Statement) conn.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM mensaje WHERE id = '" + id + "'");
            while ( rs.next() ) {
                mensaje = rs.getString("mensaje");
            }
            stmt.close();
            
            if(mensaje == null){
                mensaje = "No se encontraron datos.";
            }
            return mensaje + " -GlassFish";
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
            return "Ha ocurrido un error " + sqlExcept;
        }
    }
}
