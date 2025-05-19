package bughunters.Grafika;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import bughunters.Egyeb.Jatek;
import bughunters.Egyeb.Parancskezelok;
import bughunters.Gombafaj.Gombafonal;
import bughunters.Gombafaj.Gombatest;
import bughunters.Gombafaj.Spora;
import bughunters.Rovar.Rovar;
import bughunters.Tekton.Tekton;

public class JatekAblak  extends JFrame{
   private Parancskezelok game;
   private JButton korVegeGombasz;
   private JButton korVegeRovarasz;
    private JButton TestNov;
    private JButton Sporaszor;
    private JButton FonalNov;
    private JButton RovarEves;
    private JButton Maszik;
    private JButton Vag;
    private JButton Eszik;
    private Grafika grafika;
    private JComboBox tektonokGombasz;
    private JComboBox tektonokRovarasz;
    private JButton megjelenitGombasz;
    private JButton megjelenitRovarasz;
    private boolean isGombasz;
    private boolean elsokattintas=false;
    private Tekton elsoTekton;
    private Rovar rovarKiv;
    private HashMap<String, Boolean> mouse;
    private  JPanel gombaszGombok;
    private JPanel rovaraszGombok;
    private Jatek jatek;
    private JLabel korAdatok;
   
    JPanel jatekosInfo;

