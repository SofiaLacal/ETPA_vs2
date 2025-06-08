package modelo;

import java.util.Random;
import java.util.Scanner;

public class Batalla {

    private final Personaje personaje;
    private final Enemigo enemigo;
    private final boolean bossFinal;
    private final Escenarios escenario;

    private final Scanner scanner;

    public Batalla(Personaje personaje, Enemigo enemigo, boolean bossFinal, Escenarios escenario) {
        this.personaje = personaje;
        this.enemigo = enemigo;
        this.bossFinal = bossFinal;
        this.escenario = escenario;
        this.scanner = new Scanner(System.in);
    }

    // Método para iniciar el combate
    public void iniciar() {
           	
        while (personaje.getVidaPersonaje() > 0 && enemigo.estaVivo()) {
        	
            System.out.println("\n¡Comienza el combate entre " + personaje.getNombrePersonaje() + " y " + enemigo.getNombreEnemigo() + "!");
        	
            mostrarMenuJugador();
            
            if (enemigo.estaVivo()) {
                turnoEnemigo();
            }
        }

        if (personaje.getVidaPersonaje() <= 0) {
            System.out.println("");
        } else {
            System.out.println("\n¡Has vencido a " + enemigo.getNombreEnemigo() + "!");
        }
    }

    // Método para mostrar el menú de la batalla
    private void mostrarMenuJugador() {
        int accion = 0;

        while (accion < 1 || accion > 3) {
            System.out.println("\n--- Turno de " + personaje.getNombrePersonaje() + " ---");
            System.out.println("Vida actual: " + personaje.getVidaPersonaje());
            System.out.println("\n¿Qué deseas hacer?");
            System.out.println("1. Atacar");
            System.out.println("2. Defender");
            System.out.println("3. Curarse\n");
            System.out.print("Opción: ");

            accion = scanner.nextInt();
            scanner.nextLine();

            switch (accion) {
                case 1:
                    String[] nombresAtaques = personaje.nombreDeAtaque();
                    System.out.println("Elige un ataque:");

                    for (int i = 0; i < nombresAtaques.length; i++) {
                        System.out.println((i + 1) + ". " + nombresAtaques[i]);
                    }

                    System.out.print("Opción: ");
                    int indiceAtaque = scanner.nextInt() - 1;
                    scanner.nextLine();
                    int modificadorAtaque = escenario.getModificadorAtaque();
                    personaje.atacar(enemigo, indiceAtaque, modificadorAtaque);
                    break;

                case 2:
                    personaje.defender();
                    break;

                case 3:
                    personaje.usarBotiquin();
                    break;

                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
                    accion = 0;
                    break;
            }
        }
    }

    // Método para el turno del enemigo
    private void turnoEnemigo() {
        int danioEnemigo;

        if (bossFinal) {
            danioEnemigo = 40;

        } else {
            danioEnemigo = 30;
        }

        System.out.println("\n--- Turno del enemigo ---");
        int defensa = escenario.getModificadorDefensa();
        int danioFinal = danioEnemigo - defensa;

        danioFinal = Math.max(0, danioFinal); // Hace que no haya daño negativo

        System.out.println(
                enemigo.getNombreEnemigo() + " ataca y causa " + danioFinal + " de daño (Modificador de Defensa: "
                        + defensa + ").");
        personaje.recibirDanio(danioFinal);

    }

    // Método para elegir un enemigo aleatorio
    private static Enemigo elegirEnemigoAleatorio() {
        Enemigo[] enemigos = new Enemigo[] {
                new Enemigo("Gusano del Desierto", 100),
                new Enemigo("Espectro de Arena", 120),
                new Enemigo("Guardián de Obsidiana", 150),
                new Enemigo("Serpiente Solar", 130),
        };

        Random random = new Random();
        int indice = random.nextInt(enemigos.length);
        return enemigos[indice];
    }

    // Método para elegir al enemigo final
    private static Enemigo enemigoFinal() {

        Enemigo taharka = new Enemigo("Taharka", 300);

        return taharka;
    }

    // Método para iniciar batalla con enemigos
    public static void iniciarBatallaNormal(Personaje personaje, Escenarios escenario) {
        Enemigo enemigo = elegirEnemigoAleatorio();
        Batalla batalla = new Batalla(personaje, enemigo, false, escenario);
        batalla.iniciar();
    }

    // Método para iniciar batalla con enemigo final
    public static void iniciarBatallaFinal(Personaje personaje, Escenarios escenario) {
        Enemigo jefeFinal = enemigoFinal();
        Batalla batallaFinal = new Batalla(personaje, jefeFinal, true, escenario);
        batallaFinal.iniciar();
    }
}