class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        
        while (!s.equals("1")) {
            int cnt = 0;
            StringBuilder sb = new StringBuilder();
            
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '0') {
                    cnt++;
                } else {
                    sb.append('1');
                }
            }
            
            answer[0]++;
            answer[1] += cnt;

            int next = sb.length();
            s = Integer.toBinaryString(next);
        }
        
        return answer;
    }
}