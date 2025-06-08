package modelo;

import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

import dao.DaoJugador;

public class Rompecabezas {

    //TODO Métodos	
    public void iniciarJuegos(Jugador jugador) throws SQLException {
       
       Rompecabezas rompeCa = new Rompecabezas();
       DaoJugador dao1 = new DaoJugador();
                     
        System.out.println("\nTe encuentras con el primer rompecabezas del camino, a las puertas de la Pirámide de Ezhar. Encima de la puerta se lee lo "
            + "siguiente:" + "\n\n\t❂ Soy eterno, pero nunca envejezco. Mi rostro es de piedra, pero nunca me canso" 
        	+ "\n\tVigilante del sol y del desierto sin fin, en el misterio de las pirámides guardo mi sin fin. ¿Qué soy? ❂");

		rompeCa.adivinanzaUno(jugador);
		
        System.out.println("\nEntras a la pirámide y te encuentras con la esfinge guardiana de la pirámide.\n" 
        + "\n\t⥼Te doy la bienvenida guerrero, pero no puedo dejar que te adentres en la cámara sin saber si eres digno." 
        + "\n\tLo sabré con tu respuesta. Tienes 5 intentos." 
        + "\n\tPara en la cámara poder entrar el número debes acertar⥽");

        rompeCa.adivinanzaDos(jugador);

        System.out.println("La esfinge te deja paso a la cámara." 
        + "\nAllí te encuentras a un guardián, que te dice que está muy solo y te pide compañía. Te propone jugar con él a piedra, papel o tijera." 
        + "\nSi ganas de manera justa, te permitirá continuar. El juego será al mejor de tres.");

        rompeCa.PPT(jugador);

		System.out.println("\nAccedes a la segunda cámara" 
		+ "\nPara tu sorpresa, allí se encuentra Bennu, quien te pone otra prueba. Se trata de un trivial sobre Egipto");
                
        rompeCa.trivial(jugador);

		System.out.println("\nPasas a la siguiente cámara, donde te encuentras con Imhotep, sabio sacerdote del Antigüo Egipto, guardián de las almas.\n" 
		+ "\n\t ¿Osas adentrarte en las entrañas del conocimiento prohibido?" 
		+ "\n\t Para poder superar esta prueba, tienes que acertar la palabra secreta. Si fallas, tu alma quedará atrapada aquí por el resto de la eternidad." 
		+ "\n\nPISTA (es una de las siguientes palabras): Seth, Anubis, Horus, Osiris, Sekhmet");
        
        rompeCa.ahorcado(jugador);
       
		System.out.println("\nSe abre ante ti una puerta oculta en la pared" 
				+ "\nDe pronto aparece una gran sombra, al mirar más detenidamente ves que se trata de Ammit" 
				+ "\nTrae malas noticias con ella, ya que los valiosos soldados de Eazima han sido destruidos......" 
				+ "\nTe dice que tras las puertas se guarda un gran y valioso tesoro, quien las cruce tendrá oro, gloria y poder sin igual para derrotar a Taharka"); 

		rompeCa.favorAmmit(jugador);
				
		dao1.actualizarPuntos(jugador);	

    }
     
    
    //Adivinar Efinge

