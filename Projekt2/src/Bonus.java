
import java.awt.*;
import java.util.LinkedList;

/**
 * Klasa tworzaca bonus, po najechaniu na ktory dodawane sa punkty
 */

public class Bonus extends ObiektGry {

    private Handler handler;
    /**
     * Konstruktor klasy Bonus
     */

    public Bonus(int x, int y, ID id, Gra gierka, Handler handler) {

        super(x, y, id,gierka);
        this.handler=handler;
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
        g.drawImage(grafiki.grafikaBonusy[1],x,y+speed,32*gra.okienko.okno.getWidth()/gra.szerokosc,32*gra.okienko.okno.getHeight()/gra.wysokosc,null);
    }
}


