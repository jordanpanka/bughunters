package bughunters.Grafika;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;

import bughunters.Gombafaj.Gombafaj;
import bughunters.Gombafaj.Gombafonal;
import bughunters.Gombafaj.Gombatest;
import bughunters.Gombafaj.Spora;
import bughunters.Rovar.Rovar;
import bughunters.Tekton.Tekton;

public class Grafika extends JPanel {
    
    private HashMap<Tekton, GTekton> tektonok;

    public HashMap<Tekton, GTekton> getTektonok() {
        return tektonok;
    }
    public void setTektonok(HashMap<Tekton, GTekton> tektonok) {
        this.tektonok = tektonok;
    }
    private HashMap<Gombatest,GGombatest> gombatestek;
    public HashMap<Gombatest, GGombatest> getGombatestek() {
        return gombatestek;
    }
    public void setGombatestek(HashMap<Gombatest, GGombatest> gombatestek) {
        this.gombatestek = gombatestek;
    }
    private HashMap<Gombafonal,GGombafonal> gombafonalak;
    public HashMap<Gombafonal, GGombafonal> getGombafonalak() {
        return gombafonalak;
    }
    public void setGombafonalak(HashMap<Gombafonal, GGombafonal> gombafonalak) {
        this.gombafonalak = gombafonalak;
    }
    private HashMap<Rovar, GRovar> rovarok;
    public HashMap<Rovar, GRovar> getRovarok() {
        return rovarok;
    }
    public void setRovarok(HashMap<Rovar, GRovar> rovarok) {
        this.rovarok = rovarok;
    }
    private HashMap<Spora,GSpora> sporak;

    public HashMap<Spora, GSpora> getSporak() {
        return sporak;
    }
    public void setSporak(HashMap<Spora, GSpora> sporak) {
        this.sporak = sporak;
    }
   
    public Grafika(){
        tektonok=new HashMap<>();
        sporak=new HashMap<>();
        rovarok=new HashMap<>();
        gombafonalak=new HashMap<>();
        gombatestek=new HashMap<>();
    }
    public void Draw(Tekton t, Graphics g){
        super.paintComponent(g);
        int szomszedokSzama = t.getSzomszedok().size();
        //System.out.println("Szomszédok száma: " + szomszedokSzama);

        int R = 150;  // Kör sugara
        int cX = 680; // Középpont X koordinátája
        int cY = 250; // Középpont Y koordinátája

        // Középső tekton pozíció beállítása
        tektonok.get(t).setX(cX);
        tektonok.get(t).setY(cY);
        
       // System.out.println("Tekton középen: X=" + cX + ", Y=" + cY);

        // Szomszédok pozícióinak kiszámítása és beállítása

        //System.out.println("Gombateste száma: " + gombatestek.size());

        if (szomszedokSzama != 0) {
            //System.out.println("Vannak szomszédok.");
            double szogLepes = 2 * Math.PI / szomszedokSzama;  // Egyenlő elosztás a kör mentén

            for (int i = 0; i < szomszedokSzama; i++) {
                double szog = i * szogLepes;  // Minden szomszédnál léptetjük a szöget
                int szomszedX = (int) (cX + R * Math.cos(szog));
                int szomszedY = (int) (cY + R * Math.sin(szog));

                Tekton szomszed = t.getSzomszedok().get(i);
                GTekton gSzomszed = tektonok.get(szomszed);

                if (gSzomszed != null) {
                    gSzomszed.setX(szomszedX);
                    gSzomszed.setY(szomszedY);
                    //gSzomszed.Draw(g);  // Azonnal ki is rajzoljuk
                    //System.out.println("Szomszéd tekton rajzolva X=" + szomszedX + ", Y=" + szomszedY);

                    gombatestek.forEach((gombatest, gg) -> {
                            if (gombatest.getTekton().equals(szomszed)) {
                                gg.setX(szomszedX);
                                gg.setY(szomszedY);
                               // gg.Draw(g);
                            }
                    });
                    
                    //sporaElhelyezesKorben(szomszed,g);
                }

            }
        }
        //tektonok.forEach((tekton,gtekton)->{System.out.println(" X"+gtekton.getX());});
        // Végül a középső tekton kirajzolása
        fonalrajzol(t,g);
        tektonok.get(t).Draw(g);
         if (szomszedokSzama != 0) {
            //System.out.println("Vannak szomszédok.");
            //double szogLepes = 2 * Math.PI / szomszedokSzama;  // Egyenlő elosztás a kör mentén

            for (int i = 0; i < szomszedokSzama; i++) {
                //double szog = i * szogLepes;  // Minden szomszédnál léptetjük a szöget
                //int szomszedX = (int) (cX + R * Math.cos(szog));
                //int szomszedY = (int) (cY + R * Math.sin(szog));

                Tekton szomszed = t.getSzomszedok().get(i);
                GTekton gSzomszed = tektonok.get(szomszed);

                if (gSzomszed != null) {
                   // gSzomszed.setX(szomszedX);
                   // gSzomszed.setY(szomszedY);
                    gSzomszed.Draw(g);  // Azonnal ki is rajzoljuk
                    //System.out.println("Szomszéd tekton rajzolva X=" + szomszedX + ", Y=" + szomszedY);
                    gombatestek.forEach((gombatest, gg) -> {
                            if (gombatest.getTekton().equals(szomszed)) {
                    
                                gg.Draw(g);
                            }
                    });
                    
                    sporaElhelyezesKorben(szomszed,g);

                }

            }
        }
        sporaElhelyezesKorben(t,g);
       
        //gombatestek beállítása csak a középső
        gombatestek.forEach((gombatest,gg)->{
            if(gombatest.getTekton().equals(t)){
                gg.setX(cX);
                gg.setY(cY);
                gg.Draw(g);
            }
        });
        

        // Rovarok kirajzolása a középső és a szomszédos tektonokra is
        List<Tekton> mindenRajzolando = new ArrayList<>();
        mindenRajzolando.add(t); // középső
        mindenRajzolando.addAll(t.getSzomszedok()); // szomszédok

        for (Tekton tekton : mindenRajzolando) {
            long count = rovarok.keySet().stream()
                .filter(rovar -> rovar.getTartozkodas().equals(tekton))
                .count();

            if (count == 0) continue;

            GTekton gTekton = tektonok.get(tekton);
            int baseX = gTekton.getX();
            int baseY = gTekton.getY();

            double angleStep = 2 * Math.PI / count;
            int radius = 30;
            int i = 0;

            for (Map.Entry<Rovar, GRovar> entry : rovarok.entrySet()) {
                Rovar rovar = entry.getKey();
                GRovar gg = entry.getValue();

                if (rovar.getTartozkodas().equals(tekton)) {
                    System.out.println("Rovar rajzolva X=" + baseX + ", Y=" + baseY+"tekton: "+rovar.getTartozkodas());
                    double angle = i * angleStep;
                    int x = (int)(baseX + radius * Math.cos(angle));
                    int y = (int)(baseY + radius * Math.sin(angle));
                    gg.setX(x);
                    gg.setY(y);
                    gg.Draw(g);
                    i++;
                }
            }
        }
        
    }


