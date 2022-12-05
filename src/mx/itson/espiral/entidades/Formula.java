
package mx.itson.espiral.entidades;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import mx.itson.espiral.persistencia.Conexion;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class Formula {
    
    private int puesto;
    private String piloto;
    private String equipo;
    private String llantas;
    private String tiempo ;
     private int puntos ;

   
    public static List<Formula> obtenerTodos() {
        List<Formula> formulas = new ArrayList<>();
       
        try{
           
           Connection conexion = Conexion.obtener();
           Statement statement = conexion.createStatement();
           ResultSet resultSet = statement.executeQuery("SELECT puesto, piloto, equipo, llantas, tiempo, puntos FROM formula");
    
    while(resultSet.next()){
               Formula formula = new Formula();
               
               formula.setPuesto(resultSet.getInt(1));
               formula.setPiloto(resultSet.getString(2));
               formula.setEquipo(resultSet.getString(3));
               formula.setLlantas(resultSet.getString(4));
               formula.setTiempo(resultSet.getString(5));
               formula.setPuntos(resultSet.getInt(6));
               
               formulas.add(formula);
               
           }
        } catch (Exception ex){
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }
        return formulas;
    }
    
    
    public static Formula obtenerPorpuesto(int puesto){
        
        Formula formula = new Formula();
        try{
            Connection conexion = Conexion.obtener();
            PreparedStatement statement  = conexion.prepareStatement("SELECT puesto, piloto, equipo, llantas, tiempo, puntos FROM formula WHERE puesto = ?");
            statement.setInt(1, puesto);
            
            
            ResultSet  resultSet = statement.executeQuery();
             while(resultSet.next()){
               
               formula.setPuesto(resultSet.getInt(1));
               formula.setPiloto(resultSet.getString(2));
               formula.setEquipo(resultSet.getString(3));
               formula.setLlantas(resultSet.getString(4));
               formula.setTiempo(resultSet.getString(5));
               formula.setPuntos(resultSet.getInt(6));
          
             }
        } catch (Exception ex){
            System.err.println("Ocurrió un error: " + ex.getMessage());
        
        }
        return formula;
        
    }
    
    
     public static boolean guardar( String piloto, String equipo, String llantas, String tiempo, int puntos){
        boolean resultado = false;
        try {
            Connection conexion = Conexion.obtener();
            String consulta = "INSERT INTO formula ( piloto, equipo, llantas, tiempo, puntos) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, piloto);
            statement.setString(2, equipo);
            statement.setString(3, llantas);
            statement.setString(4, tiempo);
            statement.setInt(5, puntos);
              
            statement.execute();
            resultado = statement.getUpdateCount() == 1;
            conexion.close();
        } catch(Exception ex){
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }
        return resultado;
    }
     
     
    public static  boolean editar (int puesto, String piloto, String equipo, String llantas, String tiempo, int puntos){
        boolean resultado = false;
        try {
            Connection conexion = Conexion.obtener();
            String consulta = "UPDATE formula SET piloto = ?, equipo = ?, llantas =?, tiempo = ?, puntos = ? WHERE puesto = ? ";
             PreparedStatement statement = conexion.prepareStatement(consulta);
         
            statement.setString(1, piloto);
            statement.setString(2, equipo);
            statement.setString(3, llantas);
            statement.setString(4, tiempo);
            statement.setInt(5, puntos);
            statement.setInt(6, puesto);
            
            statement.execute();
            resultado = statement.getUpdateCount() == 1 ;
                        conexion.close();
                    
            
             } catch(Exception ex){
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }
        return resultado;
        }
    
   public static boolean eliminar (int puesto){
        boolean resultado = false;
        
         int renglon =0;
         try {
             
             
             if(renglon <0){
                 JOptionPane.showMessageDialog(null, "Cliente no seleccionado");
             }else {
                 
            Connection conexion = Conexion.obtener();
            String consulta = "DELETE FROM formula WHERE puesto = ?; ";
            PreparedStatement statement = conexion.prepareStatement(consulta);
               statement.setInt(1, puesto);
            statement.execute();
            resultado = statement.getUpdateCount() == 1 ;
            
            JOptionPane.showMessageDialog(null, "Seguro que quieres borrarlo?");
                        conexion.close();
                 
             }
             
             } catch(Exception ex){
            System.err.println("Ocurrió un error: " + ex.getMessage());
             }
         
    return resultado;


   
   }
   
   
   
   
   


    
    

   

    public int getPuesto() {
        return puesto;
    }

    public void setPuesto(int puesto) {
        this.puesto = puesto;
    }

    public String getPiloto() {
        return piloto;
    }

    public void setPiloto(String piloto) {
        this.piloto = piloto;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getLlantas() {
        return llantas;
    }

    public void setLlantas(String llantas) {
        this.llantas = llantas;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}

  
  
    
    
    
    



