package personajes;

import java.sql.SQLException;

import modelo.Ataque;
import modelo.Personaje;

public class Cambises extends Personaje {

    public Cambises() throws SQLException {
        super("Cambises", 600, new int[3]);
        getInventario()[0] = 3;
        ataque[0] = new Ataque("Básico", 40, 20, 0);
        ataque[1] = new Ataque("Berserk", 80, 40, 0);
        ataque[2] = new Ataque("Certero", 30, 15, 0);

    }
}