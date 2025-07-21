import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        
        int sum = 0;
        int cubeSum = 0;
        for (int i = 1; i <= n; i++) {
            sum = sum + i;
            cubeSum = cubeSum + (int) Math.pow(i, 3);
        }
        System.out.println(sum);
        System.out.println((int) Math.pow(sum, 2));
        System.out.println(cubeSum);
    }
}