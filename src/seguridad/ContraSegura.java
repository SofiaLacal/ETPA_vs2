package seguridad;

import java.security.MessageDigest;

public class ContraSegura {

	 public static String cifrarSHA256(String texto) {
		 
	        try {
	        	
	            MessageDigest digest = MessageDigest.getInstance("SHA-256");
	            
	            byte[] hash = digest.digest(texto.getBytes("UTF-8"));
	            
	            StringBuilder hexString = new StringBuilder();

	            for (byte b : hash) {
	                String hex = Integer.toHexString(0xff & b);
	                
	                if (hex.length() == 1) hexString.append('0');
	                hexString.append(hex);
	            }

	            return hexString.toString();

	        } catch (Exception e) {
	        	
	            throw new RuntimeException("Error al cifrar la contraseña", e);
	        }
	    }
}
