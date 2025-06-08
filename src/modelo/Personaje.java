package modelo;

import java.sql.SQLException;

import dao.DaoFinales;

public class Personaje {

    // TODO Atributos

    private String nombrePersonaje;
    private int vidaPersonaje;
    private int[] inventario;
    private Final[] finalesDisponibles = new Final[5];

    protected Ataque[] ataque = new Ataque[3];
    protected boolean estaDefendiendo = false;

    // TODO Constructor

    public Personaje(String nombrePersonaje, int vidaPersonaje, int[] inventario) throws SQLException {
        this.nombrePersonaje = nombrePersonaje;
        this.vidaPersonaje = vidaPersonaje;
        this.inventario = inventario;
        DaoFinales dao1 = new DaoFinales();

        finalesDisponibles[0] = new Final(dao1.obtenerFinalPorId(1));
        finalesDisponibles[1] = new Final(dao1.obtenerFinalPorId(2));
        finalesDisponibles[2] = new Final(dao1.obtenerFinalPorId(3));
        finalesDisponibles[3] = new Final(dao1.obtenerFinalPorId(4));
        finalesDisponibles[4] = new Final(dao1.obtenerFinalPorId(5));
    }
    
    public Personaje(String nombrePersonaje) {
        this.nombrePersonaje = nombrePersonaje;
    }

    
    // TODO Getters & Setters

    public String getNombrePersonaje() {
        return nombrePersonaje;
    }

    public int getVidaPersonaje() {
        return vidaPersonaje;
    }

    public int[] getInventario() {
        return inventario;
    }



    // TODO Otros Métodos

    public void mostrarNombrePersonaje() {
        System.out.println(nombrePersonaje);
    }

    public void atacar(Enemigo enemigo, int indiceAtaque, int modificadorAtaque) {

        if (indiceAtaque < 0 || indiceAtaque >= ataque.length || ataque[indiceAtaque] == null) {
            System.out.println("Ese no es un ataque válido");
            
        } else {
            Ataque ataqueElegido = ataque[indiceAtaque];
            Object[] resultado = ataqueElegido.obtenerDanioAleatorio();

            /* Un arreglo de Object puede contener cualquier tipo de dato. En Java, todos los tipos de objetos heredan de Object,
             y los primitivos (como int) se convierten automáticamente en objetos (Integer) al meterlos en un Object[].
             Usamos Object[] para devolver dos cosas a la vez desde un método (en nuestro caso: daño y tipo de daño). */

            int danio = (int) resultado[0];
            String tipo = (String) resultado[1];

            int danioFinal = danio + modificadorAtaque;
            if (danioFinal < 0) danioFinal = 0;

            enemigo.recibirDanio(danioFinal);
            System.out.println(nombrePersonaje + " usa " + ataqueElegido.getNombreAtaque()
                    + " (Modificador de Ataque: " + modificadorAtaque + "). Daño " + tipo + ".");
        }
    }


    public void defender() {
        estaDefendiendo = true;
        System.out.println(nombrePersonaje + " adopta una postura defensiva.");
    }
    

    /*
     public void interactuarNPC() {
     	//Para hacer más adelante
     }
     */

   public void recibirDanio(int cantidad) {

        if (estaDefendiendo) {
            cantidad /= 2;
            estaDefendiendo = false;
            System.out.println(nombrePersonaje + " bloqueó parte del daño. Solo recibe " + cantidad + " de daño.");

        } else {

        }

        int nuevaVida = vidaPersonaje - cantidad;
        vidaPersonaje = Math.max(nuevaVida, 0);

        if (vidaPersonaje <= 0) {
            System.out.println("\n" + nombrePersonaje + " ha sido derrotado.");
        }
    }

    public void curarse() {
        int cantidadCuracion = 50;
        vidaPersonaje += cantidadCuracion;
        System.out.println(
                nombrePersonaje + " se cura " + cantidadCuracion + " puntos de vida con un botiquín. Vida actual: "
                        + vidaPersonaje);
    }

    public void usarBotiquin() {
        if (inventario[0] > 0) {
            curarse();
            inventario[0]--;

        } else {
            System.out.println(nombrePersonaje + " no tiene botiquines.");
        }
    }

    public String[] nombreDeAtaque() {
        String[] nombres = new String[ataque.length];

        for (int i = 0; i < ataque.length; i++) {
            nombres[i] = ataque[i].getNombreAtaque();
        }

        return nombres;
    }

    public void activarFinal(int indice, Jugador jugador) {
        if (indice >= 0 && indice < finalesDisponibles.length && finalesDisponibles[indice] != null) {
            finalesDisponibles[indice].mostrarFinal();
        }
    }

    public void finalVida(Jugador jugador) {
        int indiceFinal;

        if (vidaPersonaje < 50) {
            indiceFinal = 2;
        } else if (vidaPersonaje < 100) {
            indiceFinal = 3;
        } else {
            indiceFinal = 4;
        }

        activarFinal(indiceFinal, jugador);
    }
}