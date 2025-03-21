package bughunters;

import java.util.ArrayList;
import java.util.List;

public class Tekton implements FonalKezeles {
    private List<Tekton> szomszedok; //melyik tektonok a szomszédjai
    private List<Gombafaj> gombafajok; //megtalálható gombafajok listája
    private List<Spora> sporak; //megtalálható spórák listája

    public Tekton() {
        szomszedok = new ArrayList<>();
        gombafajok = new ArrayList<>();
        sporak = new ArrayList<>();
    }

    /***
     * @brief Gombafonalat ad hozzá a Tektonhoz
     * @param gf Gombafaj:
     * @param honnan Tekton:
     */
    public Gombafonal gombafonalAdd(Gombafaj gf, Tekton honnan){}

    /***
     * @brief A paraméterben kapott gombafaj gombatestet növesszen rajta
     * @param gf Gombafaj
     * @return 
     */
    public Gombatest gombatestNov(Gombafaj gf){}

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
    public boolean vanUt(Tekton a){return true;}

    /***
     * @brief
     * @param gf
     */
    @Override
    public void addFonal(Gombafonal gf){}

    /***
     * @brief
     * @param gf
     */
    @Override
    public void fonalSzakad(Gombafonal gf){}
}