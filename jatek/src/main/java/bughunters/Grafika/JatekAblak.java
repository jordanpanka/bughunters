package bughunters.Grafika;

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
    private boolean isGombasz;
    public JatekAblak(){
        setTitle("Bughunters");
       // setSize(700,600);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        //panelek létrehozása
        JPanel foGombaszPanel=new JPanel();
        JPanel foRovaraszPanel=new JPanel();

        JPanel jatekosInfo=new JPanel();

        JPanel gombaszGombok=new JPanel();
        JPanel rovaraszGombok=new JPanel();

        //jatekosok adatainak megjelenítése
        for(int i=0; i<game.getJatekosok().size(); i++){
            JPanel jatekos=new JPanel();

            JLabel nev=new JLabel(game.getJatekosok().get(i).getNev());
            JLabel akcio=new JLabel("Akciók: "+game.getJatekosok().get(i).getakcioSzama());
            JLabel fajta=new JLabel(game.getJatekosok().get(i).szerepKor());
            JLabel pontok=new JLabel("Pontok: "+game.getJatekosok().get(i).getGyozelmiPontok());
            
            jatekos.add(jatekos);
            jatekos.add(Box.createVerticalStrut(10));
            jatekos.add(akcio);
            jatekos.add(Box.createVerticalStrut(10));
            jatekos.add(fajta);
            jatekos.add(Box.createVerticalStrut(10));
            jatekos.add(pontok);
            jatekos.add(Box.createVerticalStrut(10));
            
            jatekosInfo.add(jatekos);
        }

        // kör és játékos
        JLabel korAdatok=new JLabel("Kör : "+"Aktív játékos: "+game.getAktivJatekos().getNev());
        //rovarasz vagy gombasz gombok
        if(game.getGombaszok().contains(game.getAktivJatekos())){
            isGombasz=true;
        }
        else{
            isGombasz=false;
        }

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

        Dimension gombMeret=new Dimension(100,50);

        //gombok lenyomása



        //gombasz gombok
        gombaszGombok.add(TestNov);
        gombaszGombok.add(Box.createHorizontalStrut(10));
        gombaszGombok.add(Sporaszor);
        gombaszGombok.add(Box.createHorizontalStrut(10));
        gombaszGombok.add(FonalNov);
        gombaszGombok.add(Box.createHorizontalStrut(10));
        gombaszGombok.add(RovarEves);
        gombaszGombok.add(Box.createHorizontalStrut(10));
        gombaszGombok.add(korVege);


        //rovarasz gombok
        rovaraszGombok.add(Maszik);
        gombaszGombok.add(Box.createHorizontalStrut(10));
        rovaraszGombok.add(Vag);
        gombaszGombok.add(Box.createHorizontalStrut(10));
        rovaraszGombok.add(Eszik);
        gombaszGombok.add(Box.createHorizontalStrut(10));
        gombaszGombok.add(korVege);



        foGombaszPanel.add(jatekosInfo);
        foGombaszPanel.add(gombaszGombok);
        add(foGombaszPanel);

    }
    void gombokBeallitasa(JButton gomb, Dimension dimension){
        gomb.setMaximumSize(dimension);
        gomb.setPreferredSize(dimension);
        gomb.setFont(getFont());
    }
}
