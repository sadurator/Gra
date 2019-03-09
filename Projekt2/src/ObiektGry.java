
import java.awt.*;
import java.util.LinkedList;

/**
 * Klasa po ktorej dziedzicza wszystkie elementy gry
 */
public abstract class ObiektGry {


    protected final Gra gra;
    public int tempWidth;
    public int tempHeight;
    public int gravity;
    public int speed=0;
    /**
     * Zmienne zawierajace położenie x i y elementu aktywnego
     */
    public int x, y;
    /**
     * Zmienna typu enum określająca typ danego elementu
     */
    protected ID id;
    /**
     * Zmiennie określające prędkości pionowe i poziome elementu aktywnego
     */
    protected float predkoscX = 0;
    protected float predkoscY = 0;


    /**
     * otrzymujemy dostęp do grafik potrzebnych pry generowaniu elementów aktywnych
     */
    Grafika grafiki = Gra.dajDostepGrafik();

    /**
     * Konstruktor obiektu aktywnego gry
     */
    public ObiektGry(int x, int y, ID id, Gra gierka) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.gra=gierka;
        tempHeight=gierka.wysokosc;
        tempWidth=gierka.szerokosc;
    }

    /**
     * metoda abstrakcyjna odpowiadajaca za odswiezanie obrazu gry
     * @param object lista wszystkich obiektow gry
     */
    public abstract void tick(LinkedList<ObiektGry> object);

    /**
     *Metoda odpowiedzialna za skalowanie ktore dziedzicza wszystkie obiekty gry
     */
    public void tick(){
        boolean scale = false;
        if(tempWidth != gra.okienko.okno.getWidth() || tempHeight != gra.okienko.okno.getHeight()) {
            scale = true;
            // System.out.println("scale");
        }
        if(scale) {
            x=x*gra.okienko.okno.getWidth()/tempWidth;
            y=y*gra.okienko.okno.getHeight()/tempHeight;

            tempWidth=gra.okienko.okno.getWidth();
            tempHeight=gra.okienko.okno.getHeight();
        }
    }

    /**
     * Metoda abstrakcyjna odpowiadająca za wygenerowanie grafiki obiektów typu ObiektGry czyli graczy,przeszkod jak i wszelkich innnych elementow aktywnych
     */
    public abstract void render(Graphics g);

    /**
     * Okresla granice danego obiektu
     */
    public abstract Rectangle getBounds();




    /**
     * Metoda zwracajaca zmienna prywatna polozenia poziomego (x) obiektu dziedziczacego po ObiektGry
     */
    public int zwrocX() {
        return this.x;
    }

    /**
     * Metoda zwracajaca zmienna prywatna polozenia pionowego (y) obiektu dziedziczacego po ObiektGry
     */
    public int zwrocY() {
        return this.y;
    }



    /**
     * Metoda zwracajaca zmienna prywatna okreslajaca typ ( ID ) obiektu dziedziczacego po ObiektGry
     */
    public ID zwrocID() {
        return this.id;
    }

    /**
     * Metoda ustawiajaca zmienna prywatna okreslającą predkosc w poziomie ( predkosc_x ) obiektu dziedziczacego po ObiektGry
     */
    public void ustawPredkoscX(float x) {
        this.predkoscX = x;
    }

    /**
     * Metoda ustawiajaca zmienna prywatna okreslajaca predkosc w pionie ( predkosc_y ) obiektu dziedziczacego po ObiektGry
     */
    public void ustawPredkoscY(float y) {
        this.predkoscY = y;
    }


}
