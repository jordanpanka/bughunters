package bughunters;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @brief A játéktér osztálya, amely a Tekton objektumokat kezeli.
 */
public class Jatekter {
    private List<Tekton> tektonok;

    /**
     * @brief Visszaadja a játéktérben lévő Tekton objektumokat.
     * @return A tektonok listája.
     */
    public List<Tekton> getTektonok() {
        //System.out.println("Meghívódik a Jatekter getTektonok metodusa.");
        return tektonok;
    }

    /**
     * @brief Alapértelmezett konstruktor, amely létrehozza a Jatekter objektumot.
     */
    public Jatekter() {
        //System.out.println("Meghívódik a Jatekter paraméter nélküli konstruktora.");
        tektonok=new ArrayList<Tekton>();
    }

    /**
     * @brief Paraméteres konstruktor, amely egy meglévő Tekton listával inicializálja a játéktér objektumot.
     * @param t A Tekton objektumokat tartalmazó lista.
     */
    public Jatekter(ArrayList<Tekton> t) {
        System.out.println("Létrejött egy új Játéktér");
        tektonok = t;
    }

 
    /**
     * @brief Egy vagy több új Tekton objektumot hoz létre, amelyeket véletlenszerűen kiválasztott,
     * már létező Tekton objektumokhoz kapcsol. Az új Tektonokat szomszédként beállítja,
     * és átveszi az adott Tekton-hoz kapcsolódó Gombafonalakat is Ezekhez a fonalakhoz tartozó
     * végpontokat megfeleő módon beállítja.
     * 
     * A kiválasztás során nem szerepelhet duplikált index – minden új Tekton különböző
     * meglévő Tekton-hoz csatlakozik.
     * 
     * @param darab Az törés során széttörő Tekton objektumok száma.
     */
    public void tores(int darab, Tekton tekton, boolean teszt) {
        //System.out.println("Meghívódik a Jatekter tores metodusa.");

        if(teszt){
            // Új Tekton létrehozása
            Tekton ujTekton = new Tekton();

            // Az új Tekton-t szomszédként beállítjuk a kiválasztott régi Tekton-hoz
            tekton.szomszedAllitas(ujTekton);

            // Lekérjük a kiválasztott Tekton-hoz tartozó Gombafonalakat
            List<Gombafonal> fonalak = tekton.gombafonalIgazitas();

            // Módosítjuk a Gombafonal végpontját: ha a régi Tekton volt a végpont, azt lecseréljük az újra
            for (Gombafonal gombafonal : fonalak) {
                if (gombafonal.getVegpont1() == tekton) {
                    gombafonal.setVegpont1(ujTekton);
                } else {
                    gombafonal.setVegpont2(ujTekton);
                }
            }

            // Az új Tekton-hoz hozzárendeljük a módosított fonalakat
            for (Gombafonal gombafonal : fonalak) {
                ujTekton.addFonal(gombafonal);
            }

            // Az új Tekton-t hozzáadjuk a játéktérhez
            tektonAdd(ujTekton);
        }
        else{

            // Eltároljuk a jelenlegi Tekton-ok számát
            int tektondb = tektonok.size();

            Random random = new Random();

            // A már kiválasztott Tekton indexek listája – nem lehet ismétlődés
            List<Integer> sorszamok = new ArrayList<>();

            // Létrehozunk 'darab' számú új Tekton-t
            for (int i = 0; i < darab; i++) {
                int sorszam = 0;

                // Véletlenszerű index generálása a meglévő Tekton-okból
                int index = random.nextInt(tektondb);

                // Ha ez az index már ki lett választva, újra próbálkozunk
                if (!sorszamok.contains(index)) {
                    sorszamok.add(index);
                    sorszam = index;
                } else {
                    i--; // visszalépés, mert ez az index már szerepelt
                    continue;
                }

                // Új Tekton létrehozása
                Tekton ujTekton = new Tekton();

                // Az új Tekton-t szomszédként beállítjuk a kiválasztott régi Tekton-hoz
                tektonok.get(sorszam).szomszedAllitas(ujTekton);

                // Lekérjük a kiválasztott Tekton-hoz tartozó Gombafonalakat
                List<Gombafonal> fonalak = tektonok.get(sorszam).gombafonalIgazitas();

                // Módosítjuk a Gombafonal végpontját: ha a régi Tekton volt a végpont, azt lecseréljük az újra
                for (Gombafonal gombafonal : fonalak) {
                    if (gombafonal.getVegpont1() == tektonok.get(sorszam)) {
                        gombafonal.setVegpont1(ujTekton);
                    } else {
                        gombafonal.setVegpont2(ujTekton);
                    }
                }

                // Az új Tekton-hoz hozzárendeljük a módosított fonalakat
                for (Gombafonal gombafonal : fonalak) {
                    ujTekton.addFonal(gombafonal);
                }

                // Az új Tekton-t hozzáadjuk a játéktérhez
                tektonAdd(ujTekton);
            }
        }
    }

    /**
     * @brief Hozzáad egy új Tekton objektumot a játéktérhez.
     * @param t Az új Tekton objektum.
     */
    public void tektonAdd(Tekton t) {
        //System.out.println("Meghívódik a Jatekter tektonAdd metodusa.");
        tektonok.add(t);
    }
}