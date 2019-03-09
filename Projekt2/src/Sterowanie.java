
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Klasa obsugujaca reakcje programu na nacisniecie przyciskow klawiatury
 */
public class Sterowanie extends KeyAdapter {
    private Handler handler;
    public Sterowanie(Handler handler){
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        for(int  i = 0; i < handler.object.size(); i++){
            ObiektGry tempObject = handler.object.get(i);

            if (tempObject.zwrocID() == ID.Player){
                if (key == KeyEvent.VK_W) tempObject.ustawPredkoscY(-3);
                if (key == KeyEvent.VK_S) tempObject.ustawPredkoscY(6);
                if (key == KeyEvent.VK_D) tempObject.ustawPredkoscX(3);
                if (key == KeyEvent.VK_A) tempObject.ustawPredkoscX(-3);
                if (key == KeyEvent.VK_P) {
                    if (handler.getPause() == false) {
                        handler.setPause(true);
                        return;
                    }
                    if (handler.getPause()==true){
                        handler.setPause(false);
                        return;
                    }
                }
            }
        }

        if(key == KeyEvent.VK_ESCAPE)
            System.exit(1);
    }


    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        for(int  i = 0; i < handler.object.size(); i++){
            ObiektGry tempObject = handler.object.get(i);
            if (tempObject.zwrocID() == ID.Player){

                if (key == KeyEvent.VK_W) tempObject.ustawPredkoscY(0);
                if (key == KeyEvent.VK_S) tempObject.ustawPredkoscY(0);
                if (key == KeyEvent.VK_D) tempObject.ustawPredkoscX(0);
                if (key == KeyEvent.VK_A) tempObject.ustawPredkoscX(0);
            }
        }
    }
}


