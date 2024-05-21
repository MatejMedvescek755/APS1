import java.net.http.HttpResponse.ResponseInfo;

public class Izziv3 {
    public static void main(String[] args)throws CollectionException {
        ArrayPQ a = new ArrayPQ();
        ArrayHeapPQ b = new ArrayHeapPQ();
        //LinekedHeapPQ c = new LinekedHeapPQ();
        long startTime = System.currentTimeMillis();
        for(int i = 0; i != 10; i++){
            a.enqueue(i);
            //c.enqueue(i);
        }
        for(int i = 0; i != 10; i++){
            a.front();
            a.dequeue();
            //c.dequeue();
        }
        long endTime = System.currentTimeMillis();
 
        long timeElapsed = endTime - startTime;

        System.out.println("Implementacija                     Cas [ms]           Premikov             Primerjav        \n");
        System.out.println("Neurejeno polje  (64,2x)                     " + timeElapsed+"           "+a.premiki+"           "+a.primerjave);
        startTime = System.currentTimeMillis();
        for(int i = 0; i != 1000; i++){
            b.enqueue(i);
            //c.enqueue(i);
        }
        for(int i = 0; i != 1000; i++){
            b.front();
            b.dequeue();
            //c.dequeue();
        }
        endTime = System.currentTimeMillis();
        timeElapsed = endTime - startTime;
        System.out.println("Implementacija                     Cas [ms]           Premikov             Primerjav        \n");
        System.out.println("Array heap  (64,2x)                     " + timeElapsed+"            "+b.premiki+"           "+b.primerjave);

    }

    public static void print(Object x){
        System.out.println(x);
    }
}

class ArrayPQ<T extends Comparable> implements PriorityQueue{
    public static int DEFAULT_CAPACITY = 64;
    static int end = 0;
    public T[] list = (T[]) new Comparable[DEFAULT_CAPACITY];
    public int premiki = 0;
    public int primerjave = 0;

    public boolean isEmpty(){
        if(end == 0){
            return true;
        }
        return false;
    }

    public int size(){
        return end;
    }

