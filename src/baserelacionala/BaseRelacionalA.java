/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baserelacionala;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oracle
 */
public class BaseRelacionalA {
    
    static Connection conn;
    static Statement st;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        conexion();
        insireProduto("p1","parafusos",3);
        insireProduto("p2","cravos",4);
        insireProduto("p3","tachas",5);
    }
    
    public static void conexion(){
            String driver = "jdbc:oracle:thin:";
            String host = "localhost.localdomain"; // tambien puede ser una ip como "192.168.1.14"
            String porto = "1521";
            String sid = "orcl";
            String usuario = "hr";
            String password = "hr";
            String url = driver + usuario + "/" + password + "@" + host + ":" + porto + ":" + sid;
        try {
            //para conectar co native protocal all java driver: creamos un obxecto Connection usando o metodo getConnection da clase  DriverManager
            conn = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            Logger.getLogger(BaseRelacionalA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void insireProduto(String codigo,String descricion,int prezo){
        try {
            st=conn.createStatement();
            st.executeUpdate("insert into produtos values('"+codigo+"','"+descricion+"',"+prezo+")");
            System.out.println("Producto insertado con exito");
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error, vuelva a intentarlo");
        }
        
    }
    
    public static void listaProdutos(){
        
    }
    
    public static void actualizaPre(){
        
    }
    
    public static void borrarFila(){
        
    }
    
    public static void amosarFila(){
        
    }
}
