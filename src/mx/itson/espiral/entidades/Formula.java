
package mx.itson.espiral.entidades;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import mx.itson.espiral.persistencia.Conexion;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
/**
 * Clase la cual tiene atributos.
 * @author Erick Garza y Emmnuel Rivas
 */

public class Formula {
    
    private int puesto;
    private String piloto;
    private String equipo;
    private String llantas;
    private String tiempo ;
     private int puntos ;

     /**
      * 
      * @return Obtiene todos los elementos de la tabla.
      */
   
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
    
    /**
     * 
     * @param puesto
     * @return Indica si se obtuvo el registro.
     */
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
    
    /**
     * 
     * @param piloto 
     * @param puntos 
     * @param llantas 
     * @param tiempo 
     * @param equipo 
     * @return Indica si se guardó o no el registro.
     */
    
    
    
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
     
     /**
      * 
      * @param puesto
      * @param piloto
      * @param equipo
      * @param llantas
      * @param tiempo
      * @param puntos
      * @return Indica si se edito o no el registro.
      */
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
    /**
     * 
     * @param puesto
     * @return Indica si se elimino o no el registro.
     */
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
            
          int i =  JOptionPane.showConfirmDialog(null, "Seguro que quieres borrarlo?");
          
          if (i ==0 ){
              JOptionPane.showMessageDialog(null, "Se borrado exitosamente");
         
          }
                        conexion.close();
                 
             }
             
             } catch(Exception ex){
            System.err.println("Ocurrió un error: " + ex.getMessage());
             }
         
    return resultado;


   
   }
   
   
   /**
    * El getPuesto obtiene los valores que se atribuyen.
    * @return Un atributo 
    */

    public int getPuesto() {
        return puesto;
    }
    /**
     * Recibe un valor por parámetro y lo asigna en la variable
     * @param puesto 
     */

    public void setPuesto(int puesto) {
        this.puesto = puesto;
    }
    /**
     * Su función es permitir el obtener el valor de una propiedad de la clase 
     * @return Un atributo el cual se le asigna
     */

    public String getPiloto() {
        return piloto;
    }
    /**
     * Su función permite brindar acceso a propiedades especificas para poder asignar un valor fuera de la clase.
     * @param piloto 
     */

    public void setPiloto(String piloto) {
        this.piloto = piloto;
    }
    /**
     * Obtiene los valores que se atribuyen
     * @return Un atributo 
     */

    public String getEquipo() {
        return equipo;
    }
    /**
     * Su función permite brindar acceso a propiedades especificas
     * @param equipo 
     */

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }
    /**
     * Su función es permitir el obtener el valor de una propiedad de la clase.
     * @return 
     */

    public String getLlantas() {
        return llantas;
    }
    /**
     * Su función permite brindar acceso a propiedades especificas para poder asignar un valor fuera de la clase.
     * @param llantas 
     */

    public void setLlantas(String llantas) {
        this.llantas = llantas;
    }
    /**
     * Obitene los valores de la tabla de variable tiempo.
     * @return 
     */

    public String getTiempo() {
        return tiempo;
        
    }
    /**
     * Su función es permitir el obtener el valor de una propiedad de la clase.
     * @param tiempo 
     */

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }
    /**
     * Para acceder al valor del atributo.
     * @return 
     */

    public int getPuntos() {
        return puntos;
    }
    /**
     * para actualizar el valor del atributo.
     * @param puntos 
     */

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}

  
  
    
    
    
    