    private void adivinanzaUno(Jugador jugador) {

    	Scanner sc = new Scanner(System.in);
	    String respuestaUsuario = sc.nextLine();
	    int intentosR = 5;
	
		while (!respuestaUsuario.equalsIgnoreCase("Esfinge") && !respuestaUsuario.equalsIgnoreCase("La esfinge") && intentosR != 1) {
			            System.out.println (respuestaUsuario + " no es correcto, intentalo de nuevo.");
			            intentosR--;
			            System.out.println("Te quedan " +intentosR+ " intentos restantes.");
			            respuestaUsuario = sc.nextLine();
		}
			        
			if (respuestaUsuario.equalsIgnoreCase("Esfinge") || respuestaUsuario.equalsIgnoreCase("La esfinge")) {
			            System.out.println("¡Es correcto! La puerta se abre ante ti");
			            jugador.sumarPuntos(20);  
			       
			} else {
					System.out.println("Has gastado todos tus intentos" + "\nSales corriendo y te cuelas en la pirámide antes de que la puerta se cierre");
			}

    }

    
    // Adivina el número
    private void adivinanzaDos(Jugador jugador) {

        Random random = new Random ();
        Scanner sc = new Scanner(System.in);
        
        int numeroAdivinar = random.nextInt(10);
        int adivina = 20;
        int intentosR = 5;

        while (numeroAdivinar != adivina && intentosR != 0) {
        	
				adivina = sc.nextInt();
				
					while (adivina <= -1 || adivina >= 11) {
						System.out.println("Prueba otra vez");
						adivina = sc.nextInt();
					}
						
					if (adivina > numeroAdivinar) {
						System.out.println("Más bajo");
						intentosR--;
						System.out.println("Te quedan " + intentosR +" intentos.\n");
					}
						            
					if (adivina < numeroAdivinar) {
						System.out.println("Más alto");
						intentosR--;
						System.out.println("Te quedan " + intentosR +" intentos.\n");
					}    
				}
				
			if (intentosR > 0) { 
				System.out.println("Adelante, puedes pasar. " + numeroAdivinar + " era el número correcto.\n");
				 jugador.sumarPuntos(20);

			} else {
				System.out.println("Has gastado todos los intentos.");
				System.out.println("\nTe permitiré la entrada, pero a cambio te quitaré un objeto valioso para ti, el mapa ancestral de la pirámide"
					+ " que se encuentra en tu poder.");
			}
    	}    


