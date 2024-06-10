import java.util.InputMismatchException;
import java.util.Scanner;

public class GiocoCarte{
	//oggetti
	private static Mazzo MazzoDiCarte = new Mazzo();
	private static Mano NPC = new Mano();
	private static Mano Giocatore = new Mano();
	private Scanner x = new Scanner(System.in);
	private Scanner y = new Scanner(System.in);
	
	//variabili di punteggio
	private int PunteggioGiocatore = 0;
	private int PunteggioNPC = 0;
	
	//variabili di scelta carte
	private Carta sceltaGiocatore = null;
	private Carta sceltaNPC = null;
	private boolean trovata = false;
	private boolean inserimentoCorretto = true;
	private boolean turno = true;
	
	//costruttore
	public GiocoCarte () {
	}
	
	//Aggiunta di una carta a giocatore
	public static void DaiCarta() {
		
		NPC.AggiungiCarta(MazzoDiCarte);
		Giocatore.AggiungiCarta(MazzoDiCarte);
	}
	
	//Metodo di gioco
	public void Gioco(String scelta) {
		
		MazzoDiCarte.Mischia();
		
		//da le carte solo se il punteggio totale è minore di 18 quindi sono state giocate 36 carte
		//questo perchè se sono state giocate più di 36 carte il mazzo è vuoto e le ultime carte sono in mano ai giocatori
		if (PunteggioGiocatore + PunteggioNPC < 18 ) {
			
			//quando entrambi i mazzi sono vuoti vengono distribuite 3 carte ciascuno. questo avviene solo ad inizio partita
			if (NPC.getLista().size() == 0 || Giocatore.getLista().size() == 0) {
				for (int i = 0; i < 3; i++) {
					DaiCarta();
				}
			}
			
			//assegnazione delle carte se la mano di uno dei giocatori ha meno di 3 carte. ad ogni mano viene ridata una carta
			if (NPC.getLista().size() < 3 || Giocatore.getLista().size() < 3) {
				DaiCarta();
			}
		}
		NPC.RiordinaMano(NPC);
		Giocatore.RiordinaMano(Giocatore);
		
		//stampo delle carte
		Giocatore.Stampa();
		
		//vengono stampate le carte del giocatore virtuale se l'utente inserisce la modalità facile
		if (scelta.equals("si")) {
			System.out.println("||||||||||||||||||||||||||");
			NPC.Stampa();
		}
		System.out.println("||||||||||||||||||||||||||");
		System.out.println("Dimensione lista giocatore: " + Giocatore.getListaC().size());
		System.out.println("Dimensione lista NPC: " + NPC.getListaC().size());
		
		//assegnazione dei turni in base alla vittoria del punto
		if (turno == true) {
			GiocaGiocatore();
			GiocaNPC();
		}
		else {
			GiocaNPC();
			GiocaGiocatore();
		}
		
		//assegnazione del punto
		System.out.println(sceltaGiocatore.getValore() + sceltaGiocatore.getSeme());
		System.out.println(sceltaNPC.getValore() + sceltaNPC.getSeme());
		
		//se la carta buttata dal giocatore ha valore maggiore e seme uguale
		if (sceltaGiocatore.getValore() > sceltaNPC.getValore() && sceltaGiocatore.getSeme().equals(sceltaNPC.getSeme())) {
			PunteggioGiocatore++;
			System.out.println("_____");
			System.out.println("-----------------------------------------------------------Punto al giocatore------------------------------------------------------------");
			System.out.println("Punteggio Giocatore: " + PunteggioGiocatore + "\t" + "Punteggio giocatore virtuale: " + PunteggioNPC);
			System.out.println("_____");
			turno = true;
		}
		
		//se la carta buttata dall'NPC ha un valore maggiore e seme uguale
		else if (sceltaNPC.getValore() > sceltaGiocatore.getValore() && sceltaNPC.getSeme().equals(sceltaGiocatore.getSeme())) {
			PunteggioNPC++;
			System.out.println("_____");
			System.out.println("-----------------------------------------------------------Punto al giocatore virtuale------------------------------------------------------------");
			System.out.println("Punteggio Giocatore: " + PunteggioGiocatore + "\t" + "Punteggio giocatore virtuale: " + PunteggioNPC);
			System.out.println("_____");
			turno = false;
		}
		//se l'NPC butta una carta con un seme diverso da quello del giocatore mentre è il turno del giocatore allora punto al giocatore 
		else if (!sceltaNPC.getSeme().equals(sceltaGiocatore.getSeme()) && turno == true) {
			PunteggioGiocatore++;
			System.out.println("_____");
			System.out.println("-----------------------------------------------------------Punto al giocatore------------------------------------------------------------");
			System.out.println("Punteggio Giocatore: " + PunteggioGiocatore + "\t" + "Punteggio giocatore virtuale: " + PunteggioNPC);
			System.out.println("_____");
			turno = true;
		}
		//se il giocatore butta una carta con un seme diverso da quello dell'NPC durante il turno del'NPC allora punto all'NPC
		else if (!sceltaGiocatore.getSeme().equals(sceltaNPC.getSeme()) && turno == false) {
			PunteggioNPC++;
			System.out.println("_____");
			System.out.println("-----------------------------------------------------------Punto al giocatore virtuale------------------------------------------------------------");
			System.out.println("Punteggio Giocatore: " + PunteggioGiocatore + "\t" + "Punteggio giocatore virtuale: " + PunteggioNPC);
			System.out.println("_____");
			turno = false;
		}
	}

