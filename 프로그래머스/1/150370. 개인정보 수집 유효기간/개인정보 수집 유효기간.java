import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate todayDate = LocalDate.parse(today, dtf);
        
        Map<String, Integer> termMap = new HashMap<>();
        for (String t : terms) {
            String[] arr = t.split(" ");
            termMap.put(arr[0], Integer.parseInt(arr[1]));
        }
        
        List<Integer> expired = new ArrayList<>();
        
        for (int i = 0; i < privacies.length; i++) {
            String[] privacy = privacies[i].split(" ");
            LocalDate collectedDate = LocalDate.parse(privacy[0], dtf);
            String type = privacy[1];
            
            LocalDate expireDate = collectedDate.plusMonths(termMap.get(type));
            
             if (!todayDate.isBefore(expireDate)) {
                expired.add(i + 1);
            }
        }
        
        return expired.stream().mapToInt(Integer::intValue).toArray();
    }
}