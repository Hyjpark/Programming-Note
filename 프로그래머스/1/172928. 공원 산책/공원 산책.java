class Solution {
    public int[] solution(String[] park, String[] routes) {
        int x = 0,  y = 0;
        
        for (int i = 0; i < park.length; i++) {
            int idx = park[i].indexOf('S');
            if (idx != -1) {
                y = i;
                x = idx;
                break;
            }
        }
        
        for (String r : routes) {
            String[] cmd = r.split(" ");
            String op = cmd[0];
            int n = Integer.parseInt(cmd[1]);
            
            int nx = x;
            int ny = y;
            boolean blocked = false;
            
            for (int i = 0; i < n; i++) {
                switch (op) {
                    case "N": ny--; break;
                    case "S": ny++; break;
                    case "W": nx--; break;
                    case "E": nx++; break;
                }
                if (ny < 0 || ny >= park.length ||
                    nx < 0 || nx >= park[0].length() ||
                    park[ny].charAt(nx) == 'X') {
                    blocked = true;
                    break;
                }
            }
            if (!blocked) {
                x = nx;
                y = ny;
            }
        }
        
        return new int[] {y, x};
    }
}