package bughunters.Grafika;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Insets;
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
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import bughunters.Egyeb.Jatek;
import bughunters.Egyeb.Jatekos;
import bughunters.Egyeb.Parancskezelok;
import bughunters.Gombafaj.Gombafaj;

public class NevFajSzin extends JFrame{
    private JButton kovetkezo;
    private JLabel kiirjatekos;
    private JComboBox<String> gombafajKiv;
    private int gombaszokSzama;
    public int getGombaszokSzama() {
        return gombaszokSzama;
    }
    public void setGombaszokSzama(int gombaszokSzama) {
        this.gombaszokSzama = gombaszokSzama;
    }
    private int rovaraszokSzama;
    public int getRovaraszokSzama() {
        return rovaraszokSzama;
    }
    public void setRovaraszokSzama(int rovaraszokSzama) {
        this.rovaraszokSzama = rovaraszokSzama;
    }
    private Parancskezelok game;
    private JTextField jatekosNev;
    private JButton gombaInfo;
    List<Jatekos> jatekosok;
    List<String> kivalasztott;
    public NevFajSzin(Parancskezelok pk, int gombaszSzam, int rovaraszokSzam, Jatek jatek){

        setTitle("Alapadatok megadása");
        setSize(600,700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        game=pk;
        gombaszokSzama=gombaszSzam;
        rovaraszokSzama=rovaraszokSzam;

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
    
        gombaInfo = new JButton("Gomba info");
       
        gombaInfo.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                GombaInfo ginfo = new GombaInfo(); // példányosítás
                ginfo.setVisible(true);            // megjelenítés
            });
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        kovetkezo = new JButton("Következő");
        kiirjatekos = new JLabel("Gombász");
        Dimension meret = new Dimension(200,70);
        jatekosNev = new JTextField();

        //méretek beállítása
        kiirjatekos.setSize(meret);
        kiirjatekos.setFont(new Font("Arial", Font.BOLD, 36));
        kiirjatekos.setForeground(new Color(70, 50, 120));
        gbc.gridy = 0;
        panel.add(kiirjatekos, gbc);

        jatekosNev.setSize(meret);

        JLabel jatekosnevL = new JLabel("Játékos neve: ");  
        jatekosnevL.setPreferredSize(new Dimension(300, 35));
        jatekosnevL.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel gombafaj = new JLabel("Gombafaj: ");

        String[] gombafajoks=new String[]{"Lényölő galóca","Vargánya gomba", "Csiperke gomba","Szegfűgomba", "Foltos püffeteg"};
        
        gombafajKiv=new JComboBox<>(gombafajoks);

        String[] szinek=new String[]{"piros","narancssárga","magenta","barna","világos barna"};
        //szinKiv=new JComboBox<>(szinek);

        if(gombaszokSzama==0){
                kiirjatekos.setText("Rovarász");
                gombafaj.setText("Szín: ");
                gombafajKiv.setModel(new DefaultComboBoxModel<>(szinek));
        } 

