import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[] price = {350.34, 230.90, 190.55, 125.30, 180.90 };

        int n = sc.nextInt();

        for (int t = 0; t < n; t++) {
            double result = 0;

            for (int i = 0; i < 5; i++) {
                int num = sc.nextInt();
                result += price[i] * num;
            }

            System.out.printf("$%.2f\n", result);
        }
    }
}