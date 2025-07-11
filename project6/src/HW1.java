//22112353 김동근

import java.util.*;
import java.util.regex.*;

class Solution {
    public int solution(String dartResult) {
        int answer = 0;
        Map<String,Integer> hashmap = new HashMap<>();
        Map<String,Integer> hashmap1 = new HashMap<>();

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(dartResult);

        List<String> numbers = new ArrayList<>();

        while (matcher.find()) {
            numbers.add(matcher.group());  // 추출된 숫자 문자열
        }

        int arr[] = {1,1,1};
        int locate =2;

        hashmap.put("S",1);
        hashmap.put("D",2);
        hashmap.put("T",3);

        hashmap1.put("#",-1);
        hashmap1.put("*",2);

        for(int i=dartResult.length()-1; i>=0;i--){
            char a = dartResult.charAt(i);
            String t = String.valueOf(a);
            if(hashmap.containsKey(t)){
                arr[locate]*=(int)Math.pow(Integer.parseInt(numbers.get(locate)),hashmap.get(t));
                locate--;
            }

            if(hashmap1.containsKey(t)){
                int option = hashmap1.get(t);

                if (option == 2) { // "*" 처리
                    arr[locate] *= 2;
                    if (locate > 0) arr[locate - 1] *= 2;
                }
                else if (option == -1) { // "#" 처리
                    arr[locate] *= -1;
                }
            }
        }

        for(int i=0;i<3;i++){
            answer+=arr[i];
        }

        return answer;
    }
}