        kovetkezo.addActionListener(e->{
            if(gombaszokSzama!=0){
                try{
                   
                switch ((String)gombafajKiv.getSelectedItem()) {
                    case "Légyölő galóca":
                        Gombafaj g=game.createGombafajBySpora('b');
                        game.gombaszFelvetel(jatekosNev.getText(),g);
                        break;
                    case "Vargánya gomba":
                        Gombafaj g1=game.createGombafajBySpora('l');
                        game.gombaszFelvetel(jatekosNev.getText(),g1);
                        break;
                    case "Csiperke gomba":
                     Gombafaj g2=game.createGombafajBySpora('g');
                        game.gombaszFelvetel(jatekosNev.getText(),g2);
                        break;
                    case "Szegfűgomba":
                         Gombafaj g3=game.createGombafajBySpora('v');
                        game.gombaszFelvetel(jatekosNev.getText(),g3);
                        break;
                    case "Foltos püffeteg":
                         Gombafaj g4=game.createGombafajBySpora('o');
                        game.gombaszFelvetel(jatekosNev.getText(),g4);
                        break;
                    default:
                        break;
                }
                setVisible(false);
                gombaszokSzama--;
                SwingUtilities.invokeLater(() -> {
                NevFajSzin nfsz = new NevFajSzin(game,gombaszokSzama,rovaraszokSzama,jatek); // példányosítás
                nfsz.setVisible(true);            // megjelenítés
                });
                }catch(Exception ex){
                    HibaAblak hb2=new HibaAblak(ex.getMessage());
                }
               
            }
            else if(gombaszokSzama==0 && rovaraszokSzama!=0){
                try{
               // rovaraszokSzama--;
                switch((String)gombafajKiv.getSelectedItem()){
                    case "piros": 
                        Color uj=Color.RED;
                        game.rovaraszFelvetel(jatekosNev.getText(),uj);
                        break;
                    case "narancssárga":
                        game.rovaraszFelvetel(jatekosNev.getText(),Color.ORANGE);
                        break;
                    case "magenta":
                        game.rovaraszFelvetel(jatekosNev.getText(),Color.MAGENTA);
                        break;
                    case "barna":
                        Color uj2=new Color(121,87,53);
                        game.rovaraszFelvetel(jatekosNev.getText(),uj2);
                        break;
                    case "világos barna":
                        Color uj3=new Color(172,86,0);
                        game.rovaraszFelvetel(jatekosNev.getText(),uj3);
                        break;
                    default: 
                    break;
                    
                }
                setVisible(false);
                rovaraszokSzama--;

                System.out.println(rovaraszokSzama);

                if(rovaraszokSzama != 0){
                    SwingUtilities.invokeLater(() -> {
                        NevFajSzin nfsz = new NevFajSzin(game,gombaszokSzama,rovaraszokSzama,jatek); // példányosítás
                        nfsz.setVisible(true);            // megjelenítés
                    });
                }
                else{
                    game.setAktivJatekos(game.getGombaszok().get(0));
                    SwingUtilities.invokeLater(() -> {
                    //Proba proba = new Proba(game,jatek); // példányosítás
                    //proba.setVisible(true);            // megjelenítés

                        KezdoPozicioKivalaszt proba = new KezdoPozicioKivalaszt(game,jatek); // példányosítás
                        proba.setVisible(true);            // megjelenítés
                    });
                }
                
                }catch(Exception ex){
                    HibaAblak hb=new HibaAblak(ex.getMessage());
                }
            }
           
            }
          );  


        JPanel jatekosnevp=new JPanel() {
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

        jatekosnevL.setPreferredSize(meret);
        jatekosnevp.add(jatekosnevL);
        jatekosNev.setPreferredSize(meret);
        jatekosnevp.add(jatekosNev);
    
        JPanel kivPanel=new JPanel() {
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
        gombafaj.setPreferredSize(meret);
        gombafaj.setFont(new Font("SansSerif",Font.PLAIN,20));
        kivPanel.add(gombafaj);
        kivPanel.add(Box.createHorizontalStrut(10));
        gombafajKiv.setPreferredSize(meret);
        gombafajKiv.setFont(new Font("SansSerif",Font.PLAIN,20));
        kivPanel.add(gombafajKiv);

        JPanel gombPanel=new JPanel() {
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
        
        gombaInfo.setPreferredSize(meret);
        gombaInfo.setForeground(Color.WHITE);
        gombaInfo.setBackground(new Color(135, 206, 250)); // Világoskék
        gombaInfo.setFont(new Font("Arial", Font.BOLD, 13));
        gombaInfo.setFocusPainted(false);

        gombPanel.add(gombaInfo);
        gombPanel.add(Box.createHorizontalStrut(10));

        kovetkezo.setPreferredSize(meret);
        kovetkezo.setForeground(Color.WHITE);
        kovetkezo.setBackground(new Color(135, 206, 250)); // Világoskék
        kovetkezo.setFont(new Font("Arial", Font.BOLD, 13));
        kovetkezo.setFocusPainted(false);

        gombPanel.add(kovetkezo);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(Box.createVerticalStrut(30));
        kiirjatekos.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        panel.add(kiirjatekos);
        panel.add(Box.createVerticalStrut(10));
        panel.add(jatekosnevp);
        panel.add(Box.createVerticalStrut(5));
        panel.add(kivPanel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(gombPanel);

        add(panel);
        
    }

    
}
