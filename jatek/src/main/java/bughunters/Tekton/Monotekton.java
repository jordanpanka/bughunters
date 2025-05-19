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
        /*
        if(getFonalak().isEmpty()){
            Gombafonal gf2 = new Gombafonal(gf,this,honnan);
            addFonal(gf2);
            return gf2;
        }
        else {
            for (Gombafonal gombafonal : getFonalak()) {
                if(gombafonal.getGombafaj() == gf && 
                (gombafonal.getVegpont1() == this || gombafonal.getVegpont2() == this)){
                    Gombafonal gf2 = new Gombafonal(gf,this,honnan);
                    addFonal(gf2);
                    return gf2;
                }
            }
            throw new Exception("Nem növeszthet ide gombafonalat.");
        }
        */
        if(!szomszedok.contains(honnan)){ throw new Exception("Nem lehet fonalat növeszteni.");}

            //1.rajta lévő FAJ kikeresése, és ellenőrzése ki szeretne RÁ fonalat rakni.
            if(erintofajok != null && !erintofajok.isEmpty()) {
                Gombafaj gfaj = erintofajok.get(0);
                if (!gfaj.equals(g)) {
                    throw new Exception("Nem lehet fonalat növeszteni, mert más gombafaj van rajta.");
                }
            }
            
            if(gombafonalak != null) {
                Gombafaj gfaj = gombafonalak.get(0).getGombafaj();
                if (!gfaj.equals(g)) {
                    throw new Exception("Nem lehet fonalat növeszteni, mert más gombafaj van rajta.");
                }
            }

            // 2. Van-e már ilyen fonal?
            for (Gombafonal gfonal : gombafonalak) {
                if ((gfonal.getVegpont1().equals(this) && gfonal.getVegpont2().equals(honnan)) ||
                    (gfonal.getVegpont1().equals(honnan) && gfonal.getVegpont2().equals(this))) {
                    throw new Exception("Már van ilyen fonal.");
                }
            }

            // tektonok ahol gombatestek vannak
            //List<Tekton> gombatestekHelye = new ArrayList<>();   
            Boolean noveszthetTestMiatt = false;

            List<Gombatest> gombaTestek = g.getGombaTestekList();
            if (gombaTestek != null) {
                for (Gombatest gt : gombaTestek) {
                    //gombatestekHelye.add(gt.getTekton());
                    if( gt.getTekton().equals(honnan) || gt.getTekton().equals(this)) {
                        noveszthetTestMiatt = true; 
                    }
                }
            }

                // 4. Van-e a saját gombafajból fonal valamelyik tektonon?
                boolean noveszthetFonalMiatt = false;

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

            
            System.out.println("--------------\nKIVUL Növeszthető fonal miatt: "+ noveszthetFonalMiatt + " Növeszthető test miatt: "+ noveszthetTestMiatt+"\n--------------");

            if(noveszthetFonalMiatt || noveszthetTestMiatt) {
                System.out.println("Növeszthető fonal miatt: "+ noveszthetFonalMiatt + " Növeszthető test miatt: "+ noveszthetTestMiatt);
                return new Gombafonal(g, this, honnan);
            }
            else {
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