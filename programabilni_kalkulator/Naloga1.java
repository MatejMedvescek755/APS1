import java.util.Scanner;
@SuppressWarnings("unchecked")
public class Naloga1{
    static ArrayDeque<Stack> stackList = new ArrayDeque<Stack>();
    static boolean condition = false;
    static boolean fun = false;
    static int commandNum = 0;
    static int stackNum = 0;
    public static void main(String[] args) throws CollectionException{

        for(int i = 0; i != 42; i++){
            stackList.add(new ArrayDeque<>());
        }
        Scanner line = new Scanner(System.in);
        Scanner in;
        while(line.hasNextLine()){
            in = new Scanner(line.nextLine());
             while(in.hasNext()){
                  if(commandNum != 0){
                    for(int i = 0; i != commandNum; i++){
                        String command = in.next();
                        stackList.get(stackNum).push(command);
                    }
                    commandNum = 0;
                }else{
                     String command = in.next();
                     if(command.charAt(0) == '?' && condition){
                        command(command.substring(1,command.length()));
                    }else if(command.charAt(0) != '?'){
                           command(command);
                    }
                }
            }
            clear();
        }
    }
    public static void clear()throws CollectionException{
        for(int i = 0; i != stackList.size(); i++){
            while(!stackList.get(i).isEmpty()){
                stackList.get(i).pop();
            }
        }
        condition = false;
    }
    public static <T>T command(String s)throws CollectionException{
        try{
            switch(s){
                case "echo":
                    //echo();
                    if(stackList.get(0).isEmpty()){
                        System.out.println();
                    }else{
                        Object echo = stackList.get(0).top();
                        System.out.println(echo);
                    }
                    break;
                case "pop":
                    Object pop = stackList.get(0).pop();
                    break;
                case "dup":
                    stackList.get(0).push(stackList.get(0).top());
                    break;
                case "dup2":
                    Object dup2 = stackList.get(0).pop();
                    Object dup2a = stackList.get(0).top();
                    stackList.get(0).push(dup2);
                    stackList.get(0).push(dup2a);
                    stackList.get(0).push(dup2);
                    break;
                case "swap":
                    Object swap = stackList.get(0).pop();
                    Object swap2 = stackList.get(0).pop();
                    stackList.get(0).push(swap);
                    stackList.get(0).push(swap2);
                    break;
                case "char":
                    int chr = Integer.parseInt(stackList.get(0).pop()+"");
                    stackList.get(0).push((char)chr);
                    break;
                case "even":
                    int even =Integer.parseInt(stackList.get(0).pop()+"");
                    if(even % 2 == 0){
                        stackList.get(0).push(1);
                    }else{
                        stackList.get(0).push(0);
                    }
                    break;
                case "odd":
                    int odd =Integer.parseInt(stackList.get(0).pop()+"");
                    if(odd % 2 == 0){
                        stackList.get(0).push(0);
                    }else{
                        stackList.get(0).push(1);
                    }
                    break;
                case "!":
                    stackList.get(0).push(factorial(Integer.parseInt(stackList.get(0).pop()+"")));
                    break;
                case "len":
                    String len = stackList.get(0).pop()+"";
                    stackList.get(0).push(len.length());
                    break;
                case "<>":
                    int com =Integer.parseInt(stackList.get(0).pop()+"");
                    int com2 =Integer.parseInt(stackList.get(0).pop()+"");
                    if(com != com2){
                        stackList.get(0).push(1);
                    }else{
                        stackList.get(0).push(0);
                    }
                    break;
                case "<":
                    int smaller =Integer.parseInt(stackList.get(0).pop()+"");
                    int smaller2 = Integer.parseInt(stackList.get(0).pop()+"");
                    if(smaller2 < smaller){
                        stackList.get(0).push(1);
                    }else{
                        stackList.get(0).push(0);
                    }
                    break;
                case "<=":
                    Integer smallerEqual = Integer.parseInt(stackList.get(0).pop()+"");
                    Integer smallerEqual2 = Integer.parseInt(stackList.get(0).pop()+"");
                    if(smallerEqual2 <= smallerEqual ){
                        stackList.get(0).push(1);
                    }else{
                        stackList.get(0).push(0);
                    }
                    break;
                case "==":
                    Object equal =stackList.get(0).pop();
                    Object equal2 = stackList.get(0).pop();
                    if(equal.equals(equal2)){
                        stackList.get(0).push(1);
                    }else{
                        stackList.get(0).push(0);
                    }
                    break;
                case ">":
                    Integer larger =Integer.parseInt(stackList.get(0).pop()+"");
                    Integer larger2 =Integer.parseInt(stackList.get(0).pop()+"");
                    if(larger < larger2){
                        stackList.get(0).push(1);
                    }else{
                        stackList.get(0).push(0);
                    }
                case ">=":
                    Integer largerEqual =Integer.parseInt(stackList.get(0).pop()+"");
                    Integer largerEqual2 =Integer.parseInt(stackList.get(0).pop()+"");
                    if(largerEqual >= largerEqual2){
                        stackList.get(0).push(1);
                    }else{
                        stackList.get(0).push(0);
                    }
                    break;
                case "+":
                    Integer sum = Integer.parseInt(stackList.get(0).pop()+"");
                    Integer sumb = Integer.parseInt(stackList.get(0).pop()+"");
                    stackList.get(0).push(sum+sumb);
                    break;
                case "-":
                    Integer diff = Integer.parseInt(stackList.get(0).pop()+"");
                    Integer diffb = Integer.parseInt(stackList.get(0).pop()+"");
                    stackList.get(0).push(diffb-diff);
                    break;
                case "*":
                    Integer multi = Integer.parseInt(stackList.get(0).pop()+"");
                    Integer multib = Integer.parseInt(stackList.get(0).pop()+"");
                    stackList.get(0).push(multi*multib);
                    break;
                case "/":
                    Integer div = Integer.parseInt(stackList.get(0).pop()+"");
                    Integer divb = Integer.parseInt(stackList.get(0).pop()+"");
                    stackList.get(0).push(divb/div);
                    break;
                case "%":
                    Integer mod = Integer.parseInt(stackList.get(0).pop()+"");
                    Integer modb = Integer.parseInt(stackList.get(0).pop()+"");
                    stackList.get(0).push(modb%mod);
                    break;
                case ".":
                    Object con = stackList.get(0).pop();
                    Object conb = stackList.get(0).pop();
                    stackList.get(0).push(conb+""+con);
                    break;
                case "rnd":
                    Integer rnd = Integer.parseInt(stackList.get(0).pop()+"");
                    Integer rndb = Integer.parseInt(stackList.get(0).pop()+"");
                    
                    stackList.get(0).push((int)(Math.random()*(rndb-rnd+1)+rnd));
                    break;
                case "then":
                    String then = stackList.get(0).pop()+"";
                    if(then.equals("0")){
                        condition = false;
                    }else{
                        condition = true;
                    }
                    break;
                case "else":
                    condition = (!condition);
                    break;

                case "print":
                    Integer stack = Integer.parseInt(stackList.get(0).pop()+"");
                    Object array[] = new Object[stackList.get(stack).size()];
                    int printI = 0;
                    if(stackList.get(stack).size() != 0 ){
                        System.out.print(stackList.get(stack).top());
                        array[printI] = stackList.get(stack).pop();
                        printI++;
                        while(!stackList.get(stack).isEmpty()){
                            System.out.print(" ");
                            System.out.print(stackList.get(stack).top());
                            array[printI] = stackList.get(stack).pop();
                            printI++;
                        }
                        for(int j = array.length-1; j >= 0 ; j--){
                            stackList.get(stack).push(array[j]);
                        }
                    }
                    System.out.println();
                    break;
                
                case "clear":
                    Integer clearInt = Integer.parseInt(stackList.get(0).pop()+"");
                    while(!stackList.get(clearInt).isEmpty()){
                        stackList.get(clearInt).pop();
                    }
                    break;
                case "run":
                    Integer runInt = Integer.parseInt(stackList.get(0).pop()+"");
                    Object a[] = new Object[stackList.get(runInt).size()];
                    int runi = 0;
                    while(!stackList.get(runInt).isEmpty()){
                        a[runi] = stackList.get(runInt).pop();
                        runi++;
                    }
                    for(int i = 0; i != a.length; i++){
                        stackList.get(runInt).push(a[a.length-i-1]);
                    }
                    for(int i = (a.length-1); i >= 0; i--){
                        String command = (a[i]+"");
                        if(command.charAt(0) == '?' && condition){
                            command(command.substring(1,command.length()));
                        }else if(command.charAt(0) != '?'){
                            command(command);
                        }
                        
                    }
                    break;
                case "loop":
                    Integer loopRunStack = Integer.parseInt(stackList.get(0).pop()+"");
                    Integer loopRunTime = Integer.parseInt(stackList.get(0).pop()+"");
                    Object aloop[] = new Object[stackList.get(loopRunStack).size()];
                    int loopArrInd;
                    for(int i = 0; i != loopRunTime; i++){
                        loopArrInd = 0;
                        while(!stackList.get(loopRunStack).isEmpty()){
                            aloop[loopArrInd] = stackList.get(loopRunStack).pop();
                            loopArrInd++;
                        }
                        for(int j = 0; j != aloop.length; j++){
                            stackList.get(loopRunStack).push(aloop[aloop.length-j-1]);
                        }
                        for(int j = aloop.length-1; j >= 0; j--){
                            if((""+aloop[j]).charAt(0) == '?' && condition){
                                command((""+aloop[j]).substring(1,(""+aloop[j]).length()));
                            }else if(((aloop[j])+"").charAt(0) != '?'){
                                   command(aloop[j]+"");
                            }
                        }
                    }
                    break;
                
                case "fun":
                    Integer funStack = Integer.parseInt(stackList.get(0).pop()+"");
                    Integer amount = Integer.parseInt(stackList.get(0).pop()+"");
                    fun = true;
                    commandNum = amount;
                    stackNum = funStack;
                    break;
                case "move":
                    Integer moveIndex = Integer.parseInt(stackList.get(0).pop()+"");
                    Integer moveCount = Integer.parseInt(stackList.get(0).pop()+"");
                    
                    for(int i = 0; i != moveCount; i++){
                        stackList.get(moveIndex).push(stackList.get(0).pop());
                    }
                    break;
                case "reverse":
                    Integer revIndex = Integer.parseInt(stackList.get(0).pop()+"");
                    Object reverse[] = new Object[(Integer)stackList.get(revIndex).size()];
                    int reverseI = 0;
                    while(!stackList.get(revIndex).isEmpty()){
                        reverse[reverseI] = stackList.get(revIndex).pop();
                        reverseI++;
                    }
                    for(int i = 0; i != reverse.length; i++){
                        stackList.get(revIndex).push(reverse[i]);
                    }
                    break;
                default:
                    stackList.get(0).push(s);
            }
     
        }catch(Exception e){
            System.out.println(s);
            e.printStackTrace();
        }
               return(T) "";
    }    


    public static int factorial(int x){
        if(x == 0){
            return 1;
        }
        return x*factorial(x-1);
    }
    public static void print(Object item){
        System.out.println(item);
    }
}