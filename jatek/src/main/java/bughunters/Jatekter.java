package bughunters;

import java.util.ArrayList;
import java.util.List;

public class Jatekter {
    private List<Tekton> tektonok;

    public List<Tekton> getTektonok(){
        System.out.println("Meghívódik a Jatekter getTektonok metodusa.");
        return tektonok;
    }

    public Jatekter(){
        System.out.println("Meghívódik a Jatekter paraméter nélküli konstruktora.");
    }

    public Jatekter(ArrayList<Tekton> t){
        System.out.println("Meghívódik a Jatekter paraméteres konstruktora.");
        tektonok = t;
    }

    public void tores(int darab){
        System.out.println("Meghívódik a Jatekter tores metodusa.");
        tektonok.get(1).szomszedAllitas();
        /*
         * A szomszedAtallitas megkapja az újonnan létrehozott tektont,
         * és mindkettőn megcsinálja a szomszédok átállítását.
         * (azon is amin hívódik és azon is, amit megkapott paraméterként)
         * 
         * szomszedAtallitas(Tekton t)
         */

        Tekton t4 = new Tekton();
        // szomszedok beállítása!!!!!!!!!

        List<Gombafonal> ujszomszed = tektonok.get(1).gombafonalIgazitas();

        for (Gombafonal gombafonal : ujszomszed) {
            if(gombafonal.getVegpont1() == tektonok.get(1)){
                gombafonal.setVegpont1(t4);
            }
            else{
                gombafonal.setVegpont2(t4);
            }
        }

        for (Gombafonal fonal : ujszomszed) {
            t4.addFonal(fonal);
        }


        List<Tekton> t2szomszed = null;
        t2szomszed.add(t4);
        t2szomszed.add(tektonok.get(0));

        tektonok.get(1).setSzomszedok(t2szomszed);


        List<Tekton> t4szomszed = null;
        t4szomszed.add(tektonok.get(1));
        t4szomszed.add(tektonok.get(2));
        
        t4.setSzomszedok(t4szomszed);


        tektonAdd(t4);
    }
    public void tektonAdd(Tekton t){
        System.out.println("Meghívódik a Jatekter tektonAdd metodusa.");
        tektonok.add(t);
    }
}