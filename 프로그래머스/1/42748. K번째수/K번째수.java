import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        for (int c = 0; c < commands.length; c++) {
            int[] temp = new int[commands[c][1] -  commands[c][0] + 1];

            for (int i = commands[c][0] - 1, j = 0; i < commands[c][1]; i++, j++) {
                temp[j] = array[i];
            }
            Arrays.sort(temp);
            
            answer[c] = temp[commands[c][2] - 1];
        }
        return answer;
    }
}