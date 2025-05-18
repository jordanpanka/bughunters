package bughunters.Grafika;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import bughunters.Egyeb.Jatekos;
import bughunters.Egyeb.Parancskezelok;
import bughunters.Gombafaj.Gombafonal;
import bughunters.Gombafaj.Gombatest;
import bughunters.Gombafaj.Spora;
import bughunters.Rovar.Rovar;
import bughunters.Tekton.Tekton;

public class JatekAblak1 extends JFrame {
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
    public JatekAblak1(Parancskezelok pk, Jatek jatek){
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
        //setLayout(new BorderLayout());

        //panelek létrehozása
        JPanel foGombaszPanel=new JPanel();
        JPanel foRovaraszPanel=new JPanel();

        JPanel gombaszGombok=new JPanel();
        JPanel rovaraszGombok=new JPanel();

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
        gombokLetreHozasa();
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

        //rovarasz vagy gombasz gombok
        if(game.getGombaszok().contains(game.getAktivJatekos())){
            isGombasz=true;
        }
        else{
            isGombasz=false;
        }

       
        Dimension gombMeret=new Dimension(100,50);

        //gombok lenyomása
        gombokLenyomasa(jatek);

        //kattintasok kezelése
        egerKattintasok(jatek);
        
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
        rovaraszGombok.add(Box.createHorizontalStrut(10));
        rovaraszGombok.add(Vag);
        gombaszGombok.add(Box.createHorizontalStrut(10));
        rovaraszGombok.add(Eszik);
        gombaszGombok.add(Box.createHorizontalStrut(10));
        gombaszGombok.add(korVege);

        foGombaszPanel.add(jatekosInfo,BorderLayout.NORTH);
        foGombaszPanel.add(grafika);
        //foGombaszPanel.add(gombaszGombok,BorderLayout.SOUTH);

        //foRovaraszPanel.add(jatekosInfo,BorderLayout.NORTH);
        //foRovaraszPanel.add(grafika);
        //foRovaraszPanel.add(rovaraszGombok,BorderLayout.SOUTH);
        // Layout definiálása a JFrame-hez
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        // Panelek hozzáadása névvel
        cardPanel.add(foGombaszPanel, "Gombasz");
        cardPanel.add(foRovaraszPanel, "Rovarasz");

        // Panel hozzáadása az ablakhoz
        //add(felsoPanel);

        // Aktív játékos alapján váltás
        Jatekos aktivJatekos = game.getAktivJatekos();
        if (aktivJatekos != null && game.getGombaszok().contains(aktivJatekos)) {
            cardLayout.show(cardPanel, "Gombasz");
        } else {
            cardLayout.show(cardPanel, "Rovarasz");
        }

    }
    public void gombokBeallitasa(JButton gomb, Dimension dimension){
        gomb.setMaximumSize(dimension);
        gomb.setPreferredSize(dimension);
        gomb.setFont(getFont());
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
            jatek.korEllenorzes(this);
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
                            jatek.korEllenorzes(JatekAblak1.this);
                        }catch(Exception ex){
                            HibaAblak hb=new HibaAblak(ex.getMessage());
                        }
                        
                    
                    mouse.put("testNov",false);
                }
                else if(mouse.get("Sporaszor")){
                    Gombatest gt=grafika.gombatestKeres(e.getX(), e.getY());
                    
                    try{
                           game.sporaszor(gt);
                            jatek.korEllenorzes(JatekAblak1.this);
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
                            jatek.korEllenorzes(JatekAblak1.this);
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
                            jatek.korEllenorzes(JatekAblak1.this);
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
                            jatek.korEllenorzes(JatekAblak1.this);
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
                            jatek.korEllenorzes(JatekAblak1.this);
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
                             jatek.korEllenorzes(JatekAblak1.this);
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
    public void gombaszPanelBeall(){

    }
    /*public void frissitPanelt() {
    Jatekos aktivJatekos = game.getAktivJatekos();
    if (aktivJatekos != null && game.getGombaszok().contains(aktivJatekos)) {
        cardLayout.show(cardPanel, "Gombasz");
    } else {
        cardLayout.show(cardPanel, "Rovarasz");
    }
}*/

}

