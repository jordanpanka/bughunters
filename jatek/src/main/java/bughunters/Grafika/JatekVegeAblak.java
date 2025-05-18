package bughunters.Grafika;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import bughunters.Egyeb.Gombasz;
import bughunters.Egyeb.Jatek;
import bughunters.Egyeb.Jatekos;
import bughunters.Egyeb.Parancskezelok;
import bughunters.Egyeb.Rovarasz;
import bughunters.Gombafaj.Gombafaj;
import bughunters.Tekton.Tekton;

public class JatekVegeAblak extends JFrame{
    private JLabel kiirJatekVege;

    private int jatekosokSzama;


    public int getJatekosokSzama() {
        return jatekosokSzama;
    }
    public void setJatekosokSzama(int jatekosokSzama) {
        this.jatekosokSzama = jatekosokSzama;
    }


    private Parancskezelok game;

    
    public JatekVegeAblak(Parancskezelok pk, Gombasz gombasz, Rovarasz rovarasz) {

        setTitle("Játék vége ablak");
        setSize(600,700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);


        game=pk;
        this.jatekosokSzama = game.getJatekosok().size();


        kiirJatekVege=new JLabel("Játék vége!");
        Dimension meret=new Dimension(200,70);
   

        //méretek beállítása
        kiirJatekVege.setSize(meret);
        kiirJatekVege.setFont(new Font("SansSerif", Font.ITALIC,20));


        List<Jatekos> jatekosok = game.getJatekosok();

        HashMap<Jatekos, Integer> pontszamok = new HashMap<>();

        for(Jatekos jatekos : jatekosok){
            pontszamok.put(jatekos, jatekos.getGyozelmiPontok());
        }

        List<Map.Entry<Jatekos, Integer>> rendezettLista = new ArrayList<>(pontszamok.entrySet());

        rendezettLista.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue())); // Csökkenő sorrend


        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(Box.createVerticalStrut(30));
        kiirJatekVege.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        panel.add(kiirJatekVege);
        panel.add(Box.createVerticalStrut(40));


        JPanel row1_1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel gyoztesGombasz = new JLabel("Győztes gombász:");
        JLabel gyoztesGombaszNev = new JLabel(gombasz.getNev());

        row1_1.add(gyoztesGombasz);
        row1_1.add(gyoztesGombaszNev);
        panel.add(row1_1);


        JPanel row1_2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel gyoztesRovarasz = new JLabel("Győztes rovarász:");
        JLabel gyoztesRovaraszNev = new JLabel(rovarasz.getNev());

        row1_2.add(gyoztesRovarasz);
        row1_2.add(gyoztesRovaraszNev);
        panel.add(row1_2);



        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row2.setLayout(new BoxLayout(row2, BoxLayout.Y_AXIS));

        for (int i = 0; i < jatekosokSzama; i++) {
            JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));

            JLabel labelNev = new JLabel(rendezettLista.get(i).getKey().getNev());

            JLabel labelPontszam = new JLabel(rendezettLista.get(i).getValue().toString());
            
            labelNev.setPreferredSize(new Dimension(150, 25));
            labelPontszam.setPreferredSize(new Dimension(100, 25));

            row.add(labelNev);
            row.add(labelPontszam);
            
            row2.add(row);
        }

         //add(panel);
        JScrollPane scrollPane = new JScrollPane(row2);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        panel.add(scrollPane);

        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        add(panel);
    }
}
