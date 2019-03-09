
import java.awt.*;
import java.util.LinkedList;

/**
 * Klasa tworzaca portal po najechaniu na ktory gracz przechodzi na nastepny poziom gry
 */
public class Portal extends ObiektGry {

    private Handler handler;

    /**
     * Konstruktor klasy portal
     */


    public Portal(int x, int y, ID id,Gra gierka,Handler handler) {
        super(x, y, id,gierka);
        this.handler = handler;
    }

    public void tick(LinkedList<ObiektGry> object) {
        super.tick();
        if(handler.getPause()==false) {
            speed += 2;
        }
    }

    public Rectangle getBounds(){
        return new Rectangle(x +16, y +16+speed, 48,48);
    }


    public void render(Graphics g) {
        g.drawImage(grafiki.grafikaPortal[0],x,y+speed,64*gra.okienko.okno.getWidth()/gra.szerokosc,64*gra.okienko.okno.getHeight()/gra.wysokosc,null);
    }
}
