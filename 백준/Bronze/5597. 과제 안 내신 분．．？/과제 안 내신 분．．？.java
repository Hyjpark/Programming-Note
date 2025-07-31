import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] nums = new int[30];
        int[] n = new int[28];

        for (int i = 0; i < 30; i++) {
            nums[i] = i + 1;
        }

        for (int i = 0; i < 28; i++) {
            n[i] = sc.nextInt();

            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == n[i])  nums[j] = 0;
            }
        }

        for (int num : nums) {
            if (num != 0) System.out.println(num);
        }
    }
}