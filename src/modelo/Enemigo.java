package modelo;

public class Enemigo {

    // TODO Atributos

    private String nombreEnemigo;
    private int vidaEnemigo;

    public static final Enemigo GUSANO = new Enemigo("Gusano del Desierto", 100);
    public static final Enemigo ESPECTRO = new Enemigo("Espectro de Arena", 120);
    public static final Enemigo GUARDIAN = new Enemigo("Guardián de Obsidiana", 150);
    public static final Enemigo SERPIENTE = new Enemigo("Serpiente Solar", 130);
    public static final Enemigo SOMBRA = new Enemigo("Taharka", 180);

    // TODO Contructor

    public Enemigo(String nombreEnemigo, int vidaEnemigo) {
        this.nombreEnemigo = nombreEnemigo;
        this.vidaEnemigo = vidaEnemigo;
    }


    // TODO Getters & Setters

    public String getNombreEnemigo() {
        return nombreEnemigo;
    }

    
    // TODO Otros métodos

    public void recibirDanio(int cantidad) {
        vidaEnemigo -= cantidad;
        if (vidaEnemigo < 0)
            vidaEnemigo = 0;

        System.out.println("\n" + nombreEnemigo + " recibe " + cantidad + " de daño. Vida restante: " + vidaEnemigo);
    }

    public boolean estaVivo() {
        return vidaEnemigo > 0;
    }
}