    public void sporaElhelyezesKorben(Tekton t, Graphics g) {
        int sugar =45;
        int cX=tektonok.get(t).getX();
        int cY=tektonok.get(t).getY();
        List<Spora> sporakList = t.getSporak();
        int sporaSzam = sporakList.size();

       // System.out.println("Spórák száma: " + sporaSzam);

        if (sporaSzam == 0) return;  // Nincs mit elhelyezni

        double szogLepes = 2 * Math.PI / sporaSzam;  // Egyenlő elosztás a kör mentén

        for (int i = 0; i < sporaSzam; i++) {
            double szog = i * szogLepes;
            int sporaX = (int) (cX + sugar * Math.cos(szog));
            int sporaY = (int) (cY + sugar * Math.sin(szog));

            Spora spora = sporakList.get(i);
            GSpora gSpora = sporak.get(spora);

            if (gSpora != null) {
                gSpora.setX(sporaX+44);
                gSpora.setY(sporaY+44);
                gSpora.Draw(g);  // Kirajzolás
                //System.out.println("Spóra rajzolva X=" + sporaX + ", Y=" + sporaY);
            }
        }

        
    }
    
            
    public void fonalrajzol(Tekton t, Graphics g) {
    //System.out.println("fonalrajzol");

    List<Tekton> tektonok2 = new ArrayList<>(t.getSzomszedok());
    tektonok2.add(t);

    List<Gombafonal> fonalak2 = t.getFonalak();
    Set<Gombafaj> gombafajok = new HashSet<>();
    fonalak2.forEach((fon)->{
        gombafajok.add(fon.getGombafaj());
    });
    
    List<Gombafaj> gombafajLista = new ArrayList<>(gombafajok);

    
    HashMap<Gombafaj,Integer> fajok=new HashMap<>();
    if(gombafajLista.size()!=0) fajok.put(gombafajLista.get(0),50);
   
    for(int i=1; i<gombafajok.size(); i++){
        if(i%2==0)fajok.put(gombafajLista.get(i),50-i*10);
        else fajok.put(gombafajLista.get(i),50+i*16);
    }
    for(int i=0; i<t.getSzomszedok().size();i++){
        Tekton t1=t.getSzomszedok().get(i);
        for(int j=0; j<t1.getFonalak().size();j++){
            fonalak2.add(t1.getFonalak().get(j));
        }
    }
    //System.out.println("grafika: " + gombafonalak.size());

    for (Gombafonal fonal : fonalak2) {
        Tekton t1 = fonal.getVegpont1();
        Tekton t2 = fonal.getVegpont2();
        if(tektonok2.contains(t1) && tektonok2.contains(t2)){//System.out.println("Benne van");
        //else{System.out.println("Az élet szép");}
        GTekton g1 = tektonok.get(t2);
        GTekton g2 = tektonok.get(t1);

        int x1 = g1.getX();
        //System.out.println(g1.getX());
        int y1 = g1.getY();
        //System.out.println(g1.getY());
        int x2 = g2.getX();
        //System.out.println(tektonok.get(t1).getX());
        int y2 = g2.getY();
        //System.out.println(g2.getY());
        GGombafonal gFonal = gombafonalak.get(fonal);
        if (gFonal != null) {
            gFonal.setX1(x1+fajok.get(fonal.getGombafaj()));
            gFonal.setY1(y1+fajok.get(fonal.getGombafaj()));
            gFonal.setX2(x2+fajok.get(fonal.getGombafaj()));
            gFonal.setY2(y2+fajok.get(fonal.getGombafaj()));
            
            gFonal.Draw(g);
        }
    }}
    }

