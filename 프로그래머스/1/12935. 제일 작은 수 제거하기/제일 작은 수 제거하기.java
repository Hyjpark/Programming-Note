import java.util.Arrays;

class Solution {
    public int[] solution(int[] arr) {
        int min = Arrays.stream(arr).min().getAsInt();
        int[] answer = arr.length != 1 ?  Arrays.stream(arr)
                            .filter(a -> min != a).toArray() : new int[] {-1};
        return answer;
    }
}