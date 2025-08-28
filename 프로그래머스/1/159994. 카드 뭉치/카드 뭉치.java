class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        int chk1 = 0, chk2 = 0;
        boolean possible = true;

        for (int i = 0; i < goal.length; i++) {
            if (chk1 < cards1.length && goal[i].equals(cards1[chk1])) chk1++;
            else if (chk2 < cards2.length && goal[i].equals(cards2[chk2])) chk2++;
            else {
                possible = false;
                break;
            }
        }

        return possible ? "Yes" : "No";
    }
}