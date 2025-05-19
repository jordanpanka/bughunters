package bughunters.Tekton;

import java.util.List;

import bughunters.Gombafaj.Gombafaj;
import bughunters.Gombafaj.Gombafonal;
import bughunters.Gombafaj.Gombatest;
import bughunters.Gombafaj.Spora;

public class Monotekton extends Tekton {
    /***
     * @brief Gombafonalat ad hozzá a Tektonhoz
     * @param gf Gombafaj: Aktuális gombafaj
     * @param honnan Tekton: Vizsgáljuk hogy erről a tektonról vezet e fonal az aktuálisra
     * @return Növesztett gombafonalat adja vissza
     * @exception Exception akkor dobódik ha nem tud oda növeszteni fonalat
     */
    @Override
    public Gombafonal gombafonalAdd(Gombafaj g, List<Gombafaj> erintofajok, Tekton honnan) throws Exception{
        //System.out.println("Meghívódik a Monotekton gombafonalAdd metódusa.");
        //Különbség a tektontól: NEM nőhet fonal rá, hogyha rajta van már egy MÁSIK gombafaj gombafonala vagy teste
       
       // 1. Szomszédság ellenőrzése
        if (!szomszedok.contains(honnan)) {
            throw new Exception("A két tekton nem szomszédos.");
        }

        // 2. Van-e már ilyen fonal?
            for (Gombafonal gfonal : gombafonalak) {
                if ((gfonal.getVegpont1().equals(this) && gfonal.getVegpont2().equals(honnan)) && gfonal.getGombafaj().equals(g) ||
                    (gfonal.getVegpont1().equals(honnan) && gfonal.getVegpont2().equals(this)) && gfonal.getGombafaj().equals(g)) {
                    throw new Exception("Már van ilyen fonal.");
                }
            }

        // 3. Monotekton speciális szabályai:
        //    - Csak ugyanaz a gombafaj lehet rajta (test vagy fonal formájában)
        //    - Ha van rajta más gombafaj, akkor NEM lehet rá fonalat növeszteni.

        // 3.1. Van-e már más gombafajhoz tartozó test vagy fonal a Monotektonon?
        if (!gombafonalak.isEmpty()) {
            Gombafonal existingFonal = gombafonalak.get(0);
            if (!existingFonal.getGombafaj().equals(g)) {
                throw new Exception("Nem lehet fonalat növeszteni, mert más gombafaj van rajta.");
            }
        }

        // 3.2. Ha van érintett faj (pl. másik gombafaj próbál ráfonalat növeszteni)
        if (erintofajok != null && !erintofajok.isEmpty()) {
            Gombafaj existingFaj = erintofajok.get(0);
            if (!existingFaj.equals(g)) {
                throw new Exception("Nem lehet fonalat növeszteni, mert más gombafaj van rajta.");
            }
        }

        // 4. Általános feltételek (test vagy fonal alapján növeszthető-e)
        boolean noveszthetTestMiatt = false;
        boolean noveszthetFonalMiatt = false;

        // 4.1. Van-e a saját gombafajból test valamelyik tektonon?
        for (Gombatest test : g.getGombaTestekList()) {
            if (test.getTekton().equals(honnan) || test.getTekton().equals(this)) {
                noveszthetTestMiatt = true;
                break;
            }
        }

        // 4.2. Van-e a saját gombafajból fonal valamelyik tektonon?
        for (Gombafonal gfonal : gombafonalak) {
            if (gfonal.getGombafaj().equals(g)) {
                noveszthetFonalMiatt = true;
                break;
            }
        }
        for (Gombafonal gfonal : honnan.getFonalak()) {
            if (gfonal.getGombafaj().equals(g)) {
                noveszthetFonalMiatt = true;
                break;
            }
        }

        if (noveszthetTestMiatt || noveszthetFonalMiatt) {
            return new Gombafonal(g, this, honnan);
        } else {
            throw new Exception("Nem lehet fonalat növeszteni.");
        }
       
    }

    @Override
    public Monotekton cloneTekton(){
        Monotekton ujTekton = new Monotekton();
        return ujTekton;
    }

    /***
     * @brief Gombatestet növeszt saját magán a paraméterből kapott Gombafaj típusának megfelelően
     * @param gf Gombafaj: Az a gombafaj, ami gombatestet akar növeszteni
     * @return Ha tud növeszteni gombatestet, akkor azt adja vissza
     * @exception Exception akkor dobódik ha nem lehet ide gombatestet növeszteni
     */
    /*
    @Override
    public Gombatest gombatestNov(Gombafaj gf, boolean sporaval) throws Exception {
        if (getFonalak().isEmpty()) {
            throw new Exception("Nem tud gombatestet növeszteni erre a Tektonra, mert nincs jelen Gombafonal.");
        }

        // Ellenőrizzük, hogy van-e a megadott Gombafajhoz tartozó Gombafonal
        boolean gfJelen = false;
        for (Gombafonal gombafonal : getFonalak()) {
            if (gombafonal.getGombafaj() == gf && 
                (gombafonal.getVegpont1() == this || gombafonal.getVegpont2() == this)) {
                gfJelen = true;
                break;
            }
        }

        if (!gfJelen) {
            throw new Exception("Nincs jelen a megadott Gombafaj a tektonon.");
        }

        // Spóra ellenőrzés, ha szükséges
        if (sporaval) {
            boolean vanSpora = false;
            for (Spora spora : getSporak()) {
                if (spora.getGombafaj() == gf) {
                    spora.fogyaszt(3);
                    vanSpora = true;
                    break;
                }
            }
            if (!vanSpora) {
                throw new Exception("Nincs a Gombafajhoz tartozó spóra.");
            }
        }

        return new Gombatest();
    }
    */

    public Monotekton(List<Tekton> szomszed, List<Gombafonal> gombafonal, List<Spora> spora){
        super(szomszed, gombafonal, spora);
    }


    public Monotekton(){}
}