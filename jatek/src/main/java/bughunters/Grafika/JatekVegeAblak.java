package bughunters.Grafika;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;

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
    private Parancskezelok game;

    public int getJatekosokSzama() {
        return jatekosokSzama;
    }
    public void setJatekosokSzama(int jatekosokSzama) {
        this.jatekosokSzama = jatekosokSzama;
    }

    public JatekVegeAblak(Parancskezelok pk, Gombasz gombasz, Rovarasz rovarasz) {
        setTitle("Játék vége ablak");
        setSize(600,700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);


        game=pk;
        this.jatekosokSzama = game.getJatekosok().size();

        kiirJatekVege=new JLabel("Játék vége!");
        Dimension meret=new Dimension(210,75);
   
        //méretek beállítása
        kiirJatekVege.setSize(meret);
        kiirJatekVege.setFont(new Font("SansSerif", Font.ITALIC,40));


        List<Jatekos> jatekosok = game.getJatekosok();
        HashMap<Jatekos, Integer> pontszamok = new HashMap<>();

        for(Jatekos jatekos : jatekosok){
            pontszamok.put(jatekos, jatekos.getGyozelmiPontok());
        }

        List<Map.Entry<Jatekos, Integer>> rendezettLista = new ArrayList<>(pontszamok.entrySet());
        rendezettLista.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue())); // Csökkenő sorrend

        // -----------------------------

        JPanel panel = new JPanel()  {
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
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalStrut(20));

        kiirJatekVege.setFont(new Font("SansSerif", Font.BOLD, 36));
        kiirJatekVege.setAlignmentX(CENTER_ALIGNMENT);

        panel.add(kiirJatekVege);
        panel.add(Box.createVerticalStrut(40));

        // ---------------------------

        JPanel row1_1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        row1_1.setOpaque(false);

        JLabel gyoztesGombasz = new JLabel("Győztes gombász:");
        gyoztesGombasz.setFont(new Font("SansSerif", Font.BOLD, 24));

        JLabel gyoztesGombaszNev = new JLabel(gombasz.getNev());
        gyoztesGombaszNev.setFont(new Font("SansSerif", Font.BOLD, 24));
        gyoztesGombaszNev.setForeground(new Color(0, 100, 0)); // Sötétzöld

        row1_1.add(gyoztesGombasz);
        row1_1.add(gyoztesGombaszNev);
        panel.add(row1_1);

        // ---------------------------

        JPanel row1_2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        row1_2.setOpaque(false);

        JLabel gyoztesRovarasz = new JLabel("Győztes rovarász:");
        gyoztesRovarasz.setFont(new Font("SansSerif", Font.BOLD, 24));

        JLabel gyoztesRovaraszNev = new JLabel(rovarasz.getNev());
        gyoztesRovaraszNev.setFont(new Font("SansSerif", Font.BOLD, 24));
        gyoztesRovaraszNev.setForeground(new Color(0, 100, 0)); // Sötétzöld

        row1_2.add(gyoztesRovarasz);
        row1_2.add(gyoztesRovaraszNev);
        panel.add(row1_2);

        // ---------------------------

        panel.add(Box.createVerticalStrut(30));

        JPanel row2 = new JPanel(new BorderLayout());
        row2.setOpaque(false);

        String[] oszlopNevek = {"Név", "Pontszám"};
        Object[][] adatok = new Object[jatekosokSzama][2];

        for (int i = 0; i < jatekosokSzama; i++) {
            adatok[i][0] = rendezettLista.get(i).getKey().getNev();
            adatok[i][1] = rendezettLista.get(i).getValue();
        }

        JTable tabla = new JTable(adatok, oszlopNevek);
        tabla.setFont(new Font("SansSerif", Font.PLAIN, 16));
        tabla.setRowHeight(30);
        tabla.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 18));
        tabla.setEnabled(false); // Csak megjelenítés

        JScrollPane scrollPane = new JScrollPane(row2);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(550, 200)); // korlátozott magasság

        row2.add(scrollPane, BorderLayout.CENTER);
        panel.add(row2);

        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        add(panel);
    }
}
