import java.util.Stack;

public class Domaca2{
    public static void main(String[] args) {
        Stack stack =  new Stack();
        for(int i = 5; i != -1; i--){
            stack.push(i);
        }
        System.out.println(stack);
        System.out.println(obrni(stack,1,2));
    }


    public static Stack obrni(Stack s, int n,int m){
        Stack s2 = new Stack();
        Stack s3 = new Stack();
        int temp;
        for(int i = 0; i != n+m-1; i++){
            s2.push(s.pop());
        }
        for(int i = 0; i != m; i++){
            s3.push(s2.pop());
        }
        while(!s3.isEmpty()){
            s.push(s3.pop());
        }
        while(!s2.isEmpty()){
            s.push(s2.pop());
        }
        
        return s;
    }
}