
import javax.swing.*;
import java.io.*;
import java.util.LinkedList;
import java.awt.*;
import java.util.Scanner;

/**
 * Klasa odpowiadajaca za przetrzymywanie obiektow aktywnych gry oraz ich zachowanie.
 */
public class Handler {
    /**
     * Lista zawierajaca utworzone obiekty
     */

    LinkedList<ObiektGry> object = new LinkedList<>();
    Gra gierka;
    private boolean pause;


    /**
     *Konstruktor klasy Handler
     */
    public Handler(Gra gra) {
        gierka = gra;
        pause = false;

    }

    public void tick() {
        if(getPause()==false) {
            gierka.hud.points--;
        }
        for (int i = 0; i < object.size(); i++) {

            ObiektGry chwilowy = object.get(i);
            chwilowy.tick(object);
        }

    }

    /**
     * Metoda słuzaca generowaniu graficznych elementow aktywnych.
     *
     */

    public void render(Graphics g) {
        g.fillRect(0, 0, gierka.hud.wymiarOknaX, gierka.hud.wymiarOknaY);
        for (int i = 0; i < object.size(); i++) {
            ObiektGry chwilowy = object.get(i);
            chwilowy.render(g);
        }
    }


    /**
     * Metoda tworząca nowy obiekt aktywny.
     *
     * @param object obiekt gry
     */

    private void dodajObiekt(ObiektGry object) {
        this.object.add(object);
    }

    /**
     * Metoda usuwajaca obiekt z listy obiektow.
     *
     */
    public void usunObiekt(ObiektGry object) {
        this.object.remove(object);
    }

    /**
     * Metoda czyszczaca dany poziom gry
     */

    private void clearLevel() {
        object.clear();
    }

    /**
     * Metoda odpowiedzialna za zaladowanie danego poziomu z pliku
     */

    private void ZaladujPoziomZPliku(String path) {

        try {
            File plik = new File(path);

            Scanner odczyt = new Scanner(plik);
            while (odczyt.hasNextLine()) {
                if (odczyt.nextLine().equals("sciana")) {
                    int g = Integer.parseInt(odczyt.nextLine());
                    for (int z = 0; z < g; z++) {
                        this.dodajObiekt(new Sciana(Integer.parseInt(odczyt.nextLine()), Integer.parseInt(odczyt.nextLine()), ID.Wall, gierka,this));
                    }
                }
                if (odczyt.nextLine().equals("gracz")) {
                    int p = Integer.parseInt(odczyt.nextLine());
                    for (int z = 0; z < p; z++) {
                        this.dodajObiekt(new Gracz(Integer.parseInt(odczyt.nextLine()), Integer.parseInt(odczyt.nextLine()), this, ID.Player,Float.parseFloat(odczyt.nextLine()), gierka));

                    }
                }
                if (odczyt.nextLine().equals("portal")) {
                    int r = Integer.parseInt(odczyt.nextLine());
                    for (int z = 0; z < r; z++) {
                        this.dodajObiekt(new Portal(Integer.parseInt(odczyt.nextLine()), Integer.parseInt(odczyt.nextLine()), ID.Portal, gierka,this));

                    }
                }
                if (odczyt.nextLine().equals("bonus")) {
                    int r = Integer.parseInt(odczyt.nextLine());
                    for (int z = 0; z < r; z++) {
                        this.dodajObiekt(new Bonus(Integer.parseInt(odczyt.nextLine()), Integer.parseInt(odczyt.nextLine()), ID.Bonus, gierka,this));
                    }
                }
                if (odczyt.nextLine().equals("apteka")){
                    int r= Integer.parseInt(odczyt.nextLine());
                    for (int z = 0; z < r; z++){
                        this.dodajObiekt(new Apteczka(Integer.parseInt(odczyt.nextLine()), Integer.parseInt(odczyt.nextLine()), ID.Apteczka, gierka,this));
                    }
                }
                if (odczyt.nextLine().equals("przeszkoda")) {
                    int r = Integer.parseInt(odczyt.nextLine());
                    for (int z = 0; z < r; z++) {
                        this.dodajObiekt(new Przeszkoda(Integer.parseInt(odczyt.nextLine()), Integer.parseInt(odczyt.nextLine()), ID.Obstacle, gierka,this));
                    }
                }
                if (odczyt.nextLine().equals("INFO")) {
                    int we = Integer.parseInt(odczyt.nextLine());
                    for (int z = 0; z < we; z++) {
                        this.dodajObiekt(new Informacje(Integer.parseInt(odczyt.nextLine()), Integer.parseInt(odczyt.nextLine()), ID.InfoPanel, gierka));
                    }
                }
            }



        } catch (FileNotFoundException a) {
            System.out.println("Plik nie istnieje");
        }

    }
    /**
     * Metoda zmieniajaca poziom gry
     *
     */

    public void ZmienPoziom() {
        clearLevel();

        if (gierka.hud.health > 0) {
            switch (gierka.hud.LEVEL) {
                case 1:
                    ZaladujPoziomZPliku(Gra.konfiguracja.get(11));
                    break;
                case 2:

                    gierka.hud.points += 1000;
                    ZaladujPoziomZPliku(Gra.konfiguracja.get(12));
                    break;
                case 3:

                    gierka.hud.points += 1000;
                    ZaladujPoziomZPliku(Gra.konfiguracja.get(13));
                    break;
                case 4:
                    gierka.stanGry = STAN.Menu;
                    komunikatKoniecGry();
                    gierka.hud.reset();
            }
        } else {
            gierka.stanGry = STAN.Menu;
            komunikatKoniecGry();
            gierka.hud.reset();
        }
    }

    /**
     * Metoda ustawiajaca stan gry na pauze
     */
    public void setPause(boolean p) {

        this.pause = p;
    }
    /**
     * Metoda zwracajaca stan programu
     */
    public boolean getPause() {

        return pause;
    }


    /**
     * w tej metodzie zapisujemy uzyskany wynik
     */
    private void zapiszWynik(String imie, Gra gierka)  {

        try {
            FileWriter wyniki = new FileWriter("ress/Wyniki.txt", true);
            BufferedWriter wynik = new BufferedWriter(wyniki);
            wynik.write(imie);
            wynik.newLine();
            wynik.write(Long.toString(gierka.hud.points));
            wynik.newLine();
            wynik.close();

        } catch (IOException e) {

            System.out.println(e);
        }
    }
    /**
     * Metoda wyswietlajaca komunikat po zakonczeniu gry sluzaca przyporzadkowaniu nicku danemu graczowi
     */
    private void komunikatKoniecGry(){
        String imie = "";
        while (imie.length() == 0) {
            imie = JOptionPane.showInputDialog(gierka.okienko.okno, "Wprowadz Nick: ", "Gra skonczona!", JOptionPane.QUESTION_MESSAGE);
            if (imie == null)
                break;
            zapiszWynik(imie, gierka);
        }
    }

}