	//metodo di gioco del giocatore
	private Carta GiocaGiocatore () {
		do {
			inserimentoCorretto = true;
			try {
				//messa in gioco della carta inserita dall'utente
				System.out.print("Inserisci la carta da giocare: ");
				sceltaGiocatore = Giocatore.GiocaCarta(x.nextInt(), y.nextLine());
				
				//ricerca della carta, se non esiste viene chiesta finchè non viene inserita una carta presente nella mano
				while (sceltaGiocatore.getSeme().equals("null") || sceltaGiocatore.getValore() == -1 || 
						inserimentoCorretto == false) {
					System.out.print("Indice inserito non esistente, reinserisci: ");
					sceltaGiocatore = Giocatore.GiocaCarta(x.nextInt(), y.nextLine());
				}
			}
			//cattura dell'errore
			catch (InputMismatchException e) {
				System.out.println("Valore inserito non valido, " + e.getMessage());
				x.nextLine();
				inserimentoCorretto = false;
			}
			catch (NullPointerException e) {
				System.out.println("Valore inserito non valido, " + e.getMessage()); 
				x.nextLine();
				inserimentoCorretto = false;
			}
		}while (!inserimentoCorretto);
		
		return sceltaGiocatore;
	}
	
	//metodo di gioco dell'NPC
	private Carta GiocaNPC () {
		
		//l'NPC cerca di trovare una carta nel suo mazzo maggiore di quella scelta dal giocatore e dello stesso seme
		try {
			trovata = false;
			
			//se è il turno del giocatore allora giochera la sua carta in base a quella del giocatore
			if (turno == true) {
				for (int i = 0; i < NPC.getLista().size(); i++) {
					if (NPC.getLista().get(i).getValore() > sceltaGiocatore.getValore() && 
						NPC.getLista().get(i).getSeme().equals(sceltaGiocatore.getSeme())) {
						sceltaNPC = NPC.GiocaCarta(NPC.getLista().get(i).getValore(), NPC.getLista().get(i).getSeme());
						trovata = true;
						break;
					}
				}
			}
			
			//se è il suo turno butterà la carta col valore medio, non la più piccola ma nemmeno la più grande
			else {
				if (NPC.getLista().size() > 1) {
					sceltaNPC = NPC.GiocaCarta(NPC.getLista().get(NPC.getLista().size()-1).getValore(),
					NPC.getLista().get(NPC.getLista().size()-1).getSeme());
					trovata = true;
				}
				else {
					sceltaNPC = NPC.GiocaCarta(NPC.getLista().get(0).getValore(), NPC.getLista().get(0).getSeme());
					trovata = true;
				}
			}
		}
		
		//cattura dell'errore
		catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} 
		//se non trova una carta maggiore e dello stesso seme allora gioca la carta col valore minore
		if (trovata == false) {
			System.out.println("!!!");
			sceltaNPC = NPC.GiocaCarta(NPC.getLista().get(0).getValore(), NPC.getLista().get(0).getSeme());
		}
		System.out.println(sceltaNPC.getValore() + sceltaNPC.getSeme() + " CARTA APPENA GIOCATA");
		return sceltaNPC;
	}
	
	//metodi get e set
	public Mazzo getMazzoDiCarte() {
		return MazzoDiCarte;
	}
	public void setMazzoDiCarte(Mazzo mazzoDiCarte) {
		MazzoDiCarte = mazzoDiCarte;
	}
	public Mano getNPC() {
		return NPC;
	}
	public void setNPC(Mano vplayer) {
		NPC = vplayer;
	}
	public Mano getGiocatore() {
		return Giocatore;
	}
	public void setGiocatore(Mano giocatore1) {
		Giocatore = giocatore1;
	}
	public int getPunteggioGiocatore() {
		return PunteggioGiocatore;
	}
	public void setPunteggioGiocatore(int punteggioGiocatore) {
		PunteggioGiocatore = punteggioGiocatore;
	}
	public int getPunteggioNPC() {
		return PunteggioNPC;
	}
	public void setPunteggioNPC(int punteggioNPC) {
		PunteggioNPC = punteggioNPC;
	}
}