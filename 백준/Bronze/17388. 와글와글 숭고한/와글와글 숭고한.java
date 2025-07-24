import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int s = sc.nextInt();
        int k = sc.nextInt();
        int h = sc.nextInt();

        if ((s + k + h) >= 100) System.out.println("OK");
        else {
            int min = Math.min(s, Math.min(h, k));

            if (s == min) System.out.println("Soongsil");
            else if (k == min) System.out.println("Korea");
            else if (h == min) System.out.println("Hanyang");
        }
    }
}