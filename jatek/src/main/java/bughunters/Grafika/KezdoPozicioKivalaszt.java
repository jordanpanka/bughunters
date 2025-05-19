package bughunters.Grafika;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
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


/**
 * A játékban résztvevő játékosok kezdőpozícióit megjelenítő ablak.
 * A játékosok kiválaszthatják a kezdő pozíciókat a gombászok és rovarászok számára.
 */
public class KezdoPozicioKivalaszt extends JFrame{
    private JButton kovetkezo;
    private JLabel kiirJatekosNev;
    private static List<JComboBox<String>> comboBoxes = new ArrayList<>();

    private int gombaszokSzama;
    private int rovaraszokSzama;
    private Parancskezelok game;
    

    /**
    * Visszaadja a gombászok számát.
    *
    * @return a gombászok száma
    */
    public int getGombaszokSzama() {
        return gombaszokSzama;
    }

    /**
     * Beállítja a gombászok számát.
     *
     * @param gombaszokSzama a beállítandó gombászok száma
     */
    public void setGombaszokSzama(int gombaszokSzama) {
        this.gombaszokSzama = gombaszokSzama;
    }

    /**
     * Visszaadja a rovarászok számát.
     *
     * @return a rovarászok száma
     */
    public int getRovaraszokSzama() {
        return rovaraszokSzama;
    }

    /**
     * Beállítja a rovarászok számát.
     *
     * @param rovaraszokSzama a beállítandó rovarászok száma
     */
    public void setRovaraszokSzama(int rovaraszokSzama) {
        this.rovaraszokSzama = rovaraszokSzama;
    }

    
    /**
     * Konstruktor, amely létrehozza a kezdőpozíció kiválasztó ablakot.
     * Ezen az ablakon keresztül tudják a játékosok kiválasztani a kezdő pozíciókat.
     *
     * @param pk    A játékhoz tartozó parancskezelő objektum
     * @param jatek A játék logikát reprezentáló objektum
     */
    public KezdoPozicioKivalaszt(Parancskezelok pk,  Jatek jatek){
        // Ablak beállítások
        setTitle("Kezdőpozíció kiválasztása");
        setSize(600,700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);


        // Játék objektum mentése és játékosok számának lekérdezése
        game=pk;
        this.gombaszokSzama = game.getGombaszok().size();
        this.rovaraszokSzama = game.getRovaraszok().size();


        // GUI komponensek létrehozása
        kovetkezo=new JButton("Következő");
        kiirJatekosNev=new JLabel("Kezdő pozíciók");
   

        // Cím címke formázása
        kiirJatekosNev.setSize(new Dimension(200,70));
        kiirJatekosNev.setFont(new Font("SansSerif", Font.ITALIC,30));


        // Játékosok lekérdezése
        List<Gombasz> gombaszok = game.getGombaszok();
        List<Rovarasz> rovaraszok = game.getRovaraszok();


        // Fő panel egyedi háttérrel (pasztel színátmenet)
        JPanel panel = new JPanel() {
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

        // Cím hozzáadása
        panel.add(Box.createVerticalStrut(30));
        kiirJatekosNev.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        panel.add(kiirJatekosNev);
        panel.add(Box.createVerticalStrut(40)); // Ez ad egy kis helyet a cím alá


        // Gombász játékosokhoz beviteli sorok létrehozása
        for (int i = 0; i < gombaszokSzama; i++) {
            JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT)) {
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
            row.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
            row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
            JLabel label = new JLabel(gombaszok.get(i).getNev());

            // Tekton helyek feltöltése a combobox-ba
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
        }


        // Rovarász játékosokhoz beviteli sorok létrehozása
        for (int i = 0; i < rovaraszokSzama; i++) {
            JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT)) {
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
            row.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
            row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
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

        
        // "Következő" gomb eseménykezelője
        kovetkezo.addActionListener(e->{
            try{
                HashMap<Gombasz, Tekton> gombaszokTestei = new HashMap<Gombasz, Tekton>();
                HashMap<Rovarasz, Tekton> rovaraszokRovarai = new HashMap<Rovarasz, Tekton>();

                int i = 0;

                // Gombászok választásainak lekérdezése
                for(Gombasz gombasz : gombaszok){
                    Tekton valasztott = game.getTekton((String)comboBoxes.get(i).getSelectedItem());
                    gombaszokTestei.put(gombasz, valasztott);
                    i++;
                }
                
                // Rovarászok választásainak lekérdezése
                for(Rovarasz rovarasz : rovaraszok){
                    Tekton valasztott = game.getTekton((String)comboBoxes.get(i).getSelectedItem());
                    rovaraszokRovarai.put(rovarasz, valasztott);
                    i++;
                }


                // Játék inicializálása a választások alapján
                game.kezdetiRovarokGombak(gombaszokTestei, rovaraszokRovarai);


                // Ablak bezárása és játékablak megnyitása
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


        // Következő gomb formázása
        kovetkezo.setPreferredSize(new Dimension(200,50));
        kovetkezo.setAlignmentX(JButton.CENTER_ALIGNMENT);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        // Scrollozható tartalom létrehozása
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Alsó gombpanel saját háttérrel
        JPanel gombPanel = new JPanel() {
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
        gombPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        gombPanel.add(kovetkezo);

        /// Az ablak végső elrendezése
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(gombPanel, BorderLayout.SOUTH);
    }
}
