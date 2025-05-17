package bughunters.Grafika;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bughunters.Egyeb.Jatek;
import bughunters.Egyeb.Parancskezelok;

public class Proba  extends JFrame{
   private Parancskezelok game;
   JComboBox<String> tektonok;
   JButton megjelenit;
   

    public Proba(Parancskezelok pk, Jatek jatek){
        game=pk;
         setTitle("Bughunters");
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        //setLayout(new BorderLayout());
         JPanel jatekosInfo=new JPanel();
        jatekosInfo.setLayout(new BoxLayout(jatekosInfo, BoxLayout.X_AXIS));

       
        //jatekosok adatainak megjelenítése
        for(int i=0; i<game.getJatekosok().size(); i++){
            JPanel jatekos=new JPanel();
             jatekos.setLayout(new BoxLayout(jatekos, BoxLayout.Y_AXIS)); 

            JLabel nev=new JLabel(game.getJatekosok().get(i).getNev());
            JLabel akcio=new JLabel("Akciók: "+game.getJatekosok().get(i).getakcioSzama());
            JLabel fajta=new JLabel(game.getJatekosok().get(i).szerepKor());
            JLabel pontok=new JLabel("Pontok: "+game.getJatekosok().get(i).getGyozelmiPontok());
            
            jatekos.add(nev);
            jatekos.add(Box.createVerticalStrut(10));
            jatekos.add(akcio);
            jatekos.add(Box.createVerticalStrut(10));
            jatekos.add(fajta);
            jatekos.add(Box.createVerticalStrut(10));
            jatekos.add(pontok);
            jatekos.add(Box.createVerticalStrut(10));
            System.out.println("hozzaadva.");
            jatekos.setMaximumSize(new Dimension(150, 200));
            jatekos.setPreferredSize(new Dimension(150, 200));
            jatekosInfo.add(jatekos);
            //jatekosInfo.add(Box.createHorizontalStrut(5));
        }
        List<String> tekton=game.getTektonNevList();
        tektonok = new JComboBox<>();
        tektonok.setPreferredSize(new Dimension(100,30));
        
        tekton.forEach((String s)->{
            System.out.println(s);
            tektonok.addItem(s);
        });
       
        //gombokLetreHozasa();
        megjelenit=new JButton("Megjelenít");
        // kör és játékos
        JLabel korAdatok=new JLabel("Kör : "+jatek.getKorSzam()+" Aktív játékos: "+game.getAktivJatekos().getNev());

        JPanel koradatokp=new JPanel();
        koradatokp.add(korAdatok);

        //tektonok kiválasztása
        tektonok.setMaximumSize(new Dimension(50, 30));
        megjelenit.setMaximumSize(new Dimension(10, 30));

        jatekosInfo.add(tektonok);
        jatekosInfo.add(Box.createHorizontalStrut(10));
        megjelenit.setPreferredSize(new Dimension(100,50));
        jatekosInfo.add(megjelenit);

        JPanel felsoPanel=new JPanel();
        felsoPanel.setLayout(new BoxLayout(felsoPanel, BoxLayout.Y_AXIS)); 
        felsoPanel.add(jatekosInfo);
        felsoPanel.add(Box.createVerticalStrut(10));
        felsoPanel.add(koradatokp);

        add(felsoPanel);
    }
}
