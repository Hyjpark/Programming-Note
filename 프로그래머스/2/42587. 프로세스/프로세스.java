import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());

    for (int num : priorities) {
        priorityQueue.add(num);
    }

    while (!priorityQueue.isEmpty()) {
        for (int i = 0; i < priorities.length; i++) {
            if (priorities[i] == priorityQueue.peek()) {
                priorityQueue.poll();
                answer++;
                if (i == location) {
                    return answer;
                }
            }
        }
    }
        
        return answer;
    }
}