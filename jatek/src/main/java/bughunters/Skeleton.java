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
        String valasz = r.readLine();

        valasz.toLowerCase();
        if (valasz.equals("igen")) {
            return true;
        } else if (valasz.equals("nem")) {
            return false;
        }
    }
}





