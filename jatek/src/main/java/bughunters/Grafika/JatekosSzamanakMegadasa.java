package bughunters.Grafika;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import bughunters.Egyeb.Jatek;
import bughunters.Egyeb.Jatekter;
import bughunters.Egyeb.Parancskezelok;
import bughunters.Tekton.Tekton;

public class JatekosSzamanakMegadasa extends JFrame {
    JFrame frame;
    private JComboBox<Integer> gombaszokSzama;
    private JComboBox<Integer> rovaraszokSzama;
    private Parancskezelok game;
    private JPanel panel;
    private JPanel gombaszMegad;
    private JPanel rovaraszMegad;
    private JButton kovetkezo;

    public JatekosSzamanakMegadasa(Parancskezelok g) { 
        game = g;
        frame = new JFrame();

        frame.setTitle("Játékosok száma");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 250);
        frame.setLocationRelativeTo(null);

        // Fő panel létrehozása
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Gombász panel
        gombaszMegad = new JPanel(new FlowLayout(FlowLayout.LEFT));
        gombaszMegad.add(new JLabel("Gombász játékosok száma:"));
        gombaszokSzama = new JComboBox<>(new Integer[]{2, 3, 4, 5});
        gombaszMegad.add(gombaszokSzama);

        // Rovarász panel
        rovaraszMegad = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rovaraszMegad.add(new JLabel("Rovarász játékosok száma:"));
        rovaraszokSzama = new JComboBox<>(new Integer[]{2, 3, 4, 5});
        rovaraszMegad.add(rovaraszokSzama);

        // Gomb
        kovetkezo = new JButton("Következő");
        kovetkezo.addActionListener(e -> handleKovetkezo());

        panel.add(gombaszMegad);
        panel.add(rovaraszMegad);
        panel.add(kovetkezo);

        frame.add(panel);
    }

    private void handleKovetkezo() {
        SwingUtilities.invokeLater(() -> {

            //Még nincs beállítás a NevFajSzin ablak cuccra 
            int gombaszok = (int)gombaszokSzama.getSelectedItem();
            int rovaraszok = (int)rovaraszokSzama.getSelectedItem();

            game = new Parancskezelok();
            ArrayList<Tekton> t = new ArrayList<Tekton>();
            Jatekter jatekTer = new Jatekter(t);

            dispose();
            Jatek jatek = new Jatek(game, jatekTer);
            JatekAblak jatekAblak = new JatekAblak(game, jatek);
            jatekAblak.setVisible(true);
        });
    }
}
