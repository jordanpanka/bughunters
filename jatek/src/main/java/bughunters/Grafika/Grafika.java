package bughunters.Grafika;

import java.util.HashMap;

import bughunters.Gombafaj.Gombafonal;
import bughunters.Gombafaj.Gombatest;
import bughunters.Rovar.Rovar;
import bughunters.Tekton.Tekton;

public class Grafika {
   // private HashMap<Object,Rajz> grafikusElemek;
    public void Draw(Tekton t){

    }
    public Gombafonal fonalKeres(int x, int y){
        return new Gombafonal();
    }
    public Gombatest gombatestKeres(int x, int y){
        return new Gombatest();
    }
    public Tekton tektonKeres(int x, int y){
        return new Tekton();
    }
    public Rovar rovarKeres(int x, int y){
        return new Rovar();
    }

}
