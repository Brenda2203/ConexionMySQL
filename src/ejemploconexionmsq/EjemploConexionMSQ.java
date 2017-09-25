/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemploconexionmsq;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LabingXEON
 */
public class EjemploConexionMSQ {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        String consulta=null;
        
        try {
            //1. Cargar el driver 
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("Driver cargado");
            
            //2. Establecer la conexion
            Connection conexion = null;
            
            try {
		conexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root", "");
                
                if(conexion!=null){
                    System.out.println("Conexion exitosa!");
                }else{
                    System.out.println("Revisar la url de conexion, usuario");
                }
            } catch (SQLException e) {
                    System.out.println("Connection Failed! Check output console");
            }
            
            //3. Ejecutar una operacion de seleccion (copiar mysql)
            consulta="select customers.customerNumber, customers.customerName, products.productName from customers,orderdetails,orders,products where customers.customerNumber=orders.customerNumber and orders.orderNumber=orderdetails.orderNumber and products.productCode=orderdetails.productCode";
            Statement st= conexion.createStatement();
            //si la consulta retorna resultados
            ResultSet rs = st.executeQuery(consulta);
            //si la consulta es de insert, update o delete
            //int rsi = st.executeUpdate(consulta);
            while(rs.next()){
                System.out.println(rs.getString("customerName"));
            }
            
            
	} catch (ClassNotFoundException e) {
            System.out.println("Clase no encontrada");
	} catch (InstantiationException e) {
            System.out.println("No se puede crear la instancia");
	} catch (IllegalAccessException e) {
            System.out.println("No tiene acceso al driver");
	}
        
        
    }
    
}
