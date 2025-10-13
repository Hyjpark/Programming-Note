import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> result = new ArrayList<>();
        
        int[] days = new int[progresses.length];
        for (int i = 0; i < progresses.length; i++) {
            int progress = 100 - progresses[i];
            days[i] = (int) Math.ceil((double) progress / speeds[i]);
        }
        
        int current = days[0];
        int count = 1;
        
        for (int i = 1; i < days.length; i++) {
            if (days[i] <= current) {
                count++;
            } else {
                result.add(count);
                count = 1;
                current = days[i];
            }
        }
        
        result.add(count);
        
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        
        
        return answer;
    }
}