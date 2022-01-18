public class DrawTriangle {
    public static void main(String[] args) {
        int SIZE = Integer.parseInt(args[0]);
        int row, col;
        for (row = 1; row <= SIZE; row++) {
            for (col = 1; col <= row; col++) {
                System.out.print("*");
            }
            if (row < SIZE) {
                System.out.print("\n");
            } else {
                System.out.println("");
            }
        }
    }
}