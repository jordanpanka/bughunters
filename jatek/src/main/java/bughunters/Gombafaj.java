package bughunters;

import java.util.ArrayList;

public class Gombafaj implements FonalKezeles{
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
        System.out.println("Létrejött egy új gombafaj:" );
    }
    public String getNev() {
        return nev;
    }
    
    public void setNev(String nev) {
        this.nev = nev;
    }
    
    public int getTermelesIdeje() {
        return termelesIdeje;
    }
    
    public void setTermelesIdeje(int termelesIdeje) {
        this.termelesIdeje = termelesIdeje;
    }
    
    public int getEddigNovesztettTestekSzama() {
        return eddigNovesztettTestekSzama;
    }
    
    public void setEddigNovesztettTestekSzama(int eddigNovesztettTestekSzama) {
        this.eddigNovesztettTestekSzama = eddigNovesztettTestekSzama;
    }
    
    public int getGombatestFejlettsegIdo() {
        return gombatestFejlettsegIdo;
    }
    
    public void setGombatestFejlettsegIdo(int gombatestFejlettsegIdo) {
        this.gombatestFejlettsegIdo = gombatestFejlettsegIdo;
    }
    
    public int getGombatestEletSzama() {
        return gombatestEletSzama;
    }
    
    public void setGombatestEletSzama(int gombatestEletSzama) {
        this.gombatestEletSzama = gombatestEletSzama;
    }
    
    public ArrayList<Gombatest> getGombaTestek() {
        return gombaTestek;
    }
    
    public void setGombaTestek(ArrayList<Gombatest> gombaTestek) {
        this.gombaTestek = gombaTestek;
    }
    
    public ArrayList<Gombafonal> getGombafonalhalozat() {
        return gombafonalhalozat;
    }
    
    public void setGombafonalhalozat(ArrayList<Gombafonal> gombafonalhalozat) {
        this.gombafonalhalozat = gombafonalhalozat;
    }
    
    private void haldoklas(){
        
    }
    public void  fonalNov(Tekton hova, Tekton honnan){
        System.out.println("Meghívódott a Gombafaj fonalNov metódusa.");
        try{
            Gombafonal uj= hova.gombafonalAdd(this,honnan);
            addFonal(uj);
            hova.addFonal(uj);
        }catch(Exception e)
        {
            e.printStackTrace();
        } 
    }
    public void addFonal(Gombafonal gf){
        System.out.println("Meghívódik a gombafaj addFonal metódusa.");
        if (gombafonalhalozat == null) {
            gombafonalhalozat = new ArrayList<>();
        }
        gombafonalhalozat.add(gf);
    }
    public void  fonalSzakad(Gombafonal gf){
        System.out.println("Meghivodik a gombafaj fonalSzakad metódusa.");
        //haldoklas

    }
    public void testNovesztes(Tekton t){
        System.out.println("Meghívódik a gombafaj testNovesztes metodusa.");
        try{
            Gombatest uj=t.gombatestNov(this);
            addTest(uj);

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    void addTest( Gombatest gt){
        System.out.println("Meghívódik a gombafaj addTest metodusa.");
        if (gombaTestek == null) {
            gombaTestek = new ArrayList<>();
        }
        gombaTestek.add(gt);
    }
    public void sporaSzoras(Tekton t, Gombatest g){
        System.out.println("Meghívódik a Gombafaj sporaSzoras metodusa.");
        try
        {
            g.urit();
            t.sporaSzor(this);
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    public void lastChance(){
        for(int i=0; i<gombafonalhalozat.size(); i++){
            if(gombafonalhalozat.get(i).getAllapot()==Haldoklo)
            {

            }
            else if(gombafonalhalozat.get(i).getAllapot()==UtolsoEsely){

            }
            else{

            }
            
        }
    }
    public void toresHaldoklas(){
        System.out.println("Meghívódik a gombafaj toresHaldoklas metodusa.");
        haldoklas();
    }


}