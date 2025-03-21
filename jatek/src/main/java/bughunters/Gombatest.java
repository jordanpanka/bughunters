package bughunters;
public class Gombatest {
    private int sporaRaktar;
    private int kor;
    private Gombafaj gombafaj;
    public int getSporaRaktar() {
        return sporaRaktar;
    }
    public Gombatest(){

    }
    
    public void setSporaRaktar(int sporaRaktar) {
        this.sporaRaktar = sporaRaktar;
    }
    
    public int getKor() {
        return kor;
    }
    
    public void setKor(int kor) {
        this.kor = kor;
    }
    public void urit(){
        System.out.println("Meghívódik a Gombatest urit metodusa.");
    }
    public Gombafaj getGombafaj() {
        return gombafaj;
    }
    
    public void setGombafaj(Gombafaj gombafaj) {
        this.gombafaj = gombafaj;
    }
    
    public void sporaGyujtes(){
        System.out.println("Meghívódik a Gombatest sporaGyujtes metodusa.");

    }
    public void szorasTortent(){
        System.out.println("Meghívódik a Gombatest szorasTortent metodusa.");
    }
    


}
