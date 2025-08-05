import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Solution {
    public String solution(String s) {
        String[] str = s.split("");

        Arrays.sort(str, Comparator.reverseOrder());
        String answer = "";
        for (int i = 0; i < str.length; i++) {
            answer += str[i];
        }
        return answer;
    }
}