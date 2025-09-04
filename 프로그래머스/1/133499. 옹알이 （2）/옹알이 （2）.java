class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        String[] possible = {"aya", "ye", "woo", "ma"};
        
        for (String word : babbling) {
            String prev = "";
            boolean valid = true;

            while (word.length() > 0) {
                boolean matched = false;

                for (String p : possible) {
                    if (word.startsWith(p) && !p.equals(prev)) {
                        prev = p; 
                        word = word.substring(p.length()); 
                        matched = true;
                        break;
                    }
                }

                if (!matched) {
                    valid = false;
                    break;
                }
            }

            if (valid) answer++;
        }
        return answer;
    }
}