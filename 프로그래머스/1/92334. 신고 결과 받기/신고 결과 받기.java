import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, Set<String>> reportedMap = new HashMap<>();
        Map<String, Integer> mailCount = new HashMap<>();
        
        for (String id : id_list) {
            reportedMap.put(id, new HashSet<>());
            mailCount.put(id, 0);
        }
        
        for (String rep : report) {
            String[] arr = rep.split(" ");
            reportedMap.get(arr[1]).add(arr[0]);
        }
        
        for (String target : id_list) {
            Set<String> reporters = reportedMap.get(target);
            if (reporters.size() >= k) {
                for (String reporter : reporters) {
                    mailCount.put(reporter, mailCount.get(reporter) + 1);
                }
                
            }
        }
        
        int[] answer = new int[id_list.length];
        for (int i = 0; i < id_list.length; i++) {
            answer[i] = mailCount.get(id_list[i]);
        }
        
        return answer;
    }
}