import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        
        String[] arr = s.split("");
        String revers = "";
        for (int i = arr.length - 1; i >= 0; i--) {
            revers += arr[i];
        }

        if (revers.equals(s)) System.out.println("1");
        else System.out.println("0");
    }
}