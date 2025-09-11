import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        
        String[][] types = { {"R", "T"}, {"C", "F"}, {"J", "M"}, {"A", "N"} };
        int[] score = {3, 2, 1, 0, 1, 2, 3};
        
        Map<String, Integer> map = new HashMap<>();
        for (String[] type : types) {
            map.put(type[0], 0);
            map.put(type[1], 0);
        }
        
        for (int i = 0; i < survey.length; i++) {
            String first = survey[i].substring(0, 1);
            String second = survey[i].substring(1);
            int choice = choices[i];
            
            if (choice < 4) 
                map.put(first, map.get(first) + score[choice - 1]);
            else if (choice > 4) 
                map.put(second, map.get(second) + score[choice - 1]);
        }
        
        for (String[] type : types) {
            String type1 = type[0];
            String type2 = type[1];
            
            if (map.get(type1) >= map.get(type2))
                answer += type1;
            else
                answer += type2;
        }
        
        return answer;
    }
    
}