import java.util.Scanner;
public class test_GiocoCarte {

	public static void main(String[] args) {
		
		Scanner y = new Scanner(System.in);
		Mazzo MazzoDiCarte = new Mazzo();
		Carta carta = new Carta();
		GiocoCarte Partita = new GiocoCarte();
		boolean OnGame = true;
		String scelta;
		
		//scelta della modalità di gioco
		System.out.print("vuoi giocare con la modalità a carte scoperte?: ");
		Partita.Gioco(scelta = y.nextLine());
		
		//ripetizione del ciclo, viene fermato quando la variabile assume un valore "false", quindi la partita è finita
		while (OnGame) {
			
			Partita.Gioco(scelta);
			
			//se il punteggio di uno dei giocatori è maggiore di 10 allora vince a tavolino perchè è impossibile recuperare
			if (Partita.getPunteggioGiocatore() > 10) {
				OnGame = false;
				System.out.println("Il Giocatore ha vinto!") ;
			}
			else if (Partita.getPunteggioNPC() > 10) {
				OnGame = false ;
				System.out.println("L'NPC ha vinto!") ;
			}
			
			//se il mazzo, la mano del giocatore e la mano del giocatore virtuale sono vuote e il puteggio dei due è pari, allora la partita finisce in parità
			else if (Partita.getMazzoDiCarte().getLista().size() == 0 &&
				Partita.getGiocatore().getLista().size() == 0 &&
				Partita.getNPC().getLista().size() == 0) {
				
				if (Partita.getPunteggioGiocatore() == Partita.getPunteggioNPC()) {
					OnGame = false;
					System.out.println("Parità");
				}
			}
		}
	}
}