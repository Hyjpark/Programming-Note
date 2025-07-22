import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int ticks = sc.nextInt();

        double result = ticks / 4.0;
        System.out.println(result);
    }
}