import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int firstHalf = sc.nextInt();
        int end = sc.nextInt();

        if (((double) end / 2) <= firstHalf) System.out.println("E");
        else if (((double) end / 2) > firstHalf) System.out.println("H");
    }
}