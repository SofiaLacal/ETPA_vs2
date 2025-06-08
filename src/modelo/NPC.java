package modelo;

public class NPC {

    //TODO Atributos
    String nombre;
    String especie;
    String[] dialogos;

    //TODO Contructor
    public NPC(String nombre, String especie, String[] dialogos) {
        this.nombre = nombre;
        this.especie = especie;
        this.dialogos = dialogos;
    }

    //TODO Otros métodos
    public void mostrarDialogos() {
        System.out.println(nombre + " te dice:");
        for (int i = 0; i < dialogos.length; i++) {
            System.out.println("- " + dialogos[i]);
        }
    }

    //TODO NPCs
    // Primer NPC
    public static NPC NPC1() {
        String[] dialogos = {
            "NPC1 dialogo 1",
            "NPC1 dialogo 2",
            "NPC1 dialogo 3"
        };
        return new NPC("Khamotep", "Momia Guardiana", dialogos);
    }

    // Segundo NPC
    public static NPC NPC2() {
        String[] dialogos = {
            "NPC2 dialogo 1",
            "NPC2 dialogo 2",
            "NPC2 dialogo 3"
        };
        return new NPC("Neferu", "Espectro del Escriba", dialogos);
    }

    // Tercer NPC
    public static NPC NPC3() {
        String[] dialogos = {
            "NPC3 dialogo 1",
            "NPC3 dialogo 2",
            "NPC3 dialogo 3"
        };
        return new NPC("Setekh", "Vendedor de las Arenas", dialogos);
    }
}
