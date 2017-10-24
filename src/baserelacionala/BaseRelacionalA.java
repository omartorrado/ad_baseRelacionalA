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
    
    Connection conn;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    public void conexion(){
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
    
    public void insireProduto(String codigo,String descricion,String prezo){
        try {
            Statement st=conn.createStatement();
            st.executeUpdate("INSERT INTO produtos values ('"+codigo+"','"+descricion+"','"+prezo+"');");
        } catch (SQLException ex) {
            Logger.getLogger(BaseRelacionalA.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void listaProdutos(){
        
    }
    
    public void actualizaPre(){
        
    }
    
    public void borrarFila(){
        
    }
    
    public void amosarFila(){
        
    }
}
