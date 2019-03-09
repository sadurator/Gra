
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.LinkedList;


/**
 * Klasa tworzaca obiekt ktorym poruszamy sie po planszy probujac dotrzec do portalu
 */
public class Gracz extends ObiektGry {


    public int tempHeight;
    Ellipse2D Eclipse;
    private Handler handler;

    /**
     * Konstruktor klasy Gracz
     */

    public Gracz(int x, int y, Handler handler, ID id, float grav, Gra gierka) {
        super(x, y, id,gierka);
        this.handler = handler;

        tempHeight=gra.wysokosc;
        tempWidth=gra.szerokosc;

    }

    public void tick(LinkedList<ObiektGry> object) {
        super.tick();
        Eclipse = new Ellipse2D.Float(x, y + speed, 32*gra.okienko.okno.getWidth()/gra.szerokosc,32*gra.okienko.okno.getHeight()/gra.wysokosc);


        if(handler.getPause()==false) {
            y += predkoscY;
            x += predkoscX;
            speed+=2;
        }
        collision(object);
    }


    public Rectangle getBounds(){
        return null;
    }

    /**
     * Metoda ta odpowiada za reakcje programu na zderzenia gracza z pozostałymi elementami gry
     */
    private void collision(LinkedList<ObiektGry> object) {

        for (int i = 0; i < handler.object.size(); i++) {
            ObiektGry tempObject = handler.object.get(i);

            if (tempObject.zwrocID() == ID.Wall) {
                if (Eclipse.intersects(tempObject.getBounds())) {
                    predkoscX =0;
                    predkoscY =0;
                    y = tempObject.zwrocY() - y-1;
                    x = tempObject.zwrocX() - x;
                    handler.gierka.hud.health = handler.gierka.hud.health-1;
                    handler.ZmienPoziom();
                    break;
                }
            }

            if (tempObject.zwrocID() == ID.Portal) {
                if (Eclipse.intersects(tempObject.getBounds())) {
                    handler.gierka.hud.LEVEL = handler.gierka.hud.LEVEL+1;
                    handler.ZmienPoziom();
                    break;
                }
            }
            if (tempObject.zwrocID() == ID.Obstacle) {
                if (Eclipse.intersects(tempObject.getBounds())) {
                    handler.gierka.hud.health = handler.gierka.hud.health-1;
                    handler.usunObiekt(tempObject);
                    if(handler.gierka.hud.health < 1)
                        handler.ZmienPoziom();
                    break;
                }
            }
            if (tempObject.zwrocID() == ID.Bonus) {
                if (Eclipse.intersects(tempObject.getBounds())) {
                    handler.gierka.hud.points = handler.gierka.hud.points + 500 ;
                    handler.usunObiekt(tempObject);
                    break;
                }
            }
            if (tempObject.zwrocID() == ID.Apteczka){
                if(Eclipse.intersects(tempObject.getBounds())){
                    handler.gierka.hud.health = handler.gierka.hud.health+1;
                    handler.usunObiekt(tempObject);
                    break;
                }
            }

            if (Gracz.this.y > gra.okienko.okno.getHeight() - speed) {
                handler.gierka.hud.health = handler.gierka.hud.health - 1;
                handler.ZmienPoziom();
                break;
            }
        }
    }

    public void render(Graphics g) {

        g.drawImage(grafiki.grafikaGracz[0],x,y + speed,32*gra.okienko.okno.getWidth()/gra.szerokosc,32*gra.okienko.okno.getHeight()/gra.wysokosc,null);

    }
}
