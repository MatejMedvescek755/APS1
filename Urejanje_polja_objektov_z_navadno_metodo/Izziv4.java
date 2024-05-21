import java.util.Scanner;
public class Izziv4{
    public static boolean smer = true;
    public static boolean trace = true;
    public static int atr = 0;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        Oseba[] tt = new Oseba[n];
        for(int i = 0; i < n; i++){
            tt[i] = new Oseba();
        }
        while(true){
            //Prepiši tt v t
            Oseba [] t = tt.clone();
            //Izpiši polje t
            printArray(t);
            //Vnesi in nastavi atr
            atr = input.nextInt();
            //Vnesi in nastavi smer
            smer = input.nextBoolean();
            //Uredi t z navadno zamenjavo (vsebuje izpis sledi)
            bubblesort(t);
            System.out.println("end?");
            if(input.next().equals("end")){
                break;
            }
        }
    }
    //array output
    public static void printArray(Oseba[] a){
        System.out.print("[ ");
        for(int i = 0; i < a.length; i++){
            System.out.print(a[i].toString(atr) + " ");
        }
        System.out.println(" ]");
    }

    //bubble sort
    public static void bubblesort(Oseba[] a){
        if(smer){
            //max
            for(int i = 0; i < a.length; i++){
                for(int j = 0; j < a.length-1; j++){
                    a[j].atr = atr;
                    if(a[j].compareTo(a[j+1]) > 0){
                        Oseba temp = a[j];
                        a[j] = a[j+1];
                        a[j+1] = temp;
                    }
                    printArray(a);
                }
            }

        }else{
            //min
            for(int i = 0; i < a.length; i++){
                for(int j = 0; j < a.length-1; j++){
                    a[j].atr = atr;
                    if(a[j].compareTo(a[j+1]) < 0){
                        Oseba temp = a[j];
                        a[j] = a[j+1];
                        a[j+1] = temp;
                    }
                    printArray(a);
                }
            }
        }
    }
}

class Oseba implements Comparable{
    String priimek;
    String ime;
    int letoR;
    public int atr = 0;
    String[] arr = new String[50];
    public String lastNames[] = {"lastname1", "lastname2","lastname3","lastname4", "lastname5"};
    public String firstNames[] = {"firstname1", "firstname2","firstname3","firstname4", "firstname5"};
    public Oseba(){
        priimek = lastNames[(int)(Math.random()*5)];
        ime = firstNames[(int)(Math.random()*5)];
        letoR = (int)(Math.random()*100)+1900;
    }

    public int compareTo(Object o) {
        switch(atr){
            case 0:
                if(this.letoR > ((Oseba)o).letoR){
                    return 1;
                }
                if(this.letoR < ((Oseba)o).letoR){
                    return -1;
                }
                return 0;
            case 1:
                return this.priimek.compareTo(((Oseba)o).priimek);
            case 2:
                return this.ime.compareTo(((Oseba)o).ime);
            default:
                return 0;
        }
    }
    public String toString(int atr){
        switch(atr){
            case 0:
                return letoR+"";
            case 1:
                return priimek;
            case 2:
                return ime;
            default:
                return "error";
        }
    }
}
