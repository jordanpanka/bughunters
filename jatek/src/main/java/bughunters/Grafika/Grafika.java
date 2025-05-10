package bughunters.Grafika;

import java.util.HashMap;

import bughunters.Gombafaj.Gombafonal;
import bughunters.Gombafaj.Gombatest;
import bughunters.Gombafaj.Spora;
import bughunters.Rovar.Rovar;
import bughunters.Tekton.Tekton;

public class Grafika {
    private HashMap<Object,Rajz> grafikusElemek;
    private HashMap<Tekton, GTekton> tektonok;
    private HashMap<Gombatest,GGombatest> gombatestek;
    private HashMap<Gombafonal,GGombafonal> gombafonalak;
    private HashMap<Rovar, GRovar> rovarok;
    private HashMap<Spora,GSpora> sporak;
    public void Draw(Tekton t){
        
        int szomszedokSzama=t.getSzomszedok().size();
        double R;
        double cX;
        double cY;
        double elozoX=cX;
        double elozoY=cY+R;
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

    
        for(int i=0; tektonok.size(); i++){
            tektonok.get(i).Draw();
        }
        for(int i=0; gombatestek.size(); i++){
            gombatestek.get(i).Draw();
        }
        for(int i=0; gombafonalak.size(); i++){
            gombafonalak.get(i).Draw();
        }
        for(int i=0; rovarok.size(); i++){
            rovarok.get(i).Draw();
        }
        for(int i=0; sporak.size(); i++){
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
            double d=Math.sqrt(x-xC,y-yC);
            if(d<=R){
                return gombatestek.get(i);
            }

        }
    }
    public Tekton tektonKeres(int x, int y){
        for(int i=0; i< tektonok.size();i++){
            double xC=tektonok.get(i).getX();
            double yC=tektonok.get(i).getY();
            double d=Math.sqrt(x-xC,y-yC);
            if(d<=R){
                return tektonok.get(i);
            }

        }
    }
    public Rovar rovarKeres(int x, int y){
        for(int i=0; i< rovarok.size();i++){
            double xC=rovarok.get(i).getX();
            double yC=rovarok.get(i).getY();
            double d=Math.sqrt(x-xC,y-yC);
            if(d<=R){
                return rovarok.get(i);
            }

        }
    }

}
