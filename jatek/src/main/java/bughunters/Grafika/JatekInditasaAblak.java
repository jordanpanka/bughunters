package bughunters.Grafika;

import bughunters.Egyeb.Parancskezelok;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.jar.JarFile;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class JatekInditasaAblak extends JFrame {
    public JButton UjJatek;
    private Parancskezelok game;

    public JatekInditasaAblak() {
        setTitle("Játék Indítása");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new GridBagLayout());

        UjJatek = new JButton("Új játék indítása");
        UjJatek.addActionListener(e -> koviAblak());
        
        add(UjJatek);
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