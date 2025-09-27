import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int size : tangerine) {
            countMap.put(size, countMap.getOrDefault(size, 0) + 1);
        }

        List<Integer> counts = new ArrayList<>(countMap.values());
        counts.sort(Collections.reverseOrder());
        
        int answer = 0;
        int picked = 0;
        for (int c : counts) {
            picked += c;
            answer++;
            if (picked >= k) break;
        }

        
        return answer;
    }
}