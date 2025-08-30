import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] cycle1 = { 1, 2, 3, 4, 5 };
        int[] cycle2 = { 2, 1, 2, 3, 2, 4, 2, 5 };
        int[] cycle3 = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int[] score = { 0, 0, 0 };

        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == cycle1[i % 5]) score[0]++;
            if (answers[i] == cycle2[i % 8]) score[1]++;
            if (answers[i] == cycle3[i % 10]) score[2]++;
        }

        int maxScore = Math.max(score[0], Math.max(score[1], score[2]));

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < score.length; i++) {
            if (maxScore == score[i]) list.add(i + 1);
        }

        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
            System.out.println(answer[i]);
        }
        
        return answer;
    }
}