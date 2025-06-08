package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Jugador;

public class DaoJugador {

	//TODO Atributos
	
	private Connection conn = null;

	
	//TODO Constructores
	
	public DaoJugador () throws SQLException {
		
		conn = DBManager.getConnection();
	}
					
	
	//TODO Otros métodos
	
	//1. Método para insertar jugadores
    public void insert (Jugador jugadorIn) throws SQLException {
		
		//Consulta de SQL para inserción de datos
		String insertSql = "INSERT INTO JUGADOR (nombreJugador, contrasenia, puntos) VALUES (?,?,?)";
					
		//Relación con query dinámica
		PreparedStatement statement = conn.prepareStatement(insertSql);

		//Datos para la consulta
		statement.setString(1, jugadorIn.getNombreJugador());
		statement.setString(2, jugadorIn.getContraseña());
		statement.setInt(3, jugadorIn.getPuntos());
		
		//Inserción de datos					
		int recordsInserted = statement.executeUpdate();

		//Comprobación	
		if (recordsInserted > 0) {
			
		} else {
			System.out.println("Error al crear el usuario\n");
		}

		//Cerramos el INSERT
		statement.close();
    }
	
    
    //2. Método para verificar si el jugador ya existe en la BD
    public boolean existe(String nombre) throws SQLException {

    	//Consulta de SQL para comprobar los datos
        String existSQL = "SELECT COUNT(*) FROM jugador WHERE nombreJugador = ?";
        
        //Relaciono con query dinámica
        PreparedStatement stmt = conn.prepareStatement(existSQL);
        
        //Datos necesarios para hacer la consulta
        stmt.setString(1, nombre);
        
        //Ejecuto la consulta
        ResultSet rs = stmt.executeQuery();
        rs.next();
        
        return rs.getInt(1) > 0;

    }
    
    
    //3. Método para obtener la contraseña si el usuario ya existe
    public String obtenerContraseña(String nombreUsuario) throws SQLException {
    	
    	//Consulta de SQL para comprobar los datos
        String contraSQL = "SELECT contrasenia FROM jugador WHERE nombreJugador = ?";
        String pass = null; 
       
        //Relaciono con query dinámica
        PreparedStatement stmt = conn.prepareStatement(contraSQL);
        stmt.setString(1, nombreUsuario);

        //Ejecuto la consulta
        ResultSet rs = stmt.executeQuery();
        
        //Compruebo
        if (rs.next()) {
            pass = rs.getString("contrasenia");    
        }
        
         return pass;  
    }

    
    //4. Método para obtener puntos
    public int obtenerPuntos(String nombreUsuario) throws SQLException {
    	
    	int puntos = 0;
    	
    	//Consulta de SQL para comprobar los datos
        String puntoSQL = "SELECT puntos FROM jugador WHERE nombreJugador = ?";
        
        //Relaciono con query dinámica
        PreparedStatement stmt = conn.prepareStatement(puntoSQL);
        
        stmt.setString(1, nombreUsuario);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            puntos = rs.getInt("puntos");
            
        } 
            return puntos;
    }
    
    
    //5. Método para actualizar puntos
    public void actualizarPuntos(Jugador jugador) throws SQLException {
    	
    	//Consulta de SQL
        String actualizSQL = "UPDATE jugador SET puntos = ? WHERE nombreJugador = ? ";
        
        //Relaciono con query dinámica
        PreparedStatement stmt = conn.prepareStatement(actualizSQL);
        
        //Datos necesarios para hacer la consulta
        stmt.setInt(1, jugador.getPuntos());
        stmt.setString(2, jugador.getNombreJugador());
        
        //Ejecuto la consulta
        stmt.executeUpdate();

    }
    
    
    //6. Método para mostrar ranking
    public void mostrarRanking() throws SQLException {
    	
    	//Consulta de SQL
        String rankingSQL= "SELECT nombreJugador, puntos FROM jugador ORDER BY puntos DESC LIMIT 3";
        
        //Relaciono con query estática
        Statement stmt = conn.createStatement();
        
        //Ejecuto la consulta
        ResultSet rs = stmt.executeQuery(rankingSQL);

        //Compruebo
        System.out.println("\n#### RANKING DE JUGADORES ####");
        int posicion = 1;

        while (rs.next()) {
            String nombre = rs.getString("nombreJugador");
            int puntos = rs.getInt("puntos");

            System.out.println(posicion + ". " + nombre + " - " + puntos + " puntos");
            
            posicion++;
        }
    
    }   
    
}
	

