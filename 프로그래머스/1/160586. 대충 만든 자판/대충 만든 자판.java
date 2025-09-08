import java.util.*;

class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        HashMap<Character, Integer> keypad = new HashMap<>();
        
        for (int i = 0; i < keymap.length; i++) {
            for (int j = 0; j < keymap[i].length(); j++) {
                char c = keymap[i].charAt(j);
                
                if (keypad.containsKey(c)) {
                    int index = keypad.get(c);
                    keypad.put(c, Math.min(index, j + 1));
                } else {
                    keypad.put(c, j + 1);
                }
            }
        }
        
        for (int i = 0; i < targets.length; i++) {
            int sum = 0;
            for (int j = 0; j < targets[i].length(); j++) {
                char c = targets[i].charAt(j);
                if (!keypad.containsKey(c)) {
                    sum = -1;
                    break;
                }
                sum += keypad.get(c);
            }
            answer[i] = sum;
        }       
        
        return answer;
    }
}