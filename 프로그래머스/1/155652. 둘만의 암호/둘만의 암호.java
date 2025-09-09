class Solution {
    public String solution(String s, String skip, int index) {
        StringBuilder answer = new StringBuilder();
        
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            int cnt = 0;
            while (cnt < index) {
                c++;
                if (c > 'z') c = 'a';
                if (!skip.contains(String.valueOf(c))) {
                    cnt++; 
                }
            }
            answer.append(c);
        }
        
        return answer.toString();
    }
}