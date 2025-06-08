package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoFinales {

	// TODO Atributos
	private Connection conn;

	
	//TODO Constructor
    public DaoFinales() throws SQLException {
        conn = DBManager.getConnection();
    }
    
    //TODO Otros métodos
    public String obtenerFinalPorId(int id) throws SQLException {
    	
    	//Consulta de SQL para comprobar los datos
        String sql = "SELECT descripcion FROM finales WHERE id = ?";
        String finalFinal = "";
        
        //Relaciono con query dinámica
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);

        //Ejecuto la consulta
        ResultSet rs = stmt.executeQuery();

        //Compruebo
        if (rs.next()) {
        	
            finalFinal = rs.getString("descripcion");
        }
        
        return finalFinal;
    }
    
 
    
    
}
