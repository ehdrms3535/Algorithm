//22112353 김동근
import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        int answer=0;
        Arrays.sort(routes, Comparator.comparingInt(o -> o[0]));

        int x = routes[0][0];
        int y = routes[0][1];

        for(int k=1; k<routes.length;k++){
            if(routes[k][0]<=y){
                x = routes[k][0];
                y = (y<routes[k][1])?y:routes[k][1];
            }
            else if(routes[k][0]>y) {
                answer++;
                x=routes[k][0];
                y=routes[k][1];
            }
        }
        return answer+1;
    }
}