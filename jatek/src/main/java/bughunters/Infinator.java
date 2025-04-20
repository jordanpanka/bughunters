package bughunters;

public class Infinator extends Tekton{

    /***
     * @brief Az összes rajta található fonalat átállítja Ép-é
     */
    @Override
    public void eletbenTartas(){
        for(Gombafonal gf: getFonalak()){
            gf.setAllapot(fonalAllapot.Ep);
        }
    }
}
