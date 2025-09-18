import java.util.*;

class Solution {
    public String solution(String s) {
        String[] arr = s.split(" ");
        int[] nums = Arrays.stream(arr)
                           .mapToInt(Integer::parseInt)
                           .toArray();
        Arrays.sort(nums);
        return nums[0] + " " + nums[nums.length - 1];
    }
}