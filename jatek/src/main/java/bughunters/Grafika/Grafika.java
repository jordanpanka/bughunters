package bughunters.Grafika;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

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
        System.out.println("Szomszédok száma: " + szomszedokSzama);

        int R = 150;  // Kör sugara
        int cX = 680; // Középpont X koordinátája
        int cY = 250; // Középpont Y koordinátája

        // Középső tekton pozíció beállítása
        tektonok.get(t).setX(cX);
        tektonok.get(t).setY(cY);
        sporaElhelyezesKorben(t,g);
        
        System.out.println("Tekton középen: X=" + cX + ", Y=" + cY);

        // Szomszédok pozícióinak kiszámítása és beállítása
        if (szomszedokSzama != 0) {
            System.out.println("Vannak szomszédok.");
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
                    gSzomszed.Draw(g);  // Azonnal ki is rajzoljuk
                    System.out.println("Szomszéd tekton rajzolva X=" + szomszedX + ", Y=" + szomszedY);
                    gombatestek.forEach((gombatest, gg) -> {
                            if (gombatest.getTekton().equals(szomszed)) {
                                gg.setX(szomszedX);
                                gg.setY(szomszedY);
                                gg.Draw(g);
                            }
                    });
                    sporaElhelyezesKorben(szomszed,g);
                }

            }
        }

        // Végül a középső tekton kirajzolása
        tektonok.get(t).Draw(g);
            
        //gombatestek beállítása csak a középső
        gombatestek.forEach((gombatest,gg)->{
            if(gombatest.getTekton().equals(t)){
                gg.setX(cX);
                gg.setY(cY);
                gg.Draw(g);
            }
        });
