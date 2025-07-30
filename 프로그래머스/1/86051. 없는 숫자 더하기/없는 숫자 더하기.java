import java.util.List;
import java.util.ArrayList;

class Solution {
    public int solution(int[] numbers) {
        int answer = 0;
        
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) arr.add(numbers[i]);
        
        for (int i = 0; i < 10; i ++) {
            if (!arr.contains(i)) answer += i;
        }
        
        return answer;
    }
}