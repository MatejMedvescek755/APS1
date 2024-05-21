
public class Test {
    public static void main(String[] args) {
        Queue q = new Queue();
        q.addElement(5);
        q.addElement(2);
        q.addElement(4);
        q.addElement(3);
        q.addElement(1);
        System.out.println(q.toString());
        System.out.println(q.dequeue());
        System.out.println(q.toString());
    }
}

class Queue{
    int [] queue = new int[64];
    int end = 0;

    public void resize(){
        int temp[] = new int[queue.length*2];
        for(int i = 0; i != queue.length; i++){
            temp[i] = queue[i];
        }
        queue = temp.clone();
    }


    public boolean isEmpty(){
        if(end == 0){
            return true;
        }
        return false;
    }

    public void addElement(int k){
        if(end == queue.length){
            resize();
        }
        queue[end] = k;
        end++
        ;
    }

    public int dequeue(){
        int temp = queue[end-1];
        int el = queue[0];
        for(int i = end-1; i > 0 ; i--){
            int t = queue[i-1];
            queue[i-1] = temp;
            temp = t;
        }
        end--;
        return el;
    }

    public String toString(){
        String str = "";
        for(int i = 0; i != end; i++){
            str += queue[i] + " ";
        }
        return str;
    }
}



class Stack{
    int [] stack = new int[64];
    int end = 0;
    public void resize(){
        int temp[] = new int[stack.length*2];
        for(int i = 0; i != stack.length; i++){
            temp[i] = stack[i];
        }
        stack = temp.clone();
    }

    public void add(int k){
        if(end == stack.length-1){ 
            resize();
        }
        
        stack[end] = k;
        end++;
    }


    public int remove()throws Exception{
        if(stack[end-1] == -1){
            throw new Exception("stack is empty");
        }
        int temp = stack[end-1];
        stack[end-1] = -1;
        end--;
        return temp;
    }

    //write a to string function for stack
    public String toString(){
        String str = "";
        for(int i = 0; i != end; i++){
            str += stack[i] + " ";
        }
        return str;
    }
}
