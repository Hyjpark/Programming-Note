class Solution {
    public int solution(int num) {
        if (num == 1) return 0;
        
        long n = num;
        int cnt = 0;
        
        while (n != 1) {
            n = n % 2 == 0 ? n / 2 : (n * 3) + 1;
            cnt++;
            
            if (cnt == 500) return -1;
        }
        
        return cnt;
    }
}