	    // Piedra, Papel o Tijera
	    private void PPT(Jugador jugador) {
	       
	        Scanner sc = new Scanner(System.in);
	        Random random = new Random();
	        String[] opciones = {"Piedra", "Papel", "Tijera"};

	        int eleccionOponente = random.nextInt(3);
			int puntosEnemigo = 0;
		    int puntosUsuario = 0;
	        int eleccionJugador;

	        while (puntosEnemigo != 3 && puntosUsuario != 3) {
					eleccionOponente = random.nextInt(3);
					System.out.println("Piedra, papel o tijera.... 1, 2, 3!");
					System.out.println("0: Piedra, 1: Papel, 2: Tijera");
					System.out.println("Tu elección");
					eleccionJugador = sc.nextInt();
							
					while (eleccionJugador < 0 || eleccionJugador > 2) {
								System.out.println("Esa no es una opción válida");
								eleccionJugador = sc.nextInt();
					}
							
				    System.out.println("\nTu elección: " + opciones[eleccionJugador]);
				    System.out.println("Elección del guardián: " + opciones[eleccionOponente]);
							
				    if (eleccionJugador == eleccionOponente) {
					    System.out.println("\n¡Es un empate!\n");
				            
				    } else if ((eleccionJugador == 0 && eleccionOponente == 2) || 
				    		   (eleccionJugador == 1 && eleccionOponente == 0) || 
				    		   (eleccionJugador == 2 && eleccionOponente == 1)) {
				        System.out.println("\n¡Has ganado!");
				        puntosUsuario++;
				        System.out.println("\nPuntuación: ");
				        System.out.println("Tienes: " + puntosUsuario + " puntos");
				        System.out.println("El guardián tiene: " + puntosEnemigo + " puntos \n");
				           	
				    } else {
				        System.out.println("\n¡Has perdido!");
				        puntosEnemigo++;
				        System.out.println("\nPuntuación: ");
				        System.out.println("Tienes: " + puntosUsuario + " puntos");
				        System.out.println("El guardián tiene: " + puntosEnemigo + " puntos \n");
				    }
				}
						
				if (puntosUsuario == 3) {
					System.out.println("\nMe has derrotado justamente, puedes continuar tu camino.");
					jugador.sumarPuntos(40);  
							
				} else {
					System.out.println("\nHas perdido la prueba, el guardián extiende su brazo y te bloquea el paso." +
						"\nFinges retirarte... pero en cuanto su atención se desvía, te deslizas silenciosamente tras una pared cercana." + 
						"\nCuando el guardián se aleja, sin sospechar nada, aprovechas el momento y te cuelas en la siguiente cámara");		
				}		
	        }

	    
	    // Trivial
	    private void trivial (Jugador jugador) {

	        Scanner sc = new Scanner(System.in);
		    String respuesta1, respuesta2, respuesta3, respuesta4 = "";

	        // Pregunta 1
				System.out.println("\nPregunta 1: ¿Cuál es el río más largo de Egipto?");
				System.out.println("a) Amazonas" + "\nb) Nilo" + "\nc) Yangtsé" + "\nd) Misisipi");
				respuesta1 = sc.nextLine().toLowerCase();
					       
				while (!respuesta1.equals("a") && !respuesta1.equals("b") && !respuesta1.equals("c") && !respuesta1.equals("d")) {
					respuesta1 = sc.nextLine().toLowerCase();
				}
					       
					if (respuesta1.equals("b")) {
						System.out.println("¡Correcto!\n");
						jugador.sumarPuntos(20);
					            
					} else {
						System.out.println("No has acertado, la respuesta correcta es: el Nilo.\n");
					}

				// Pregunta 2
				System.out.println("\nPregunta 2: ¿Quién fue el faraón más joven de Egipto?");
				System.out.println("a) Ramsés II" + "\nb) Tutankamón" + "\nc) Cleopatra" + "\nd) Akhenatón");
				respuesta2 = sc.nextLine().toLowerCase();
					        
				while (!respuesta2.equals("a") && !respuesta2.equals("b") && !respuesta2.equals("c") && !respuesta2.equals("d")) {
						respuesta2 = sc.nextLine().toLowerCase();
				}
						    
					if (respuesta2.equals("b")) {
				        System.out.println("¡Correcto!\n");
				        jugador.sumarPuntos(20);
				        
				    } else {
				        System.out.println("No has acertado, la respuesta correcta es: Tutankamón.\n");
				    }

				// Pregunta 3
				System.out.println("\nPregunta 3: ¿Qué estructura famosa se encuentra en Giza?");
				System.out.println("a) La Gran Muralla" + "\nb) El Coliseo" + "\nc) La Torre Eiffel" + "\nd) Las Pirámides");
				respuesta3 = sc.nextLine().toLowerCase();
					        
				while (!respuesta3.equals("a") && !respuesta3.equals("b") && !respuesta3.equals("c") && !respuesta3.equals("d")) {
						respuesta3 = sc.nextLine().toLowerCase();
				}
						    
					if (respuesta3.equals("d")) {
				        System.out.println("¡Correcto!\n");
				        jugador.sumarPuntos(20);
				            
				    } else {
				        System.out.println("No has acertado, la respuesta correcta es: las Pirámides.\n");
				    }

				// Pregunta 4
				System.out.println("\nPregunta 4: ¿Qué animal se representa en la Esfinge de Giza?");
				System.out.println("a) León" + "\nb) Águila" + "\nc) Serpiente" + "\nd) Gato");
				respuesta4 = sc.nextLine().toLowerCase();
					        
				while (!respuesta4.equals("a") && !respuesta4.equals("b") && !respuesta4.equals("c") && !respuesta4.equals("d")) {
						respuesta4 = sc.nextLine().toLowerCase();
				}	   
						    
					if (respuesta4.equals("a")) {
				        System.out.println("¡Correcto!\n");
				        jugador.sumarPuntos(20);
				          
					} else {
				        System.out.println("No has acertado, la respuesta correcta es: el león.\n");
				    }
	    }
	    
	    // Ahorcado
	    
