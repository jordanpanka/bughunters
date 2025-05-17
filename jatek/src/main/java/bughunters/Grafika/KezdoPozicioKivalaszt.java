package bughunters.Grafika;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

public class KezdoPozicioKivalaszt extends JFrame{
    private JButton kovetkezo;
    private JLabel kiirJatekosNev;

    private int gombaszokSzama;
    private int rovaraszokSzama;


    public int getGombaszokSzama() {
        return gombaszokSzama;
    }
    public void setGombaszokSzama(int gombaszokSzama) {
        this.gombaszokSzama = gombaszokSzama;
    }

    public int getRovaraszokSzama() {
        return rovaraszokSzama;
    }
    public void setRovaraszokSzama(int rovaraszokSzama) {
        this.rovaraszokSzama = rovaraszokSzama;
    }

    private Parancskezelok game;

    private static List<JComboBox<String>> comboBoxes = new ArrayList<>();

    
    public KezdoPozicioKivalaszt(Parancskezelok pk, int gombaszokSzama, int rovaraszokSzama, Jatek jatek){

        setTitle("Kezdőpozíció kiválasztása");
        setSize(600,700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);


        game=pk;
        this.gombaszokSzama = gombaszokSzama;
        this.rovaraszokSzama = rovaraszokSzama;


        kovetkezo=new JButton("Következő");
        kiirJatekosNev=new JLabel("Kezdő pozíciók");
        Dimension meret=new Dimension(200,70);
   

        //méretek beállítása
        kiirJatekosNev.setSize(meret);
        kiirJatekosNev.setFont(new Font("SansSerif", Font.ITALIC,20));


        List<Gombasz> gombaszok = game.getGombaszok();
        List<Rovarasz> rovaraszok = game.getRovaraszok();


        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(Box.createVerticalStrut(30));
        kiirJatekosNev.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        panel.add(kiirJatekosNev);


        for (int i = 0; i < gombaszokSzama; i++) {
            JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel label = new JLabel(gombaszok.get(i).getNev());

            JComboBox<String> comboBox = new JComboBox<>();
            comboBoxes.add(comboBox);

            List<String> tektonokNev = game.getTektonNevList();
            for(String tektonNev : tektonokNev){
                comboBox.addItem(tektonNev);
            }
            
            label.setPreferredSize(new Dimension(150, 25));
            comboBox.setPreferredSize(new Dimension(200, 25));

            row.add(label);
            row.add(comboBox);
            panel.add(row);
            panel.add(Box.createVerticalStrut(10));
        }


        for (int i = 0; i < rovaraszokSzama; i++) {
            JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel label = new JLabel(rovaraszok.get(i).getNev());

            JComboBox<String> comboBox = new JComboBox<>();
            comboBoxes.add(comboBox);

            List<String> tektonoknev = game.getTektonNevList();
            for(String tektonNev : tektonoknev){
                comboBox.addItem(tektonNev);
            }
            
            label.setPreferredSize(new Dimension(150, 25));
            comboBox.setPreferredSize(new Dimension(200, 25));

            row.add(label);
            row.add(comboBox);
            panel.add(row);
        }

        
        kovetkezo.addActionListener(e->{
            try{
                HashMap<Gombasz, Tekton> gombaszokTestei = new HashMap<Gombasz, Tekton>();
                HashMap<Rovarasz, Tekton> rovaraszokRovarai = new HashMap<Rovarasz, Tekton>();

                int i = 0;

                for(Gombasz gombasz : gombaszok){
                    Tekton valasztott = game.getTekton((String)comboBoxes.get(i).getSelectedItem());
                    gombaszokTestei.put(gombasz, valasztott);
                    i++;
                }
                
                for(Rovarasz rovarasz : rovaraszok){
                    Tekton valasztott = game.getTekton((String)comboBoxes.get(i).getSelectedItem());
                    rovaraszokRovarai.put(rovarasz, valasztott);
                    i++;
                }


                game.kezdetiRovarokGombak(gombaszokTestei, rovaraszokRovarai);


                setVisible(false);

                SwingUtilities.invokeLater(() -> {
                    JatekAblak jAblak = new JatekAblak(game,jatek); // példányosítás
                    jAblak.setVisible(true);            // megjelenítés
                });


                }catch(Exception ex){
                    HibaAblak hb2=new HibaAblak(ex.getMessage());
                }
            }
        );


        kovetkezo.setPreferredSize(meret);
        kovetkezo.setAlignmentX(JButton.CENTER_ALIGNMENT);
        panel.add(kovetkezo);

        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        //add(panel);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane);
    }

}
