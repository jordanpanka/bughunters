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
import bughunters.Egyeb.Parancskezelok;
import bughunters.Gombafaj.Gombafonal;
import bughunters.Gombafaj.Gombatest;
import bughunters.Gombafaj.Spora;
import bughunters.Rovar.Rovar;
import bughunters.Tekton.Tekton;

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
    private boolean elsokattintas;
    private Tekton elsoTekton;
    private Rovar rovarKiv;
    private HashMap<String, Boolean> mouse;
    public JatekAblak(Parancskezelok pk, Jatek jatek){
        game=pk;
        mouse=new HashMap<>();
        grafika=new Grafika();
        mouse.put("testNov",false);
        mouse.put("Sporaszor",false);
        mouse.put("FonalNov",false);
        mouse.put("Rovareves", false);
        mouse.put("Maszik",false);
        mouse.put("Vag",false);
        mouse.put("Eszik",false);

        setTitle("Bughunters");
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
        /*Set<String> nevek=game.getObjektumok().keySet();
        List<String> nevek2=new ArrayList<String>();
        nevek.forEach(s->nevek2.add(s));
        tektonok=new JComboBox<>(nevek2);*/
        tektonok = new JComboBox<>(game.getObjektumok().keySet().toArray(new String[0]));


        // kör és játékos
        JLabel korAdatok=new JLabel("Kör : "+jatek.getKorSzam()+"Aktív játékos: "+game.getAktivJatekos().getNev());
        
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
        korVege.addActionListener(e->{
            game.endTurn();
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
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Boolean.TRUE.equals(mouse.get("testNov"))) {
                    Tekton t =grafika.tektonKeres(e.getX(), e.getY());
                    game.gtNov(t);
                    mouse.put("testNov",false);
                }
                else if(mouse.get("Sporaszor")){
                    Gombatest gt=grafika.gombatestKeres(e.getX(), e.getY());
                    game.sporaszor(gt);
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
                        game.gfNov(elsoTekton,t2);
                       mouse.put("FonalNov",false);
                    }
                }
                else if( mouse.get("Rovareves")){
                    Rovar r=grafika.rovarKeres(e.getX(),e.getY());
                    game.rovart_eszik(r);
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
                        game.maszik(rovarKiv, t);
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
                        game.vag(rovarKiv, gf);
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
                        game.eszik(rovarKiv,sp);
                        rovarKiv=null;
                         mouse.put("Eszik",false);
                    }
                }

            };
        }
            
        );
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


        foGombaszPanel.add(jatekosInfo,BorderLayout.NORTH);
        foGombaszPanel.add(grafika);
        foGombaszPanel.add(gombaszGombok,BorderLayout.SOUTH);

        foRovaraszPanel.add(jatekosInfo,BorderLayout.NORTH);
        foRovaraszPanel.add(grafika);
        foRovaraszPanel.add(rovaraszGombok,BorderLayout.SOUTH);
        add(foGombaszPanel);

    }
    public void gombokBeallitasa(JButton gomb, Dimension dimension){
        gomb.setMaximumSize(dimension);
        gomb.setPreferredSize(dimension);
        gomb.setFont(getFont());
    }
}

