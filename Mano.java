import java.util.ArrayList;
import java.util.Collections;

public class Mano extends Mazzo{
	 
	//Pulizia dell'Array e costruttore
	public Mano() {
		Lista = new ArrayList();
	}
	
	//Aggiunta di una carta alla mano tramite in metodo Cancella della classe Mazzo
	public void AggiungiCarta (Mazzo nuovoMazzo) {
		
		//viene tolta una carta dal mazzo e aggiunta alla mano del giocatore che la richiede
		Carta carta = nuovoMazzo.Cancella();
		this.getLista().add(carta);
	}
	
	//Rimozione della carta inserita dalla mano dell'utente per poi essere giocata
	public Carta GiocaCarta (int valore, String seme) {
		Carta RimuoviCarta = new Carta ();
		boolean trovata = false;
		try {
			
			//ricerca dei valori inseriti nella mano del giocatore 
			for (int i = 0; i < Lista.size(); i++) {
				if (Lista.get(i).getValore() == valore && Lista.get(i).getSeme().equals(seme)) {
					RimuoviCarta.setValore(getLista().get(i).getValore());
					RimuoviCarta.setSeme(getLista().get(i).getSeme());
					Lista.remove(i);
					trovata = true;
				}
			}
			return RimuoviCarta;
		}
		
		//cattura dell'errore
		catch(IndexOutOfBoundsException e) {
			System.out.println("Carte terminate---");
			if (trovata = false) {
				RimuoviCarta.setSeme("null");
				RimuoviCarta.setValore(-1);
			}
			return RimuoviCarta;
		}
	}
	
	//metodo per riordinare le carte in base al loro valore e seme
		public static void RiordinaMano (Mano OggettoMano) {
			
			try {
				for (int i = 0; i < OggettoMano.getLista().size(); i++) {
					
					for (int k = i + 1; k < OggettoMano.getLista().size(); k++) {
						
						if (OggettoMano.getLista().get(i).getValore() > OggettoMano.getLista().get(k).getValore()) {
							Collections.swap(OggettoMano.getLista(), i, k);
						}
					}
				}
			}
			catch (NullPointerException e) {
				System.out.println(e.getMessage());
			}
		}
	
	public ArrayList <Carta> getListaC() {
		return this.getLista();
	}
}