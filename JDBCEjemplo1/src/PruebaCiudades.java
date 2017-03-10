import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase creada como ejemplo en clase de IW para consultar una lista de ciudades de la 
 * DB clase y mostrar los resultados en consola.
 * Su repositorio git es https://github.com/IngWebUdea/clase1
 *
 *@author Andrés Castro - andres.castrop@udea.edu.co
 */
public class PruebaCiudades {

	public static void main(String[] args) {
		
		consultarCiudades(); 
	}
	
	/**
	 * Método que permite consultar la lista de ciudades de la DB clase
	 * para mostrar su nombre y codigo en consola
	 */
	public static void  consultarCiudades(){
		
		Connection cnx = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String url = "jdbc:mysql://localhost:3306/clase";
		String user = "root";
		String pwd = "root";
		String query = "SELECT * FROM Ciudades";
		
		try{
			/*
			 * Cargamos en el class loader de la aplicación el driver 
			 * de conexion a nuestra base de datos.
			 */
			Class.forName("com.mysql.jdbc.Driver");
			
			/*
			 * Se establece la conexión a la DB solicitando tres parámetros
			 * Url de conexión, usuario de la db, contraseña de usuario.
			 */
			cnx = DriverManager.getConnection(url, user, pwd);
			
			/*
			 * Se prepara la cosnsulta que se va a realizar contra la DB.
			 */
			ps = cnx.prepareStatement(query);
			
			/*
			 * Se obtiene la cosnulata en un objeto que se pueda iterar
			 */
			rs = ps.executeQuery();
			
			while(rs.next()){	//Iteramos el resultado
				System.out.println(rs.getLong("codigo") + ": " + rs.getString("Nombre"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}finally{		// Debemos cerrar todas las conexiones 
			try{
				if(rs!=null){
					rs.close();
				}
				if(ps!=null){
					ps.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			
		}
	}

}
