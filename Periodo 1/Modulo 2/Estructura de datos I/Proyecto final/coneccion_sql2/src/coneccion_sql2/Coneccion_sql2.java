
package coneccion_sql2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.SQLException;
public class Coneccion_sql2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //informacion de la base de datos
        String url = "jdbc:mysql://localhost:3306/edison";
        String usuario = "root";
        String contraseña="";
        
        //Datos del usuario a ingresar
        String nombre = "Angie";
        String apellido = "Poloche";
        try {
            //conectar a la base de datos
            Connection conn = DriverManager.getConnection(url,usuario,contraseña);
            
            //Sentencia SQL para insertar un nuevo usuario
            String sql = "INSERT INTO nombres (nombre, apellido) VALUES('"+nombre+"','"+apellido+"')";
            
            //Preparar la sentencia SQL
            PreparedStatement statement =conn.prepareStatement(sql);
            
            //Ejecutar la sentencia SQL
            int filasAfectadas = statement.executeUpdate();
            
            if(filasAfectadas>0){
                System.out.println("Usuario insertado correctamente");
            }else{
                System.out.println("No se pudo insertar el usuario");
            }
            //cerrar la conneccion
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
}
