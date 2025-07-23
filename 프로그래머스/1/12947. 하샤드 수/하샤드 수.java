class Solution {
    public boolean solution(int x) {
        boolean answer = true;
        
        String[] n = String.valueOf(x).split("");

        int nPlus = 0;
        for (String s : n) {
            nPlus += Integer.parseInt(s);
        }

        if (x % nPlus == 0) {
            answer = true;
        } else {
            answer = false;
        }
        
        return answer;
    }
}