package bughunters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Skeleton {
    private String testCase;

    public String getTestCase() {
        return testCase;
    }

    public void setTestCase(String testCase) {
        this.testCase = testCase;
    }

    public Skeleton() {
        testCase = "";
    }

    public boolean Kerdes(String kerdes) {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(kerdes);
        String valasz;
        try {
            valasz = r.readLine();
            valasz.toLowerCase();
            while (valasz.equals("igen") || valasz.equals("nem")) {
                if (valasz.equals("igen")) {
                    return true;
                } else if (valasz.equals("nem")) {
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}





