public class DrawTriangle {
    public static void main(String[] args) {
        int SIZE = 5;
        int row, col;
        for (row = 1; row <= SIZE; row++) {
            for (col = 1; col <= row; col++) {
                System.out.print("*");
            }
            if (row < 5) {
                System.out.print("\n");
            }
        }
    }
}