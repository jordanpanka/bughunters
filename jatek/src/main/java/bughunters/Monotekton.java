package bughunters;

public class Monotekton : Tekton {

    /***
     * @brief Létrehoz egy gombafonalat, amit hozzáad a gombafajhoz, ha a tektonra tehető gombafonal
     * @param gf Gombafaj: ehhez adja hozzá a gombafonalat
     * @return Létrehozott gombafonal
     */
    public Gombafonal gombafonalAdd(Gombafaj gf){}

    /***
     * @brief Gombatestet növeszt saját magán a paraméterből kapott Gombafaj típusának megfelelően
     * @param gf Gombafaj: melyik gombafaj gombatestét növeszti
     * @param honnan Tekton: ??????
     * @return Növesztett gombatest
     */
    public Gombatest gombatestNov(Gombafaj gf, Tekton honnan){}
}