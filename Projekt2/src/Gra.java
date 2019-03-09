

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * W tej klasie zostaja wywolane wszystkie najwazniejsze klasy tworzace rozgrywke
 */

public class Gra extends Canvas implements Runnable {

    /**
     * Zmienna przechowywująca szerokość ( pixele ) okna rozgrywki
     *
     */
    protected final int szerokosc = 900;
    /**
     * Zmienna przechowywująca wysokość ( pixele ) okna rozgrywki
     */
    protected final int wysokosc = 643;
    /**
     * Obiekt wykorzystywany w jądrze silnika gry
     */
    private Thread thread;
    /**
     * Zmienna określająca stan pracy gry
     */
    private boolean dzialanie = false;
    /**
     * Lista typu Array przechowywująca dane z pliku konfiguracyjnego sterującego dzialaniem programu
     */
    static public final ArrayList<String> konfiguracja = new ArrayList<>();



    /**
     * Handler to coś w rodzaju wskazania dzięki ktoremu będziemy mogli weryfikować zachowania elementów
     */
    private Handler handler;
    /**
     * Zmienna typu Grafika trzymająca niezbędne tekstury graficzne do pracy programu
     */
    static Grafika grafiki;
    private final Menu menu;
    public STAN stanGry = STAN.Menu;
    public final HUD hud;
    public final OknoGry okienko;

    /**
     * Konstruktor Gry, startujący całe działanie programu
     */
    public Gra() {


        zaladujPlikKonfiguracyjny();
        handler = new Handler(this);
        menu = new Menu(this,handler);
        this.addKeyListener(new Sterowanie(handler));
        this.addMouseListener(menu);

        hud = new HUD(szerokosc,wysokosc);
        grafiki = new Grafika(hud);


        okienko = new OknoGry(szerokosc, wysokosc, "Scieżka", this);

    }

    /**
     * Metoda startująca
     */
    public synchronized void start() {
        dzialanie = true;
        thread = new Thread(this);
        thread.start();
    }



    /**
     * Metoda odpowiadająca za działanie systemu gry, aktywowana wraz z utworzeniem Okna_Gry, mechanizm ten jest jedynm z najpopularniejszym z występujących w tego typu projektach
     */
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while (dzialanie) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                //    System.out.println("FPS: " + frames + " TICKS: " + updates + " Zycia :" + hud.health + " PUNKTY : " + hud.points + " W.X : " + okienko.okno.getWidth() +" W.Y : " + okienko.okno.getHeight());
                frames = 0;
                updates = 0;
            }
        }
    }

    private void tick() {
        handler.tick();
        if(stanGry ==STAN.Rozgrywka){

        }else if(stanGry == STAN.Menu){
            menu.tick();
        }
    }

    /**
     * Metoda odpowiadajaca za grafike okna gry
     */
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(2);
            return;
        }
        Graphics graph;
        graph = bs.getDrawGraphics();

        handler.render(graph);
        if(stanGry ==STAN.Rozgrywka){

        }else if(stanGry == STAN.Menu){
            menu.render(graph);
        }else if( stanGry == STAN.Pomoc){
            menu.render(graph);
        }else if( stanGry == STAN.Wyniki) {
            menu.render(graph);
        }
        graph.dispose();
        bs.show();
    }

    /**
     * Metoda zwracajaca plik graficzny w celu wykorzystania grafik w innych klasach, przeważnie w klasach obiektów aktywnych dziedziczących po ObiektGry
     */
    public static Grafika dajDostepGrafik() {
        return grafiki;
    }



    /**
     * Metoda wczytujaca sciezki do plikow graficznych oraz konfiguracyjnych poziomow gry
     *
     */
    private void zaladujPlikKonfiguracyjny() {
        try {
            File plik = new File("ress/Configuration.txt");
            Scanner odczyt = new Scanner(plik);
            while (odczyt.hasNextLine()) {
                String odczytane = odczyt.nextLine();
                konfiguracja.add(odczytane);
            }
        }catch(Exception e){

            e.printStackTrace();
        }
    }
    /**
     * Metoda zwracajaca listw przechowujaca sciezki do plikow
     */
    public static ArrayList<String> dajDostepKonfiguracji(){
        return konfiguracja;
    }




}