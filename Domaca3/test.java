public class test {
    public static <T> void main(String[] args) {
        T[] a = (T[]) (new Object[4]);
        System.out.println(a[0]);
    }
}
