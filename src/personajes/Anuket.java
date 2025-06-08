package personajes;

import java.sql.SQLException;

import modelo.Ataque;
import modelo.Personaje;

public class Anuket extends Personaje {

    public Anuket() throws SQLException {
        super("Ánuket", 650, new int[3]);
        getInventario()[0] = 1;
        ataque[0] = new Ataque("Básico", 30, 15, 0);
        ataque[1] = new Ataque("Titán", 130, 65, 0);
        ataque[2] = new Ataque("Llama Eterna", 20, 10, 0);
    }
}
