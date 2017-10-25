/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baserelacionala;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    static PreparedStatement pst;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        conexion();
        //Dejo aqui escrito el metodo para limpiar la tabla durante las pruebas
        /*
         try {
         st=conn.createStatement();
         st.executeUpdate("delete from produtos");
         } catch (SQLException ex) {
         Logger.getLogger(BaseRelacionalA.class.getName()).log(Level.SEVERE, null, ex);
         }
         */
        insireProdutoStatement("p1", "parafusos", 3);
        insireProdutoStatement("p2", "cravos", 4);
        insireProdutoPreparedStatement("p3", "tachas", 5);
        System.out.println("Productos agregados:");
        listaProdutosStatement();
        actualizaPrePS("p1", 6);
        amosarFilaPS("p1");
        borrarFilaPS("p1");
        listaProdutosPreparedStatement();
        amosarFilaPS("p2");
        borrarTodoPS("produtos");
        listaProdutosPreparedStatement();
    }

    public static void conexion() {
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

    public static void insireProdutoStatement(String codigo, String descricion, int prezo) {
        try {
            st = conn.createStatement();
            st.executeUpdate("insert into produtos values('" + codigo + "','" + descricion + "'," + prezo + ")");
            System.out.println("Producto insertado con exito");
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error, vuelva a intentarlo");
        }

    }

    public static void insireProdutoPreparedStatement(String codigo, String descricion, int prezo) {
        try {
            pst = conn.prepareStatement("insert into produtos(codigo,descricion,prezo) values(?,?,?)");
            pst.setString(1, codigo);
            pst.setString(2, descricion);
            pst.setInt(3, prezo);
            pst.executeUpdate();
            System.out.println("Producto insertado con exito");
        } catch (SQLException ex) {
            System.out.println("Ha ocurrido un error, vuelva a intentarlo");
        }

    }

    public static void listaProdutosStatement() {
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("select Codigo,Descricion,Prezo from produtos order by Codigo");
            if(rs.next()==false){
                System.out.println("No hay nada que mostrar");
            }
            while (rs.next()) {
                System.out.println("Codigo: " + rs.getString(1) + ", Descrición: " + rs.getString(2) + ", Prezo: " + rs.getInt(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseRelacionalA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void listaProdutosPreparedStatement() {
        try {
            pst = conn.prepareStatement("select Codigo,Descricion,Prezo from produtos order by Codigo");
            ResultSet rs = pst.executeQuery();
            if(rs.next()==false){
                System.out.println("No hay nada que mostrar");
            }
            while (rs.next()) {
                //Podemos acceder a los valores de la tabla a traves del numero de columna (empieza en 1)
                //System.out.println("Codigo: "+rs.getString(1)+", Descrición: "+rs.getString(2)+", Prezo: "+rs.getInt(3));
                //O podemos hacer lo mismo indicando el nombre de la columna
                System.out.println("Codigo: " + rs.getString("Codigo") + ", Descrición: " + rs.getString("Descricion") + ", Prezo: " + rs.getInt("Prezo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseRelacionalA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void actualizaPrePS(String codigo, int prezo) {
        try {
            pst = conn.prepareStatement("update produtos set prezo=? where codigo=?");
            pst.setInt(1, prezo);
            pst.setString(2, codigo);
            pst.executeUpdate();
            System.out.println("Registro actualizado con exito");
        } catch (SQLException ex) {
            Logger.getLogger(BaseRelacionalA.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void borrarFilaPS(String codigo) {
        try {
            pst = conn.prepareStatement("delete from produtos where codigo=?");
            pst.setString(1, codigo);
            pst.executeUpdate();
            System.out.println("la entrada " + codigo + " ha sido eliminada con exito");
        } catch (SQLException ex) {
            Logger.getLogger(BaseRelacionalA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void amosarFilaPS(String codigo) {
        try {
            pst = conn.prepareStatement("select * from produtos where codigo=?");
            pst.setString(1, codigo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("Datos de " + codigo + "-> Codigo: " + rs.getString("Codigo") + ", Descrición: " + rs.getString("Descricion") + ", Prezo: " + rs.getInt("Prezo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseRelacionalA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void borrarTodoPS(String tableName) {
        try {
            pst = conn.prepareStatement("delete from " + tableName);
            pst.executeUpdate();
            System.out.println("Los registros de la tabla " + tableName + " ha sido eliminada con exito");
        } catch (SQLException ex) {
            Logger.getLogger(BaseRelacionalA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
