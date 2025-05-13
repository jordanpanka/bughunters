package bughunters.Grafika;

import javax.swing.*;

import org.w3c.dom.events.MouseEvent;

import java.awt.*;
import java.util.HashMap;
import java.util.Set;

import bughunters.Egyeb.Jatek;
import bughunters.Egyeb.Parancskezelok;
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
        Set<String> nevek=game.getObjektumok().keySet();
        String[] nevek2;
        nevek.forEach(s->nevek2.add(s));
        tektonok=new JComboBox<>(nevek2);

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
            mouse.get("testNov").booleanValue(true);
        });
        Sporaszor.actionListener(e->{
            mouse.get("Sporaszor").booleanValue(true);
        });
        FonalNov.actionListener(e->{
            mouse.get("FonalNov").booleanValue(true);
        });
        RovarEves.actionListener(e->{
            mouse.get("Rovareves").booleanValue(true);
        });
        Maszik.actionListener(e->{
            mouse.get("Maszik").booleanValue(true);
        });
        Vag.actionListener(e->{
            mouse.get("Vag").booleanValue(true);
        });
        Eszik.actionListener(e->{
            mouse.get("Eszik").booleanValue(true);
        });
        megjelenit.actionListener(e->{
            String kivalasztott=tektonok.getSelectedItem();
            Tekton kiv =game.getObjektumok().get(kivalasztott);
            grafika.Draw(kiv);
        });
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if (mouse.get("testNov")) {
                    Tekton t =grafika.tektonKeres(e.getScreenX(), e.getScreenY());
                    game.gtNov(t);
                    mouse.get("testNov").booleanValue(false);
                }
                else if(mouse.get("Sporaszor")){
                    Gombatest gt=grafika.gombatestKeres(e.getScreenX(), e.getScreenY());
                    game.sporaszor(gt);
                    mouse.get("Sporaszor").booleanValue(false);
                }
                else if(mouse.get("FonalNov")){
                    if(!elsokattintas){
                        elsokattintas=true;
                        elsoTekton=grafika.tektonKeres(e.getScreenX(),e.getScreenY());
                    } 
                    else{
                        elsokattintas=false;
                        Tekton t2=grafika.tektonKeres(e.getScreenX(),e.getScreenY());
                        game.gfNov(elsoTekton,t2);
                        mouse.get("FonalNov").booleanValue(false);
                    }
                }
                else if( mouse.get("Rovareves")){
                    Rovar r=grafika.rovarKeres(e.getScreenX(),e.getScreenY());
                    game.rovart_eszik(r);
                    mouse.get("RovarEves").booleanValue(false);
                }
                else if(mouse.get("Maszik")){
                    if(!elsokattintas){
                        elsokattintas=true;
                        rovarKiv=grafika.rovarKeres(e.getScreenX(), e.getScreenY());
                    }
                    else{
                        elsokattintas=false;
                        Tekton t=grafika.tektonKeres(e.getScreenX(),e.getScreenY());
                        game.maszik(rovarKiv, t);
                        rovarKiv=null;
                        mouse.get("Maszik").booleanValue(false);
                    }
                }
                else if(mouse.get("Vag")){
                    if(!elsokattintas){
                        elsokattintas=true;
                        rovarKiv=grafika.rovarKeres(e.getScreenX(), e.getScreenY());
                    }
                    else{
                        elsokattintas=false;
                        Gombafonal gf=grafika.fonalKeres(e.getScreenX(),e.getScreenY());
                        game.vag(rovarKiv, gf);
                        rovarKiv=null;
                        mouse.get("Vag").booleanValue(false);
                    }
                }
                else if(mouse.get("Eszik")){
                    if(!elsokattintas){
                        elsokattintas=true;
                        rovarKiv=grafika.rovarKeres(e.getScreenX(), e.getScreenY());
                    }
                    else{
                        elsokattintas=false;
                        Spora sp=grafika.sporaKeres(e.getScreenX(),e.getScreenY());
                        game.eszik(rovarKiv,sp);
                        rovarKiv=null;
                        mouse.get("Vag").booleanValue(false);
                    }
                }

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

        foRovaraszPanel.add();
        foRovaraszPanel.add();
        foRovaraszPanel.add();
        add(foGombaszPanel);

    }
    public void gombokBeallitasa(JButton gomb, Dimension dimension){
        gomb.setMaximumSize(dimension);
        gomb.setPreferredSize(dimension);
        gomb.setFont(getFont());
    }
}

