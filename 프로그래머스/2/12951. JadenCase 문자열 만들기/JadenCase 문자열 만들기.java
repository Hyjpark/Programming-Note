class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        boolean startOfWord = true;

        for (char ch : s.toCharArray()) {
            if (ch == ' ') {
                sb.append(ch);
                startOfWord = true;
            } else {
                if (startOfWord) {
                    if (Character.isLetter(ch)) {
                        sb.append(Character.toUpperCase(ch));
                    } else {
                        sb.append(Character.toLowerCase(ch));
                    }
                    startOfWord = false;
                } else {
                    sb.append(Character.toLowerCase(ch));
                }
            }
        }
        return sb.toString();
    }
}