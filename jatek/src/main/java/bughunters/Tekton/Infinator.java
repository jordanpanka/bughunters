package bughunters.Tekton;

import java.util.List;

import bughunters.Gombafaj.Gombafonal;
import bughunters.Gombafaj.Spora;
import bughunters.Gombafaj.fonalAllapot;

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

    @Override
    public Infinator cloneTekton(){
        Infinator ujTekton = new Infinator();
        return ujTekton;
    }
}
