import java.util.Scanner;

public class Naloga{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //Scanner input = new Scanner("count insert up\n10 42 27 17 58 39 91 19 42 66");
        Scanner line;
        String sort = "";
        Array array = new Array();
        for(int i = 0; i != 2; i++){
            line = new Scanner(input.nextLine());
            while(line.hasNext()){
                String word = line.next();
                if((int)word.charAt(0) > 48 && (int)word.charAt(0) <= 57){
                    array.addElement(Integer.parseInt(word));   
                }else{
                    if(word.equals("trace")){
                        Array.trace = true;
                    }else if(word.equals("down")){
                        Array.type = true;
                    }else if(word.equals("up")){
                        Array.type = false;
                    }else{
                        sort = word;
                    }
                }
            }
        }

        switch (sort) {
            case "insert":
                array.insertSort();
                if(!Array.trace){
                    System.out.print(Array.swap+" "+Array.compare);

                    System.out.println();
                }
                break;
            case "select":
                array.selectionSort();
                break;
            case "bubble":
                array.bubbleSort();
                break;
            case "heap":
                array.heapSort();
                break;
            case "merge":
                array.mergeSort();
                break;
            case "quick":
                array.quickSort();
                break;
            case "radix":
                array.radixSort();
                break;
            case "bucket":
                array.bucketSort();
                break;
            default:
                
                break;
        }
    
    }
}

class Array{
    int DEFAULT_CAPACITY = 64;
    int array[] = new int[DEFAULT_CAPACITY];
    int end = 0;
    public static boolean trace = false;
    //type defines max sort or min sort
    public static boolean type = false;
    public static int swap = 0;
    public static int compare = 0;


    public void addElement(int x){
        if(end == DEFAULT_CAPACITY-1){

        }else{
            array[end] = x;
            end++;
        }   
    }

