package bughunters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        Skeleton s = Skeleton.getInstance();
        boolean fut = true;
        while(fut)
        {
            userConsoleDraw();
            int valasz = userConsoleInput();
            switch (valasz) {
                case 1:
                    s.setTestCase("Gombafonal növesztése");
                    s.teszt1_setup();
                    break;
                case 2:
                    s.setTestCase("Gombafonal növesztése növesztése monotektonra gombafonalból / ALT");
                    s.teszt3_setup();
                    break;
                case 3:
                    s.setTestCase("Gombafonal növesztése tektonra gombatestből");
                    s.teszt2_setup();
                    break;
                case 4:
                    s.setTestCase("Gombatest növesztése tektonra");
                    s.teszt4_setup();
                    break;
                case 5:
                    s.setTestCase("Gombatest növesztése puritektonra");
                    s.teszt5_setup();
                    break;
                case 6:
                    s.setTestCase("Spora szórása, már létező spórával");
                    s.teszt6_setup();
                    break;
                case 7:
                    s.setTestCase("Spóra szórást, még nem létező spórával");
                    s.teszt7_setup();
                    break;
                case 8:
                    s.setTestCase("Spóra szórása fejlett gombatest álltal");
                    s.teszt8_setup();
                    break;
                case 9:
                    s.setTestCase("Rovar mászik");
                    s.teszt9_setup();
                    break;
                case 10:
                    s.setTestCase("Gombafonal felszívódása disszolátoron");
                    s.teszt10_setup();
                    break;
                case 11:
                    s.setTestCase("Fonal vágás");
                    s.teszt12_setup();
                    break;
                case 12:
                    s.setTestCase("Tekton törés");
                    s.teszt11_setup();
                    break;
                case 13:
                    s.setTestCase("Bénító spóra evése");
                    s.teszt14_setup();
                    break;
                case 14:
                    s.setTestCase("Fonál LastChance()");
                    s.teszt13_setup();
                    break;
                case 0:
                    fut = false;
                    System.out.println("A program leáll...");
                    break;
                default:
                    System.out.println("Írjon be egy számot 1 és 14 között.");
                    break;
            }
            System.exit(0);
        }

    }


    private static void userConsoleDraw(){
        System.out.println("Programból való kilépés: 0");
        System.out.println("A következő teszt esetek léteznek, válassza ki amit le szeretne futtatni:");
        System.out.println("1. Gombafonal növesztése");
        System.out.println("2. Gombafonal növesztése növesztése monotektonra gombafonalból / ALT");
        System.out.println("3. Gombafonal növesztése tektonra gombatestből");
        System.out.println("4. Gombatest növesztése tektonra");
        System.out.println("5. Gombatest növesztése puritektonra");
        System.out.println("6. Spora szórása, már létező spórával");
        System.out.println("7. Spóra szórást, még nem létező spórával");
        System.out.println("8. Spóra szórása fejlett gombatest álltal");
        System.out.println("9. Rovar mászik");
        System.out.println("10. Gombafonal felszívódása disszolátoron");
        System.out.println("11. Fonal vágás");
        System.out.println("12. Tekton törés");
        System.out.println("13. Bénító spóra evése");
        System.out.println("14. Fonál LastChance()");
    }

    private static int userConsoleInput(){
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String valasz;
        try {
            valasz = r.readLine();
            return Integer.parseInt(valasz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
}