	    private void ahorcado(Jugador jugador) {

	        Scanner sc = new Scanner(System.in);

	        String[] palabras = {"Seth", "Anubis", "Horus", "Osiris", "Sekhmet"};
			String palabraSecreta = palabras[(int) (Math.random() * palabras.length)];
			int longitud = palabraSecreta.length();
			char [] letrasAdivinadas = new char[longitud];

	        int intentosRestantes = 6; 
		    boolean palabraAdivinada = false;
		    boolean letraEncontrada = false;
		    String entrada;

	        for (int i = 0; i < longitud; i++) {
					letrasAdivinadas[i] = '-';  
				}
					        
				while (intentosRestantes > 0 && !palabraAdivinada) {
				System.out.println("\nPalabra: " + new String(letrasAdivinadas));
				System.out.println("Tienes " + intentosRestantes + " intentos restantes.");
				System.out.print("Introduce una letra o la palabra completa si la tienes: ");
				entrada = sc.nextLine(); 
					        
					if (entrada.length() == 1) {
						char letra = entrada.charAt(0);
				                      
						for (int i = 0; i < longitud; i++) {
						            	
							 if (palabraSecreta.charAt(i) == letra) {
								 letrasAdivinadas[i] = letra;
								 letraEncontrada = true;
							 }
						 }
					            		            
							 if (!letraEncontrada) {
								 intentosRestantes--;
								 System.out.println("\n¡Letra incorrecta!\n");             
							 }
					            	
					} else if (entrada.length() == longitud) {
				                
					         if (entrada.equalsIgnoreCase(palabraSecreta)) {
						         letrasAdivinadas = palabraSecreta.toCharArray(); 
						         System.out.println("\n¡Felicidades! Has adivinado la palabra: " + palabraSecreta);
						         palabraAdivinada = true;
						         					                    
					         } else {
						         intentosRestantes--;
						         System.out.println("\n¡Has perdido! La palabra era: " + palabraSecreta);
					         }
					                
					} else {
						System.out.println("Entrada no válida. Debes introducir una letra o la palabra completa.");
					}
					        
					palabraAdivinada = true;
					
					for (char c : letrasAdivinadas) {
					                
						if (c == '-') {
							palabraAdivinada = false;
							continue;
						}
					}
				}      
					        
					if (!palabraAdivinada) {
						System.out.println("\n¡Has perdido! La palabra era: " + palabraSecreta);
						System.out.println("\nImhotep intenta atraparte para quitarte el alma, pero lo esquivas y accedes a la siguiente cámara");
						
					} else {
						System.out.println("\nHas superado la prueba de Imhotep. Se aparta para dejarte paso.");
						jugador.sumarPuntos(40);
					}
	    }
	    
	    
	    // Favor de Ammit
	    
	    private void favorAmmit (Jugador jugador) throws SQLException {
	    	
	    	Personaje personaje = new Personaje(null, 0, null);
	    	Scanner sc = new Scanner (System.in);
	    	int respuestaFinal;

			System.out.println("Ammit también te da la opción de continuar con la ayuda de su bendición, pero deberás enfrentarte a Taharka sin los soldados");
			System.out.println("\nPara continuar sólo te pide que elijas lo que harías: ");
			System.out.println("1. Entrar en la cámara, obtener el poder y huir con el tesoro");
			System.out.println("2. Pelear contra Taharka para liberar a Egipto de su maldición");
			System.out.println("Debes elegir ahora");
			respuestaFinal = sc.nextInt();
			        	

			while (respuestaFinal != 1 && respuestaFinal != 2) {
			    System.out.println("No entiendo tu respuesta, ¿cuál es tu decisión?");
			    respuestaFinal = sc.nextInt();
			}

				if (respuestaFinal == 1) {
				    				    
					DaoJugador dao = new DaoJugador();
				    personaje.activarFinal(1, jugador);
				    jugador.sumarPuntos(-50); 
				  				    
				    System.out.println("\nEstadísticas de la partida");
		            jugador.infoJugador();
		            
		            dao.actualizarPuntos(jugador);
		            dao.mostrarRanking();
		            
		            System.exit(0);
	
				} else if (respuestaFinal == 2) {
				   
					personaje.activarFinal(0, jugador);
				    
				    jugador.sumarPuntos(60);
				    
				}
	    	}   
	    }



	
