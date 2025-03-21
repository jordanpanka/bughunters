package bughunters;

import java.util.ArrayList;
import java.util.List;

public class Jatekter {
    private ArrayList<Tekton> tektonok;

    public Jatekter(ArrayList<Tekton> t){
        tektonok = t;
    }

    public void tores(int darab){
        tektonok.get(1).szomszedAllitas();

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
    }
    public void tektonAdd(Tekton t){}
}