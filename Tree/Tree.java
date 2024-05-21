import java.awt.*;
import javax.swing.*;

public class Tree {
    public static void main(String[] args) {
        int n  = Integer.parseInt(args[0]);
        CompleteBinaryTreeDrawer d = new CompleteBinaryTreeDrawer(n);
        d.drawLevelOrder();
        //d.drawInorder(0);
        //d.drawPreOrder(0);
        //d.drawPostorder(0);
    }

}

class CompleteBinaryTreeDrawer extends JFrame{
    int n;
    int xp [];
    int yp [];
    String el[];
    int offset = 80;
    public CompleteBinaryTreeDrawer(int n){
        this.n = n;
        this.xp = new int[n];
        this.yp = new int[n];
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int)size.getWidth();
        int y = (int)size.getHeight();
        StdDraw.setCanvasSize(n*offset, n*offset);
        traverse(0, 0, 0);
        this.el = new String[n];
        for(int i = 0; i != n; i++){
            el[i] = (char)(65+i)+"";
        }
    }
    private int traverse(int i, int x, int y) {
        xp[i] = x;
        yp[i] = y;

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

    public void drawNode(int i){
        int x = xp[i];
        int y = (int)(Math.log(n)/Math.log(2))-yp[i];
        StdDraw.setPenColor(0,0,0);
        int x1;
        int y1;
        if((i*2)+1 < n){
            x1 = xp[(i*2)+1];
            y1 = (int)(Math.log(n)/Math.log(2))-yp[(i*2)+1];
            System.out.println(x+" "+y);          
            StdDraw.line(0.08*x+0.02, (0.2*y)+0.1, 0.08*x1+0.02,(0.2*y1)+0.1);
        }
        if((i*2)+2 < n){
            x1 = xp[(i*2)+2];
            y1 = (int)(Math.log(n)/Math.log(2))-yp[(i*2)+2];
            System.out.println(x+" "+y);          
            StdDraw.line(0.08*x+0.02, (0.2*y)+0.1, 0.08*x1+0.02,(0.2*y1)+0.1);
        }
        StdDraw.setPenColor(255,255,255);
        StdDraw.filledCircle(0.08*x+0.02, (0.2*y)+0.1, 0.02);
        StdDraw.setPenColor(0,0,0);
        StdDraw.circle(0.08*x+0.02, (0.2*y)+0.1, 0.02);
        StdDraw.text(0.08*x+0.02, (0.2*y)+0.099, el[i]);
    }

    public void drawLevelOrder(){
        int c = 0;
        for(int i = 1; i < 10; i = i*2){
            for(int a = 0; a < i && c < n; a++){
                drawNode(c);
                // System.out.print(el[c]);
                c++;
            }
            // System.out.println();
        }
        for(int i = 0; i != n; i++){
            System.out.println(xp[i]+"  "+yp[0]);
        }
    }

    public void drawPreOrder(int i){
        drawNode(i);
        if(2*i+1 < n){
            drawPreOrder(2*i+1);
        }
        if(2*i+2 < n){
            drawPreOrder(2*i+2);
        }
    }

    public void drawInorder(int i){
        if(2*i+1 < n){
            drawInorder(2*i+1);
        }
        drawNode(i);
        if(2*i+2 < n){
            drawInorder(2*i+2);
        }
    }

    public void drawPostorder(int i){
        if(2*i+1 < n){
            drawPostorder(2*i+1);
        }
        if(2*i+2 < n){
            drawPostorder(2*i+2);
        }
        drawNode(i);
    }
}