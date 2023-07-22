package kakao2023_01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution02 {
    public static void main(String[] args)  {
        Solution_02 a = new Solution_02();
        String today = "2022.05.19";
        String[] terms = {"A 6", "B 12", "C 3"};

        String[] privacies = {"2021.05.02 A", "2021.07.01 B",
                "2022.02.19 C", "2022.02.20 C"};
        int[] solution = a.solution(today, terms, privacies);
        System.out.println("_____ result _____");
        for (int i : solution) {
            System.out.println(i);
        }
        System.out.println("_____ ______ _____");
    }
}

class Solution_02 {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> result = new ArrayList<>();

        String[] today_split = today.split("[.]");

        Map<String, Integer> terms_map = new HashMap<>();

        for (String term : terms) {//약관 별 기간 값을 Map으로 변환  (사용하기 편하게 하기 위해)
            String[] term_pair = term.split("\\s");
            terms_map.put(term_pair[0], Integer.parseInt(term_pair[1]));
        }

//        privacies 계산 처리
        for (int j = 0; j<privacies.length; j++) {

            //현재 날짜 int[]형 변환
            int[] today_split_integer = new int[3];
            for (int i = 0; i < 3; i++) {
                today_split_integer[i] = Integer.parseInt(today_split[i]);
            }

            String[] privacy_pair = privacies[j].split("\\s");
            String[] privacy_Date = privacy_pair[0].split("[.]");

            //약관동의일자 int[]형 변환
            int[] privacy_split_Date_integer = new int[3];
            for (int i = 0; i < 3; i++) {
                privacy_split_Date_integer[i] = Integer.parseInt(privacy_Date[i]);
            }

            //약관 동의 일자 만료일 계산
            int expireMonth = privacy_split_Date_integer[1] + terms_map.get(privacy_pair[1]);
            if (expireMonth > 12) {
                privacy_split_Date_integer[0] += expireMonth / 12;
                privacy_split_Date_integer[1] = expireMonth % 12;
            }

            if (privacy_split_Date_integer[0] > today_split_integer[0]) {
                continue;
            } else if (privacy_split_Date_integer[0] == today_split_integer[0]
                    && privacy_split_Date_integer[1] > today_split_integer[1]) {
                continue;
            } else if (privacy_split_Date_integer[1] == today_split_integer[1]
                    && privacy_split_Date_integer[2] > today_split_integer[2]) {
                continue;
            }else {
                result.add(j+1);
            }


        }//end of for(String privacy : privacies)


        int[] arr = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            arr[i] = result.get(i).intValue();
        }
        return arr;
    }
}
