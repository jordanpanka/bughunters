package bughunters;

import java.util.ArrayList;
import java.util.List;

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
        System.out.println("Meghívódik a Jatekter getTektonok metodusa.");
        return tektonok;
    }

    /**
     * @brief Alapértelmezett konstruktor, amely létrehozza a Jatekter objektumot.
     */
    public Jatekter() {
       
        System.out.println("Létrejött egy új Játéktér");
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
     * @brief Egy új Tekton objektumot hoz létre és hozzáadja a játéktérhez.
     * Emellett frissíti a kapcsolódó Gombafonal objektumokat.
     * @param darab A törés során létrejövő új elemek száma (jelenleg nem használatos a metódusban).
     */
    public void tores(int darab) {
        Tekton t4 = new Tekton();

        System.out.println("Meghívódik a Jatekter tores metodusa.");
        tektonok.get(1).szomszedAllitas(t4);

        List<Gombafonal> ujszomszed = tektonok.get(1).gombafonalIgazitas();

        for (Gombafonal gombafonal : ujszomszed) {
            if (gombafonal.getVegpont1() == tektonok.get(1)) {
                gombafonal.setVegpont1(t4);
            } else {
                gombafonal.setVegpont2(t4);
            }
        }

        for (Gombafonal fonal : ujszomszed) {
            t4.addFonal(fonal);
        }

        tektonAdd(t4);
    }

    /**
     * @brief Hozzáad egy új Tekton objektumot a játéktérhez.
     * @param t Az új Tekton objektum.
     */
    public void tektonAdd(Tekton t) {
        System.out.println("Meghívódik a Jatekter tektonAdd metodusa.");
        tektonok.add(t);
    }
}