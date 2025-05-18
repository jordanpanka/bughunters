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

public class JatekInditasaAblak extends JFrame {
    public JButton UjJatek;
    private Parancskezelok game;

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
                
                // Színátmenet beállítása (kék -> lila)
                GradientPaint gradient = new GradientPaint(
                    0, 0, new Color(70, 130, 180),   // Kezdőszín (Acélkék)
                    getWidth(), getHeight(), new Color(147, 112, 219)  // Végszín (Lila)
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        setContentPane(backgroundPanel);

        // Üdvözlő szöveg
        JLabel udvozol = new JLabel("Üdvözöljük!");
        udvozol.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
        udvozol.setForeground(Color.WHITE);
        udvozol.setOpaque(false);  // Átlátszó háttér

        // Gomb stílusa
        UjJatek = new JButton("Új játék indítása");
        UjJatek.addActionListener(e -> koviAblak());
        UjJatek.setForeground(Color.WHITE);
        UjJatek.setBackground(new Color(46, 139, 87));  // Tengerzöld háttér
        UjJatek.setFocusPainted(false);  // Fókusz keret eltüntetése
        UjJatek.setFont(new Font("Arial", Font.BOLD, 14));
        UjJatek.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 25, 10, 25));  // Padding

        // Layout beállítások
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 20, 0);

        backgroundPanel.add(udvozol, gbc);
        
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        backgroundPanel.add(UjJatek, gbc);
        
        setLocationRelativeTo(null);
    }

    private void koviAblak() {
        SwingUtilities.invokeLater(() -> {
            dispose();
            game = new Parancskezelok();
            JatekosSzamanakMegadasa megadas = new JatekosSzamanakMegadasa(game);
            megadas.setVisible(true);
        });
    }
}