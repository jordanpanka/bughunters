package bughunters.Grafika;

import javax.swing.*;

//import org.w3c.dom.events.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.List;

import bughunters.Egyeb.Jatek;
import bughunters.Egyeb.Jatekos;
import bughunters.Egyeb.Parancskezelok;
import bughunters.Gombafaj.Gombafonal;
import bughunters.Gombafaj.Gombatest;
import bughunters.Gombafaj.Spora;
import bughunters.Rovar.Rovar;
import bughunters.Tekton.Tekton;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.HashMap;
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
import bughunters.Gombafaj.Gombafonal;
import bughunters.Gombafaj.Gombatest;
import bughunters.Gombafaj.Spora;
import bughunters.Rovar.Rovar;
import bughunters.Tekton.Tekton;

public class Proba  extends JFrame{
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
    private boolean elsokattintas;
    private Tekton elsoTekton;
    private Rovar rovarKiv;
    private HashMap<String, Boolean> mouse;
   

    public Proba(Parancskezelok pk, Jatek jatek){
        game=pk;
        mouse=new HashMap<>();
        grafika=new Grafika();

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
        tektonok.setPreferredSize(new Dimension(100,50));
        
        tekton.forEach((String s)->{
            System.out.println(s);
            tektonok.addItem(s);
        });
       
        //gombok létrehozása
        gombokLetreHozasa();

        //megjelenit=new JButton("Megjelenít");
        // kör és játékos
        JLabel korAdatok=new JLabel("Kör : "+jatek.getKorSzam()+" Aktív játékos: "+game.getAktivJatekos().getNev());

        JPanel koradatokp=new JPanel();
        koradatokp.add(korAdatok);
        koradatokp.setPreferredSize(new Dimension(600,40));
        koradatokp.setMaximumSize(new Dimension(600,40));

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
        //felsoPanel.add(Box.createVerticalStrut(10));
        felsoPanel.add(koradatokp);
        felsoPanel.add(Box.createVerticalStrut(10));
        grafika.setPreferredSize(new Dimension(30,30));
        grafika.setMaximumSize(new Dimension(30,30));
        felsoPanel.add(grafika);
        add(felsoPanel);


        JPanel gombaszGombok=new JPanel();
        gombaszGombok.setLayout(new BoxLayout(gombaszGombok,BoxLayout.X_AXIS));

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

        felsoPanel.add(gombaszGombok);

        JPanel rovaraszGombok=new JPanel();
        rovaraszGombok.setLayout(new BoxLayout(rovaraszGombok,BoxLayout.X_AXIS));

        //rovarasz gombok
        rovaraszGombok.add(Maszik);
        rovaraszGombok.add(Box.createHorizontalStrut(10));
        rovaraszGombok.add(Vag);
        rovaraszGombok.add(Box.createHorizontalStrut(10));
        rovaraszGombok.add(Eszik);
        rovaraszGombok.add(Box.createHorizontalStrut(10));
        rovaraszGombok.add(korVege);

    }
    public void gombokLetreHozasa(){
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
    public void gombokLenyomasa(Jatek jatek){
        korVege.addActionListener(e->{
            game.endTurn();
            jatek.korEllenorzes();
        });
        TestNov.addActionListener(e->{
            mouse.put("testNov",true);
        });
        Sporaszor.addActionListener(e->{
            mouse.put("Sporaszor",true);
        });
        FonalNov.addActionListener(e->{
            mouse.put("FonalNov",true);
        });
        RovarEves.addActionListener(e->{
            mouse.put("Rovareves",true);
        });
        Maszik.addActionListener(e->{
            mouse.put("Maszik",true);
        });
        Vag.addActionListener(e->{
            mouse.put("Vag",true);
        });
        Eszik.addActionListener(e->{
            mouse.put("Eszik",true);
        });
        megjelenit.addActionListener(e->{
            String kivalasztott=(String)tektonok.getSelectedItem();
            Object kiv =game.getObjektumok().get((Object)kivalasztott);
            grafika.Draw((Tekton)kiv,grafika.getGraphics());
        });
    }
    public void egerKattintasok(Jatek jatek){
          this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Boolean.TRUE.equals(mouse.get("testNov"))) {
                    Tekton t =grafika.tektonKeres(e.getX(), e.getY());
                        try{
                            game.gtNov(t);
                            jatek.korEllenorzes();
                        }catch(Exception ex){
                            HibaAblak hb=new HibaAblak(ex.getMessage());
                        }
                        
                    
                    mouse.put("testNov",false);
                }
                else if(mouse.get("Sporaszor")){
                    Gombatest gt=grafika.gombatestKeres(e.getX(), e.getY());
                    
                    try{
                           game.sporaszor(gt);
                            jatek.korEllenorzes();
                        }catch(Exception ex){
                            HibaAblak hb=new HibaAblak(ex.getMessage());
                        }
                    mouse.put("Sporaszor",false);
                }
                else if(mouse.get("FonalNov")){
                    if(!elsokattintas){
                        elsokattintas=true;
                        elsoTekton=grafika.tektonKeres(e.getX(),e.getY());
                    } 
                    else{
                        elsokattintas=false;
                        Tekton t2=grafika.tektonKeres(e.getX(),e.getY());
                        
                        try{
                           game.gfnov(elsoTekton,t2);
                            jatek.korEllenorzes();
                        }catch(Exception ex){
                            HibaAblak hb=new HibaAblak(ex.getMessage());
                        }
                       mouse.put("FonalNov",false);
                    }
                }
                else if( mouse.get("Rovareves")){
                    Rovar r=grafika.rovarKeres(e.getX(),e.getY());
                    
                    try{
                           game.rovart_eszik(r);
                            jatek.korEllenorzes();
                        }catch(Exception ex){
                            HibaAblak hb=new HibaAblak(ex.getMessage());
                        }
                    mouse.put("Rovareves", false);
                }
                else if(mouse.get("Maszik")){
                    if(!elsokattintas){
                        elsokattintas=true;
                        rovarKiv=grafika.rovarKeres(e.getX(), e.getY());
                    }
                    else{
                        elsokattintas=false;
                        Tekton t=grafika.tektonKeres(e.getX(),e.getY());
                        
                        try{
                           game.maszik(rovarKiv, t);
                            jatek.korEllenorzes();
                        }catch(Exception ex){
                            HibaAblak hb=new HibaAblak(ex.getMessage());
                        }
                        rovarKiv=null;
                        mouse.put("Maszik",false);
                    }
                }
                else if(mouse.get("Vag")){
                    if(!elsokattintas){
                        elsokattintas=true;
                        rovarKiv=grafika.rovarKeres(e.getX(), e.getY());
                    }
                    else{
                        elsokattintas=false;
                        Gombafonal gf=grafika.fonalKeres(e.getX(),e.getY());
                        
                        try{
                           game.vag(rovarKiv, gf);
                            jatek.korEllenorzes();
                        }catch(Exception ex){
                            HibaAblak hb=new HibaAblak(ex.getMessage());
                        }
                        rovarKiv=null;
                        mouse.put("Vag",false);
                    }
                }
                else if(mouse.get("Eszik")){
                    if(!elsokattintas){
                        elsokattintas=true;
                        rovarKiv=grafika.rovarKeres(e.getX(), e.getY());
                    }
                    else{
                        elsokattintas=false;
                        Spora sp=grafika.sporaKeres(e.getX(),e.getY());
                       
                        try{
                            game.eszik(rovarKiv,sp);
                             jatek.korEllenorzes();
                        }catch(Exception ex){
                            HibaAblak hb=new HibaAblak(ex.getMessage());
                        }
                        rovarKiv=null;
                         mouse.put("Eszik",false);
                    }
                }

            };
        }
            
        );
    }

}
