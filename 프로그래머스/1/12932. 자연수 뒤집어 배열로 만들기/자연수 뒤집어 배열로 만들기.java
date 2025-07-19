class Solution {
    public int[] solution(long n) {
        String str = String.valueOf(n);
        int len = str.length();
        int[] answer = new int[len];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = (int)(n % 10);
            n = n / 10;
        }
        
        return answer;
    }
}