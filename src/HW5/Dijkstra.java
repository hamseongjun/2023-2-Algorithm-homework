package HW5;

import java.util.ArrayList;

public class Dijkstra {
    public static void main(String[] args) {
        int inf = 100_000_000;      // 적당히 큰 수를 무한대로 설정.
        int n = 5;                  // vertex 5개인 그래프
        // ########### 교재의 입력 데이터(Figure 4.8) ###########
        int[][] W = {               // 각각의 행,열은 1부터 시작함. 0행과 0열은 안 씀.
                {0, 0, 0, 0, 0, 0},
                {0, 0, 7, 4, 6, 1},
                {0, inf, 0, inf, inf, inf},
                {0, inf, 2, 0, 5, inf},
                {0, inf, 3, inf, 0, inf},
                {0, inf, inf, inf, 1, 0}
        };

        // ########### 임의의 자작 입력 데이터 ############

        ArrayList<int[]> F = new ArrayList<>();
        dijkstra(n, W, F);
        printF(F);      // 집합 F의 엣지 목록 출력
    }


    static void dijkstra (int n, int[][] W, ArrayList<int[]> F) {
        int i = 0, vnear = 0;
        int[] e;        // node1, node2, weight
        int[] touch = new int[n+1];     // n을 index n에 넣기 위해 배열 사이즈+1
        int[] length = new int[n+1];    // 위와 동일
        // 1. 초기화
        for (i = 2; i <= n; i++) {
            touch[i] = 1;
            length[i] = W[1][i];
        }
        // 2. 다익스트라 실행 (vertex 갯수 - 1번. 각각의 실행에서 최소 weight 간선을 선택함)
        for (int repeat = 1; repeat <= n-1; repeat++) {
            int min = Integer.MAX_VALUE;
            for (i = 2; i <= n; i++) {                      // 반복문을 돌며 Y와 가장 가까운 edge 찾기.
                if (0 <= length[i] && length[i] <= min) {
                    min = length[i];    // edge의 가중치
                    vnear = i;          // 선택된 edge에 연결된 vertex.
                }
            }
            e = new int[3];
            e[0] = touch[vnear];        // 바로 직전 vertex
            e[1] = vnear;               // 새로 선택된 vertex.
            e[2] = W[touch[vnear]][vnear];  // weight
            F.add(e);
            for(i = 2; i <= n; i++) {
                if (length[vnear] + W[vnear][i] < length[i]) {  // 만약 새로 추가된 vertex를 거쳐가는 것이 기존보다 더 짧다면?
                    length[i] = length[vnear] + W[vnear][i];    // length 갱신
                    touch[i] = vnear;                           // 직전 노드 갱신
                }
            }
            length[vnear] = -1;     // 중복 선택 방지
        }
    }

    static void printF(ArrayList<int[]> F) {
        System.out.println("집합 F : ");
        for (int[] edge : F) {
            System.out.print("(v" + edge[0] + ",v" + edge[1] + ") ");
        }
    }
}