    public void swap(int i, int m){
        int temp = array[i];
        array[i] = array[m];
        array[m] = temp;
    }

    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("[ ");
        sb.append(array[0]);
        for(int i = 1; i != end; i++){
            sb.append(", "+array[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    //INSERTION SORT
    public void insertSort(){
        int sorted;
        int temp;
        int step = -1;
        if(trace){
            printArray(step);
        }
        //max to min
        if(type){
            for(int i = 1; i < end; i++){
                sorted = i;
                swap+=2;
                compare++;
                while( sorted > 0 && array[sorted] > array[sorted-1]){
                    temp = array[sorted-1];
                    array[sorted-1] = array[sorted];
                    array[sorted] = temp;
                    sorted--;
                    swap++;
                    compare++;
                } 
                if(trace){
                    printArray(i);
                }
            }
            if(!trace){
                System.out.print(swap+ " "+compare);
            }
            //min to max
        }else{
            for(int i = 1 ; i < end; i++){
                sorted = i;
                while( sorted > 0 && array[sorted] < array[sorted-1]){
                    temp = array[sorted-1];
                    array[sorted-1] = array[sorted];
                    array[sorted] = temp;
                    sorted--;
                    if(!trace){
                        swap+=3;
                        compare++;
                    }
                } 
                if(trace){
                    printArray(i);
                }
            }
        }
    }

    //SELECTION SORT
    public void selectionSort(){
        int sorted = 0;
        int min;
        int minI;
        if(trace){
            printArray(-1);
        }
        if(type){
            //max
            for(int i = 0; i != end-1; i++){
                min = array[sorted];
                minI = sorted;
                for(int j = sorted; j != end; j++){
                    if(array[j] > min){
                        min = array[j];
                        minI = j;
                    }
                }
                array[minI] = array[sorted];
                array[sorted] = min;
                sorted++;
                if(trace){
                    printArray(sorted-1);
                }
            }
        }else{
            //min
            for(int i = 0; i < end - 1; i++){
                min = array[sorted];
                minI = sorted;
                for(int j = sorted; j < end; j++){
                    if(array[j] < min){
                        min = array[j];
                        minI = j;
                    }
                }
                array[minI] = array[sorted];
                array[sorted] = min;
                sorted++;
                if(trace){
                    printArray(sorted-1);
                }
            }
        }
    }

    //BUBBLE SORT
    public void bubbleSort(){
        if(trace){
            printArray(-1);
        }
        int index = 0;
        //max
        if(type){
            for(int i = end; i > 1; i--){
                for(int j = end ; j != end-i; j--){
                    if(array[j] > array[j-1]){
                        int temp = array[j];
                        array[j] = array[j-1];
                        array[j-1] = temp;
                    }
                    index = j;
                }
                if(trace){
                    printArray(index-1);
                }
            }
        }else{
            //min
            boolean sorted = false;
            for(int i = end-1; i > 1; i--){
                for(int j = end-1 ; j != end-i; j--){
                    if(array[j] < array[j-1]){
                        int temp = array[j];
                        array[j] = array[j-1];
                        array[j-1] = temp;
                        sorted = true;
                    }
                    index = j;
                }
                if(trace && sorted){
                    printArray(index-1);
                    sorted = false;
                }
            }
            printArray(end-2);
        }
        
    }

    //HEAP SORT
    public void heapSort(){
        Heap heap = new Heap();      
        heap.type = !type;
        if(trace){
            printArray(-1);
        }
        for(int i = 0; i != end; i++){
            heap.enqueue(array[i]);
            array[i] = -1;
        }
        for(int i = 0; i != end; i++){
            if(trace){
                System.out.print(heap.toString());
                System.out.print(" | ");
                printArray(-1);       
            }
            array[i] = heap.dequeue();
        }
    }

    //MERGE SORT
    public void mergeSort(){
        if(trace){
            printArray(-1);
        }
        int arr[] = new int[end];
        for(int i = 0; i != end; i++){
            arr[i] = array[i];
        }
        int [] a = merge(arr); 
    }

    public int[] merge(int a[]){
        if(a.length <= 1){
            return a;
        }
        int middle = (a.length-1)/2;
        if(trace){
            for(int i = 0; i != a.length; i++){
                System.out.print(a[i]+ (i == middle ? " | " : " "));
            }
            System.out.println();
        }
        int left[] = new int[middle+1];
        for(int i = 0; i != middle+1; i++){
            left[i] = a[i];
        }
           left = merge(left);
        int right[] = new int[a.length-middle-1];
        for(int i = 0; i != a.length-middle-1; i++){
            right[i] = a[middle+1+i];
        }
        right = merge(right);
        int combined[] = combine(left, right);
        if(trace){
            for(int i = 0; i != a.length; i++){
                System.out.print(combined[i]+" ");
            }
            System.out.println();
        }
        return combined;
    }

    public int[] combine(int a[], int b[]){
        int combined[] = new int[a.length + b.length];
        if(type){
            int aI = 0;
            int bI = 0;
            for(int i = 0; i != combined.length; i++){
                if( aI < a.length && bI < b.length){
                    if( a[aI] > b[bI]){
                        combined[i] = a[aI];
                        aI++;
                    }else{
                        combined[i] = b[bI];
                        bI++;
                    }
                }else if(bI < b.length){
                    combined[i] = b[bI];
                    bI++;
                }else if(aI < a.length){
                    combined[i] = a[aI];
                    aI++;
                }
            }
        }else{
            int aI = 0;
            int bI = 0;
            for(int i = 0; i != combined.length; i++){
                if( aI < a.length && bI < b.length){
                    if( a[aI] < b[bI]){
                        combined[i] = a[aI];
                        aI++;
                    }else{
                        combined[i] = b[bI];
                        bI++;
                    }
                }else if(bI < b.length){
                    combined[i] = b[bI];
                    bI++;
                }else if(aI < a.length){
                    combined[i] = a[aI];
                    aI++;
                }   
            }
        }
        return combined;

    }

    //QUICK SORT
    //implement quick sort
    public void quickSort(){
        if(trace){
            printArray(-1);
        }
        int arr[] = new int[end];
        for(int i = 0; i != end; i++){
            arr[i] = array[i];
        }
        int [] a = quick(arr, 0, arr.length-1); 
    }

    public int[] quick(int a[], int start, int end){
        int index = 0;
        if(start < end){
            int pivot = a[end];
            index = start;
            for(int i = start; i != end; i++){
                if(type){
                    if(a[i] <= pivot){
                        int temp = a[i];
                        a[i] = a[index];
                        a[index] = temp;
                        index++;
                    }
                }else{
                    if(a[i] >= pivot){
                        int temp = a[i];
                        a[i] = a[index];
                        a[index] = temp;
                        index++;
                    }
                }
            }
            int temp = a[index];
            a[index] = a[end];
            a[end] = temp;
            if(trace){
                for(int i = 0; i != a.length; i++){
                    System.out.print((i == index+1 ? " | " : " ")+a[i]+ (i == index+1 ? " | " : " "));
                }
                System.out.println();
            }
            quick(a, start, index-1);
            quick(a, index+1, end);
        }
        return a;
    }

    // public int[] combine(int left[], int right[]){





    
    //     int combined[] = new int[left.length + right.length];
    //     for(int i = 0; i != combined.length; i++){
    //         if(i < left.length){
    //             combined[i] = left[i];

    //         }else{
    //             combined[i] = right[i-left.length];
    //         }
    //     }
    //     return combined;
    // }

    //RADIX SORT
    public void radixSort(){
        if(trace){
            printArray(-1);
        }
        int max = 0;
        for(int i = 0; i != end; i++){
            if(array[i] > max){
                max = array[i];
            }
        }
        int maxDigits = 0;
        while(max != 0){
            max /= 10;
            maxDigits++;
        }
        if(type){
            for(int i = 0; i != maxDigits; i++){
                int temp[] = new int[end];
                for(int j = 0; j != end; j++){
                    temp[j] = array[j];
                }
                int count[] = new int[10];
                for(int j = 0; j != end; j++){
                    int digit = (int) (temp[j] / Math.pow(10, i)) % 10;
                    count[count.length-digit-1]++;
                }
                for(int j = 1; j != 10; j++){
                    count[j] += count[j-1];
                }
                for(int j = end-1; j != -1; j--){
                    int digit = (int) (temp[j] / Math.pow(10, i)) % 10;
                    array[count[count.length-digit-1]-1] = temp[j];
                    count[count.length-digit-1]--;
                }
                if(trace){
                    printArray(-1);
                }
            }
        }else{
            for(int i = 0; i != maxDigits; i++){
                int temp[] = new int[end];
                for(int j = 0; j != end; j++){
                    temp[j] = array[j];
                }
                int count[] = new int[10];
                for(int j = 0; j != end; j++){
                    int digit = (int) (temp[j] / Math.pow(10, i)) % 10;
                    count[digit]++;
                }
                for(int j = 1; j != 10; j++){
                    count[j] += count[j-1];
                }
                for(int j = end-1; j != -1; j--){
                    int digit = (int) (temp[j] / Math.pow(10, i)) % 10;
                    array[count[digit]-1] = temp[j];
                    count[digit]--;
                }
                if(trace){
                    printArray(-1);
                }
            }
        }
    }

    //BUCKET SORT
    public void bucketSort(){
        if(trace){
            printArray(-1);
        }
        int max = 0;
        for(int i = 0; i != end; i++){
            if(array[i] > max){
                max = array[i];
            }
        }
        int min = array[0];
        for(int i = 0; i != end; i++){
            if(array[i] < min){
                min = array[i];
            }
        }
        int k = end/2;
        int a = (int)Math.ceil((max-(double)min+1)/k);;
        int buckets[][] = new int[k][a];
        int bucketCount[] = new int[k];
        for(int i = 0; i != end; i++){
            int bucket = (int) ((array[i]-min)/a);
            buckets[bucket][bucketCount[bucket]] = array[i];
            bucketCount[bucket]++;
        }

        int c[] = new int[k];
        c[0] = bucketCount[0];
        for(int i = 1; i != k; i++){
            c[i] = c[i-1] + bucketCount[i];
        }

        int temp[] = new int[end];
        if(type){
            for(int i = 0; i != end; i++){
                int bucket = (int) ((array[i]-min)/a);
                temp[end-c[bucket]] = array[i];
                c[bucket]--;
            }
        }else{
            for(int i = end-1; i != -1; i--){
                int bucket = (int) ((array[i]-min)/a);
                temp[c[bucket]-1] = array[i];
                c[bucket]--;
            }
        }
        int tempCount;
        int count;
        if(type){
            tempCount = bucketCount[k-1];
            count = k-1;
        }else{
            tempCount = bucketCount[0];
            count = 0;
        }
        for(int i = 0; i != end; i++){
            if(i == tempCount){
                if(trace){
                    System.out.print("| "+temp[i]+" ");
                }   
                if(type){
                    count--;
                    tempCount = bucketCount[count] + tempCount;
                }else{
                    count++;
                    tempCount = bucketCount[count] + tempCount;
                }
                
            }else{
                if(trace){
                    System.out.print(temp[i]+" ");
                }
            }

        }
        System.out.println();
        temp = insertSort(temp);
        for(int i = 0; i != end; i++){
            array[i] = temp[i];
        }
        
    }
    
    public int[] insertSort(int []arr){
        int sorted;
        int temp;
        if(type){
            for(int i = 1; i < arr.length; i++){
                sorted = i;
                while( sorted > 0 && arr[sorted] > arr[sorted-1]){
                    temp = arr[sorted-1];
                    arr[sorted-1] = arr[sorted];
                    arr[sorted] = temp;
                    sorted--;
                } 
                for(int j = 0; j != arr.length && trace; j++){
                    System.out.print(arr[j] + " ");
                    if(j == i){
                        System.out.print("| ");
                    }
                }
                if(trace){
                    System.out.println(); 
                }
            }
            //min to max
        }else{
            for(int i = 1 ; i < arr.length; i++){
                sorted = i;
                while( sorted > 0 && arr[sorted] < arr[sorted-1]){
                    temp = arr[sorted-1];
                    arr[sorted-1] = arr[sorted];
                    arr[sorted] = temp;
                    sorted--;
                }
                for(int j = 0; j != arr.length && trace; j++){
                    System.out.print(arr[j] + " ");
                    if(j == i ){
                        System.out.print("| ");
                    }
                }
                if(trace){
                    System.out.println(); 
                }
            }
        }
        return arr;
    }

    
    public void printArray(int place){
        for(int i = 0; i != this.end; i++){
            if(this.array[i] != -1){
                System.out.print(this.array[i] + " ");
            }
            if(i == place){
                System.out.print("| ");
            }
        }
        System.out.println();
    }
}


class Heap{
    int DEFAULT_CAPACITY = 64;
    int array[] = new int[DEFAULT_CAPACITY];
    int end = 0;
    public boolean type = false;

    public void resize(){
        int temp[] = new int[DEFAULT_CAPACITY*2];
        for(int i = 0; i != DEFAULT_CAPACITY; i++){
            temp[i] = array[i];
        }
        array = temp;
        DEFAULT_CAPACITY *= 2;
    }

    public void enqueue(int x){
        if(type){
            //max heap
            int a = x;
            if(end+1 == DEFAULT_CAPACITY)
                resize();
            int temp;
            array[end] = a;
            end++;
            int next = end-1;
            while ((next > 0) && (next-1)/2 < end && array[next] > array[(next-1)/2]){
             temp = array[next];
             array[next] = array[(next-1)/2];
             array[(next-1)/2]= temp;
             next = (next-1)/2;
            }            
        }else{
            //min heap
            int a = x;
            if(end+1 == DEFAULT_CAPACITY)
                resize();
            int temp;
            array[end] = a;
            end++;
            int next = end-1;
            while ((next > 0) && (next-1)/2 < end && array[next] < array[(next-1)/2]){
                temp = array[next];
                array[next] = array[(next-1)/2];
                array[(next-1)/2]= temp;
                next = (next-1)/2;
            }
        }
    }

    //write a method to dequeue the root of the heap
    public int dequeue(){
        int temp = array[0];
        array[0] = array[end-1];
        array[end-1] = -1;
        end--;
        if(type){
            //max heap
            int next = 0;
            int a = array[next];
            int b;
            int c;
            while((next*2+1) < end){
                if((next*2+2) < end){
                    b = array[next*2+1];
                    c = array[next*2+2];
                    if(b > c && b > a){
                        array[next] = b;
                        array[next*2+1] = a;
                        next = next*2+1;
                    }else if(c > a){
                        array[next] = c;
                        array[next*2+2] = a;
                        next = next*2+2;
                    }else{
                        break;
                    }
                }else{
                    b = array[next*2+1];
                    if(b > a){
                        array[next] = b;
                        array[next*2+1] = a;
                        next = next*2+1;
                    }else{
                        break;
                    }
                }
            }
        }else{
            //min heap
            int next = 0;
            int a = array[next];
            int b;
            int c;
            while((next*2+1) < end){
                if((next*2+2) < end){
                    b = array[next*2+1];
                    c = array[next*2+2];
                    if(b < c && b < a){
                        array[next] = b;
                        array[next*2+1] = a;
                        next = next*2+1;
                    }else if(c < a){
                        array[next] = c;
                        array[next*2+2] = a;
                        next = next*2+2;
                    }else{
                        break;
                    }
                }else{
                    b = array[next*2+1];
                    if(b < a){
                        array[next] = b;
                        array[next*2+1] = a;
                        next = next*2+1;
                    }else{
                        break;
                    }
                }
            }
        }
        return temp;
    }

    public boolean isEmpty(){
        if(end == 0){
            return true;
        }else{
            return false;
        }
    }

    
    

    public String toString(){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i != end; i++){
            sb.append(array[i]+" ");
        }   
        return sb.toString();
    }
}
