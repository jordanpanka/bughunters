package bughunters.Grafika;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.JPanel;

import bughunters.Gombafaj.Gombafonal;
import bughunters.Gombafaj.Gombatest;
import bughunters.Gombafaj.Spora;
import bughunters.Rovar.Rovar;
import bughunters.Tekton.Tekton;

public class Grafika extends JPanel {
    //private HashMap<Object,Rajz> grafikusElemek;
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
    public void Draw(Tekton t, Graphics g){

        int szomszedokSzama=t.getSzomszedok().size();
        int R=8;
        int cX=2;
        int cY=3;
        int elozoX=cX;
        int elozoY=cY+R;
        //középső tekton pozíciójának beállítása
        tektonok.get(t).setX(2);
        tektonok.get(t).setY(3);
        
        //szomszedok poziciójának beállítása
        double elfordulasSzoge=Math.PI/(double)szomszedokSzama;
        tektonok.get(t.getSzomszedok().get(0)).setX(elozoX);
        tektonok.get(t.getSzomszedok().get(0)).setY(elozoY);
        for(int i=1; i<t.getSzomszedok().size(); i++){
            double iranySzog=Math.atan2(elozoY-cY,elozoX-cX);
            double Szog=iranySzog+elfordulasSzoge;
            elozoX=(int)(cX+R*Math.cos(Szog));
            elozoY=(int) (cY+R*Math.cos(Szog));
            tektonok.get(t.getSzomszedok().get(0)).setX(elozoX);
            tektonok.get(t.getSzomszedok().get(0)).setY(elozoY);
        }
        //gombatestek beállítása
        gombatestek.forEach((gombatest,gg)->{
            if(gombatest.getTekton().equals(t)){
                gg.setX(cX);
                gg.setY(cY);
            }
        });
        //spórák beállítássa
        Map<Spora, GSpora> szurtSpora = sporak.entrySet().stream()
        .filter(entry -> t.getSporak().contains(entry.getKey())) // elérés a kulcs objektumhoz
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue
        ));
        //EZ MÉG ÍGY NEM JÓÓÓÓÓÓÓÓÓÓÓÓÓÓÓÓÓÓÓÓÓÓÓÓÓÓÓÓÓÓ
        double elfordulasSzoges=Math.PI/(double)t.getSporak().size();
        R=2;
        tektonok.get(t.getSporak().get(0)).setX(elozoX);
        tektonok.get(t.getSporak().get(0)).setY(elozoY);
        for(int i=1; i<szurtSpora.size(); i++){
            double iranySzog=Math.atan2(elozoY-cY,elozoX-cX);
            double Szog=iranySzog+elfordulasSzoges;
            elozoX=(int)(cX+R*Math.cos(Szog));
            elozoY=(int) (cY+R*Math.cos(Szog));
            tektonok.get(t.getSporak().get(0)).setX(elozoX);
            tektonok.get(t.getSporak().get(0)).setY(elozoY);
        }
        ////////////////////////////////////////////////////////////////////////////////////
        //rovar beállítása
        Map<Rovar, GRovar> szurtRovar = rovarok.entrySet().stream()
        .filter(entry -> entry.getKey().getTartozkodas().equals(t)) // elérés a kulcs objektumhoz
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue
        ));

        paintComponent(g);
       
    }
    @Override
    public void paintComponent(Graphics g){
        tektonok.forEach((kulcs, ertek)->{ertek.Draw(g);});
        gombatestek.forEach((kulcs, ertek)->{ertek.Draw(g);});
        gombafonalak.forEach((kulcs, ertek)->{ertek.Draw(g);});
        rovarok.forEach((kulcs, ertek)->{ertek.Draw(g);});
        sporak.forEach((kulcs, ertek)->{ertek.Draw(g);});
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
