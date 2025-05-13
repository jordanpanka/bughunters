package bughunters.Grafika;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import bughunters.Egyeb.Parancskezelok;

public class NevFajSzin extends JFrame{
    private JButton kovetkezo;
    private JButton kiirjatekos;
    private JComboBox<String> gombafajKiv;
    private JComboBox <String> szinKiv;
    private int gombaszokSzama;
    private int rovaraszokSzama;
    private Parancskezelok game;
    private JTextField jatekosNev;
    private JButton gombaInfo;
    public NevFajSzin(Parancskezelok pk, int gombaszSzam, int rovaraszokSzam){

        setTitle("Alapadatok megadása");
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        game=pk;
        gombaszokSzama=gombaszSzam;
        rovaraszokSzama=rovaraszokSzam;
        JPanel panel=new JPanel();
        
        gombaInfo=new JButton("Gomba info");
       
        gombaInfo.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                GombaInfo ginfo = new GombaInfo(); // példányosítás
                ginfo.setVisible(true);            // megjelenítés
            });
        });

        kovetkezo=new JButton("Következő");
        kiirjatekos=new JButton("Gombász");

        JLabel jatekosnevL=new JLabel("Játékos neve: ");
        JLabel gombafaj=new JLabel("Gombafaj: ");

        String[] gombafajoks=new String[]{"Lényölő galóca","Vargánya gomba", "Csiperke gomba","Szegfűgomba", "Foltos püffeteg"};
        gombafajKiv=new JComboBox<>(gombafajoks);


        String[] szinek=new String[]{"fekete","gesztenye barna","piszkos barna","terrakotta","arany"};
        szinKiv=new JComboBox<>(szinek);

        if(gombaszokSzama==0){
                    kiirjatekos.setText("Rovarász");
                    gombafaj.setText("Szín: ");
                    gombafajKiv.setModel(new DefaultComboBoxModel<>(szinek))
                }
        kovetkezo.addActionListener(e->{
            while(gombaszokSzama!=0){

                gombaszokSzama--;
                SwingUtilities.invokeLater(() -> {
                NevFajSzin nfsz = new NevFajSzin(game,gombaszokSzama,rovaraszokSzama); // példányosítás
                nfsz.setVisible(true);            // megjelenítés
                });
            }
            while(gombaszokSzama==0 && rovaraszokSzama!=0){
                rovaraszokSzam--;
                SwingUtilities.invokeLater(() -> {
                NevFajSzin nfsz = new NevFajSzin(game,gombaszokSzama,rovaraszokSzama); // példányosítás
                nfsz.setVisible(true);            // megjelenítés
                });
            }
            SwingUtilities.invokeLater(() -> {
                 nfsz = new NevFajSzin(game,gombaszokSzama,rovaraszokSzama); // példányosítás
                nfsz.setVisible(true);            // megjelenítés
                });
        });

        

        JPanel jatekosnevp=new JPanel();
        jatekosnevp.add(jatekosnevL,jatekosNev);
    
        JPanel kivPanel=new JPanel();
        kivPanel.add(gombafaj);
        kivPanel.add(gombafajKiv);

        JPanel gombPanel=new JPanel();
        gombPanel.add(gombaInfo);
        gombPanel.add(kovetkezo);

        panel.add(kiirjatekos);
        panel.add(Box.createVerticalStrut(10));




    }

    
}
