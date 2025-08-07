class Solution {
    public boolean solution(String s) {
        String REGEX = "[0-9]+";

        if ((s.length() == 4 || s.length() == 6) && s.matches(REGEX)) return true;
        else return false;
    }
}