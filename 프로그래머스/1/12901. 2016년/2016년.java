import java.time.DayOfWeek;
import java.time.LocalDate;

class Solution {
    public String solution(int a, int b) {
        String answer = "";
        String[] weekArray = {"MON","TUE","WED","THU","FRI","SAT","SUN"};

        LocalDate date = LocalDate.of(2016, a, b);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int dayOfWeekValue = dayOfWeek.getValue();

        answer = weekArray[dayOfWeekValue - 1];
        
        return answer;
    }
}