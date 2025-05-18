package bughunters.Tekton;

import java.util.List;

import bughunters.Gombafaj.Gombafaj;
import bughunters.Gombafaj.Gombafonal;
import bughunters.Gombafaj.Gombatest;
import bughunters.Gombafaj.Spora;

public class Puritekton extends Tekton {

    /***
     * @brief Megpróbál gombatestet növeszteni
     * @param gf Gombafaj: ehhez tartozó testet próbál növeszteni
     * @throws Exception nem lehet gombatestet növeszteni
     */
    @Override
    public Gombatest gombatestNov(Gombafaj gf,boolean sporaval) throws Exception{
        //System.out.println("Meghívódik a Puritekton gombatestNov metódusa.");
        throw new Exception("Erre a tektonra nem lehet gombatestet növeszteni.");
    }

     public Puritekton(List<Tekton> szomszed, List<Gombafonal> gombafonal, List<Spora> spora){
        super(szomszed,gombafonal,spora);
    }

    public Puritekton(){}

    @Override
    public Puritekton cloneTekton() {
        Puritekton clonedTekton = new Puritekton();
        return clonedTekton;
    }
}