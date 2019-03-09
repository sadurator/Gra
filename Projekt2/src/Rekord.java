
/**
 * Klasa pomocnicza w sensie sortowania wynikow zczytywanych z pliku, Rekord = pojedynczy wynik gracza.
 */

public class Rekord  {
    private int punkty;
    private String Imie;

    public Rekord(int pkty, String name) {
        punkty = pkty;
        Imie = name;
    }
    /**
     *  Metoda zwracajaca ilosc punktow
     */

    public int zwrocPunkty()
    {
        return this.punkty;
    }

    /**
     * Metoda wypisujaca wyniki
     */

    public String wypiszDane() {
        //System.out.println(" Nick : " + this.Imie + " Wynik : " + this.Punkty);
        String zwrot = this.Imie + "  " + this.punkty;
        return zwrot;
    }
}

