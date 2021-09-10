public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
        int cumulative_sum = 0;
        while (x < 10) {
            cumulative_sum = cumulative_sum + x;
            System.out.print(cumulative_sum + " ");
            x = x + 1;
        }
    }
}