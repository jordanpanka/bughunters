package bughunters;

import java.util.List;

public class Tekton {
    private List<Tekton> szomszedok;
    private List<Gombafonal> gombafonalak;
    private List<Spora> sporak;

    public abstract void gombafonalAdd(Gombafaj gf, Tekton honnan);
    public abstract void gombatestNov(Gombafaj gf);
    public void eszik(Spora sp, Rovar r){}
    public void sporaSzor(Gombafaj gf){}
    public void addSpora(Spora sp){}
    public void szomszedAllitas(){}
    public List<Gombafonal> gombafonalIgazitas(){}
    public boolean vanUt(Tekton a){}
}