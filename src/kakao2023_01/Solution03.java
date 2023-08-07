package kakao2023_01;

/*
 * term = 1~20
 * kind = A~Z
 * privacies = 1~100
 * 1 month = 28
 *  day kind = 1~28
 * */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution03 {
    public static void main(String[] args) {
        String today = "2022.05.19";
        String[] terms = {"A 6", "B 12", "C 3"};

        String[] privacies = {"2021.05.02 A", "2021.07.01 B",
                "2022.02.19 C", "2022.02.20 C"};
        Solve03 s3 = new Solve03();
        int[] solved = s3.solve(today, terms, privacies);
        for (int i = 0; i < solved.length; i++) {
            System.out.println("solved = " + solved[i]);
        }
    }
}

class Solve03 {
    public static final int MONTH_BY_DAY = 28;

    public int dateToDays(int[] value) {

        int year = value[0];
        int month = value[1];
        int day = value[2];
        int result = 0;
        result += year * 12 * MONTH_BY_DAY;
        result += month * MONTH_BY_DAY;
        result += day;
        return result;
    }

    public int[] stringArrToIntegerArr(String[] strings) {
        int[] result = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            result[i] = Integer.parseInt(strings[i]);
        }
        return result;
    }

    public int[] solve(String today, String[] terms, String[] privacies) {
        List<Integer> res = new ArrayList<>();
        String[] today_arr = today.split("[.]");
        int[] today_int = stringArrToIntegerArr(today_arr);

        int today_Days = dateToDays(today_int);

        Map<String, Integer> terms_map = new HashMap<>();

        for (String term : terms) {
            String[] ter = term.split("\\s");
            terms_map.put(ter[0], Integer.parseInt(ter[1]));
        }

        int count = 1;
        for (String privacy : privacies) {
            String[] pri = privacy.split("\\s");
            String[] privacy_date = pri[0].split("[.]");
            int[] pri_date_int = stringArrToIntegerArr(privacy_date);
            pri_date_int[1] += terms_map.get(pri[1]);
            int target_Days = dateToDays(pri_date_int);

            if (target_Days <= today_Days) {
                res.add(count);
            }
            count++;
        }


        return res.stream().mapToInt(i -> i).toArray();
    }
}