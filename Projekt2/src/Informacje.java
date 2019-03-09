
import java.awt.*;
import java.util.LinkedList;


/**
 * Klasa odpowiedzialna za panel na ktorym wyswietlane sa punkty, zycie i poziom
 */
public class Informacje extends ObiektGry {



    public Informacje(int x, int y, ID id, Gra gierka) {
        super(x, y, id, gierka);
    }

    public void tick(LinkedList<ObiektGry> object) {
        super.tick();
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, 32*gra.okienko.okno.getWidth()/gra.szerokosc,32*gra.okienko.okno.getHeight()/gra.wysokosc);
    }

    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.drawImage(grafiki.grafikaBonusy[0],x,y,84*gra.okienko.okno.getWidth()/gra.szerokosc,84*gra.okienko.okno.getHeight()/gra.wysokosc,null);
        Font czcionka2 = new Font("arial", Font.BOLD, 20);
        g.setFont(czcionka2);
        g.drawString("PUNKTY : " + gra.hud.points,50*gra.okienko.okno.getWidth()/gra.szerokosc,600*gra.okienko.okno.getHeight()/gra.wysokosc);
        if(gra.hud.health < 2) {
            g.setColor(Color.RED);
            g.drawString("ZYCIA : " + gra.hud.health,250*gra.okienko.okno.getWidth()/gra.szerokosc,600*gra.okienko.okno.getHeight()/gra.wysokosc);
        }else {
            g.drawString("ZYCIA : " + gra.hud.health, 250 * gra.okienko.okno.getWidth() / gra.szerokosc, 600 * gra.okienko.okno.getHeight() / gra.wysokosc);
        }
        g.drawString("POZIOM : " + gra.hud.LEVEL, 375*gra.okienko.okno.getWidth()/gra.szerokosc,600*gra.okienko.okno.getHeight()/gra.wysokosc);

    }
}


