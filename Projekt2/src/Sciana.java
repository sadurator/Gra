
import java.awt.*;
import java.util.LinkedList;

/**
 *
 * Klasa tworząca sciany, ktore ograniczaja sciezke
 */
public class Sciana extends ObiektGry {

    private Handler handler;

    public Sciana(int x, int y, ID id, Gra gierka, Handler handler) {
        super(x, y, id, gierka);
        this.handler = handler;
    }

    public void tick(LinkedList<ObiektGry> object) {
        super.tick();
        if(handler.getPause()==false){
            speed+=2;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y+speed, 100 * gra.okienko.okno.getWidth() / gra.szerokosc, 100 * gra.okienko.okno.getHeight() / gra.wysokosc);
    }

    /**
     * Metoda generujaca grafike scian
     */
    public void render(Graphics g) {
        g.drawImage(grafiki.grafikaSciana[0], x, y+speed, 100 * gra.okienko.okno.getWidth() / gra.szerokosc, 100 * gra.okienko.okno.getHeight() / gra.wysokosc, null);
    }
}