    public String toString(){
        int start = 0;
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        if(list[start] == null){
            sb.append("]");
            return sb.toString();
        }
        sb.append(list[start]);
        for(int i = start+1; i != end && start+1 != end; i++){
            if(i > DEFAULT_CAPACITY-1){
                i = 0;
            }
            sb.append(", "+list[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public void resize() {
        DEFAULT_CAPACITY = DEFAULT_CAPACITY*2;
        T [] temp = (T[]) new Comparable[DEFAULT_CAPACITY];
        for(int i = 0; i != end; i++){
            temp[i] = list[i];
            premiki++;
        }
        list = temp;
    }
    
    public T front() throws CollectionException{
        if( end == 0){
            throw new CollectionException(ERR_MSG_EMPTY);
        }else{
            T max = list[0];
            premiki++;
            for(int i = 1; i < end; i++){
                int a = max.compareTo(list[i]);
                primerjave++;
                if(a < 0){
                    max = list[i];
                    premiki++;
                }
            }
            return max;
        }

    }

    public T dequeue() throws CollectionException{
        if( end == 0){
            throw new CollectionException(ERR_MSG_EMPTY);
        }else{
            T max = list[0];
            premiki++;
            int maxi = 0;
            for(int i = 1; i < end; i++){
                primerjave++;
                if(max.compareTo(list[i]) < 0){
                    max = list[i];
                    premiki++;
                    maxi = i;
                    premiki++;
                }
            }
            list[maxi] = list[end-1];
            premiki++;
            end--;
            return max;
        }

    }

    public void enqueue(Object x){
        if(end+1 == DEFAULT_CAPACITY){
            resize();
        }
        list[end] =(T)x;
        premiki++;
        end++;
    }
}

class ArrayHeapPQ<T extends Comparable> implements PriorityQueue{
    public static int DEFAULT_CAPACITY = 64;
    static int end = 0;
    public T[] list = (T[]) new Comparable[DEFAULT_CAPACITY];
    public int premiki = 0;
    public int primerjave = 0;

    public boolean isEmpty(){
        if(end == 0){
            return true;
        }
        return false;
    }

    public int size(){
        return end;
    }

    public String toString(){
        int start = 0;
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        if(list[start] == null){
            sb.append("]");
            return sb.toString();
        }
        sb.append(list[start]);
        for(int i = start+1; i != end && start+1 != end; i++){
            if(i > DEFAULT_CAPACITY-1){
                i = 0;
            }
            sb.append(", "+list[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public void resize() {
        DEFAULT_CAPACITY = DEFAULT_CAPACITY*2;
        T [] temp = (T[]) new Comparable[DEFAULT_CAPACITY];
        for(int i = 0; i != end; i++){
            temp[i] = list[i];
            premiki++;
        }
        list = temp;
    }
    
    public T front() throws CollectionException{
        if( end == 0){
            throw new CollectionException(ERR_MSG_EMPTY);
        }else{
            return list[0];
        }

    }

    public T dequeue() throws CollectionException{
        if( end == 0){
            throw new CollectionException(ERR_MSG_EMPTY);
        }else{
            T max = list[0];
            list[0] = (T)Integer.valueOf(0);
            T temp;
            int next = 0;
            while (next < end){
                primerjave++;
                if((next*2)+2 < end && list[(next*2)+2].compareTo(list[(next*2)+1]) > 0){   
                    temp = list[next];
                    list[next] = list[(next*2)+2];
                    list[(next*2)+2]= temp;
                    next = (next*2)+2;

                }else if((next*2)+1 < end && (next*2)+2 >= end){
                    temp = list[next];
                    list[next] = list[(next*2)+1];
                    list[(next*2)+1]= temp;
                    next = (next*2)+1;
                }else if((next*2)+1 < end && list[(next*2)+2].compareTo(list[(next*2)+1]) < 0){
                    temp = list[next];
                    list[next] = list[(next*2)+1];
                    list[(next*2)+1]= temp;
                    next = (next*2)+1;
                }else if((next*2)+1 >= end){
                    primerjave++;
                    break;
                }

            }
            end--;
            return max;
        }

    }

    public void enqueue(Object x){
        T a = (T)x;
        if(end+1 == DEFAULT_CAPACITY)
            resize();
        T temp;
        list[end] = a;
        end++;
        int next = end-1;
        while ((next > 0) && list[(next-1)/2] != null && (((Comparable)list[next]).compareTo(list[(next-1)/2]) > 0)){
        primerjave++;
         temp = list[next];
         list[next] = list[(next-1)/2];
         list[(next-1)/2]= temp;
         next = (next-1)/2;
      }
    }
}

// class LinekedHeapPQ<T extends Comparable<T>> implements PriorityQueue{
//     public static int DEFAULT_CAPACITY = 64;
//     static int end = 0;
//     Node<T> lastParent;
//     Node<T> start;

//     public boolean isEmpty(){
//         if(end == 0){
//             return true;
//         }
//         return false;
//     }

//     public int size(){
//         return end;
//     }

//     public void resize(){
//         //does nothing
//     }

//     public String toString(){
//         return "";
//     }

    
//     public void enqueue(Object x){
//         T a = (T)x;
//         Node<T> temp = new Node<T>(a);
//         if(end == 0){
//             start = temp;
//             lastParent = temp;
//             end++;
//         }else{
//             if(lastParent.left == null){
//                 lastParent.left = temp;
//             }else if(lastParent.right == null){
//                 lastParent.right = temp;
//                 lastParent = lastParent.next;
//             }
//             end++;
//             Node<T> next = lastParent;
//             while ((next != start) && (((Comparable)next.data).compareTo(next.parent.data) > 0))
//           {
//              temp.data = next.data;
//              next.data = next.parent.data;
//              next.parent.data= temp.data;
//              next = next.parent;
//           }
//         }
//     }

//     public Object dequeue(){
//         T max = start.data;
//         Node<T> temp = new Node(null);
//         int next = 0;
//         //removes the first node and reorders the tree
//         while (next < end){
//             if((next*2)+2 < end && start.left.data.compareTo(start.right.data) > 0){   
//                 temp.data = start.data;
//                 start.data = start.left.data;
//                 start.left.data = temp.data;
//                 next = (next*2)+2;

//             }else if((next*2)+1 < end && (next*2)+2 >= end){
//                 temp.data = start.data;
//                 start.data = start.left.data;
//                 start.left.data = temp.data;
//                 next = (next*2)+1;
//             }else if((next*2)+1 < end && start.left.data.compareTo(start.right.data) < 0){
//                 temp.data = start.data;
//                 start.data = start.left.data;
//                 start.left.data = temp.data;
//                 next = (next*2)+1;
//             }else if((next*2)+1 >= end){
//                 break;
//             }

//         }
//         end--;
//         return max;
//     }


//     public T front() throws CollectionException{
//         if(start == null){
//             throw new CollectionException(ERR_MSG_EMPTY);
//         }else{
//             return start.data;
//         }
        
//     }
// }

// class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
//     T data;
//     Node<T> left;
//     Node<T> right;
//     Node<T> parent;
//     public Node(T x){
//         data = x;
//         left = null;
//         right = null;
//         parent = null;
//     }
//     public Node(T x, Node<T> p){
//         data = x;
//         left = null;
//         right = null;
//         parent = p;
//     }
//     public int compareTo(Node<T> other){
//         return data.compareTo(other.data);
//     }
//     public String toString(){
//         return data.toString();
//     }
// }

class CollectionException extends Exception {
    public CollectionException(String msg) {
         super(msg);
    }
}
interface Collection {
    static final String ERR_MSG_EMPTY = "Collection is empty.";

    boolean isEmpty();
    int size();
    String toString();
}
interface Queue<T> extends Collection {
    T front() throws CollectionException;
    void enqueue(T x);
    T dequeue() throws CollectionException;
    void resize();
}
interface PriorityQueue<T extends Comparable> extends Queue<T> {}