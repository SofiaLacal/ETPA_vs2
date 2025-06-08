package modelo;

import personajes.Anuket;
import personajes.Bastet;
import personajes.Cambises;

import java.sql.SQLException;
import java.util.Scanner;

import dao.DaoJugador;
import dao.DaoPersonaje;

public class Menu {
	
	//Menú para elegir iniciar partida o ver ranking
	public void menuEleccion() {
		 
		 Scanner sc = new Scanner(System.in);
	     String opcion = "";


	    	System.out.println("                     Bienvenid@ a                           \n");
			System.out.println("             山卂尺尺丨ㄖ尺丂 ㄖ千 乇卂乙丨爪卂                   \n");
			System.out.println("         Sofia Lacal, Javier Monzón, Harley Mena             \n");
			System.out.println("       ...........................................           \n");
			
	        while (!opcion.equals("3")) {
	            System.out.println("\n======= MENÚ PRINCIPAL =======");
	            System.out.println("1. Iniciar partida");
	            System.out.println("2. Ver ranking de jugadores");
	            System.out.println("3. Salir");
	            System.out.print("Elige una opción: ");
	            opcion = sc.nextLine();

	            if (opcion.equals("1")) {
	                iniciarPartida();
	                
	            } else if (opcion.equals("2")) {
	                
	            	try {
	                    DaoJugador dao = new DaoJugador();
	                    dao.mostrarRanking();
	                    
	                } catch (SQLException e) {
	                    System.out.println("Error al mostrar el ranking.");
	                }
	                
	            } else if (opcion.equals("3")) {
	                System.out.println("Gracias por jugar. ¡Hasta la próxima!");
	                System.exit(0);
	                
	            } else {
	                System.out.println("Esa no es una opción. Inténtalo de nuevo.");
	            }
	        }
	    }
    
	
	 //Opción para iniciar la partida
	
	  public void iniciarPartida(){
	
	        try {
			
	        Jugador jugador = Jugador.crearJugador();
	        
	        System.out.println("\nVamos a comenzar la aventura, debes ir eligiendo tu propio camino." 
	            	+ "\n¡¡Pero cuidado!! \nTus decisiones pueden afectar al desenlace final.\n" 
	            	+ "\nRecibes un mensaje de los Dioses avisando de un peligro inminente." 
	            	+ "\nTaharka, nigromante exiliado en los tiempos de Amunoketh II, ha regresado. Debe ser detenido a toda costa para evitar la destrucción de Egipto.");
	        
	
	        //ELEGIR PERSONAJE
	        Personaje personajeSeleccionado = null;
	        DaoPersonaje dao = new DaoPersonaje();
	        Scanner sc = new Scanner(System.in);
	
	        System.out.println("\nElige a tu personaje:\n");
	
	        do {
	
	            System.out.println("1. Cambises - Maestro del fuego");
	            System.out.println("2. Bastet - Diosa de la protección y la luz");
	            System.out.println("3. Ánuket - Guardiana del agua y el rayo");
	            System.out.print("Escoge uno de los tres personajes: ");
	
	            int eleccion = sc.nextInt();
	            String nombreElegido = "";
	            
	            switch (eleccion) {
	                case 1 : 
	                    personajeSeleccionado = new Cambises();
	                    nombreElegido = dao.cargarNombrePersonaje("Cambises");
	                    break;
	                case 2 : 
	                    personajeSeleccionado = new Bastet();
	                    nombreElegido = dao.cargarNombrePersonaje("Bastet");
	                    break;
	                case 3 : 
	                    personajeSeleccionado = new Anuket();
	                    nombreElegido = dao.cargarNombrePersonaje("Ánuket");
	                    break;
	                default : 
	                    System.out.println("\nSelección inválida. Intente nuevamente.\n");
	            }
	            
	            System.out.println("\nHas elegido al personaje: " + nombreElegido);
	            
	        } while (personajeSeleccionado == null);
	        
	
	        //INICIAMOS ROMEPCABEZAS
	        Rompecabezas rompe = new Rompecabezas();
	        rompe.iniciarJuegos(jugador);           
	        
	
	        //INICIAMOS BATALLA
	        Escenarios escena = new Escenarios();
	
	        Batalla.iniciarBatallaNormal(personajeSeleccionado, escena);
	        Batalla.iniciarBatallaFinal(personajeSeleccionado, escena);
	        
	        personajeSeleccionado.finalVida(jugador);
	        
	        System.out.println("\nEstadísticas de la partida:");
	        jugador.infoJugador();
	        
	    } catch (SQLException e) {
	        System.out.println("Error al acceder a la base de datos.");
	        e.printStackTrace();
	    }	
	}
	    
	 
	
    
}