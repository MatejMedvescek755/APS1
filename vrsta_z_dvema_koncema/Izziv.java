public class Izziv{

    public static void main(String[] args){
        
    }
}

class CollectionException extends Exception {
    public CollectionException(String msg) {
         super(msg);
    }
}

interface Collection {
    static final String ERR_MSG_EMPTY = "Collection is empty.";
    static final String ERR_MSG_FULL = "Collection is full.";

    boolean isEmpty();
    boolean isFull();
    int size();
    String toString();
}

interface Stack<T> extends Collection {
    T top() throws CollectionException;
    void push(T x) throws CollectionException;
    T pop() throws CollectionException;
}

interface Deque<T> extends Collection {
    T front() throws CollectionException;
    T back() throws CollectionException;
    void enqueue(T x) throws CollectionException;
    void enqueueFront(T x) throws CollectionException;
    T dequeue() throws CollectionException;
    T dequeueBack() throws CollectionException;
}

interface Sequence<T> extends Collection {
    static final String ERR_MSG_INDEX = "Wrong index in sequence.";
    T get(int i) throws CollectionException;
    void add(T x) throws CollectionException;
}


class ArrayDeque<T> implements Deque<T>, Stack<T>, Sequence<T>{
    private static final int DEFAULT_CAPACITY = 64;
    private T[] array = new T[DEFAULT_CAPACITY];
    private int end = 0;
    private int start = 0;
    private int size;
    public boolean isEmpty(){
        if (size == 0) return true;
        return false;
    }
    
    public boolean isFull(){
        if(size == DEFAULT_CAPACITY ) return true; 
        return false;
    }

    public int size(){
        return end-start;
    }

    public String toString(){
        if(isEmpty()){
            try {
                throw new CollectionException("array is empty");
            } catch (CollectionException e) {
                e.printStackTrace();
            }
        }
        String str = "";
        for( int i = 0; i != DEFAULT_CAPACITY; i++){
            str+=array[i];
        }
        return str;
    }

    public T top(){
        if(isEmpty()){
            try {
                throw new CollectionException("array is empty");
            } catch (CollectionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return array[size];
    }

    public void push( T x){
        if(isFull()){
            try {
                throw new CollectionException("array is full");
            } catch (CollectionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        array[end] = x;
        end++;
        size++;
    }

    public T pop(){
        if(isEmpty()){
            try {
                throw new CollectionException("array is empty");
            } catch (CollectionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        T el = array[end];
        end--;
        size--;
        return el;
    }

    public T front(){
        if(isEmpty()){
            try {
                throw new CollectionException(ERR_MSG_EMPTY);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        return array[end];
    }
    public T back(){
        if(isEmpty()){
            try {
                throw new CollectionException(ERR_MSG_EMPTY);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        return array[end];
    }    

    public void enqueue(T x){
        if(isFull()){
            try {
                throw new CollectionException("full");
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        array[end] = x;
        end++;
        size++;
    }

    public void enqueueFront(T x){
        if(isFull()){
            try {
                throw new CollectionException("full");
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        if(start == 0){
            start=DEFAULT_CAPACITY;
        }else{
            start--;
        }
        array[start] = x;
        size++;
    }

    public T dequeue(){
        size--;
        T el = array[start];
        if(start == DEFAULT_CAPACITY){
            start = 0;
        }else{
            start++;
        }
        size--;
        return el;
    }

    public T dequeueBack(){
        T el = array[end];
        end--;
        size--;
        return el;
    }

    public T get(int i){
        if(i > end){
            try {
                throw new CollectionException(ERR_MSG_INDEX);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return array[i];
    }

    public void add(T x){
        if(isFull()){
            try {
                throw new CollectionException(ERR_MSG_FULL);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        array[end] = x;
    }

}