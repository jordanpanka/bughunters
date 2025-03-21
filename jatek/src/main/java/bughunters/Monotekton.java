package bughunters;

public class Monotekton extends Tekton {

    /***
     * @brief
     * @param gf
     * @return
     */
    @Override
    public Gombafonal gombafonalAdd(Gombafaj gf){
        return new Gombafonal();
    }

    /***
     * @brief
     * @param gf
     * @return
     */
    @Override
    public Gombatest gombatestNov(Gombafaj gf){
        return new Gombatest();
    }
}