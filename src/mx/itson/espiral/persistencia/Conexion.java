
package mx.itson.espiral.persistencia;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {


public static Connection obtener() {
     Connection conexion = null;
        try {

            conexion = DriverManager.getConnection("jdbc:mysql://localhost/stroke?user=root&password=admin");
        } catch (Exception ex){
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }
        return conexion;
    }
    
}
    


