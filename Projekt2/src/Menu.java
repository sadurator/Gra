
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Klasa odpowiedzialna za wyglad i działanie menu
 */

public class Menu extends MouseAdapter {

    private Gra gra;
    Handler handler;
    static int zmiennaKomunikat = 0;
    private ArrayList<Rekord> Gracze = new ArrayList<>();
    /**
     * Konstruktor klasy menu
     */

    public Menu(Gra gra, Handler handler) {
        this.gra = gra;
        this.handler = handler;
    }
    /**
     * metoda odpowiadajaca za obsluge zdarzen w oknie głownym
     */

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        float pomoc_x = Gra.grafiki.hud.wymiarOknaX;
        float pomoc_y = Gra.grafiki.hud.wymiarOknaY;
        double wsp_y1 = (pomoc_y - gra.wysokosc + 35) * 0.25;
        double wsp_x = 584 + (pomoc_x - gra.szerokosc) * 0.65;
        double wsp_dlugosc = (pomoc_x / gra.szerokosc) * 260;
        double wsp_wysokosc = (pomoc_y / gra.wysokosc) * 75;

        if (gra.stanGry == STAN.Menu) {
            // Przycisk "Rozpocznij Grę "
            if (mouseOver(mx, my, (int) wsp_x, 147 + (int) wsp_y1, (int) wsp_dlugosc, (int) wsp_wysokosc)) {
                gra.stanGry = STAN.Rozgrywka;
                handler.ZmienPoziom();
            }
            // Przycisk " Wyjście"
            if (mouseOver(mx, my, (int) wsp_x, 398 + (int) (wsp_y1 * 2.64), (int) wsp_dlugosc, (int) wsp_wysokosc)) {
                System.exit(1);
            }
            if (mouseOver(mx, my, (int) wsp_x, 313 + (int) (wsp_y1 * 2.12), (int) wsp_dlugosc, (int) wsp_wysokosc)) {
                gra.stanGry = STAN.Pomoc;
            }
            if (mouseOver(mx, my, (int) wsp_x, 232 + (int) (wsp_y1 * 1.48), (int) wsp_dlugosc, (int) wsp_wysokosc)) {
                zmiennaKomunikat = 1;

                gra.stanGry = STAN.Wyniki;
                pobierzGraczy();


            }
        }
        if (gra.stanGry == STAN.Pomoc) {
            if (mouseOver(mx, my, (int) wsp_x, 445 + (int) (wsp_y1 * 2.9), (int) wsp_dlugosc, (int) wsp_wysokosc)) {
                gra.stanGry = STAN.Menu;
            }
        }
        if (gra.stanGry == STAN.Wyniki) {
            if (mouseOver(mx, my, (int) wsp_x, 445 + (int) (wsp_y1 * 2.9), (int) wsp_dlugosc, (int) wsp_wysokosc)) {
                gra.stanGry = STAN.Menu;
            }

        }

    }


    public void mouseReleased(MouseEvent e) {


    }
    /** Metoda sprawdzajaca polozenie kursora
     */


    private boolean mouseOver(int mx, int my, int x, int y, int szerokosc, int wysokosc) {
        if (mx > x && mx < x + szerokosc) {
            if (my > y && my < y + wysokosc) {
                return true;
            } else return false;
        } else return false;
    }

    public void tick() {

    }

    public void render(Graphics g) {

        Font czcionka2 = new Font("arial", Font.BOLD, 20);
        Font czcionka_mala = new Font("arial", Font.BOLD, 12);
        Font czcionka3 = new Font("arial", Font.BOLD, 15);
        g.setFont(czcionka2);

        if (gra.stanGry == STAN.Menu) {
            g.drawImage(Gra.grafiki.grafikaTla[0], 0, 0, gra);
            float pomoc_x = Gra.grafiki.hud.wymiarOknaX;
            float pomoc_y = Gra.grafiki.hud.wymiarOknaY;
            double wsp_y1 = (pomoc_y - gra.wysokosc + 35) * 0.25;

            double wsp_xx = 584 + (pomoc_x - gra.szerokosc) * 0.8;

            g.setColor(Color.white);
            g.drawString("Rozpocznij Grę", 56 + (int) wsp_xx, 195 + (int) wsp_y1);
            g.drawString("Tabela Wynikow", 53 + (int) wsp_xx, 275 + (int) (wsp_y1 * 1.6));
            g.drawString("Pomoc", 96 + (int) wsp_xx, 358 + (int) (wsp_y1 * 2.22));
            g.drawString("Wyjscie", 93 + (int) wsp_xx, 440 + (int) (wsp_y1 * 2.70));
            g.setColor(Color.BLACK);




        }
        if (gra.stanGry == STAN.Pomoc) {

            g.drawImage(Gra.grafiki.grafikaTla[1], 0, 0, gra);
            float pomoc_x = Gra.grafiki.hud.wymiarOknaX;
            float pomoc_y = Gra.grafiki.hud.wymiarOknaY;
            double wsp_y1 = (pomoc_y - gra.wysokosc + 35) * 0.25;
            double wsp_xx = 584 + (pomoc_x - gra.szerokosc) * 0.8;

            g.setColor(Color.white);
            g.setFont(czcionka_mala);
            int x = -74 + (int) wsp_xx;
            int y = (int) (wsp_y1 * 1);
            g.drawString("Witaj w zasadach gry ścieżka. Za pomocą klawiszy ", x, 125 + y);
            g.drawString("funkcyjnych mianowicie klawiszy W A S D w każdym ", x, 145 + y);
            g.drawString("kierunku  możesz sterować postacią. Twoim ", x, 165 + y);
            g.drawString("głównym celem jest dotarcie do wyznaczonego miejsca", x, 185 + y);
            g.drawString("na końcu kazdego z poziomów, jednak uważaj!", x, 205 + y);
            g.drawString("Po drodze czekają różnorodne przeszkody które nie ", x, 225 + y);
            g.drawString("ułatwią Ci tego zadania. Dodatkowo możesz napotkać ", x, 245 + y);
            g.drawString("wiele bonusów które sprawią owe wyzwanie łatwiejsze ! ", x, 265 + y);
            g.drawString("Powodzenia !", x, 285 + y);
            g.setColor(Color.white);
            g.setFont(czcionka2);
            g.drawString("Powrót", 80 + (int) wsp_xx, 465 + (int) (wsp_y1 * 3.1));
        }
        if (gra.stanGry == STAN.Wyniki) {
            float pomoc_x = Gra.grafiki.hud.wymiarOknaX;
            float pomoc_y = Gra.grafiki.hud.wymiarOknaY;
            double wsp_y1 = (pomoc_y - gra.wysokosc + 35) * 0.25;
            double wsp_x = 584 + (pomoc_x - gra.szerokosc) * 0.65;
            double wsp_xx = 584 + (pomoc_x - gra.szerokosc) * 0.8;
            int x = 10 + (int) wsp_x;
            int y = (int) (wsp_y1 * 1);

            g.drawImage(Gra.grafiki.grafikaTla[2], 0, 0, gra);
            g.setColor(Color.white);
            g.setFont(czcionka2);
            g.drawString("NICK :     PUNKTY :", x, 165 + y);
            g.drawString("Powrót", 80 + (int) wsp_xx, 465 + (int) (wsp_y1 * 3.1));
            for (int i = 0; i < Gracze.size(); i++) {

                g.setFont(czcionka3);
                g.setColor(Color.white);
                String wyswietl = Gracze.get(i).wypiszDane();
                g.drawString(wyswietl, x, 195 + y + 20 * i);

            }
        }
    }


    /**
     *  Metoda odpowiedzalna za pobranie wynikow z pliku i dodanie ich do listy
     */

    public void pobierzGraczy() {
        try {
            Gracze.clear();
            File plik = new File("ress/Wyniki.txt");
            Scanner odczyt = new Scanner(plik);
            while (odczyt.hasNextLine()) {
                String name = odczyt.nextLine();
                int punkty = Integer.parseInt(odczyt.nextLine());
                Rekord player = new Rekord(punkty, name);
                Gracze.add(player);




            }
            Komparator komp = new Komparator();
            //sortujemy tablicę przy użyciu komparatora
            Gracze.sort(komp);


        } catch (FileNotFoundException a) {
            System.out.println("Plik nie istnieje");
        }
    }
}