/* 
      //rovarok szűrése
        if(rovarok==null)System.out.println("A rovarok null");
        Map<Rovar, GRovar> szurtRovar = rovarok.entrySet().stream()
        .filter(entry -> entry.getKey().getTartozkodas().equals(t)) // elérés a kulcs objektumhoz
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue
        ));
 
        int rovarSporaSzam=szurtSpora.size()+szurtRovar.size();
        double elfordulasSzoges=Math.PI/(double)t.getSporak().size();
        R=5;
        if(szurtRovar!=null){
            szurtRovar.get(0).setX(elozoX);
            szurtRovar.get(0).setY(elozoY);
            for(int i=1; i<szurtRovar.size();i++){
                double iranySzog=Math.atan2(elozoY-cY,elozoX-cX);
               double Szog=iranySzog+elfordulasSzoges;
               elozoX=(int)(cX+R*Math.cos(Szog));
               elozoY=(int) (cY+R*Math.cos(Szog));
               szurtRovar.get(i).setX(elozoX);
                szurtRovar.get(i).setY(elozoY);            
            }

        }
        if(szurtSpora!=null){
            szurtSpora.get(0).setX(elozoX);
            szurtSpora.get(0).setY(elozoY);
            for(int i=1; i<szurtSpora.size();i++){
                double iranySzog=Math.atan2(elozoY-cY,elozoX-cX);
               double Szog=iranySzog+elfordulasSzoges;
               elozoX=(int)(cX+R*Math.cos(Szog));
               elozoY=(int) (cY+R*Math.cos(Szog));
               szurtSpora.get(i).setX(elozoX);
                szurtSpora.get(i).setY(elozoY);            
            }
        }

        //repaint();
*/
    }
    public void sporaElhelyezesKorben(Tekton t, Graphics g) {
        int sugar =50;
        int cX=tektonok.get(t).getX();
        int cY=tektonok.get(t).getY();
    List<Spora> sporakList = t.getSporak();
    int sporaSzam = sporakList.size();

    if (sporaSzam == 0) return;  // Nincs mit elhelyezni

    double szogLepes = 2 * Math.PI / sporaSzam;  // Egyenlő elosztás a kör mentén

    for (int i = 0; i < sporaSzam; i++) {
        double szog = i * szogLepes;
        int sporaX = (int) (cX + sugar * Math.cos(szog));
        int sporaY = (int) (cY + sugar * Math.sin(szog));

        Spora spora = sporakList.get(i);
        GSpora gSpora = sporak.get(spora);

        if (gSpora != null) {
            gSpora.setX(sporaX);
            gSpora.setY(sporaY);
            gSpora.Draw(g);  // Kirajzolás
            System.out.println("Spóra rajzolva X=" + sporaX + ", Y=" + sporaY);
        }
        }
    }

    @Override
    public void paintComponent(Graphics g){
        
        //tektonok.forEach((kulcs, ertek)->{ertek.Draw(g);});
        //gombatestek.forEach((kulcs, ertek)->{ertek.Draw(g);});
        //gombafonalak.forEach((kulcs, ertek)->{ertek.Draw(g);});
        //rovarok.forEach((kulcs, ertek)->{ertek.Draw(g);});
        //sporak.forEach((kulcs, ertek)->{ertek.Draw(g);});
        System.out.println(tektonok.size());
        System.out.println(rovarok.size());
        System.out.println(gombatestek.size());
        

    }
    public Gombafonal fonalKeres(int x, int y){
        for (Map.Entry<Gombafonal, GGombafonal> entry : gombafonalak.entrySet()) {
            double X1=entry.getValue().getX();
            double X2=entry.getValue().getX2();
            double Y1=entry.getValue().getY();
            double Y2=entry.getValue().getY2();
            double dX=entry.getValue().getX()-entry.getValue().getX2();
            double dY=entry.getValue().getY()-entry.getValue().getY2();
            double t=((x-X1)*dX+(y-Y1)*dY)/(dX*dX+dY*dY);
            t=Math.max(0,Math.min(1,t));
            double projX = X1 + t * dX;
            double projY = Y1 + t * dY;
            double tav=Math.hypot(x - projX, y - projY);
            if(tav<=0.5){
                return entry.getKey();
            }
        }
        return null; // nem találtuk meg
    }
   
    public Gombatest gombatestKeres(int x, int y) {
        for (Map.Entry<Gombatest, GGombatest> entry : gombatestek.entrySet()) {
            double xC = entry.getValue().getX();
            double yC = entry.getValue().getY();
            double d = Math.sqrt(Math.pow(x - xC, 2) + Math.pow(y - yC, 2));
            if (d < 1) {
                return entry.getKey(); // megtaláltuk
            }
        }
        return null; // nem találtuk meg
    }
    public Tekton tektonKeres(int x, int y){
        for (Map.Entry<Tekton, GTekton> entry : tektonok.entrySet()) {
            double xC = entry.getValue().getX();
            double yC = entry.getValue().getY();
            double d = Math.sqrt(Math.pow(x - xC, 2) + Math.pow(y - yC, 2));
            if (d <= 10) {
                return entry.getKey(); // megtaláltuk
            }
        }
        return null; // nem találtuk meg
    }
    public Rovar rovarKeres(int x, int y){
        //lehet nem jó
        for (Map.Entry<Rovar, GRovar> entry : rovarok.entrySet()) {
            double xC = entry.getValue().getX();
            double yC = entry.getValue().getY();
            double d = Math.sqrt(Math.pow(x - xC, 2) + Math.pow(y - yC, 2));
            if (d < 1) {
                return entry.getKey(); // megtaláltuk
            }
        }
        return null; // nem találtuk meg
    }
    public Spora sporaKeres(int x, int y){
         for (Map.Entry<Spora, GSpora> entry : sporak.entrySet()) {
            double xC = entry.getValue().getX();
            double yC = entry.getValue().getY();
            double d = Math.sqrt(Math.pow(x - xC, 2) + Math.pow(y - yC, 2));
            if (d < 1) {
                return entry.getKey(); // megtaláltuk
            }
        }
        return null;
    }

}
