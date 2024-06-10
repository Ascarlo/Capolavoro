public class Carta {

	//definizione delle carte dell'intero mazzo
	private String Seme;
	private int Valore;
	
	public Carta() {
		
	}
	public Carta (String seme, int valore) {
		Seme = seme;
		Valore = valore;
	}
	public String getSeme() {
		return Seme;
	}
	public void setSeme(String seme) {
		Seme = seme;
	}
	public int getValore() {
		return Valore;
	}
	public void setValore(int valore) {
		Valore = valore;
	}
}