package HW6;

import java.util.Arrays;

public class Knapsack {
    // ########### 교재의 입력 데이터 (Example 5.6) ##############
//    static int n = 4;
//    static int W = 16;
//    static int[] p = {0, 40, 30, 50, 10};
//    static int[] w = {0, 2, 5, 10, 5};

    // ########## 임의의 자작 입력 데이터 ###############
    static int n = 4;
    static int W = 12;
    static int[] p = {0, 30, 40, 35, 18};
    static int[] w = {0, 2, 5, 7, 6};

    public static void main(String[] args) {
        boolean[] bestset = new boolean[n + 1];     // 최적해가 어떻게 만들어졌는지에 대한 정보 (최적해가 갱신되는 순간 아래 include 배열을 캡처)
        boolean[] include = new boolean[n + 1];     // 해가 어떻게 만들어지는지에 대한 정보 (경로를 저장하며 상태공간트리 탐색)
        int[] maxprofit = new int[1];               // 최적해 (최대 이윤)
        knapsack(0, 0, 0, maxprofit, bestset, include);

        for (int i = 1; i <= n; i++) {
            System.out.println(bestset[i] + " ");
        }
    }

    static void knapsack(int i, int profit, int weight, int[] maxprofit, boolean[] bestset, boolean[] include) {
        if (weight <= W && profit > maxprofit[0]) {     // maxprofit 갱신 조건 (더 좋은 경우의 수가 나왔냐?)
            maxprofit[0] = profit;      // 최적해 갱신
            int numbest = i;            // 현재 아이템까지의 경로만을 bestset에 저장하기 위한 변수.
            Arrays.fill(bestset, false);        // 이전에 들어있던 쓰레기 값 초기화.
            System.arraycopy(include, 1, bestset, 1, numbest);    // bestset = include; (src, srcPos, dest, destPos, length)
        }

        if (promising(i, profit, weight, maxprofit)) {      // 유망하냐?
            include[i + 1] = true;      // 왼쪽 자식(true) 방문 (다음 아이템을 넣는 경우)
            knapsack(i + 1, profit + p[i + 1], weight + w[i + 1], maxprofit, bestset, include);
            include[i + 1] = false;     // 오른쪽 자식(false) 방문 (다음 아이템을 안 넣는 경우)
            knapsack(i + 1, profit, weight, maxprofit, bestset, include);
        }
    }

    static boolean promising(int i, int profit, int weight, int[] maxprofit) {
        int j, k;       // 인덱스
        int totweight;  //
        float bound;

        if (weight >= W) return false;
        else {
            j = i + 1;
            bound = profit;
            totweight = weight;
            while ((j <= n) && (totweight + w[j] <= W)) {       // 아이템을 값어치 순으로 가방에 담아본다. (n번까지 다 넣어도 가방이 꽉 안 차든가 or 중간에 무게가 넘치는 물건이 있든가)
                totweight = totweight + w[j];
                bound = bound + p[j];
                j++;
            }
            k = j;
            if (k <= n)     // 중간에 무게가 넘쳤다 -> 가방의 남은 공간보다 더 큰 물건이 있다
                bound = bound + (W - totweight) * (float) p[k] / w[k];  // bound 조건 (k-1까지 다 담았을 때 이윤 + 아이템k를 가루로 만들어서까지 쑤셔넣는 이윤)
            return bound > maxprofit[0];    // 이렇게 똥꼬쇼를 해서라도 갱신할 가능성이 있다면 true.
        }
    }
}