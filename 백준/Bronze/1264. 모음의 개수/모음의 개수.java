import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);

       while (true) {
           String str = sc.nextLine().toLowerCase();
           if ("#".equals(str)) break;

           int count = 0;
           for (int i = 0; i < str.length(); i++) {
               char c = str.charAt(i);
               if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') count++;
           }

           System.out.println(count);
       }
    }
}