class Solution {
    public int solution(int n) {
        String[] s = Integer.toString(n, 3).split("");

        String reversString = "";
        for (int i = 0; i < s.length; i++) {
            reversString += s[s.length - 1 - i];
        }
        return Integer.parseInt(reversString, 3);
    }
}