import java.util.*;

public class ArrayDeque<T> implements Stack, Deque, Sequence{
    private static final int DEFAULT_CAPACITY = 64;
    T[] list = (T[]) new Object[DEFAULT_CAPACITY];
    int start = 0;
    int end = 0;
    public boolean isEmpty(){
        if(start == end){
            return true;
        }
        return false;
    }
    public boolean isFull(){
        if(start + DEFAULT_CAPACITY-1 == end || start- 1 == end){
            return true;
        }
        return false;
    }
    public int size(){
        int count = 0;
        for(int i = start; i != end+1; i++){
            if(list[i] == null){
                return count;
            }
            count++;
            if( i == 63 ){
                i =0;
            }
        }
        return count;
    }
    public String toString(){
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

    public T top()throws CollectionException{

        T a = list[end-1];
        if(end == start){
            throw new CollectionException(ERR_MSG_EMPTY);
        }
        return a;
        
    }
    public void push(Object x)throws CollectionException{
        list[end] =(T) x;
        if(DEFAULT_CAPACITY != 1){
            if(end != DEFAULT_CAPACITY-1 && end+1 == start || start == 0 && end == DEFAULT_CAPACITY-1){
                throw new CollectionException(ERR_MSG_FULL);
            }
        }else if(end > DEFAULT_CAPACITY){
            throw new CollectionException(ERR_MSG_FULL);
        }
        
        if(end == DEFAULT_CAPACITY-1){
            end = 0;
        }else{
            end++;
        }
    }

    public T pop()throws CollectionException{
        T el = list[end-1];
        list[end-1] = null;
        if(end == start){
            throw new CollectionException(ERR_MSG_EMPTY);
        }else{
            end--;
        }
        return el;
    }

    public T front()throws CollectionException{
        T el = list[start];
        if(el == null){
            throw new CollectionException(ERR_MSG_EMPTY);
        }
        return el;
    }
    
    public T back()throws CollectionException{
        if(start == end){
            throw new CollectionException(ERR_MSG_EMPTY);
        }
        T el = list[end-1];
        return el;
    }
    public void enqueueFront(Object x)throws CollectionException{
        if(start-1 == end ||  start == 0 && end == DEFAULT_CAPACITY-1){
            throw new CollectionException(ERR_MSG_FULL);
        }
        if(start == 0){
            start=DEFAULT_CAPACITY-1;
        }else{
            start--;
        }
        list[start] = (T) x;
    }
    public void enqueue(Object x) throws CollectionException{
        if(start-1 == end ||  start == 0 && end == DEFAULT_CAPACITY-1){
            throw new CollectionException(ERR_MSG_FULL);
        }
        list[end] = (T) x;
        if(end == DEFAULT_CAPACITY-1){
            end = 0;
        }else{
            end++;
        }
    }
    public T dequeue()throws CollectionException{
        if(start == end){
            throw new CollectionException(ERR_MSG_EMPTY);
        }
        T el = list[start];
        list[start] = null;
        if(start != DEFAULT_CAPACITY-1){
            start++;
        }else{
            start = 0;
        }
        return el;
    }

    public T dequeueBack() throws CollectionException{
        if(start == end){
            throw new CollectionException(ERR_MSG_EMPTY);
        }
        if(end == DEFAULT_CAPACITY-1){
            end = 0;
        }else if(end == 0){
            end = DEFAULT_CAPACITY-1;
        }else{
            end--;
        }
        T el = list[end];
        list[end] = null;
        return el;
    }
    
    public T get(int i) throws CollectionException{
        if(start == end){
            throw new CollectionException(ERR_MSG_EMPTY);
        }
        if(list[i] == null){
            throw new CollectionException(ERR_MSG_INDEX);
        }
        return list[i];
    }
     public void add(Object x) throws CollectionException{
        if(end == DEFAULT_CAPACITY-1){
            throw new CollectionException(ERR_MSG_FULL);
        }
        list[end] = (T) x;
        end++;
     }
     public static void print(Object t){
        System.out.println(t);
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

