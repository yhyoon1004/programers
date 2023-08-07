package kakao2023_01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

//SimpleDateFormat 과 java.util.date을 이용한 풀이
//실패
//todo 날짜값 수정하면 해결할 수 있을 듯 조건 28일이라 명시된걸 못봄
public class Solution01 {
    public static void main(String[] args) throws ParseException {
        Solve01 a = new Solve01();
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

class Solve01 {
    public int[] solution(String today, String[] terms, String[] privacies) throws ParseException {
        List<Integer> result = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        Date today_date = sdf.parse(today);
        Map<String, Integer> termMap = new HashMap<>();

        //문자열 배열로 받은 정책의 기간을 Map으로 변환 key=정책명 / value=기간(달)
        for (String term : terms) {
            String[] term_value = term.split("\\s");
            termMap.put(term_value[0], Integer.parseInt(term_value[1]));
        }

        //약관동의일자 목록을 순회하며 만료일자를 현재와 비교
        int count = 0;
        for (String privacy : privacies) {
            count++;
            String[] privacy_value = privacy.split("\\s");

            Date parsedPrivacy_date = sdf.parse(privacy_value[0]);
            // 60 = 1m // 60m = 1h // 24h = 1d // 28d = 1M
            // 60 * 60 * 24 * 28 = 2,419,200
            Long expiredTime = (termMap.get(privacy_value[1]) * 2419200000L) + parsedPrivacy_date.getTime();
            System.out.println("----인덱스 [ " + count + " ] ----");
            System.out.println("termMap.get(privacy_value[1]) = " + termMap.get(privacy_value[1]) + "\n(termMap.get(privacy_value[1]) * 2419200000L) = " + (termMap.get(privacy_value[1]) * 2419200L));
            System.out.println("약관 동의일 " + parsedPrivacy_date.getTime()+"|"+sdf.format(new Date(parsedPrivacy_date.getTime())));
            System.out.println("오늘   " + today_date.getTime()+"|"+sdf.format(new Date(today_date.getTime())));
            System.out.println("만료일 " + expiredTime +"|"+sdf.format(new Date(expiredTime)));
            System.out.println("------------------");

            if (today_date.getTime() > expiredTime) {
                System.out.println("추가된 인덱스 = " + count);
                result.add(count);
            }
        }//end of for

        return result.stream().mapToInt(i -> i).toArray();
    }
}