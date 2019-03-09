import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Klasa odpowiedzialna za obsluge grafiki w grze
 */

public class Grafika {

    private BufferedImage gracz = null;
    private BufferedImage sciana = null;
    private BufferedImage portal = null;
    private BufferedImage przeszkoda = null;
    private BufferedImage bonus = null;
    private BufferedImage tloMenu = null;
    private BufferedImage tloPomoc = null;
    private BufferedImage tloWyniki = null;
    private BufferedImage apteczka = null;

    /** Lista typu Array przechowywujaca dane z pliku konfiguracyjnego sterujacego dzialaniem programu*/
    private static final ArrayList<String> Konfiguracja = Gra.dajDostepKonfiguracji();

    /** Tablice grafik posiadajacych grafiki obiektow gry */
    public final Image[] grafikaPortal = new java.awt.Image[1];
    public final Image[] grafikaGracz = new java.awt.Image[1];
    public final Image[] grafikaPrzeszkoda = new java.awt.Image[1];
    public final Image[] grafikaSciana = new java.awt.Image[1];
    public final Image[] grafikaBonusy = new java.awt.Image[2];
    public final Image[] grafikaTla = new java.awt.Image[3];
    public final Image[] grafikaApteczki = new java.awt.Image[1];


    public final HUD hud;
    private BufferedImage obrazek;
    /**
     * Konstruktor klasy Grafika, zawiera grafiki uzywane w programie
     */

    public Grafika(HUD hudd){
        this.hud=hudd;


        try{
            sciana = zaladujobraz(Konfiguracja.get(4)); //sciana.png
            portal = zaladujobraz(Konfiguracja.get(5)); //portal.png
            gracz = zaladujobraz(Konfiguracja.get(6)); //gracz.png
            przeszkoda = zaladujobraz(Konfiguracja.get(7)); //przeszkoda.png
            bonus = zaladujobraz(Konfiguracja.get(8)); //bonus.png
            tloMenu = zaladujobraz(Konfiguracja.get(3));//tlo menu.png
            tloPomoc = zaladujobraz(Konfiguracja.get(1));//tlo pomoc.png
            tloWyniki =  zaladujobraz(Konfiguracja.get(2));//tlo wyniki.png
            apteczka = zaladujobraz(Konfiguracja.get(9));//apteczka.png

        }catch(Exception e){
            e.printStackTrace();
        }
        zwrocObraz();
    }

    /**
     * Metoda ustawiajaca dane grafiki w tablicach poszczegolnych elementow, dodatkowo skalujaca.
     */
    public void zwrocObraz() {
        grafikaSciana[0] = sciana;
        grafikaPortal[0] = portal.getScaledInstance(82, 82, Image.SCALE_DEFAULT);
        grafikaGracz[0] = gracz.getScaledInstance(48, 48, Image.SCALE_DEFAULT);
        grafikaPrzeszkoda[0] = przeszkoda.getScaledInstance(84, 84, Image.SCALE_DEFAULT);
        grafikaBonusy[1] = bonus.getScaledInstance(32, 32, Image.SCALE_DEFAULT);
        grafikaTla[0] = tloMenu.getScaledInstance(hud.wymiarOknaX, hud.wymiarOknaY, Image.SCALE_DEFAULT);
        grafikaTla[1] = tloPomoc.getScaledInstance(hud.wymiarOknaX, hud.wymiarOknaY, Image.SCALE_DEFAULT);
        grafikaTla[2] = tloWyniki.getScaledInstance(hud.wymiarOknaX, hud.wymiarOknaY, Image.SCALE_DEFAULT);
        grafikaApteczki[0] = apteczka.getScaledInstance(hud.wymiarOknaX, hud.wymiarOknaY, Image.SCALE_DEFAULT);
    }

    /**
     * Metoda odpowiedzialna za zaladowanie odpowiedniej grafiki
     */

    public BufferedImage zaladujobraz(String path){
        try {
            obrazek = ImageIO.read(getClass().getResourceAsStream(path));
        }catch (IOException e){

            e.printStackTrace();
        }
        return obrazek;
    }



}

