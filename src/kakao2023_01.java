import java.util.*;

public class kakao2023_01 {
    public static void main(String[] args) {
        Solution a = new Solution();
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
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> result = new ArrayList<>();
        String[] _today = today.split("[.]");
        int[] i_today = new int[3];
        i_today[0] = Integer.parseInt(_today[0]);
        i_today[1] = Integer.parseInt(_today[1]);
        i_today[2] = Integer.parseInt(_today[2]);

        Map<String, Integer> _terms = new HashMap<>();

        for (String v : terms) {
            String[] temp = v.split("\\s");
            _terms.put(temp[0], Integer.parseInt(temp[1]));
        }

        Integer[] date = new Integer[3];

        int counter = 1;
        for (String v : privacies) {
            String[] temp = v.split("\\s");
            String[] dateTemp = temp[0].split("[.]");
            date[0] = Integer.parseInt(dateTemp[0]);
            date[1] = Integer.parseInt(dateTemp[1]);
            date[2] = Integer.parseInt(dateTemp[2]);
            date[1] = date[1] + _terms.get(temp[1]);
            if (date[1] > 12) {
                date[0] += (date[1] / 12);
                date[1] -= (date[1] / 12) * 12;
            }

            if (i_today[0] < date[0]) {
                result.add(counter);
            } else if (i_today[0] == date[0] && i_today[1] < date[1]) {
                result.add(counter);
            } else if (i_today[1] == date[1] && i_today[2] < date[2]) {
                result.add(counter);
            }
            counter++;
        }//end of for

        return result.stream().mapToInt(i -> i).toArray();
    }
}


