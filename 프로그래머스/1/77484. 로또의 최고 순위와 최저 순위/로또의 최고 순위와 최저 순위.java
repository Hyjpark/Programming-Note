class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        
        int[] rank = {6, 5, 4, 3, 2, 1};
        
        int cnt = 0;
        int zeroCnt = 0; 
        
        for (int lotto : lottos) {
            if (lotto == 0) {
                zeroCnt++;
                continue;
            }
            for (int win : win_nums) {
                if (lotto == win) cnt++;
            }
        }
        
        
        answer[0] = rank[Math.max(0, (cnt + zeroCnt) - 1)];
        answer[1] = rank[Math.max(0, cnt - 1)];
        
        return answer;
    }
}