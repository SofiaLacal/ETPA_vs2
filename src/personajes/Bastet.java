package personajes;

import java.sql.SQLException;

import modelo.Ataque;
import modelo.Personaje;

public class Bastet extends Personaje {

    public Bastet() throws SQLException {
        super("Bastet", 700, new int[3]);
        getInventario()[0] = 2;
        ataque[0] = new Ataque("Básico", 30, 15, 0);
        ataque[1] = new Ataque("Flor Fatal", 70, 35, 0);
        ataque[2] = new Ataque("Alma Voraz", 40, 20, 0);
    }
}
