public class Test {
    public static void main(String[] args) {
        System.out.println(factorial(4));
    }
    
    public static int factorial(int x){
        if(x == 1){
            return 1;
        }
        return x*factorial(x-1);
    }
}
