class Solution {
    public int solution(int[][] sizes) {
       int width = 0;
        int height = 0;

        for (int[] size : sizes) {
            int w = Math.max(size[0], size[1]);
            int h = Math.min(size[0], size[1]);

            width = Math.max(width, w);
            height = Math.max(height, h);
        }
        
        return width * height;
    }
}