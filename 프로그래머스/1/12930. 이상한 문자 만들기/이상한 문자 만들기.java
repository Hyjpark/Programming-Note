class Solution {
    public String solution(String s) {
        String[] str = s.split("");

        int cnt = 0;
        String answer = "";

       for (int i = 0; i < str.length; i++) {
            if(" ".equals(str[i])) {
                cnt = 0;
                answer += " ";
                continue;
            }
            if(cnt % 2 == 0 || cnt == 0) answer += str[i].toUpperCase();
            else answer += str[i].toLowerCase();
            cnt++;
        }
        return answer;
    }
}