import java.util.Scanner;
import java.io.File;

@SuppressWarnings("unchecked")
public class Main {
    public static boolean condition = false;

    public static void main(String[] args) {
        String temp;
        String temp2;
        try {
            Scanner input = new Scanner(System.in);
            String command;
            String[] commandArray;
            String word;
            Stack<String> skladi[] = new Stack[42];
            for (int i = 0; i != skladi.length; i++) {
                skladi[i] = new Stack<String>();
            }
            Stack<String> a;
            while (input.hasNextLine()) {
                a = skladi[0];
                command = input.nextLine();
                if (command.isEmpty()) {
                    break;
                }
                commandArray = removeEmpty(command.split(" "));
                for (int i = 0; i != commandArray.length; i++) {
                    word = commandArray[i];
                    if(word == null){
                        if(i+1 == commandArray.length){
                            break;
                        }
                        continue;
                    }
                    if(word != null && word.isEmpty()){
                        if(i+1 == commandArray.length){
                            break;
                        }
                        continue;
                    }
                    if (word.charAt(0) != '?' || condition && word.charAt(0) == '?') {
                        if (word.charAt(0) == '?') {
                            word = word.substring(1, word.length());
                        }
                        switch (word) {
                            case "echo":
                                System.out.println(a.top());
                                break;
                            case "pop":
                                a.pop();
                                break;
                            case "dup":
                                a.push(a.top());
                                break;
                            case "dup2":
                                temp = a.pop();
                                temp2 = a.top();
                                a.push(temp);
                                a.push(temp2);
                                a.push(temp);
                                break;
                            case "swap":
                                temp = a.pop();
                                temp2 = a.pop();
                                a.push(temp);
                                a.push(temp2);
                                break;

                            case "char":
                                temp = a.pop();
                                a.push(((char) Integer.parseInt(temp)) + "");
                                break;
                            case "even":
                                temp = a.pop();
                                if (Integer.parseInt(temp) % 2 == 0) {
                                    a.push("1");
                                } else {
                                    a.push("0");
                                }
                                break;
                            case "odd":
                                temp = a.pop();
                                if (Integer.parseInt(temp) % 2 != 0) {
                                    a.push("1");
                                } else {
                                    a.push("0");
                                }
                                break;
                            case "!":
                                temp = a.pop();
                                a.push("" + factorial(Integer.parseInt(temp)));
                                break;
                            case "len":
                                temp = a.pop();
                                a.push(temp.length() + "");
                                break;
                            case "<>":
                                temp = a.pop();
                                temp2 = a.pop();
                                if (temp.equals(temp2)) {
                                    a.push("0");
                                } else {
                                    a.push("1");
                                }
                                break;
                            case "<":
                                temp = a.pop();
                                temp2 = a.pop();
                                if (Integer.parseInt(temp2) < Integer.parseInt(temp)) {
                                    a.push("1");
                                } else {
                                    a.push("0");
                                }
                                break;
                            case "<=":
                                temp = a.pop();
                                temp2 = a.pop();
                                if (Integer.parseInt(temp2) <= Integer.parseInt(temp)) {
                                    a.push("1");
                                } else {
                                    a.push("0");
                                }
                                break;
                            case "==":
                                temp = a.pop();
                                temp2 = a.pop();
                                if (temp.equals(temp2)) {
                                    a.push("1");
                                } else {
                                    a.push("0");
                                }
                                break;
                            case ">":
                                temp = a.pop();
                                temp2 = a.pop();
                                if (Integer.parseInt(temp2) > Integer.parseInt(temp)) {
                                    a.push("1");
                                } else {
                                    a.push("0");
                                }
                                break;
                            case ">=":
                                temp = a.pop();
                                temp2 = a.pop();
                                if (Integer.parseInt(temp2) >= Integer.parseInt(temp)) {
                                    a.push("1");
                                } else {
                                    a.push("0");
                                }
                                break;
                            case "+":
                                temp = a.pop();
                                temp2 = a.pop();
                                a.push(Integer.parseInt(temp) + Integer.parseInt(temp2) + "");
                                break;
                            case "-":
                                temp = a.pop();
                                temp2 = a.pop();
                                a.push(Integer.parseInt(temp2) - Integer.parseInt(temp) + "");
                                break;
                            case "*":
                                temp = a.pop();
                                temp2 = a.pop();
                                a.push(Integer.parseInt(temp) * Integer.parseInt(temp2) + "");
                                break;
                            case "/":
                                temp = a.pop();
                                temp2 = a.pop();
                                a.push(Integer.parseInt(temp2) / Integer.parseInt(temp) + "");
                                break;
                            case "%":
                                temp = a.pop();
                                temp2 = a.pop();
                                a.push(Integer.parseInt(temp2) % Integer.parseInt(temp) + "");
                                break;
                            case ".":
                                temp = a.pop();
                                temp2 = a.pop();
                                a.push(temp2 + temp);
                                break;
                            case "rnd":
                                temp = a.pop();
                                temp2 = a.pop();
                                int min = Integer.parseInt(temp2);
                                int max = Integer.parseInt(temp);
                                a.push((int) Math.floor(Math.random() * (max - min) + min) + "");
                                break;
                            case "then":
                                temp = a.pop();
                                if (Integer.parseInt(temp) == 0) {
                                    condition = false;
                                } else {
                                    condition = true;
                                }
                                break;
                            case "else":
                                condition = !condition;
                                break;
                            case "print":
                                temp = a.pop();
                                skladi[Integer.parseInt(temp)].print();
                                break;
                            case "clear":
                                temp = a.pop();
                                skladi[Integer.parseInt(temp)].clear();
                                break;
                            case "run":
                                temp = a.pop();
                                int num = Integer.parseInt(temp);
                                int counter = 0;
                                String runCommands[] = new String[skladi[num].end + (commandArray.length - i) - 1];
                                for (int j = 0; j != skladi[num].end; j++) {
                                    runCommands[j] = skladi[num].getElement(j);
                                    counter++;
                                }
                                for (int z = i + 1; z < commandArray.length; z++) {
                                    runCommands[counter] = commandArray[z];
                                    counter++;
                                }
                                commandArray = runCommands;
                                i = -1;
                                break;
                            case "loop":
                                temp = a.pop();
                                temp2 = a.pop();
                                int stack = Integer.parseInt(temp);
                                int repeat = Integer.parseInt(temp2);
                                int loopCounter = 0;
                                String loopCommands[] = new String[skladi[stack].end * repeat
                                        + (commandArray.length - i) - 1];
                                for (int j = 0; j != repeat; j++) {
                                    for (int z = 0; z < skladi[stack].end; z++) {
                                        loopCommands[j * 3 + z] = skladi[stack].getElement(z);
                                        loopCounter++;
                                    }
                                }
                                for (int z = i + 1; z < commandArray.length; z++) {
                                    loopCommands[loopCounter] = commandArray[z];
                                    loopCounter++;
                                }
                                commandArray = loopCommands;
                                i = -1;
                                break;
                            case "fun":
                                temp = a.pop();
                                temp2 = a.pop();
                                Stack<String> tempSklad = new Stack();
                                for (int j = 0; j != Integer.parseInt(temp2); j++) {
                                    i++;
                                    tempSklad.push(commandArray[i]);
                                }
                                for (int x = 0; x != Integer.parseInt(temp2); x++) {
                                    skladi[Integer.parseInt(temp)].push(tempSklad.pop());
                                }
                                skladi[Integer.parseInt(temp)].reverse();
                                break;
                            case "move":
                                temp = a.pop();
                                temp2 = a.pop();
                                Stack<String> tempSklad2 = new Stack();
                                for (int x = 0; x != Integer.parseInt(temp2); x++) {
                                    tempSklad2.push(a.pop());
                                }
                                tempSklad2.reverse();
                                for (int x = 0; x != Integer.parseInt(temp2); x++) {
                                    skladi[Integer.parseInt(temp)].push(tempSklad2.pop());
                                }
                                break;
                            case "reverse":
                                temp = a.pop();
                                int z = Integer.parseInt(temp);
                                skladi[z].reverse();
                                break;
                            default:
                                a.push(word);
                                break;
                        }
                    }
                }
                skladi = new Stack[42];
                for (int i = 0; i != skladi.length; i++) {
                    skladi[i] = new Stack<String>();
                }
                condition = false;
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static long factorial(int n) {
        if(n == 0){
            return 1;
        }
        if (n <= 2) {
            return n;
        }
        return n * factorial(n - 1);
    }
    public static String[] removeEmpty(String[] x){
        String [] temp = new String[x.length];
        int end = 0;
        for(int i = 0; i != x.length; i++){
            if(x[i] != ""){
                temp[end] = x[i];
                end++;
            }
        }
        String [] returnArray = new String[end];
        for(int i = 0; i != end; i++){
            returnArray[i] = temp[i];
        }
        return returnArray;
    } 
}

@SuppressWarnings("unchecked")
class Stack<T> {
    private static int DEFAULT_CAPACITY = 64;
    private T[] array = (T[]) new Object[DEFAULT_CAPACITY];
    public int end = 0;
    public int length = DEFAULT_CAPACITY;

    public T top() {
        return array[end - 1];
    }

    public T pop() {
        T item = array[end - 1];
        array[end] = null;
        end--;
        return item;
    }

    public void push(T x) throws Exception {
        if (end < array.length) {
            array[end] = x;
            end++;
        } else {
            throw new Exception("Stack is full");
        }
    }

    public void print() {
        if (end != 0) {
            for (int i = end - 1; i != 0; i--) {
                System.out.print(array[i] + " ");
            }
            System.out.println(array[0]);
        } else {
            System.out.println("");
        }
    }

    public void reverse() {
        T[] reversed = (T[]) new Object[end];
        for (int i = 0; i < end; i++) {
            reversed[end - 1 - i] = array[i];
        }
        for (int i = 0; i != reversed.length; i++) {
            array[i] = reversed[i];
        }
    }

    public void clear() {
        for (int i = 0; i != end; i++) {
            array[i] = null;
        }
        end = 0;
    }

    public T getElement(int n) {
        return array[n];
    }

}