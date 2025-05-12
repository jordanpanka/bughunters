package bughunters.Grafika;

import bughunters.Egyeb.Parancskezelok;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.jar.JarFile;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class JatekInditasaAblak<game> extends JFrame {
    public JButton UjJatek;
    private Parancskezelok game;

    public JatekInditasaAblak() {
        JFrame jFrame = new JFrame();
        setTitle("Játék Indítása");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(300, 200);
        jFrame.setLayout(new FlowLayout());

        UjJatek = new JButton("Új játék indítása");
        UjJatek.addActionListener(e -> koviAblak());
        
        jFrame.add(UjJatek);
        jFrame.setLocationRelativeTo(null); // Középre igazítás
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