    public JatekAblak(Parancskezelok pk, Jatek jatek){

        game=pk;
        mouse=new HashMap<>();
        grafika=new Grafika();
        this.jatek=jatek;

        if(game.getGombaszok().contains(game.getAktivJatekos())){
            isGombasz=true;
        }
        else{
            isGombasz=false;
        }

        setTitle("Bughunters");
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        
        //játékos infó panel beállítása
        jatekosInfo=new JPanel();
        jatekosInfo.setLayout(new BoxLayout(jatekosInfo, BoxLayout.X_AXIS));
        jatekosInfo.setMaximumSize(new Dimension(Integer.MAX_VALUE,200));
        jatekosInfo.setPreferredSize(new Dimension(Integer.MAX_VALUE,200));
        jatekosInfo.setLayout(new FlowLayout(FlowLayout.CENTER));

        //jatekosok adatainak megjelenítése
         korAdatok=new JLabel(" almy");
        jatekosAdatFrissit(jatekosInfo);

        //tektonok kiválasztása a ComboBoxból
        tektonokGombasz=new JComboBox<>();
        tektonokRovarasz=new JComboBox<>();
        tektonokJComboBox(tektonokGombasz);
        tektonokJComboBox(tektonokRovarasz);
       
        //gombok létrehozása
        gombokLetreHozasa();
        gombokMeretekBeallitas();
        gombokLenyomasa();
        egerKattintasok();

        // kör és játékos
        //JLabel korAdatok=new JLabel("Kör : "+jatek.getKorSzam()+" Aktív játékos: "+game.getAktivJatekos().getNev());

        JPanel koradatokp=new JPanel();
        koradatokp.add(korAdatok);
        koradatokp.setPreferredSize(new Dimension(Integer.MAX_VALUE,30));
        koradatokp.setMaximumSize(new Dimension(Integer.MAX_VALUE,30));

        //tektonok kiválasztása
        megjelenitGombasz.setMaximumSize(new Dimension(10, 30));
        megjelenitRovarasz.setMaximumSize(new Dimension(10, 30));

        //felső panel jatkosinfo+aktuális kör és játékos
        JPanel felsoPanel=new JPanel();
        felsoPanel.setLayout(new BoxLayout(felsoPanel, BoxLayout.Y_AXIS)); 
        felsoPanel.add(jatekosInfo);
        felsoPanel.add(koradatokp);
        felsoPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE,200));
        felsoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE,200));
        
        //gombasz gombok
        gombaszGombok=new JPanel();
        gombaszGombok.setLayout(new BoxLayout(gombaszGombok,BoxLayout.X_AXIS));
        gombaszPanelBeall();
       
        //rovaraszgombok
        rovaraszGombok=new JPanel();
        rovaraszGombok.setLayout(new BoxLayout(rovaraszGombok,BoxLayout.X_AXIS));
        rovaraszPanelBeall();
        

        grafika=game.getGrafika();
        grafika.setPreferredSize(new Dimension(Integer.MAX_VALUE, 600)); // Beállíthatsz más méretet is

        

        //JFramehez panelek hozzáadása
        System.out.println("felso:"+felsoPanel.getHeight());
        add(felsoPanel,BorderLayout.NORTH);
        add(grafika, BorderLayout.CENTER);
        frissitPanel();
        egerKattintasok();
        
    }
   
    public void gombokLetreHozasa(){
        korVegeGombasz=new JButton("Kör vége");
        korVegeRovarasz=new JButton("Kör vége");
        TestNov=new JButton("Test növesztés");
        Sporaszor=new JButton("Spóra szórás");
        FonalNov=new JButton("Fonal növesztése");
        RovarEves=new JButton("Rovar evése");
        Maszik=new JButton("Mászik");
        Vag=new JButton("Fonal vágása");
        Eszik=new JButton("Spóra evése");
        megjelenitGombasz=new JButton("Megjelenít");
        megjelenitRovarasz=new JButton("Megjelenít");

    }
    public void gombokMeretekBeallitas(){
        TestNov.setPreferredSize(new Dimension(130,50));
        Sporaszor.setPreferredSize(new Dimension(130,50));
        FonalNov.setPreferredSize(new Dimension(150,50));
        RovarEves.setPreferredSize(new Dimension(130,50));
        korVegeGombasz.setPreferredSize(new Dimension(100,50));
        megjelenitGombasz.setPreferredSize(new Dimension(100,50));
        Maszik.setPreferredSize(new Dimension(130,50));
        Vag.setPreferredSize(new Dimension(130,50));
        Eszik.setPreferredSize(new Dimension(130,50));
        korVegeRovarasz.setPreferredSize(new Dimension(100,50));
        megjelenitRovarasz.setPreferredSize(new Dimension(100,50));
    }
    public void gombokLenyomasa(){
        korVegeGombasz.addActionListener(e->{
            game.endTurn();
            akcioVege();
        });
         korVegeRovarasz.addActionListener(e->{
            game.endTurn();
            akcioVege();
            
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
            if(elsokattintas)System.out.println("elso");
        });
        Eszik.addActionListener(e->{
            mouse.put("Eszik",true);
        });
        megjelenitGombasz.addActionListener(e->{
            String kivalasztott=(String)tektonokGombasz.getSelectedItem();
            Object kiv =game.getObjektumok().get((Object)kivalasztott);
            grafika.Draw((Tekton)kiv,grafika.getGraphics());
        });
         megjelenitRovarasz.addActionListener(e->{
            String kivalasztott=(String)tektonokRovarasz.getSelectedItem();
            Object kiv =game.getObjektumok().get((Object)kivalasztott);
            grafika.Draw((Tekton)kiv,grafika.getGraphics());
        });
    }
    public void egerKattintasok(){
          this.addMouseListener(new MouseAdapter(){
           
            @Override
            public void mouseClicked(MouseEvent e) { 
                //if (e.getClickCount() != 1) return; 
                //Point grafikaPont = SwingUtilities.convertPoint(JatekAblak.this, e.getPoint(), grafika);
                System.out.println("Meghívódik a kttintás");
                if (Boolean.TRUE.equals(mouse.get("testNov"))) {
                    Tekton t =grafika.tektonKeres(e.getX(), e.getY());
                        try{
                            game.gtNov(t);
                            jatek.korEllenorzes(JatekAblak.this);
                        }catch(Exception ex){
                            HibaAblak hb=new HibaAblak(ex.getMessage());
                        }
                        
                    
                    mouse.put("testNov",false);
                }
                else if(Boolean.TRUE.equals(mouse.get("Sporaszor"))){
                    Gombatest gt=grafika.gombatestKeres(e.getX(), e.getY());
                    
                    try{
                            game.sporaszor(gt);
                            jatek.korEllenorzes(JatekAblak.this);
                        }catch(Exception ex){
                            HibaAblak hb=new HibaAblak(ex.getMessage());
                        }
                    mouse.put("Sporaszor",false);
                }
                else if(Boolean.TRUE.equals(mouse.get("FonalNov"))){
                    if(!elsokattintas){
                        elsokattintas=true;
                        elsoTekton=grafika.tektonKeres(e.getX(),e.getY());
                    } 
                    else{
                        elsokattintas=false;
                        Tekton t2=grafika.tektonKeres(e.getX(),e.getY());
                        
                        try{
                           game.gfnov(elsoTekton,t2);
                            jatek.korEllenorzes(JatekAblak.this);
                        }catch(Exception ex){
                            HibaAblak hb=new HibaAblak(ex.getMessage());
                        }
                       mouse.put("FonalNov",false);
                    }
                }
                else if(Boolean.TRUE.equals( mouse.get("Rovareves"))){
                    Rovar r=grafika.rovarKeres(e.getX(),e.getY());
                    
                    try{
                           game.rovart_eszik(r);
                            jatek.korEllenorzes(JatekAblak.this);
                        }catch(Exception ex){
                            HibaAblak hb=new HibaAblak(ex.getMessage());
                        }
                    mouse.put("Rovareves", false);
                }
                else if(Boolean.TRUE.equals(mouse.get("Maszik"))){
                    if(!elsokattintas){
                        elsokattintas=true;
                        rovarKiv=grafika.rovarKeres(e.getX(), e.getY());
                    }
                    else{
                        elsokattintas=false;
                        Tekton t=grafika.tektonKeres(e.getX(),e.getY());
                        
                        try{
                           game.maszik(rovarKiv, t);
                            jatek.korEllenorzes(JatekAblak.this);
                        }catch(Exception ex){
                            HibaAblak hb=new HibaAblak(ex.getMessage());
                        }
                        rovarKiv=null;
                        mouse.put("Maszik",false);
                    }
                }
                else if(Boolean.TRUE.equals(mouse.get("Vag"))){
                    if(!elsokattintas){
                        elsokattintas=true;
                        rovarKiv=grafika.rovarKeres(e.getX(), e.getY());
                        System.out.println("elso kett.");
                        return;
                    }
                    else{
                        elsokattintas=false;
                        Gombafonal gf=grafika.fonalKeres(e.getX(),e.getY());
                        System.out.println("masodik kett.");
                        try{
                           game.vag(rovarKiv, gf);
                            jatek.korEllenorzes(JatekAblak.this);
                        }catch(Exception ex){
                            HibaAblak hb=new HibaAblak(ex.getMessage());
                        }
                        rovarKiv=null;
                        mouse.put("Vag",false);
                    }
                }
                else if(Boolean.TRUE.equals(mouse.get("Eszik"))){
                    if(!elsokattintas){
                        elsokattintas=true;
                        rovarKiv=grafika.rovarKeres(e.getX(), e.getY());
                    }
                    else{
                        elsokattintas=false;
                        Spora sp=grafika.sporaKeres(e.getX(),e.getY());
                       
                        try{
                            game.eszik(rovarKiv,sp);
                             jatek.korEllenorzes(JatekAblak.this);
                        }catch(Exception ex){
                            HibaAblak hb=new HibaAblak(ex.getMessage());
                        }
                        rovarKiv=null;
                         mouse.put("Eszik",false);
                    }
                }

                jatekosAdatFrissit(jatekosInfo);
            };
             
        }
        );
    }
    public void gombaszPanelBeall(){
        
        gombaszGombok.add(TestNov);
        gombaszGombok.add(Box.createHorizontalStrut(10));
        
        gombaszGombok.add(Sporaszor);
        gombaszGombok.add(Box.createHorizontalStrut(10));
        
        gombaszGombok.add(FonalNov);
        gombaszGombok.add(Box.createHorizontalStrut(10));
       
        gombaszGombok.add(RovarEves);
        gombaszGombok.add(Box.createHorizontalStrut(10));
        
        gombaszGombok.add(korVegeGombasz);
        gombaszGombok.add(Box.createHorizontalStrut(10));

        gombaszGombok.add(tektonokGombasz);
        gombaszGombok.add(Box.createHorizontalStrut(10));
       
        gombaszGombok.add(megjelenitGombasz);

        gombaszGombok.setBorder(BorderFactory.createEmptyBorder(20, 0, 50, 0));
        gombaszGombok.setLayout(new FlowLayout(FlowLayout.CENTER));
    }
    public void rovaraszPanelBeall(){
        
        rovaraszGombok.add(Maszik);
        rovaraszGombok.add(Box.createHorizontalStrut(10));
       
        rovaraszGombok.add(Vag);
        rovaraszGombok.add(Box.createHorizontalStrut(10));
        
        rovaraszGombok.add(Eszik);
        rovaraszGombok.add(Box.createHorizontalStrut(10));
       
        rovaraszGombok.add(korVegeRovarasz);
        rovaraszGombok.add(Box.createHorizontalStrut(10));

        rovaraszGombok.add(tektonokRovarasz);
        rovaraszGombok.add(Box.createHorizontalStrut(10));
        
        rovaraszGombok.add(megjelenitRovarasz);

        rovaraszGombok.setBorder(BorderFactory.createEmptyBorder(20, 0, 50, 0));
        rovaraszGombok.setLayout(new FlowLayout(FlowLayout.CENTER));
    }
    public void tektonokJComboBox(JComboBox tektonok){
        tektonok.removeAllItems();
        List<String> tekton=game.getTektonNevList();
        tektonok.setPreferredSize(new Dimension(130,50));
        tektonok.setMaximumSize(new Dimension(50, 30));

        tekton.forEach((String s)->{
            System.out.println(s);
            tektonok.addItem(s);
        });
    }
    
    public void frissitPanel(){
        //még nem teljes
        if(game.getGombaszok().contains(game.getAktivJatekos())){
            isGombasz=true;
        }
        else{
            isGombasz=false;
        }
        //isGombasz=false;

        // Először eltávolítjuk a meglévő SOUTH panel(eke)t
        remove(gombaszGombok);
        remove(rovaraszGombok);

        tektonokJComboBox(tektonokGombasz);
        tektonokJComboBox(tektonokRovarasz);

        if(isGombasz){
            add(gombaszGombok,BorderLayout.SOUTH);

        }
        else{
           
        add(rovaraszGombok,BorderLayout.SOUTH);

        }
        // Frissítjük a nézetet
        revalidate();
        repaint();
    }
    public void akcioVege(){
         boolean ujJatekos=jatek.korEllenorzes(this);
         System.out.println("ujjatekos: "+game.getAktivJatekos().getNev());
        jatekosAdatFrissit(jatekosInfo);
            if(ujJatekos){
                frissitPanel();
            }
    }
    public void jatekosAdatFrissit(JPanel jatekosInfo){
        jatekosInfo.removeAll();
        for(int i=0; i<game.getJatekosok().size(); i++){

            JPanel jatekos=new JPanel();
            jatekos.setLayout(new BoxLayout(jatekos, BoxLayout.Y_AXIS)); 

            JLabel nev=new JLabel(game.getJatekosok().get(i).getNev());
            JLabel akcio=new JLabel("Akciók: "+game.getJatekosok().get(i).getakcioSzama());
            JLabel fajta=new JLabel(game.getJatekosok().get(i).szerepKor());
            JLabel pontok=new JLabel("Pontok: "+game.getJatekosok().get(i).getGyozelmiPontok());
            JLabel szin = new JLabel("Szin: "+game.getColorStringByJatekos(game.getJatekosok().get(i)));

            jatekos.add(nev);
            jatekos.add(Box.createVerticalStrut(10));
            jatekos.add(akcio);
            jatekos.add(Box.createVerticalStrut(10));
            jatekos.add(fajta);
            jatekos.add(Box.createVerticalStrut(10));
            jatekos.add(pontok);
            jatekos.add(Box.createVerticalStrut(10));
            jatekos.add(szin);
            jatekos.add(Box.createVerticalStrut(10));

            jatekos.setMaximumSize(new Dimension(150, 200));
            jatekos.setPreferredSize(new Dimension(150, 200));
            jatekosInfo.add(jatekos);

        
        }

        korAdatok.setText("Kör : "+jatek.getKorSzam()+" Aktív játékos: "+game.getAktivJatekos().getNev());

        // Frissítés a grafikus felületen
        jatekosInfo.revalidate();
        jatekosInfo.repaint();
    }
}
