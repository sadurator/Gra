
import java.util.Comparator;

/**
 * Komparator do generowania tablicy wyników.
 */

public class Komparator implements Comparator<Rekord> {
    @Override
    public int compare(Rekord g1, Rekord g2) {
        if(g2 == null) return 1;
        return Integer.compare(g2.zwrocPunkty(), g1.zwrocPunkty());
    }
}


