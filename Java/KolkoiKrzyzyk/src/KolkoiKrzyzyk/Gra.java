/* This code is a window application that shows an extended version of the tic-tac-toe game.
The application consists of a menu where you can select the size of the map, save and load the game.
 */

package KolkoiKrzyzyk;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Gra extends JFrame implements ActionListener {

    // utworzenie wszystkich niezbednych pol i przyciskow do programu
    // oraz zmiennej n, mowiacej o ilosci kolumn i wierszy
    int n;
    JButton[][] przyciski = new JButton[7][7];
    Random random = new Random();
    JFrame frame = new JFrame();
    JTextField tytul = new JTextField();
    JButton nowa_gra = new JButton();
    JButton zapisz_gre = new JButton();
    JButton wczytaj_gre = new JButton();
    JButton wybierz_rozmiar_mapy = new JButton();
    JButton trzy_na_trzy = new JButton();
    JButton cztery_na_cztery = new JButton();
    JButton piec_na_piec = new JButton();
    JButton wyjdz_z_gry = new JButton();
    JButton o_programie = new JButton();
    JTextArea o_tworcach = new JTextArea();
    JButton cofnij = new JButton();
    boolean kolejgracza1;

    /**
     * Metoda Menu przechowuje wszystkie przyciski i stanowi menu glowne dla uruchamianej gry
     */
    Gra() {

        // ustawienie layoutu
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(615, 785);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(null);
        frame.setResizable(false);

        // ustawienie pola tytul oraz umiejscowienie go w layoutcie
        tytul.setBounds(0, 0, 615, 100);
        tytul.setVisible(true);
        tytul.setBackground(new Color(25, 25, 25));
        tytul.setForeground(new Color(25, 255, 0));
        tytul.setFont(new Font("Ink Free", Font.BOLD, 60));
        tytul.setBorder(BorderFactory.createBevelBorder(1));
        tytul.setHorizontalAlignment(JTextField.CENTER);
        tytul.setEditable(false);
        tytul.setText("Kolko i Krzyzyk");

        // dodanie tytulu do layoutu i ustawienie go jako widoczne
        frame.add(tytul);
        frame.setVisible(true);

        // ustawienie przycisku zacznij_gre oraz umiejscowienie go w layoutcie
        nowa_gra.setBounds(0,275,615,50);
        nowa_gra.addActionListener(this);
        nowa_gra.setBackground(new Color(25,25,25));
        nowa_gra.setForeground(new Color(25,255,0));
        nowa_gra.setFont(new Font("Ink Free",Font.BOLD,30));
        nowa_gra.setBorder(BorderFactory.createBevelBorder(1));
        nowa_gra.setHorizontalAlignment(JTextField.CENTER);
        nowa_gra.setText("Nowa gra");
        nowa_gra.setFocusable(false);
        nowa_gra.setVisible(true);
        nowa_gra.setEnabled(true);

        // ustawienie przycisku wczytaj_gre oraz umiejscowienie go w layoutcie
        wczytaj_gre.setBounds(0,345,615,50);
        wczytaj_gre.addActionListener(this);
        wczytaj_gre.setBackground(new Color(25,25,25));
        wczytaj_gre.setForeground(new Color(25,255,0));
        wczytaj_gre.setFont(new Font("Ink Free",Font.BOLD,30));
        wczytaj_gre.setBorder(BorderFactory.createBevelBorder(1));
        wczytaj_gre.setHorizontalAlignment(JTextField.CENTER);
        wczytaj_gre.setText("Wczytaj gre");
        wczytaj_gre.setFocusable(false);
        wczytaj_gre.setVisible(true);
        wczytaj_gre.setEnabled(true);

        // ustawienie przycisku wyjdz_z_gry oraz umiejscowienie go w layoutcie
        wyjdz_z_gry.setBounds(0,415,615,50);
        wyjdz_z_gry.addActionListener(this);
        wyjdz_z_gry.setBackground(new Color(25,25,25));
        wyjdz_z_gry.setForeground(new Color(25,255,0));
        wyjdz_z_gry.setFont(new Font("Ink Free",Font.BOLD,30));
        wyjdz_z_gry.setBorder(BorderFactory.createBevelBorder(1));
        wyjdz_z_gry.setHorizontalAlignment(JTextField.CENTER);
        wyjdz_z_gry.setText("Wyjdz z gry");
        wyjdz_z_gry.setFocusable(false);
        wyjdz_z_gry.setVisible(true);
        wyjdz_z_gry.setEnabled(true);

        // ustawienie przycisku cofnij oraz umiejscowienie go w layoutcie
        cofnij.setBounds(0,100,100,50);
        cofnij.addActionListener(this);
        cofnij.setBackground(new Color(25,25,25));
        cofnij.setForeground(new Color(25,255,0));
        cofnij.setFont(new Font("Ink Free",Font.BOLD,30));
        cofnij.setBorder(BorderFactory.createBevelBorder(1));
        cofnij.setHorizontalAlignment(JTextField.CENTER);
        cofnij.setText("Cofnij");
        cofnij.setFocusable(false);
        cofnij.setVisible(false);

        // ustawienie przycisku tworcy oraz umiejscowienie go w layoutcie
        o_programie.setBounds(0,485,615,50);
        o_programie.addActionListener(this);
        o_programie.setBackground(new Color(25,25,25));
        o_programie.setForeground(new Color(25,255,0));
        o_programie.setFont(new Font("Ink Free",Font.BOLD,30));
        o_programie.setBorder(BorderFactory.createBevelBorder(1));
        o_programie.setHorizontalAlignment(JTextField.CENTER);
        o_programie.setText("O programie...");
        o_programie.setFocusable(false);
        o_programie.setVisible(true);
        o_programie.setEnabled(true);

        // ustawienie przycisku o_tworcach oraz umiejscowienie go w layoutcie
        o_tworcach.setBounds(0,205,615,400);
        o_tworcach.setBackground(new Color(25, 25, 25));
        o_tworcach.setForeground(new Color(25, 255, 0));
        o_tworcach.setFont(new Font("Ink Free",Font.BOLD,30));
        o_tworcach.setBorder(BorderFactory.createBevelBorder(1));
        o_tworcach.setFocusable(false);
        o_tworcach.setVisible(false);
        o_tworcach.setEnabled(false);

        // ustawienie przycisku wybierz_rozmiar_mapy oraz umiejscowienie go w layoutcie
        wybierz_rozmiar_mapy.setBounds(0,225,615,70);
        wybierz_rozmiar_mapy.addActionListener(this);
        wybierz_rozmiar_mapy.setBackground(new Color(25,25,25));
        wybierz_rozmiar_mapy.setForeground(new Color(25,255,0));
        wybierz_rozmiar_mapy.setFont(new Font("Ink Free",Font.BOLD,30));
        wybierz_rozmiar_mapy.setBorder(BorderFactory.createBevelBorder(1));
        wybierz_rozmiar_mapy.setHorizontalAlignment(JTextField.CENTER);
        wybierz_rozmiar_mapy.setText("Wybierz rozmiar mapy");
        wybierz_rozmiar_mapy.setFocusable(false);
        wybierz_rozmiar_mapy.setVisible(false);

        // ustawienie przycisku zapisz_gre oraz umiejscowienie go w layoutcie
        zapisz_gre.setBounds(100,100,515,50);
        zapisz_gre.addActionListener(this);
        zapisz_gre.setBackground(new Color(25,25,25));
        zapisz_gre.setForeground(new Color(25,255,0));
        zapisz_gre.setFont(new Font("Ink Free",Font.BOLD,30));
        zapisz_gre.setBorder(BorderFactory.createBevelBorder(1));
        zapisz_gre.setHorizontalAlignment(JTextField.CENTER);
        zapisz_gre.setText("Zapisz gre");
        zapisz_gre.setFocusable(false);
        zapisz_gre.setVisible(false);
        zapisz_gre.setEnabled(false);

        // ustawienie przycisku trzy_na_trzy oraz umiejscowienie go w layoutcie
        trzy_na_trzy.setBounds(0,345,615,50);
        trzy_na_trzy.addActionListener(this);
        trzy_na_trzy.setBackground(new Color(25,25,25));
        trzy_na_trzy.setForeground(new Color(25,255,0));
        trzy_na_trzy.setFont(new Font("Ink Free",Font.BOLD,30));
        trzy_na_trzy.setBorder(BorderFactory.createBevelBorder(1));
        trzy_na_trzy.setHorizontalAlignment(JTextField.CENTER);
        trzy_na_trzy.setText("3x3");
        trzy_na_trzy.setFocusable(false);
        trzy_na_trzy.setVisible(false);
        trzy_na_trzy.setEnabled(false);

        // ustawienie przycisku cztery_na_cztery oraz umiejscowienie go w layoutcie
        cztery_na_cztery.setBounds(0,405,615,50);
        cztery_na_cztery.addActionListener(this);
        cztery_na_cztery.setBackground(new Color(25,25,25));
        cztery_na_cztery.setForeground(new Color(25,255,0));
        cztery_na_cztery.setFont(new Font("Ink Free",Font.BOLD,30));
        cztery_na_cztery.setBorder(BorderFactory.createBevelBorder(1));
        cztery_na_cztery.setHorizontalAlignment(JTextField.CENTER);
        cztery_na_cztery.setText("4x4");
        cztery_na_cztery.setFocusable(false);
        cztery_na_cztery.setVisible(false);
        cztery_na_cztery.setEnabled(false);

        // ustawienie przycisku piec_na_piec oraz umiejscowienie go w layoutcie
        piec_na_piec.setBounds(0,465,615,50);
        piec_na_piec.addActionListener(this);
        piec_na_piec.setBackground(new Color(25,25,25));
        piec_na_piec.setForeground(new Color(25,255,0));
        piec_na_piec.setFont(new Font("Ink Free",Font.BOLD,30));
        piec_na_piec.setBorder(BorderFactory.createBevelBorder(1));
        piec_na_piec.setHorizontalAlignment(JTextField.CENTER);
        piec_na_piec.setText("5x5");
        piec_na_piec.setFocusable(false);
        piec_na_piec.setVisible(false);
        piec_na_piec.setEnabled(false);

        // dodanie wszystkich przyciskow do layoutu
        frame.add(nowa_gra);
        frame.add(wczytaj_gre);
        frame.add(wybierz_rozmiar_mapy);
        frame.add(wyjdz_z_gry);
        frame.add(trzy_na_trzy);
        frame.add(cztery_na_cztery);
        frame.add(piec_na_piec);
        frame.add(o_programie);
        frame.add(o_tworcach);
        frame.add(cofnij);
        frame.add(zapisz_gre);
    }

    /**
     * Metoda pozwala losowo wyznaczyc ktory z graczy (X czy O) zacznie jako pierwszy
     * oraz wyswietla odpowiedni komunikat w layoutcie
     */
    public void kto_zaczyna(){
        if(random.nextInt(2)==0){
            kolejgracza1=true;
            tytul.setText("Kolej X");
        }
        else{
            kolejgracza1 = false;
            tytul.setText("Kolej O");
        }

    }

    /**
     * Metoda wykorzystuje zmienna x (ilosc kolumn i wierszy),mowiaca o wielkosci tablicy
     * w celu utworzenia odpowiedniej ilosci przyciskow i umiejcowieniu ich w okreslony
     * sposob w layoutcie
     *
     * @param x -umozliwia metodzie utworzenie tablicy o wielkosci x wierszy na x kolumn
     */
    public void umiejscowienie_przyciskow(int x){
            for(int a=0;a<x;a++) {
                for(int b=0;b<x;b++) {
                    przyciski[a][b] = new JButton();
                    frame.add(przyciski[a][b]);
                    przyciski[a][b].setFont(new Font("MV Boli", Font.BOLD, 100));
                    przyciski[a][b].setBounds(b*600/x, 150+(a*600/x), 600 / x, 600 / x);
                    przyciski[a][b].setHorizontalAlignment(JTextField.CENTER);
                    przyciski[a][b].setFocusable(false);
                    przyciski[a][b].addActionListener(this);
                    przyciski[a][b].setVisible(true);
                }
            }
    }

    /**
     * Metoda pozwala sprawdzic czy wygrywa gracz X czy gracz O lub czy dochodzi do remisu
     *
     * @param x -pozwala przeanalizowac tablice przyciskow o wymiarach x kolumn na x wierszy
     */
    public void sprawdzenie_kto_wygral(int x){

        // Utworzenie zmiennych, ktore dadza informacje czy doszlo do zwyciestwa gracza X,
        // gracza O, czy do remisu
        int R=0;
        int ilosc_pustych_pol=0;
        int X=0;
        int O=0;

        // Sprawdzenie kolumn pionowych dla X oraz O

        for(int a=0;a<x;a++) {
            for (int b=0; b<x; b++) {
                if(przyciski[b][a].getText().contains("X")){
                    X++;

                    // Pokolorowanie zwycieskich pol na zielono
                    if(X==x){
                        for(int z=b;z>=0;z--){
                            przyciski[z][a].setBackground(Color.GREEN);
                        }
                    }
                }
                if(przyciski[b][a].getText().contains("O")){
                    O++;

                    // Pokolorowanie zwycieskich pol na zielono
                    if(O==x){
                        for(int z=b;z>=0;z--){
                            przyciski[z][a].setBackground(Color.GREEN);
                        }
                    }
                }
            }
            if(X==x){
                zwyciestwo_lub_remis(x);
                tytul.setText("Wygral X");
                R++;
            }
            else {
                X=0;
            }
            if(O==x){
                zwyciestwo_lub_remis(x);
                tytul.setText("Wygral O");
                R++;
            }
            else {
                O=0;
            }
        }

        // Sprawdzenie kolumn poziomych dla X oraz O

        for(int a=0;a<x;a++) {
            for (int b=0; b<x; b++) {
                if(przyciski[a][b].getText().contains("X")){
                    X++;

                    // Pokolorowanie zwycieskich pol na zielono
                    if(X==x){
                        for(int z=b;z>=0;z--){
                            przyciski[a][z].setBackground(Color.GREEN);
                        }
                    }
                }
                if(przyciski[a][b].getText().contains("O")){
                    O++;
                    // Pokolorowanie zwycieskich pol na zielono
                    if(O==x){
                        for(int z=b;z>=0;z--){
                            przyciski[a][z].setBackground(Color.GREEN);
                        }
                    }
                }
            }
            if(X==x){
                zwyciestwo_lub_remis(x);
                tytul.setText("Wygral X");
                R++;
            }
            else X=0;
            if(O==x){
                zwyciestwo_lub_remis(x);
                tytul.setText("Wygral O");
                R++;
            }
            else O=0;
        }

        // Sprawdzenie po ukosie dla X oraz O (od lewej do prawej)

        for (int b=0; b<x; b++) {
            if(przyciski[b][b].getText().contains("X")){
                X++;

                // Pokolorowanie zwycieskich pol na zielono
                if(X==x){
                    for(int z=b;z>=0;z--){
                        przyciski[z][z].setBackground(Color.GREEN);
                    }
                }
            }
            if(przyciski[b][b].getText().contains("O")){
                O++;

                // Pokolorowanie zwycieskich pol na zielono
                if(O==x){
                    for(int z=b;z>=0;z--){
                        przyciski[z][z].setBackground(Color.GREEN);
                    }
                }
            }
        }
        if(X==x){
            zwyciestwo_lub_remis(x);
            tytul.setText("Wygral X");
            R++;
        }
        else X=0;
        if(O==x){
            zwyciestwo_lub_remis(x);
            tytul.setText("Wygral O");
            R++;
        }
        else O=0;

        // Sprawdzenie po ukosie dla X oraz O (od prawej do lewej)

        for (int b=x-1; b>=0; b--) {
            if(przyciski[-(b-x+1)][b].getText().contains("X")){
                X++;

                // Pokolorowanie zwycieskich pol na zielono
                if(X==x){
                    for(int z=b;z<x;z++){
                        przyciski[-(z-x+1)][z].setBackground(Color.GREEN);
                    }
                }
            }
            if(przyciski[-(b-x+1)][b].getText().contains("O")){
                O++;

                // Pokolorowanie zwycieskich pol na zielono
                if(O==x){
                    for(int z=b;z<x;z++){
                        przyciski[-(z-x+1)][z].setBackground(Color.GREEN);
                    }
                }
            }
        }
        if(X==x){
            zwyciestwo_lub_remis(x);
            tytul.setText("Wygral X");
            R++;
        }
        else X=0;
        if(O==x){
            zwyciestwo_lub_remis(x);
            tytul.setText("Wygral O");
            R++;
        }
        else O=0;


        // Sprawdzenie czy mial miejsce remis

        for(int a=0;a<x;a++) {
            for (int b = 0; b < x; b++) {
                if(przyciski[a][b].getText()!=""){
                    ilosc_pustych_pol++;
                }
            }
        }
        if(ilosc_pustych_pol==(x*x) && R==0){
            tytul.setText("Remis");
            zwyciestwo_lub_remis(x);
        }
        else ilosc_pustych_pol=0;
    }

    /**
     * Metoda okreslajaca co dzieje, gdy bedzie mialo miejsce zwyciestwo jednego z graczy
     * lub, gdy dojdzie do remisu
     *
     * @param x -pozwala ustalic wielkosc tablicy o wymiarach x kolumn na x wierszy
     */
    public void zwyciestwo_lub_remis(int x) {

        // Jesli nastapi zwyciestwo lub remis wszystkim przyciskom odebrana zostaje mozliwosc
        // klikniecia na nie
            for (int a = 0; a < x; a++) {
                for (int b = 0; b < x; b++) {
                    przyciski[a][b].setEnabled(false);
                }
            }

        // Odebranie przyciskom cofnij oraz zapisz_gre mozliwosci klikniecia na nie jesli nastapi
        // zwyciestwo lub remis
        cofnij.setEnabled(false);
        zapisz_gre.setEnabled(false);

        // Utworzenie obiektu klasy Timer w celu opoznienia powrotu do menu glownego gry
            Timer przerwa = new Timer();

        // Wykorzystanie metody powrot_do_menu_glownego w celu powrotu do menu glownego
            TimerTask wykonaj_po_dwoch_sekundach = new TimerTask() {
                @Override
                public void run() {
                    powrot_do_menu_glownego(x);
                }
            };

        // Metoda opozniajaca wykonanie instrukcji powrotu do glownego menu o 1.8 [s]
            przerwa.schedule(wykonaj_po_dwoch_sekundach, 1800);
    }

    /**
     * Metoda pozwalajaca otrzymac ustawienie layoutu dla menu glownego
     *
     * @param x -pozwala ustalic wielkosc tablicy o wymiarach x kolumn na x wierszy
     */
    public void powrot_do_menu_glownego(int x){

        // Odebranie wszystkim przyciskom z pola gry widocznosci oraz mozliwosci
        // klikniecia na nie
        for (int a = 0; a < x; a++) {
            for (int b = 0; b < x; b++) {
                przyciski[a][b].setEnabled(false);
                przyciski[a][b].setVisible(false);
            }
        }
        // Ustawienie wszystkich przyciskow dla menu glownego oraz tytulu
        nowa_gra.setVisible(true);
        nowa_gra.setEnabled(true);
        wczytaj_gre.setVisible(true);
        wczytaj_gre.setEnabled(true);
        wyjdz_z_gry.setVisible(true);
        wyjdz_z_gry.setEnabled(true);
        o_programie.setVisible(true);
        o_programie.setEnabled(true);
        zapisz_gre.setVisible(false);
        zapisz_gre.setEnabled(false);
        wybierz_rozmiar_mapy.setVisible(false);
        wybierz_rozmiar_mapy.setEnabled(false);
        trzy_na_trzy.setVisible(false);
        trzy_na_trzy.setEnabled(false);
        cztery_na_cztery.setVisible(false);
        cztery_na_cztery.setEnabled(false);
        piec_na_piec.setVisible(false);
        piec_na_piec.setEnabled(false);
        o_tworcach.setVisible(false);
        cofnij.setVisible(false);
        tytul.setText("");
        tytul.setText("Kolko i Krzyzyk");

    }

    /**
     * Metoda pozwalajaca zapisac do pliku informacje na temat przyciskow z pola gry
     * (ile ich jest, jakie maja znaki lub czy sa puste)
     *
     * @param przyciski -pozwala okreslic typ zapisywanych obiektow
     */
    public static void zapisane_informacje_o_przyciskach(JButton[][] przyciski) {

        // Utworzenie strumienia oraz zapisanie informacji na temat przyciskow z pola
        // gry do pliku
        try {
            OutputStream fos = Files.newOutputStream(Paths.get("zapisane_przyciski"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(przyciski);
            oos.close();
        } catch (IOException throwables){
            throwables.printStackTrace();
        }
    }

    /**
     * Metoda pozwalajaca zapisac do pliku informacje na temat tytulu
     * (czy jest kolej gracza X, czy kolej gracza O)
     *
     * @param tytul -pozwala okreslic typ zapisywanych obiektow
     */
    public static void zapisane_informacje_o_kolejnosci(JTextField tytul) {

        // Utworzenie strumienia oraz zapisanie informacji na temat tytulu do pliku
        try {
            OutputStream fos = Files.newOutputStream(Paths.get("zapisany_tytul"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tytul);
            oos.close();
        } catch (IOException throwables){
            throwables.printStackTrace();
        }
    }

    /**
     * Metoda pozwalajaca zapisac do pliku informacje na temat wielkosci mapy
     * (rozmiar mapy, czyli n wierszy i n kolumn)
     *
     * @param n -pozwala okreslic typ zapisywanych obiektow
     */
    public static void zapisane_informacje_o_wielkosci_mapy (int n) {

        // Utworzenie strumienia oraz zapisanie informacji na temat wielkosci mapy gry
        // do pliku
        try {
            OutputStream fos = Files.newOutputStream(Paths.get("zapisana_wielkosc"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(n);
            oos.close();
        } catch (IOException throwables){
            throwables.printStackTrace();
        }
    }

    /**
     * Metoda pozwalajaca wczytac z pliku informacje na temat przyciskow z pola gry
     * (ile ich jest, jakie maja znaki lub czy sa puste)
     * @return -przyciski z pola gry
     */
    public static JButton[][] wczytaj_informacje_o_przyciskach(){
        try {

            // Utworzenie strumienia oraz wczytanie informacji na temat przyciskow z pola
            // gry z pliku oraz zwrocenie ich
            InputStream in = Files.newInputStream(Paths.get("zapisane_przyciski"));
            ObjectInputStream ois = new ObjectInputStream(in);
            JButton[][] przyciski = (JButton[][]) ois.readObject();
            return przyciski;
        } catch (ClassNotFoundException | IOException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * Metoda pozwalajaca zapisac do pliku informacje na temat tytulu
     * (czy jest kolej gracza X, czy kolej gracza O)
     *
     * @return -tytul mowiacy, czyja kolej jest w danym ruchu
     */
    public static JTextField wczytaj_informacje_o_kolejnosci(){
        try {

            // Utworzenie strumienia oraz wczytanie informacji na temat tytulu z pliku
            // oraz zwrocenie ich
            InputStream in = Files.newInputStream(Paths.get("zapisany_tytul"));
            ObjectInputStream ois = new ObjectInputStream(in);
            JTextField tytul = (JTextField) ois.readObject();
            return tytul;
        } catch (ClassNotFoundException | IOException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * Metoda pozwalajaca zapisac do pliku informacje na temat wielkosci mapy
     * (rozmiar mapy, czyli n wierszy i n kolumn)
     *
     * @return -rozmiar mapy, czyli n wierszy i n kolumn
     */
    public static int wczytaj_informacje_o_wielkosci_mapy(){
        try {

            // Utworzenie strumienia oraz wczytanie informacji na temat wielkosci mapy gry
            // z pliku oraz zwrocenie ich
            InputStream in = Files.newInputStream(Paths.get("zapisana_wielkosc"));
            ObjectInputStream ois = new ObjectInputStream(in);
            int n = (int) ois.readObject();
            return n;
        } catch (ClassNotFoundException | IOException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    /**
     * Metoda okresla zasady gry, czyli ktory gracz ma kolej, co dzieje sie,
     * gdy zostanie nacisniety przycisk oraz sprawdza czy po kliknieciu przycisku
     * nie nastepuje zwyciestwo lub remis
     *
     * @param a -pozwala okreslic konkretny przycisk z tablicy przyciskow
     * @param b -pozwala okreslic konkretny przycisk z tablicy przyciskow
     */
    private void schemat_gry(int a, int b){

        // Sprawdzenie, ktory gracz ma kolej oraz oznaczenie kliknietego przycisku
        // jako X lub O
        if(kolejgracza1){
            if(przyciski[a][b].getText()==""){
                przyciski[a][b].setForeground(new Color(255,0,0));
                przyciski[a][b].setText("X");
                kolejgracza1=false;
                tytul.setText("Kolej O");
                sprawdzenie_kto_wygral(n);
            }
        }
        else{
            if(przyciski[a][b].getText()==""){
                przyciski[a][b].setForeground(new Color(0,0,255));
                przyciski[a][b].setText("O");
                kolejgracza1=true;
                tytul.setText("Kolej X");
                sprawdzenie_kto_wygral(n);
            }
        }
    }

    /**
     * Metoda ustawia layout dla pola gry, czyli po okresleniu jaki rozmiar mapy gry
     * zostal wybrany
     */
    private void pole_gry(){

        // Ustawienie layoutu
        wybierz_rozmiar_mapy.setVisible(false);
        wybierz_rozmiar_mapy.setEnabled(false);
        trzy_na_trzy.setVisible(false);
        trzy_na_trzy.setEnabled(false);
        cztery_na_cztery.setVisible(false);
        cztery_na_cztery.setEnabled(false);
        piec_na_piec.setVisible(false);
        piec_na_piec.setEnabled(false);
        zapisz_gre.setVisible(true);
        zapisz_gre.setEnabled(true);
        cofnij.setVisible(true);
        cofnij.setEnabled(true);
    }

    /**
     * Metody ustawia layout jako pusty, czyli bez widocznych przyciskow, z widocznym
     * jedynie tytulem
     */
    private void pusty_layout(){

        // Ustawienie layoutu
        nowa_gra.setVisible(false);
        nowa_gra.setEnabled(false);
        wczytaj_gre.setVisible(false);
        wczytaj_gre.setEnabled(false);
        wyjdz_z_gry.setVisible(false);
        wyjdz_z_gry.setEnabled(false);
        o_programie.setVisible(false);
        o_programie.setEnabled(false);
        cofnij.setVisible(false);
        cofnij.setEnabled(false);
        zapisz_gre.setVisible(false);
        zapisz_gre.setEnabled(false);
        wybierz_rozmiar_mapy.setVisible(false);
        wybierz_rozmiar_mapy.setEnabled(false);
        trzy_na_trzy.setVisible(false);
        trzy_na_trzy.setEnabled(false);
        cztery_na_cztery.setVisible(false);
        cztery_na_cztery.setEnabled(false);
        piec_na_piec.setVisible(false);
        piec_na_piec.setEnabled(false);
    }

    /**
     * Metoda pozwala na wykonanie instrukcji poprzez klikniecie na odpowiedni
     * przycisk
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        // Wprowadzenie zasad gry do kazdego kliknietego przycisku z pola gry
            for (int a = 0; a < n; a++) {
                for (int b = 0; b < n; b++) {
                    if (e.getSource() == przyciski[a][b]) {
                        schemat_gry(a, b);
                    }
                }
            }

        // Wykonanie instrukcji po kliknieciu przycisku nowa_gra
        if(e.getSource()== nowa_gra) {

            // Ustawienie layoutu
            pusty_layout();
            wybierz_rozmiar_mapy.setVisible(true);
            trzy_na_trzy.setVisible(true);
            trzy_na_trzy.setEnabled(true);
            cztery_na_cztery.setVisible(true);
            cztery_na_cztery.setEnabled(true);
            piec_na_piec.setVisible(true);
            piec_na_piec.setEnabled(true);
            cofnij.setVisible(true);
            cofnij.setEnabled(true);
        }

        // Wykonanie instrukcji po kliknieciu przycisku trzy_na_trzy
            if(e.getSource()==trzy_na_trzy){

                // Ustawienie layoutu oraz rozpoczecie gry dla rozmiaru mapy:
                // 3 kolumny i 3 wiersze
                pole_gry();;
                n=3;
                umiejscowienie_przyciskow(n);
                kto_zaczyna();
            }

        // Wykonanie instrukcji po kliknieciu przycisku cztery_na_cztery
            if(e.getSource()== cztery_na_cztery){

                // Ustawienie layoutu oraz rozpoczecie gry dla rozmiaru mapy:
                // 4 kolumny i 4 wiersze
                pole_gry();
                n=4;
                umiejscowienie_przyciskow(n);
                kto_zaczyna();
            }

        // Wykonanie instrukcji po kliknieciu przycisku piec_na_piec
            if(e.getSource()== piec_na_piec){

                // Ustawienie layoutu oraz rozpoczecie gry dla rozmiaru mapy:
                // 5 kolumn i 5 wierszy
                pole_gry();
                n=5;
                umiejscowienie_przyciskow(n);
                kto_zaczyna();
            }

        // Wykonanie instrukcji po kliknieciu przycisku o_programie
            if(e.getSource()== o_programie){

                // Ustawienie layoutu
                pusty_layout();
                o_tworcach.setVisible(true);
                cofnij.setVisible(true);
                cofnij.setEnabled(true);
                o_tworcach.setText("\n\n\n" +
                        "         Copyright 2020 Kacper Gladys\n\n" +
                        "      Program wydany na warunkach GNU\n" +
                        "                 General Public Licence\n");
            }

        // Wykonanie instrukcji po kliknieciu przycisku cofnij
            if(e.getSource()==cofnij){

                // Ustawienie layoutu
                powrot_do_menu_glownego(n);
            }

        // Wykonanie instrukcji po kliknieciu przycisku wczytaj_gre
            if(e.getSource()==wczytaj_gre){

                // Ustawienie layoutu
                pusty_layout();
                cofnij.setVisible(true);
                cofnij.setEnabled(true);
                zapisz_gre.setVisible(true);
                zapisz_gre.setEnabled(true);

                // Utworzenie zmiennej oraz przypisanie do niej wczytanej informacji
                // okreslajacej wielkosc mapy, czyli ile ma kolumn i wierszy
                int wielkosc_mapy = wczytaj_informacje_o_wielkosci_mapy();

                // Nadanie wartosci zmiennej okreslajacej ilosc wierszy i kolumn
                // w zaleznosci od wczytanej informacji
                if(wielkosc_mapy==3){
                    n=3;
                }
                else if (wielkosc_mapy==4){
                    n=4;
                }
                else if(wielkosc_mapy==5){
                    n=5;
                }

                // Utworzenie zmiennej oraz przypisanie do niej wczytanej informacji
                // okreslajacej czyja byla kolej w grze zapisanej
                JTextField zmieniony_tytul = wczytaj_informacje_o_kolejnosci();
                tytul.setText(zmieniony_tytul.getText());

                // Okreslenie czyja jest kolej w zaleznosci od wczytanej informacji
                if(zmieniony_tytul.getText().contains("Kolej X")){
                    kolejgracza1=true;
                }
                else{
                    kolejgracza1=false;
                }

                // Utworzenie tablicy zmiennych oraz przypisanie do niej wczytanych informacji
                // okreslajacych jakie znaki znajdowaly sie na przyciskach w zapisanej grze
                JButton[][] wczytane_przyciski = wczytaj_informacje_o_przyciskach();

                // Utworzenie tablicy String przechowujacej znaki z przyciskow o wielkosci
                // ilosci przyciskow z zapisanej gry oraz utworzenie nowego pola gry, na podstawie
                // wielkosci pola gry z gry zapisanej
                String[][] znak = new String[n][n];
                umiejscowienie_przyciskow(n);

                // Nadanie znakow dla wszystkich nowych przyciskow na podstawie wczytanych informacji
                // z pliku
                for(int a=0;a<n;a++) {
                    for (int b = 0; b<n; b++) {

                        // Zapisanie znaku kazdego przycisku do tablicy String oraz ustalenie kolor
                        // i grafiki takiej jak w zapisanej grze
                        znak[a][b] = wczytane_przyciski[a][b].getText();
                        znak[a][b] = znak[a][b].replaceAll("s+","");
                        if(znak[a][b]!="") {
                            if(znak[a][b].contains("X")) {
                                przyciski[a][b].setForeground(new Color(255, 0, 0));
                                przyciski[a][b].setText("X");
                            }
                            if(znak[a][b].contains("O")) {
                                przyciski[a][b].setForeground(new Color(0, 0, 255));
                                przyciski[a][b].setText("O");
                            }
                        }
                    }
                }
            }

        // Wykonanie instrukcji po kliknieciu przycisku zapisz_gre
            if(e.getSource()==zapisz_gre){

                // Zapisanie informacji do plikow
                zapisane_informacje_o_przyciskach(przyciski);
                zapisane_informacje_o_kolejnosci(tytul);
                zapisane_informacje_o_wielkosci_mapy(n);
            }

        // Wykonanie instrukcji po kliknieciu przycisku wyjdz_z_gry
            if(e.getSource()==wyjdz_z_gry){

                // Zakonczenie dzialanai programu
                System.exit(0);
            }
    }
}
