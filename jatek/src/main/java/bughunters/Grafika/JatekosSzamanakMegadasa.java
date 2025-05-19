package bughunters.Grafika;

import bughunters.Egyeb.Jatek;
import bughunters.Egyeb.Jatekter;
import bughunters.Egyeb.Parancskezelok;

import java.awt.*;
import javax.swing.*;


/***
 * @brief Játékosok számának megadására szolgáló ablak
 * 
 * Ez az ablak lehetővé teszi a felhasználó számára, hogy kiválassza a
 * Gombász és Rovarász játékosok számát. A háttér pasztell lila színátmenettel
 * rendelkezik, és tartalmaz egy "Következő" gombot a továbblépéshez.
 */
public class JatekosSzamanakMegadasa extends JFrame {
    private JComboBox<Integer> gombaszokSzama;
    private JComboBox<Integer> rovaraszokSzama;
    private Parancskezelok game;
    private JPanel panel;
    private JPanel gombaszMegad;
    private JPanel rovaraszMegad;
    private JButton kovetkezo;

    /***
     * @brief Inicializálja a játékos számának megadó ablakot
     * @param g A játék parancskezelője, ami továbbadódik a következő ablaknak
     * 
     * Beállítja az ablak címét és méretét, létrehozza a színátmenetes háttért,
     * a legördülő listákat a játékosok számának kiválasztásához, valamint
     * a "Következő" gombot. A gomb eseménykezelője a handleKovetkezo() metódust hívja.
     */
    public JatekosSzamanakMegadasa(Parancskezelok g) { 
        game = g;
        
        setTitle("Játékosok száma");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 250);
        setLocationRelativeTo(null);

        //------------------------------

        // Pasztel háttér
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                
                // Színátmenet (pasztel lila árnyalatok)
                GradientPaint gradient = new GradientPaint(
                    0, 0, new Color(230, 230, 250),  // Lavender
                    getWidth(), getHeight(), new Color(216, 191, 216)  // Thistle
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new GridBagLayout());
        setContentPane(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 0, 5, 0); // Kisebb térköz
        gbc.anchor = GridBagConstraints.CENTER; // Középre igazítás

        // -------------------------------

        // Gombász panel
        gbc.gridy = 0;
        gombaszMegad = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5)); // Középre igazítás, kisebb térköz
        panel.setOpaque(false);

        JLabel label = new JLabel("Gombász játékosok száma: ");
        label.setForeground(new Color(64, 64, 64)); // Szürke szöveg
        label.setFont(new Font("Arial", Font.PLAIN, 12));
        gombaszMegad.add(label);

        gombaszokSzama = new JComboBox<>(new Integer[]{2,3,4,5});
        gombaszokSzama.setBackground(new Color(255, 255, 255, 200)); // Átlátszóbb fehér
        gombaszokSzama.setFont(new Font("Arial", Font.PLAIN, 12));
        gombaszMegad.add(gombaszokSzama);

        panel.add(gombaszMegad, gbc);

        // ----------------------------------

        // Rovarász panel
        gbc.gridy = 1;
        rovaraszMegad = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panel.setOpaque(false);

        JLabel label2 = new JLabel("Rovarász játékosok száma: ");
        label2.setForeground(new Color(64, 64, 64)); // Szürke szöveg
        label2.setFont(new Font("Arial", Font.PLAIN, 12));
        rovaraszMegad.add(label2);

        rovaraszokSzama = new JComboBox<>(new Integer[]{2,3,4,5});
        rovaraszokSzama.setBackground(new Color(255, 255, 255, 200)); // Átlátszóbb fehér
        rovaraszokSzama.setFont(new Font("Arial", Font.PLAIN, 12));
        rovaraszMegad.add(rovaraszokSzama);

        panel.add(rovaraszMegad, gbc);

        // -----------------------------------

        // Gomb
        gbc.gridy = 2;
        gbc.insets = new Insets(15, 0, 0, 0); // Kicsit több tér a gomb felett
        kovetkezo = new JButton("Következő");
        kovetkezo.addActionListener(e -> handleKovetkezo());
        styleButton(kovetkezo);
        panel.add(kovetkezo, gbc);
    }

    /***
     * @brief Stílust alkalmaz a gombokra
     * @param button A stílusozandó gomb
     * 
     * Beállítja a gomb szövegének színét, háttérszínét, betűtípusát,
     * és eltávolítja a fókusz keretet.
     */
    private void styleButton(JButton button) {
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(135, 206, 250)); // Világoskék
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setFocusPainted(false);
    }

    /***
     * @brief Feldolgozza a "Következő" gomb nyomását
     * 
     * Lekéri a kiválasztott játékosok számát, inicializálja a játékteret,
     * bezárja az aktuális ablakot, és megnyitja a következő ablakot
     * (NevFajSzin) a játékosok nevének, fajának és színének megadásához.
     */
    private void handleKovetkezo() {
        SwingUtilities.invokeLater(() -> {
            int gombaszok = (int) gombaszokSzama.getSelectedItem();
            int rovaraszok = (int) rovaraszokSzama.getSelectedItem();

            Jatekter jatekTer = new Jatekter();
            dispose();
            
            Jatek jatek = new Jatek(game, jatekTer);
            game.setjatekter(jatekTer);
            jatek.jatekPalyaAlkotasa();

            NevFajSzin kovi = new NevFajSzin(game, gombaszok, rovaraszok, jatek);
            kovi.setVisible(true);
        });
    }
}