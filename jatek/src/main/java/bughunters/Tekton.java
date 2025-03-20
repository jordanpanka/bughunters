package bughunters;

import java.util.List;

public class Tekton {
    private List<Tekton> szomszedok;
    private List<Gombafonal> gombafonalak;
    private List<Spora> sporak;

    public Tekton() {
        szomszedok = new ArrayList<Tekton>();
        gombafonalak = new ArrayList<Gombafonal>();
        sporak = new ArrayList<Spora>();
    }

    /***
     * @brief
     * @param gf
     * @param honnan
     */
    public abstract void gombafonalAdd(Gombafaj gf, Tekton honnan);

    /***
     * @brief
     * @param gf
     */
    public abstract void gombatestNov(Gombafaj gf);

    /***
     * @brief
     * @param sp
     * @param r
     */
    public void eszik(Spora sp, Rovar r){}

    /***
     * @brief
     * @param gf
     */
    public void sporaSzor(Gombafaj gf){}

    /***
     * @brief
     * @param sp
     */
    public void addSpora(Spora sp){}

    /***
     * @brief
     */
    public void szomszedAllitas(){}

    /***
     * @brief
     * @return
     */
    public List<Gombafonal> gombafonalIgazitas(){}

    /***
     * @brief
     * @param a
     * @return
     */
    public boolean vanUt(Tekton a){}
}