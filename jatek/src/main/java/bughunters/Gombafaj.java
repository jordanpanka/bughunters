package bughunters;

import java.util.ArrayList;

public class Gombafaj{
    private String nev;
    private int termelesIdeje;
    private int eddigNovesztettTestekSzama;
    private int gombatestFejlettsegIdo;
    private int gombatestEletSzama;
    private ArrayList<Gombatest> gombaTestek;
    private ArrayList<Gombafonal> gombafonalhalozat;

    public Gombafaj(String n, int tI, int ents, int gtfi,int gtesz){
        nev=n;
        termelesIdeje=tI;
        eddigNovesztettTestekSzama=ents;
        gombatestFejlettsegIdo=gtfi;
        gombatestEletSzama=gtesz;
        gombaTestek=new ArrayList();
        gombafonalhalozat=new ArrayList();
    }
    private void haldoklas(){

    }
    //void  fonalNov(t1: Tekton, t2: Tekton){}
    void addFonal(Gombafonal gf){
        gombafonalhalozat.add(gf);
    }
    void  fonalSzakad(Gombafonal gf){
        //haldoklas

    }
    //void testNovesztes(Tekton t){}
    void addTest( Gombatest g){
        gombaTestek.add(g);
    }
    //void sporaSzoras(Tekton t, Gombatest g){}
    //boolean vanUt(Tekton a, Tekton b){}
    //boolean lastChance(){}
    void toresHaldoklas(){}


}