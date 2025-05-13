package bughunters.Grafika;

import java.util.HashMap;

import bughunters.Gombafaj.Gombafonal;
import bughunters.Gombafaj.Gombatest;
import bughunters.Gombafaj.Spora;
import bughunters.Rovar.Rovar;
import bughunters.Tekton.Tekton;

public class Grafika  {
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
        int R;
        int cX;
        int cY;
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
            elozoX=cX+R*Math.cos(Szog);
            elozoY=cY+R*Math.cos(Szog);
            tektonok.get(t.getSzomszedok().get(0)).setX(elozoX);
            tektonok.get(t.getSzomszedok().get(0)).setY(elozoY);
        }
        //gombatestek beállítása

        //spórák beállítássa
        //rovar beállítása

    
        for(int i=0; i<tektonok.size(); i++){
            tektonok.get(i).Draw();
        }
        for(int i=0; i<gombatestek.size(); i++){
            gombatestek.get(i).Draw();
        }
        for(int i=0; i<gombafonalak.size(); i++){
            gombafonalak.get(i).Draw();
        }
        for(int i=0; i<rovarok.size(); i++){
            rovarok.get(i).Draw();
        }
        for(int i=0; i<sporak.size(); i++){
            sporak.get(i).Draw();
        }
    }
    public Gombafonal fonalKeres(int x, int y){
        for (int i=0; i<gombafonalak.size(); i++){
            double X1=gombafonalak.get(i).getX();
            double X2=gombafonalak.get(i).getX2();
            double Y1=gombafonalak.get(i).getY();
            double Y2=gombafonalak.get(i).getY2();
            double dX=gombafonalak.get(i).getX()-gombafonalak.get(i).getX2();
            double dY=gombafonalak.get(i).getY()-gombafonalak.get(i).getY2();
            double t=((x-X1)*dX+(y-Y1)*dY)/(dX*dX+dY*dY);
            t=Math.max(0,Math.min(1,t));
            double projX = X1 + t * dX;
            double projY = Y1 + t * dY;
            double tav=Math.hypot(x - projX, y - projY);
            if(tav<=0.5){
                return gombafonalak.get(i);
            }

        }
        return new Gombafonal();
    }
    public Gombatest gombatestKeres(int x, int y){
        for(int i=0; i< gombatestek.size();i++){
            double xC=gombatestek.get(i).getX();
            double yC=gombatestek.get(i).getY();
           // double d=Math.sqrt(x-xC,y-yC);
            double d = Math.sqrt(Math.pow(x - xC, 2) + Math.pow(y - yC, 2));

            if(d<=R){
                return gombatestek.get(i);
            }

        }
    }
    public Tekton tektonKeres(int x, int y){
        for(int i=0; i< tektonok.size();i++){
            int xC=tektonok.get(i).getX();
            int yC=tektonok.get(i).getY();
            //double d=Math.sqrt(x-xC,y-yC);
            double d = Math.sqrt(Math.pow(x - xC, 2) + Math.pow(y - yC, 2));

            if(d<=R){
                return tektonok.get(i);
            }

        }
    }
    public Rovar rovarKeres(int x, int y){
        for(int i=0; i< rovarok.size();i++){
            double xC=rovarok.get(i).getX();
            double yC=rovarok.get(i).getY();
            //double d=Math.sqrt(x-xC,y-yC);
            double d = Math.sqrt(Math.pow(x - xC, 2) + Math.pow(y - yC, 2));

            if(d<=4){
                return rovarok.get(i);
            }

        }
    }

}
