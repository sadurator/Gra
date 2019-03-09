
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.*;

/**
 * Klasa odpowedzialna za okno na ktorym wyswietlana jest gra
 */
public class OknoGry extends Canvas {

    JFrame okno;
    /**
     * Konstruktor klasy OknoGry
     */

    public OknoGry(int width, int height , String title, Gra gra) {

        okno = new JFrame("Sciezka");
        okno.setPreferredSize(new Dimension(width,height));
        okno.setMaximumSize(new Dimension(width,height));
        okno.setMinimumSize(new Dimension(width,height));

        okno.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        okno.setLocationRelativeTo(null);
        okno.add(gra);
        okno.setVisible(true);

        okno.getContentPane().addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                Component c = (Component) e.getSource();
                okno.setTitle("W: " + c.getWidth() + "H: " + c.getHeight());
                gra.hud.wymiarOknaX = c.getWidth();
                gra.hud.wymiarOknaY = c.getHeight();
                Gra.grafiki.hud.wymiarOknaX = c.getWidth();
                Gra.grafiki.hud.wymiarOknaY = c.getHeight();
                Gra.grafiki.zwrocObraz();

            }
        });

        gra.start();
    }
}
