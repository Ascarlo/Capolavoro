import java.util.ArrayList;
import java.util.Collections;

public class Mazzo {

	protected ArrayList <Carta> Lista;
	
	//Costruttore, creazione del mazzo formato da 4 semi, ognuno con 10 carte
	public Mazzo () {
		Lista = new ArrayList();
		Carta oggetto1 = new Carta ();
		String Seme [] = {"spade", "coppe", "bastoni", "ori"};
		
		for (int i = 0; i < 4; i++) {
			
			for (int k = 1; k <= 10; k++) {
				oggetto1 = new Carta(Seme[i], k);
				Lista.add(oggetto1);
			}
		}
	}
	
	//Stampa del mazzo per intero
	public void Stampa () {
		
		try {
			for (int i = 0; i < Lista.size(); i++) {
				System.out.print(Lista.get(i).getValore() + " ");
				System.out.println(Lista.get(i).getSeme());
			}
		}
		catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//Mescolamento casuale degli oggetti dell'array
	public void Mischia () {
		Collections.shuffle(Lista);
	}
	
	//Cancellamento della prima carta presente nel mazzo
	public Carta Cancella () {
		try {
			Carta RimuoviCarta = Lista.remove(0);
			return RimuoviCarta;
		}
		
		//cattura dell'errore in caso di carte terminate
		catch(IndexOutOfBoundsException e) {
			System.out.println("Carte terminate");
			return null;
		}
	}
	
	public ArrayList <Carta> getLista() {
		return Lista;
	}
}
