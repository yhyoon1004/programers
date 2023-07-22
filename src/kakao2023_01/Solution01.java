package kakao2023_01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

//SimpleDateFormat 과 java.util.date을 이용한 풀이
//실패
//todo 날짜값 수정하면 해결할 수 있을 듯 조건 28일이라 명시된걸 못봄
public class Solution01 {
    public static void main(String[] args) throws ParseException {
        Solution_02 a = new Solution_02();
        String today = "2022.05.19";
        String[] terms = {"A 6", "B 12", "C 3"};

        String[] privacies = {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"};
        int[] solution = a.solution(today, terms, privacies);
        System.out.println("________________");
        for (int i : solution) {
            System.out.println(i);
        }
        System.out.println("________________");
    }

}

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) throws ParseException {
        List<Integer> result = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        Date today_date = sdf.parse(today);
        Map<String, Integer> termMap = new HashMap<>();

        for (String term : terms) {
            String[] term_value = term.split("\\s");
            termMap.put(term_value[0], Integer.parseInt(term_value[1]));
        }

        int count = 0;
        for (String privacy : privacies) {
            count++;
            String[] privacy_value = privacy.split("\\s");

            Date parsedPrivacy_date = sdf.parse(privacy_value[0]);

            Long privacyTime = (termMap.get(privacy_value[1]) * 2592000000L) + parsedPrivacy_date.getTime();
            if (today_date.getTime() > privacyTime) {
                result.add(count);
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }
}