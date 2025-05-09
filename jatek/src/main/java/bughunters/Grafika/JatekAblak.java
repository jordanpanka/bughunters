package bughunters.Grafika;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.*;
import java.awt.*;
import bughunters.Egyeb.Parancskezelok;

public class JatekAblak extends JFrame {
    private Parancskezelok game;
    private JButton korVege;
    private JButton TestNov;
    private JButton Sporaszor;
    private JButton FonalNov;
    private JButton RovarEves;
    private JButton Maszik;
    private JButton Vag;
    private JButton Eszik;
    private Grafika grafika;
    private JComboBox tektonok;
    private JButton megjelenit;
    public JatekAblak(){
        setTitle("Bughunters");
        setSize(700,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        //gombok elrendezése
        JPanel jatekosInfo=new JPanel();

        JPanel gombaszGombok=new JPanel();
        JPanel rovaraszGombok=new JPanel();
        //gombok létrehozása
        korVege=new JButton("Kör vége");
        TestNov=new JButton("Test növesztés");
        Sporaszor=new JButton("Spóra szórás");
        FonalNov=new JButton("Fonal növesztése");
        RovarEves=new JButton("Rovar evése");
        Maszik=new JButton("Mászik");
        Vag=new JButton("Fonal vágása");
        Eszik=new JButton("Spóra evése");
        megjelenit=new JButton("Megjelenít");




        
    }
    
}
