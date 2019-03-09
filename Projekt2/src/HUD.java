
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Heads up display - podstawowe atrybuty
 */
public class HUD {

    LinkedList<String> ob = new LinkedList<>();

    public int LEVEL;
    public long points;
    public float health;
    public int wymiarOknaX;
    public int wymiarOknaY;

    /**
     * Konstruktor klasy HUD
     */
    public HUD(int x, int y) {

        zaladujParametry();
        LEVEL = Integer.parseInt(ob.get(2));
        points =Long.parseLong(ob.get(1));
        health = Float.parseFloat(ob.get(0));
        wymiarOknaY = 608;
        wymiarOknaX = 900;

    }

    /**
     * Metoda ustawiajaca warunki poczatkowe po zakonczeniu gry
     */

    public void reset(){
       this.LEVEL= Integer.parseInt(ob.get(2));
       this.points= Long.parseLong(ob.get(1));
       this.health= Float.parseFloat(ob.get(0));
    }


    /**
     * Metoda ladujaca parametry konfiguracyjne
     */
    public void zaladujParametry(){

        try {
            File plik = new File("ress/konfig.txt");

            Scanner odczyt = new Scanner(plik);
            while (odczyt.hasNextLine()){
                if (odczyt.nextLine().equals("zycia")){
                    this.dodajObiekt(odczyt.nextLine());
                }
                if (odczyt.nextLine().equals("punkty")){
                    this.dodajObiekt(odczyt.nextLine());
                }
                if (odczyt.nextLine().equals("lvl")){
                    this.dodajObiekt(odczyt.nextLine());
                }
            }
        }
        catch (FileNotFoundException a) {
            System.out.println("Plik nie istnieje");
        }


    }


    /**
     * Metoda dodajaca parametr do listy
     */
    private void dodajObiekt(String ob) {
        this.ob.add(ob);
    }
}