  /*  public Gombafonal fonalKeres(int x, int y){
        y=y-230;
        System.out.println("fonalkeres");
        for (Map.Entry<Gombafonal, GGombafonal> entry : gombafonalak.entrySet()) {
            double X1=(double)entry.getValue().getX1();
            double X2=(double)entry.getValue().getX2();
            double Y1=(double)entry.getValue().getY1();
            double Y2=(double)entry.getValue().getY2();
            System.out.println("Koordináták: "+X1+","+","+Y1+","+X2+","+Y2);
            double dX=(double)entry.getValue().getX()-entry.getValue().getX2();
            double dY=(double)entry.getValue().getY()-entry.getValue().getY2();
            double t=((x-X1)*dX+(y-Y1)*dY)/(dX*dX+dY*dY);
            t=Math.max(0,Math.min(1,t));
            double projX = X1 + t * dX;
            double projY = Y1 + t * dY;
            double tav=Math.hypot(x - projX, y - projY);
            if(tav<=5){
                return entry.getKey();
            }
        }
        return null; // nem találtuk meg
    }*/
  /*  public Gombafonal fonalKeres(int x, int y){
        y=y-230;


   }*/
   /*public double ponTav(Point point1,Point point2){
        return Math.sqrt((point1.x - point2.x) * (point1.x - point2.x) + (point1.y - point2.y) * (point1.y - point2.y));
   }*/

    public Gombatest gombatestKeres(int x, int y) {
        y=y-230;
        //System.out.println("Képernyő"+x+y);

        for (Map.Entry<Gombatest, GGombatest> entry : gombatestek.entrySet()) {
            double xC =(double) entry.getValue().getX()+50;
            double yC = (double)entry.getValue().getY()+50;
            //System.out.println("Aktuális tektin:"+xC+yC);
            double d = Math.sqrt(Math.pow(x - xC, 2) + Math.pow(y - yC, 2));
            if (d <=35) {
                return entry.getKey(); // megtaláltuk
            }
        }
        return null; // nem találtuk meg
    }
    public Tekton tektonKeres(int x, int y){
        y=y-230;
        for (Map.Entry<Tekton, GTekton> entry : tektonok.entrySet()) {
            double xC = (double)entry.getValue().getX()+50;
            double yC = (double)entry.getValue().getY()+50;
            //System.out.println("Aktuális tektin:"+xC+yC);
            double d = Math.sqrt(Math.pow(x - xC, 2) + Math.pow(y - yC, 2));
            //System.out.println("Táv:"+d);
            if (d <=50 ) {
                return entry.getKey(); // megtaláltuk
            }
        }
        return null; // nem találtuk meg
    }
    public Rovar rovarKeres(int x, int y){
        //lehet nem jó
        y=y-230;
        System.out.println("Rovarkeres"+x+y);
        for (Map.Entry<Rovar, GRovar> entry : rovarok.entrySet()) {
            double xC = (double)entry.getValue().getX()+45;
            double yC = (double)entry.getValue().getY()+45;
            //System.out.println("Aktuális tektin:"+xC+yC);
            double d = Math.sqrt(Math.pow(x - xC, 2) + Math.pow(y - yC, 2));
            if (d < 22) {
                return entry.getKey(); // megtaláltuk
            }
        }
        return null; // nem találtuk meg
    }
    public Spora sporaKeres(int x, int y){
        y=y-230;
         for (Map.Entry<Spora, GSpora> entry : sporak.entrySet()) {
            double xC = (double)entry.getValue().getX()+3;
            double yC = (double)entry.getValue().getY()+3;
            double d = Math.sqrt(Math.pow(x - xC, 2) + Math.pow(y - yC, 2));
            if (d <=3) {
                return entry.getKey(); // megtaláltuk
            }
        }
        return null;
    }

}
