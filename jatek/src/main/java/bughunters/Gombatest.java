package bughunters;
public class Gombatest {
    private int sporaRaktar;
    private int kor;
    private Gombafaj gombafaj;
    private Tekton tekton;

    public Gombatest(){}
    public Gombatest(int sporaRaktar, int kor, Gombafaj gombafaj, Tekton tekton) {
        this.sporaRaktar = sporaRaktar;
        this.kor = kor;
        this.gombafaj = gombafaj;
        this.tekton = tekton;
    }

    public Tekton getTekton() {
        return tekton;
    }
    
    public void setTekton(Tekton tekton) {
        this.tekton = tekton;
    }
    
    public int getSporaRaktar() {
        return sporaRaktar;
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
    public void urit() throws Exception{
        System.out.println("Meghívódik a Gombatest urit metodusa.");
        if(Skeleton.getInstance().Kerdes("Van elég spóra a szóráshoz?") && Skeleton.getInstance().Kerdes("Fejlett-e a gombatest?") ){
        }
        else
        {
            throw new Exception("Nem tud gombatest nőni.");
        }
    }
    public Gombafaj getGombafaj() {
        return gombafaj;
    }
    
    public void sporaGyujtes(){
        System.out.println("Meghívódik a Gombatest sporaGyujtes metodusa.");

    }
    public void szorasTortent(){
        System.out.println("Meghívódik a Gombatest szorasTortent metodusa.");
    }
    


}
