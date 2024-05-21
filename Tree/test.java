public class test {
    public static int n = 10;
    public static int xp [] = new int[n];
    public static int yp [] = new int[n];
    public static void main(String[] args) {
        int x = traverse(0,0,0);
        for(int i = 0; i != n; i++){
            System.out.print(xp[i]+" ");
        }
        System.out.println();
        for(int i = 0; i != n; i++){
            System.out.print(yp[i]+" ");
        }
    }
    private static int traverse(int i, int x, int y) {
        if ((2 * i) + 1 < n) {
            x = traverse((2 * i) + 1, x, y + 1);
            xp[i] = x;
            yp[i] = y;
        }

        if ((2 * i) + 2 < n) {
            int a = traverse((2 * i) + 2, x + 1, y + 1);
            xp[i] = x;
            yp[i] = y;
            x = a - 1;
        }

        return x + 1;

    }
}
