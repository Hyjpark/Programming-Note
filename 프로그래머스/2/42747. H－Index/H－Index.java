import java.util.Arrays;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length;
        int h = 0;
        
        for (int i = 0; i < n; i++) {
            int candidate = n - i; 
            if (citations[i] >= candidate) {
                h = candidate;
                break; 
            }
        }
        
        return h;
    }
}