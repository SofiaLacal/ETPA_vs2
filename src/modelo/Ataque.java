package modelo;

import java.util.Random;

public class Ataque {

    // TODO Atributos
    private String nombreAtaque;
    private int danioCompleto;
    private int danioParcial;
    private int danioNulo;

    // TODO Constructor
    public Ataque(String nombreAtaque, int danioCompleto, int danioParcial, int danioNulo) {
        this.nombreAtaque = nombreAtaque;
        this.danioCompleto = danioCompleto;
        this.danioParcial = danioParcial;
        this.danioNulo = danioNulo;
    }

    //TODO Getters & Setters
    public String getNombreAtaque() {
        return nombreAtaque;
    }

    //TODO Otros métodos
    
    public Object[] obtenerDanioAleatorio() {
        Random random = new Random();
        int opcion = random.nextInt(3);

        switch (opcion) {
            case 0:
                return new Object[] {danioCompleto, "completo"};
            case 1:
                return new Object[] {danioParcial, "parcial"};
            default:
                return new Object[] {danioNulo, "nulo"};
        }
    }

    
  /*  public Object[] obtenerDanioAleatorio() {
        Random random = new Random();
        int opcion = random.nextInt(3);

        int danio;
        String tipo;

        switch (opcion) {
            case 0:
                danio = danioCompleto;
                tipo = "completo";
                break;
            case 1:
                danio = danioParcial;
                tipo = "parcial";
                break;
            default:
                danio = danioNulo;
                tipo = "nulo";
                break;
        }

        return new Object[] { danio, tipo };
    } */

}