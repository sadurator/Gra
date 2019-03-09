
import java.awt.*;
import java.util.LinkedList;

/**
 * Klasa tworzaca przeszkode, po najechaniu na przeszkode gracz traci zycie
 */

public class Przeszkoda extends ObiektGry {
    private Handler handler;
    /**
     * Konstruktor klasy przeszkoda
     */
    public Przeszkoda(int x, int y, ID id, Gra gierka, Handler handler) {

        super(x, y, id, gierka);
        this.handler = handler;
    }
    public void tick(LinkedList<ObiektGry> object) {
        super.tick();
        if(handler.getPause()==false) {
            speed += 2;
        }
    }
    public Rectangle getBounds(){
        return new Rectangle(x, y+speed, 32*gra.okienko.okno.getWidth()/gra.szerokosc,32*gra.okienko.okno.getHeight()/gra.wysokosc);
    }

    public void render(Graphics g) {
        g.drawImage(grafiki.grafikaPrzeszkoda[0],x,y+speed,32*gra.okienko.okno.getWidth()/gra.szerokosc,32*gra.okienko.okno.getHeight()/gra.wysokosc,null);
    }
}


