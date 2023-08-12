package kakao2023_02;

public class Solution01 {
    public static void main(String[] args) {

        int testCap01 = 4;
        int testN01 = 5;
        int[] testDeliveries01 = {1, 0, 3, 1, 2};
        int[] testPickups01 = {0, 3, 0, 4, 0};
        long testResult01;

        int testCap02 = 2;
        int testN02 = 7;
        int[] testDeliveries02 = {1, 0, 2, 0, 1, 0, 2};
        int[] testPickups02 = {0, 2, 0, 1, 0, 2, 0};
        long testResult02;

        Solve solve = new Solve();
        testResult01 = solve.solution(testCap01, testN01, testDeliveries01, testPickups01);
        testResult02 = solve.solution(testCap02, testN02, testDeliveries02, testPickups02);

        //result = 16
        System.out.println("testResult01 = " + testResult01);
        //result =30
        System.out.println("testResult02 = " + testResult02);
    }

}

class Solve {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = -1;
        return answer;
    }
}
