package Izziv1;

public class Izziv1 {
    public static void main(String[] args)throws Exception {
        
        ArrayDeque<Integer> testStack = new ArrayDeque<Integer>();
        print(testStack.isEmpty());
        print(testStack.size());
        testStack.push(1);
        print(testStack.isFull());
        testStack.push(2);
        print(testStack.top());
        print(testStack.toString());
        print(testStack.pop());
        print(testStack.pop());
        print(testStack.toString());
        print("");
        print("");
        ArrayDeque<String> testDeque = new ArrayDeque<String>();
        testDeque.enqueue("hello");
        print(testDeque.toString());
        testDeque.enqueueFront("yes");
        print(testDeque.toString());
        print(testDeque.dequeueBack());
        print(testDeque.dequeueBack());
        print(testDeque.toString());
        testDeque.enqueue("yes");
        testDeque.enqueue("no");
        testDeque.enqueue("hello");
        print(testDeque.toString());
        print(testDeque.front());
        print(testDeque.back());
        print(testDeque.dequeue());
        print(testDeque.dequeue());
        print(testDeque.dequeue());
        print(testDeque.toString());
        print("");
        print("");
        ArrayDeque<Integer> testSeq = new ArrayDeque<Integer>();
        testSeq.add(1);
        testSeq.add(1);
        testSeq.add(1);
        testSeq.add(1);
        print(testSeq.toString());
        print(testSeq.get(2));
    }

    public static void print(Object t){
        System.out.println(t);
    }
}