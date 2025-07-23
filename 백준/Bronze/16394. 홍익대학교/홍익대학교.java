import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int openYear = 1946;
        int year = sc.nextInt();

        System.out.println(year - openYear);
    }
}