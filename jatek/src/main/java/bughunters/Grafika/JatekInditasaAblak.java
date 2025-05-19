package bughunters.Grafika;

import bughunters.Egyeb.Parancskezelok;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/***
 * @brief Játék indításához használt ablak
 * 
 * Ez az ablak tartalmazza az üdvözlő üzenetet és az "Új játék" gombot,
 * amely segítségével a felhasználó elindíthatja a játékot. A háttér
 * pasztell lila színátmenettel van kialakítva.
 */
public class JatekInditasaAblak extends JFrame {
    public JButton UjJatek;
    private Parancskezelok game;

    /***
     * @brief Inicializálja a játék indító ablakot
     * 
     * Beállítja az ablak címét, méretét, és létrehozza a felhasználói felület
     * elemeket, beleértve a színátmenetes háttérpanelt, az üdvözlő szöveget,
     * valamint az "Új játék" gombot. A gomb eseménykezelője a koviAblak()
     * metódust hívja meg.
     */
    public JatekInditasaAblak() {
        setTitle("Játék Indítása");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 250);
        
        // Egyedi JPanel a háttérhez
        JPanel backgroundPanel = new JPanel(new GridBagLayout()) {
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
        setContentPane(backgroundPanel);

        // Üdvözlő szöveg
        JLabel udvozol = new JLabel("Üdvözöljük!");
        udvozol.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
        udvozol.setForeground(Color.BLACK);
        udvozol.setOpaque(false);  // Átlátszó háttér

        // Gomb stílusa
        UjJatek = new JButton("Új játék indítása");
        UjJatek.addActionListener(e -> koviAblak());
        UjJatek.setForeground(Color.WHITE);
        UjJatek.setBackground(new Color(135, 206, 250)); // Világoskék
        UjJatek.setFont(new Font("Arial", Font.BOLD, 13));
        UjJatek.setFocusPainted(false);

        // Layout beállítások
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 20, 0);

        backgroundPanel.add(udvozol, gbc);
        
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        backgroundPanel.add(UjJatek, gbc);
        
        setLocationRelativeTo(null);
    }

    /***
     * @brief Átvált a következő ablakra
     * 
     * Bezárja az aktuális ablakot, létrehoz egy új Parancskezelok példányt,
     * és megnyitja a játékosok számának megadására szolgáló ablakot.
     * Ezt a metódust az "Új játék" gomb aktiválja.
     */
    private void koviAblak() {
        SwingUtilities.invokeLater(() -> {
            dispose();
            game = new Parancskezelok();
            JatekosSzamanakMegadasa megadas = new JatekosSzamanakMegadasa(game);
            megadas.setVisible(true);
        });
    }
}