import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < players.length; i++) {
            map.put(players[i], i);
        }
        
        for (String call : callings) {
            int idx = map.get(call); 
            if (idx == 0) continue; 
            
            String front = players[idx - 1];
            players[idx - 1] = call;
            players[idx] = front;
            
            map.put(call, idx - 1);
            map.put(front, idx);
        }

        return players;
    }
}