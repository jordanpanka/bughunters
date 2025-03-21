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

        List<Gombafonal> ujszomszed = tektonok.get(1).gombafonalIgazitas();

        Tekton t4 = new Tekton(ujszomszed);

        for (List<Tekton> szomszed : t4.getSzomszedok) {
            szomszed.gombafonalAdd()
        }
    }
    public void tektonAdd(Tekton t){}
}