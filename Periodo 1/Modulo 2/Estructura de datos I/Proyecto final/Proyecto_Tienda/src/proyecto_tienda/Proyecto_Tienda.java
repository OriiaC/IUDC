//poner un retorno en el login con contraseña incorrecta
//impedir duplicados en el usuario al registrar
package proyecto_tienda;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.ResultSet;
public class Proyecto_Tienda {


    public static void main(String[] args) {
        imprimir_menu_inicial();
    }
    
public static int pedir_opcion_numerica(){
    Scanner sc = new Scanner(System.in);
    int opcion_numerica = sc.nextInt();
    return opcion_numerica; 
}

public static long pedir_opcion_telefono(){
    Scanner sc = new Scanner(System.in);
    long opcion_numerica = sc.nextLong();
    return opcion_numerica;
}

public static String pedir_opcion_alfanumerica(){
    Scanner sc = new Scanner(System.in);
    String opcion_alfanumerica = sc.nextLine();
    return opcion_alfanumerica;
}

public static void imprimir_menu_inicial(){
    System.out.println("Ingrese una de las siguientes opciones: ");
    System.out.println("1: Iniciar sesion");
    System.out.println("2: Registrarse");
    System.out.println("3: Salir del programa");
    System.out.print("opcion #: ");
    switch(pedir_opcion_numerica()){
            case 1 -> login();
            case 2 -> registro();
            case 3 -> System.out.println("Programa finalizado");
            default -> {
                System.out.println("el numero ingresado no esta en el menu");
                imprimir_menu_inicial();
            }
        }
}

public static void login(){
    System.out.println("Ingrese su usuario: ");
    String usuario = pedir_opcion_alfanumerica();
    int comp_usuario = comprueba_usuario(usuario);
    System.out.println("Ingrese su contraseña: ");
    String contrasena = pedir_opcion_alfanumerica();
        try {
        String url = "jdbc:mysql://localhost:3306/tienda1000";
        String usuario_db = "root";
        String contrasena_db="";
        Connection conn = DriverManager.getConnection(url,usuario_db,contrasena_db);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select contrasena_usuario From usuarios WHERE nombre_usuario='"+usuario+"';");
        rs.next();
        String contrasena_en_bd = rs.getString(1);
        int opcion = 1;
        if(opcion==0){
            imprimir_menu_inicial();            
        }else{
            if (comp_usuario>0){
                if (contrasena.equals(contrasena_en_bd)){
                System.out.println("Bienvenido :D");
                carrito_compras();
                }else{
                    System.out.println("Clave incorrecta, intente de nuevo.");
                    login();
                }
            }else{
                
            }
        }
        conn.close();
    }catch(SQLException e){
    }

}

public static void registro(){
    System.out.println("Usuario: ");
    String usuario = pedir_opcion_alfanumerica();
    System.out.println("Contraseña: ");
    String contrasena = pedir_opcion_alfanumerica();
    System.out.println("Telefono: ");
    long telefono = pedir_opcion_telefono();
    System.out.println("Correo: ");
    String correo = pedir_opcion_alfanumerica();
    
    try {
        String url = "jdbc:mysql://localhost:3306/tienda1000";
        String usuario_db = "root";
        String contrasena_db="";
            Connection conn = DriverManager.getConnection(url,usuario_db,contrasena_db);
            String sql = "INSERT INTO usuarios (nombre_usuario, contrasena_usuario, telefono_usuario, email_usuario) VALUES('"+usuario+"','"+contrasena+"', "+telefono+", '"+correo+"')";
            PreparedStatement statement =conn.prepareStatement(sql);
            int filasAfectadas = statement.executeUpdate();
            if(filasAfectadas>0){
                System.out.println("Usuario insertado correctamente.");
                System.out.println("Inicie sesion con sus nuevas credenciales.");
                imprimir_menu_inicial();
            }else{
                System.out.println("No fue posible registrar");
            }
            conn.close();
        }catch(SQLException e){
        }
}

public static void muestra_productos(){
    try{
        String url = "jdbc:mysql://localhost:3306/tienda1000";
        String usuario_db = "root";
        String contrasena_db="";
        Connection conn = DriverManager.getConnection(url,usuario_db,contrasena_db);
        Statement stmt = conn.createStatement();
        ResultSet menu = stmt.executeQuery("Select * From productos;");
        System.out.println("precione el numero 0 para finalizar compra");
        System.out.println("");
        while (menu.next()) {
            Integer id_producto = menu.getInt(1);
            String nombre_producto = menu.getString(2);
            String desc_producto = menu.getString(3);
            BigDecimal precio_producto = menu.getBigDecimal(4);
            System.out.println("id del producto:" + id_producto);
            System.out.println("nombre del producto:" + nombre_producto);
            System.out.println("descripsion del producto:" + desc_producto);
            System.out.println("precio del producto:" + precio_producto);
            System.out.println("");
        }
        conn.close();
    }catch(SQLException e){        
    }    
}

public static void carrito_compras(){
    int vuelta = 1;
    muestra_productos();
    int id_producto = 1;
    System.out.print("ingrese el numero del producto que desea comprar o 0 para terminar la compra y cerrar sesion: ");
    id_producto = pedir_opcion_numerica();
        do{
            if(id_producto!=0){
                if (vuelta==1){
                    int total_filas = cuenta_filas_tabla();
                    crea_fila_tabla_pedido(total_filas);
                    vuelta++;
                }
                try{
                    String url = "jdbc:mysql://localhost:3306/tienda1000";
                    String usuario_db = "root";
                    String contrasena_db="";
                    Connection conn = DriverManager.getConnection(url,usuario_db,contrasena_db);
                    Statement stmt = conn.createStatement();
                    ResultSet menu = stmt.executeQuery("Select precio_producto, nombre_producto From productos where id_producto="+id_producto+";");
                    menu.next();
                    BigDecimal precio_producto_unidad = menu.getBigDecimal(1);
                    double precio_producto_double = precio_producto_unidad.doubleValue();
                    String nombre_producto = menu.getString(2);
                    System.out.print("cuantos "+nombre_producto+" desea comprar?: ");
                    int id_pedido = cuenta_filas_tabla();
                    int cantidad_producto_entero = pedir_opcion_numerica();
                    Double cantidad_producto_double=Double.valueOf(cantidad_producto_entero);
                    double total_precio = multiplica(precio_producto_double,cantidad_producto_double);
                    System.out.println("Precio unidad:"+precio_producto_unidad+" - Total:"+total_precio);
                    registra_tabla_productos_pedido(id_producto, id_pedido, nombre_producto, cantidad_producto_entero, precio_producto_double, total_precio);
                    System.out.println("");
                    System.out.print("ingrese el numero del producto que desea comprar o 0 para terminar la compra y cerrar sesion: ");
                    id_producto = pedir_opcion_numerica();
                    if(id_producto==0){
                        double total_a_pagar = suma_total_productos_pedido(id_pedido);
                        if(total_a_pagar>0){
                            System.out.println("carrito cerrado.");
                            int total_cant_productos = suma_total_cantidad_productos_pedido(id_pedido);
                            actualiza_tabla_pedido_total_cant_productos(id_pedido, total_cant_productos);
                            actualiza_tabla_pedido_total_pedido(id_pedido, total_a_pagar);
                            actualiza_tabla_pedido_estado(id_pedido);
                            System.out.println("*paga los productos*");
                            actualiza_tabla_pedido_pagado(id_pedido);
                            login();
                        }else{
                            System.out.println("carrito cerrado.");
                            login();
                        }

                    }else{
                        
                    }
                    conn.close();
                }catch(SQLException e){        
                }
            }else{
                System.out.println("carrito cerrado.");
                login();
            }
        }while(id_producto!=0);   
}

public static double multiplica(double precio, double cantidad){
    double result = precio*cantidad;
    return result;
}

public static int cuenta_filas_tabla (){
    try{
        String url = "jdbc:mysql://localhost:3306/tienda1000";
        String usuario_db = "root";
        String contrasena_db="";
        Connection conn = DriverManager.getConnection(url,usuario_db,contrasena_db);
        Statement stmt = conn.createStatement();
        ResultSet menu = stmt.executeQuery("SELECT count(*) FROM pedido;");
        menu.next();
        int cantidad_filas = menu.getInt(1);
        conn.close();
        return cantidad_filas;
    }catch(SQLException e){
        return 0;
    }
}

public static void crea_fila_tabla_pedido(int cantidad_filas){
        //informacion de la base de datos
        String url = "jdbc:mysql://localhost:3306/tienda1000";
        String usuario = "root";
        String contraseña="";
        //Datos del usuario a ingresar
        int nuevafila=cantidad_filas+1;
        try {
            //conectar a la base de datos
            Connection conn = DriverManager.getConnection(url,usuario,contraseña);
            //Sentencia SQL para insertar un nuevo usuario
            String sql = "INSERT INTO pedido (id_pedido, estado, pagado) VALUES ("+nuevafila+", 'en proceso', 'no');";
            //Preparar la sentencia SQL
            PreparedStatement statement =conn.prepareStatement(sql);
            //Ejecutar la sentencia SQL
            statement.executeUpdate();
            //cerrar la conneccion
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
}

public static int comprueba_usuario(String usuario){
        try {
        String url = "jdbc:mysql://localhost:3306/tienda1000";
        String usuario_db = "root";
        String contrasena_db="";
        Connection conn = DriverManager.getConnection(url,usuario_db,contrasena_db);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("Select id_usuario From usuarios WHERE nombre_usuario='"+usuario+"';");
        rs.next();
        int id_usuario = rs.getInt(1);
        conn.close();
        return id_usuario;
    }catch(SQLException e){
        System.out.println("Usuario incorrecto, intente de nuevo o ingrese la opcion 0 para volver al menu principal");
        login();
        return 0;
    }
}

public static void registra_tabla_productos_pedido(int id_producto, int id_pedido, String nombre_producto, int cantidad, double precio_producto, double total_precio){
            //informacion de la base de datos
        String url = "jdbc:mysql://localhost:3306/tienda1000";
        String usuario = "root";
        String contraseña="";
        //Datos del usuario a ingresar
        try {
            Connection conn = DriverManager.getConnection(url,usuario,contraseña);
            String sql = "INSERT INTO productos_pedido (id_producto, id_pedido, nombre_producto, cantidad, precio_producto, total_precio) values ("+id_producto+", "+id_pedido+", '"+nombre_producto+"', "+cantidad+", "+precio_producto+", "+total_precio+");";
            PreparedStatement statement =conn.prepareStatement(sql);
            statement.executeUpdate();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
}

public static double suma_total_productos_pedido(int id_pedido){
    try{
        String url = "jdbc:mysql://localhost:3306/tienda1000";
        String usuario_db = "root";
        String contrasena_db="";
        Connection conn = DriverManager.getConnection(url,usuario_db,contrasena_db);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select sum(total_precio) from productos_pedido where id_pedido="+id_pedido+";");
        rs.next();
        double total_pedido = rs.getDouble(1);
        System.out.println("total_pedido: " + total_pedido);
        conn.close();
        return total_pedido;
    }catch(SQLException e){
        return 1;
    }
}

public static int suma_total_cantidad_productos_pedido(int id_pedido){
    try{
        String url = "jdbc:mysql://localhost:3306/tienda1000";
        String usuario_db = "root";
        String contrasena_db="";
        Connection conn = DriverManager.getConnection(url,usuario_db,contrasena_db);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select sum(cantidad) from productos_pedido where id_pedido="+id_pedido+";");
        rs.next();
        int total_cantidad = rs.getInt(1);
        conn.close();
        return total_cantidad;
    }catch(SQLException e){
        return 1;
    }
}

public static void actualiza_tabla_pedido_total_cant_productos (int id_pedido, int valor){
        //informacion de la base de datos
        String url = "jdbc:mysql://localhost:3306/tienda1000";
        String usuario = "root";
        String contraseña="";
        //Datos del usuario a ingresar
        try {
            Connection conn = DriverManager.getConnection(url,usuario,contraseña);
            String sql = "update pedido set total_cant_productos="+valor+" where id_pedido="+id_pedido+";";
            PreparedStatement statement =conn.prepareStatement(sql);
            statement.executeUpdate();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
}

public static void actualiza_tabla_pedido_total_pedido(int id_pedido, double valor){
    //informacion de la base de datos
        String url = "jdbc:mysql://localhost:3306/tienda1000";
        String usuario = "root";
        String contraseña="";
        //Datos del usuario a ingresar
        try {
            Connection conn = DriverManager.getConnection(url,usuario,contraseña);
            String sql = "update pedido set total_pedido="+valor+" where id_pedido="+id_pedido+";";
            PreparedStatement statement =conn.prepareStatement(sql);
            statement.executeUpdate();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
}

public static void actualiza_tabla_pedido_estado(int id_pedido){
        //informacion de la base de datos
        String url = "jdbc:mysql://localhost:3306/tienda1000";
        String usuario = "root";
        String contraseña="";
        //Datos del usuario a ingresar
        try {
            Connection conn = DriverManager.getConnection(url,usuario,contraseña);
            String sql = "update pedido set estado='Entregado' where id_pedido="+id_pedido+";";
            PreparedStatement statement =conn.prepareStatement(sql);
            statement.executeUpdate();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
}

public static void actualiza_tabla_pedido_pagado(int id_pedido){
        //informacion de la base de datos
        String url = "jdbc:mysql://localhost:3306/tienda1000";
        String usuario = "root";
        String contraseña="";
        //Datos del usuario a ingresar
        try {
            Connection conn = DriverManager.getConnection(url,usuario,contraseña);
            String sql = "update pedido set pagado='si' where id_pedido="+id_pedido+";";
            PreparedStatement statement =conn.prepareStatement(sql);
            statement.executeUpdate();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
}

}