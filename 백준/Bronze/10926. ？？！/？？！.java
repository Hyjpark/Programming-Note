import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String inputId = sc.next();

        if (inputId.equals(inputId)) {
            System.out.print(inputId + "??!");
        }
    }
}