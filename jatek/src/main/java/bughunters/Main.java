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
                    s.setTestCase("Gombafonal növesztése Tektonra Gombafonalból, SIKERES");
                    s.teszt1();
                    break;
                case 2:
                    s.setTestCase("Gombafonal növesztése Tektonra Gombafonalból, SIKERTELEN: nem szomszédosak");
                    s.teszt2();
                    break;
                case 3:
                    s.setTestCase("Gombafonal növesztése Tektonra Gombafonalból, SIKERTELEN: nincs kezdeti gombafonal");
                    s.teszt3();
                    break;
                case 4:
                    s.setTestCase("Gombafonal növesztése Monotektonra Gombafonalból, SIKERES");
                    s.teszt4();
                    break;
                case 5:
                    s.setTestCase("Gombafonal növesztése Monotektonra Gombafonalból, SIKERTELEN: m1-el jelen van másik gombafaj fonala");
                    s.teszt5();
                    break;
                case 6:
                    s.setTestCase("Gombafonal növesztése Tektonra Gombatestből, SIKERES");
                    s.teszt6();
                    break;
                case 7:
                    s.setTestCase("Gombafonal növesztése Tektonra Gombatestből, SIKERTELEN");
                    s.teszt7();
                    break;
                case 8:
                    s.setTestCase("Gombatest növesztése Tektonra, SIKERES");
                    s.teszt8();
                    break;
                case 9:
                    s.setTestCase("Gombatest növesztése Tektonra, SIKERTELEN: nincs elég spóra a test növesztésére");
                    s.teszt9();
                    break;
                case 10:
                    s.setTestCase("Gombatest növesztése Tektonra, SIKERTELEN: nincs g1 típusú spóra a test növesztésére");
                    s.teszt10();
                    break;
                case 11:
                    s.setTestCase("Gombatest növesztése Tektonra, SIKERTELEN: van már gombatest t1 tektonon");
                    s.teszt11();
                    break;
                case 12:
                    s.setTestCase("Gombatest növesztése Puritektonra");
                    s.teszt12();
                    break;
                case 13:
                    s.setTestCase("Spóra szórása már létező Spórával");
                    s.teszt13();
                    break;
                case 14:
                    s.setTestCase("Spóra szórása még nem létező Spórával");
                    s.teszt14();
                    break;
                case 15:
                    s.setTestCase("Spóra szórása fejlett gombatest által");
                    s.teszt15();
                    break;
                case 16:
                    s.setTestCase("Rovar mászik létező úttal");
                    s.teszt16();
                    break;
                case 17:
                    s.setTestCase("Rovar mászik NEM létező úttal");
                    s.teszt17();
                    break;
                case 18:
                    s.setTestCase("Gombafonal felszívódása Disszolátoron");
                    s.teszt18();
                    break;
                case 19:
                    s.setTestCase("Fonal vágás");
                    s.teszt19();
                    break;
                case 20:
                    s.setTestCase("Bénító spóra evése");
                    s.teszt20();
                    break;
                case 21:
                    s.setTestCase("Tekton törés");
                    s.teszt21();
                    break;
                case 22:
                    s.setTestCase("Fonál lastChance újrakötött fonállal");
                    s.teszt22();
                    break;
                case 23:
                    s.setTestCase("Fonál lastChance nem újrakötött fonállal");
                    s.teszt23();
                    break;
                case 0:
                    fut = false;
                    System.out.println("A program leáll...");
                    break;
                default:
                    System.out.println("Írjon be egy számot 1 és 23 között.");
                    break;
            }
        }
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

    private static void userConsoleDraw() {
        System.out.println("Programból való kilépés: 0");
        System.out.println("A következő teszt esetek léteznek, válassza ki amit le szeretne futtatni:");
        System.out.println("1. Gombafonal növesztése Tektonra Gombafonalból, SIKERES");
        System.out.println("2. Gombafonal növesztése Tektonra Gombafonalból, SIKERTELEN: nem szomszédosak");
        System.out.println("3. Gombafonal növesztése Tektonra Gombafonalból, SIKERTELEN: nincs kezdeti gombafonal");
        System.out.println("4. Gombafonal növesztése Monotektonra Gombafonalból, SIKERES");
        System.out.println("5. Gombafonal növesztése Monotektonra Gombafonalból, SIKERTELEN: m1-el jelen van másik gombafaj fonala");
        System.out.println("6. Gombafonal növesztése Tektonra Gombatestből, SIKERES");
        System.out.println("7. Gombafonal növesztése Tektonra Gombatestből, SIKERTELEN");
        System.out.println("8. Gombatest növesztése Tektonra, SIKERES");
        System.out.println("9. Gombatest növesztése Tektonra, SIKERTELEN: nincs elég spóra a test növesztésére");
        System.out.println("10. Gombatest növesztése Tektonra, SIKERTELEN: nincs g1 típusú spóra a test növesztésére");
        System.out.println("11. Gombatest növesztése Tektonra, SIKERTELEN: van már gombatest t1 tektonon");
        System.out.println("12. Gombatest növesztése Puritektonra");
        System.out.println("13. Spóra szórása már létező Spórával");
        System.out.println("14. Spóra szórása még nem létező Spórával");
        System.out.println("15. Spóra szórása fejlett gombatest által");
        System.out.println("16. Rovar mászik létező úttal");
        System.out.println("17. Rovar mászik NEM létező úttal");
        System.out.println("18. Gombafonal felszívódása Disszolátoron");
        System.out.println("19. Fonal vágás");
        System.out.println("20. Bénító spóra evése");
        System.out.println("21. Tekton törés");
        System.out.println("22. Fonál lastChance újrakötött fonállal");
        System.out.println("23. Fonál lastChance nem újrakötött fonállal");
    }

    
}