package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DaoPersonaje {

	// TODO Atributos
    private Connection conn;

    
    //TODO Constructor
    public DaoPersonaje() throws SQLException {
        conn = DBManager.getConnection();
    }

    
    //TODO Otros métodos
    public String cargarNombrePersonaje (String nombrePersonaje) throws SQLException {
    	
    	//Consulta de SQL para comprobar los datos
        String sql = "SELECT * FROM personaje WHERE nombrePersonaje = ?";
        String resultNombre = null;
        
        //Relaciono con query dinámica
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, nombrePersonaje);

        //Ejecuto la consulta
        ResultSet rs = stmt.executeQuery();

        //Compruebo
        if (rs.next()) {
            String nombre = rs.getString("nombrePersonaje");
            String ataque1 = rs.getString("ataque1");
            String ataque2 = rs.getString("ataque2");
            String ataque3 = rs.getString("ataque3");

            resultNombre = nombre + "\nQue tiene estos ataques: " + ataque1 + ", " + ataque2 + " y " + ataque3;
        }
        
         return resultNombre;               
    }
}
