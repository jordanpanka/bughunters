package bughunters;

import java.util.List;

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

    public Infinator(List<Tekton> szomszed, List<Gombafonal> gombafonal, List<Spora> spora){
        super(szomszed,gombafonal,spora);
    }

    public Infinator(){}
}
