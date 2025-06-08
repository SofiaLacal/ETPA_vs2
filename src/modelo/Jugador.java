package modelo;

import java.sql.SQLException;
import java.util.Scanner;

import dao.DaoJugador;
import seguridad.ContraSegura;

public class Jugador {

    //TODO Atributos

    private String nombreJugador;
    private String contraseña;
    private int puntos;

    //TODO Constructores

    public Jugador(String nombre, String contraseña, int puntos) {
        this.nombreJugador = nombre;
        this.contraseña = contraseña;
        this.puntos = puntos;
    }

    
    //Getters & Setters

    public String getNombreJugador() {
        return nombreJugador;
    }

    public String getContraseña() {
        return contraseña;
    }

    public int getPuntos() {
        return puntos;
    }

    //TODO Otros métodos    
    
    //1. Método para crear jugador nuevo
    public static Jugador crearJugador() throws SQLException {
        
    	Scanner sc = new Scanner(System.in);
		
        System.out.print("Dime tu nombre: ");
        String nombre = sc.nextLine().toUpperCase();

        DaoJugador daoJugador = new DaoJugador();

        if (daoJugador.existe(nombre)) {
            // LOGIN
            String contraseñaReal = daoJugador.obtenerContraseña(nombre);
            int puntos = daoJugador.obtenerPuntos(nombre);

            Jugador jugadorExistente = new Jugador(nombre, contraseñaReal, puntos);
            jugadorExistente.verificarContraseña();

            return jugadorExistente;

        } else {
            // REGISTRO
            String pass, confirmar;

            do {
                System.out.print("Crea una contraseña: ");
                pass = sc.nextLine();
                System.out.print("Confirma la contraseña: ");
                confirmar = sc.nextLine();

                if (!pass.equals(confirmar)) {
                    System.out.println("Las contraseñas no coinciden");
                }

            } while (!pass.equals(confirmar));

        
           //Cifrar contraseña antes de guardar
            String passCifrada = ContraSegura.cifrarSHA256(pass);
            
            Jugador nuevoJugador = new Jugador(nombre, passCifrada, 0);
            daoJugador.insert(nuevoJugador);
            
            System.out.println("Usuario registrado con éxito");
            
            return nuevoJugador;
        }
    }
    

    //2. Método para verificar la contraseña
    public void verificarContraseña() {

        Scanner sc = new Scanner(System.in);
        String intento;
        
        do {
            System.out.print("Introduce tu contraseña: ");
            intento = sc.nextLine();  
            
            //Convertir intento a SHA-256
            String intentoCifrado = ContraSegura.cifrarSHA256(intento);

            if (!this.contraseña.equals(intentoCifrado)) {
                System.out.println("Las contraseñas no coinciden.  Inténtalo de nuevo.");
            }

        } while (!this.contraseña.equals(ContraSegura.cifrarSHA256(intento)));

            System.out.println("Contraseña correcta. ¡Bienvenid@ al juego, " + this.nombreJugador + "!");
        }
            
    
    //3. toString + imprimirlo
    public String toString () {

        return "-------------------------------" + "\nNombre del jugador: " + getNombreJugador() + 
        		"\nPuntos: " + getPuntos() + "\n-------------------------------";
    }

    public void infoJugador () {

        System.out.println(toString());
    }
 

    //4. Método para sumar los puntos del usuario
    public void sumarPuntos(int puntos) {
        this.puntos += puntos;
    }

}






