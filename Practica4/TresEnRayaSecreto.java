package Practica4;
import java.util.*;
public class TresEnRayaSecreto {
	
	static Scanner entrada=new Scanner(System.in);
	
	//Constantes 
	static final char BARRA='|';
	static final char J1='X';
	static final char J2='O';

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Este es un 3 en raya es algo diferente....
		
		//Variables, añadimos la variable bestia como boolean en este caso.
		char[] tablero=new char[9];
		char ficha;
		boolean win=false;
		int contador=0;
		boolean bestia;
		
		
		//Rellenamos la array de espacios, con un bucle for y recorriendo todas las posiciones de la array
		
		//Si no rellenaba la arrays de espacios daba error a la hora de comprobar que jugador habia ganado ya que si
		//habia 3 espacios haciendo un 3 en raya salia del bucle y daba por ganador al ultimo jugador, rellenandola
		//de espacios y a la hora de comprobar si el jugador ha ganado, comprobamos que sea un 3 en raya con "X" y "O" y 
		//no cuente que sea 3 en raya con los espacios, más adelante con el método evaluateWin.
		
		for(int i=0;i<tablero.length;i++) {
			tablero[i]=' ';
		}
		

		//Dentro de este do-while introducimos todos los metodos para que se vayan repitiendo sucesivamente, hasta
		//que uno de los dos jugadores gane.
		
		do {
			
			//Decidir jugador, con un condicional if y con un contador, dependiendo de si la division devuelve 1 o 0
			if(contador%2==0) {
				ficha=J1;
			}else {
				ficha=J2;
			}
			contador++;
			
			//Mostrar el tablero
			showTablero(tablero);
			
			//Turno y movimiento del jugador, este método ha sido modificado del juego normal, devolviendo ahora un boolean.
			bestia=juegaPlayer(ficha, tablero);
			
			//Verificar si ha ganado o no
			win=evaluateWin(tablero, bestia);
			
			
		}while(!win || contador==8);
		
		
		//Con este if comprobamos si ha ganado la bestia, si ha ganado algun jugador o si se ha quedado en empate.
		if(bestia) {
			
			//Mostramos que ha ganado la bestia y rellenamos la array de chars '6'
			System.out.println("***HA GANADO LA BESTIA****");
			
			for(int i=0;i<tablero.length;i++) {
				tablero[i]='6';
			}
			
			showTablero(tablero);
			
		}else if(contador<9) {
			
			//Mostramos el resultado del tablero y quien ha sido el ganador (es el ultimo jugador que ha movido).
			System.out.println("------------------------------------------");
			System.out.println("Felicidades has ganado jugador " + ficha);
			showTablero(tablero);
			
		}else {
			
			//En caso de que quede en empate, también se muestra el tablero.
			System.out.println("-------------------------------------------");
			System.out.println("El juego queda en empate, no habeis ganado ninguno de los dos.");
			showTablero(tablero);
			
		}

	}
	
	//Este método muestra las posiciones del tablero una a una.
	static void showTablero(char[] tablero) {
		
		System.out.println(tablero[0] + " " + BARRA + " " + tablero[1] + " " + BARRA + " " + tablero[2] + "\n" + 
				tablero[3] + " " + BARRA + " " + tablero[4] + " " + BARRA + " " + tablero[5] + "\n" + 
				tablero[6] + " " + BARRA + " " + tablero[7] + " " + BARRA + " " + tablero[8] + "\n");
		
	}
	
	//Este método sirve para introducir el movimiento del jugador y verifica si es un movimiento erroneo o si la posición del tablero
	//esta ocupada o no.
	static boolean juegaPlayer(char ficha, char[] tablero) {
		
		//Variables, añadimos dos variables nuevas, posicion correcta y bestia, para mas tarde en el bucle do-while si se ha introducido
		//el numero 666 o no.
		int posicion;
		boolean ocupada;
		boolean bestia=false;
		boolean posicionCorrecta=false;
		
		//Con el bucle do-while introducimos la posicion y comprobamos si la posicion que ha introducido no es erronea 
		//y si es erronea se vuelve a pedir, en caso de que se introduzca el 666, no se vuelve a pedir.
		do {
			
			do {
				
				System.out.println("Donde quieres mover, jugador " + ficha);
				posicion=entrada.nextInt();
				
				if(posicion==666) {
					bestia=true;
				}else if(posicion<0 || posicion>8) {
					System.out.println("Posicion erronea, pruebe otra posición");
					posicionCorrecta=true;
				}else {
					posicionCorrecta=false;
				}
				
			}while(posicionCorrecta && bestia);
			
			//En caso de que la posicion sea 666, hacemos un break para saltarnos el método.
			if(posicion==666) {
				break;
			}
			
			
			//Con este condicional if comprobamos que la posicion que ha introducido esta ocupada o no, y si esta ocupada se vuelve a pedir 
			//la posicion.
			if(tablero[posicion]!=J1 && tablero[posicion]!=J2) {
				
				tablero[posicion]=ficha;
				ocupada=true;
				
			}else {
				System.out.println("Esa posicion ya esta ocupada, eliga otra posición");
				ocupada=false;
			}
			
		}while(!ocupada);
		
		return bestia;
		
	}
	
	//Este método evalua si alguno de los dos jugadores ha ganado. Este método también ha sido modificado, añadiendole un nuevo parametro
	//que en este caso es el boolean bestia.
	static boolean evaluateWin(char[] tablero, boolean bestia) {
		
		//Variables
		boolean win=false;
		
		//Evaluamos las posiciones una por una con sucesivos if, comprobamos también que no sea 3 en raya con los espacios.
		
		if(bestia) {
			win=true;
		}else if((tablero[0]==tablero[1] && tablero[1]==tablero[2]) && (tablero[0]!=' ' && tablero[1]!=' ' && tablero[2]!=' ')){
			win=true;
		}else if((tablero[3]==tablero[4] && tablero[4]==tablero[5]) && (tablero[3]!=' ' && tablero[4]!=' ' && tablero[5]!=' ')) {
			win=true;
		}else if((tablero[6]==tablero[7] && tablero[7]==tablero[8]) && (tablero[6]!=' ' && tablero[7]!=' ' && tablero[8]!=' ')) {
			win=true;
		}else if((tablero[0]==tablero[4] && tablero[4]==tablero[8]) && (tablero[0]!=' ' && tablero[4]!=' ' && tablero[8]!=' ')) {
			win=true;
		}else if((tablero[2]==tablero[4] && tablero[4]==tablero[6]) && (tablero[2]!=' ' && tablero[4]!=' ' && tablero[6]!=' ')){
			win=true;
		}else if((tablero[0]==tablero[3] && tablero[3]==tablero[6]) && (tablero[0]!=' ' && tablero[3]!=' ' && tablero[6]!=' ')) {
			win=true;
		}else if((tablero[1]==tablero[4] && tablero[4]==tablero[7]) && (tablero[1]!=' ' && tablero[4]!=' ' && tablero[7]!=' ')) {
			win=true;
		}else if((tablero[2]==tablero[5] && tablero[5]==tablero[8]) && (tablero[2]!=' ' && tablero[5]!=' ' && tablero[8]!=' ')) {
			win=true;
		}else {
			win=false;
		}
		
		return win;
		
	}

}
