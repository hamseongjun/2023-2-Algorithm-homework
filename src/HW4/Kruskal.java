package HW4;

import java.util.ArrayList;
import java.util.Arrays;

public class Kruskal {
    public static void main(String[] args) {
        // ####### 교재의 입력 데이터(Figure 4.7) #######
//        int[][] E = {       // {node1, node2, weight}
//                {1, 2, 1},
//                {1, 3, 3},
//                {2, 3, 3},
//                {3, 5, 2},
//                {3, 4, 4},
//                {2, 4, 6},
//                {4, 5, 5}
//        };
//        int n = 5;          // 모든 vertex 갯수
//        int m = E.length;   // 모든 간선 갯수

        // ####### 임의의 자작 입력 데이터 #######
        int[][] E = {       // {node1, node2, weight}
                {1, 2, 9},
                {1, 3, 1},
                {1, 4, 2},
                {2, 4, 3},
                {3, 4, 3},
                {3, 5, 4},
                {4, 5, 8}
        };
        int n = 5;          // 모든 vertex 갯수
        int m = E.length;   // 모든 간선 갯수

        ArrayList<int[]> F = new ArrayList<>();
        kruskal(n, m, E, F);
        printF(F);
    }

    static void kruskal(int n, int m, int[][] E, ArrayList<int[]> F) {
        // ######## Disjoint Set Data Structure 2 ########
        class DisJointSetDataStructure2 {
            int parent = 0;
            int depth = 0;
            DisJointSetDataStructure2[] U;

            public DisJointSetDataStructure2() {
            }

            // initial
            DisJointSetDataStructure2(int n) {
                U = new DisJointSetDataStructure2[n + 1];       // vertex가 0이 아닌 1부터 시작하도록 size + 1
                for (int i = 1; i <= n; i++) {
                    makeset(i);
                }
            }


            // makeset : 초기화 함수.
            void makeset(int i) {
                U[i] = new DisJointSetDataStructure2();
                U[i].parent = i;
                U[i].depth = 0;
            }

            // find : 자신이 속한 트리(집합)의 루트를 찾는 함수.
            int find(int i) {
                int j = i;
                while (U[j].parent != j)
                    j = U[j].parent;
                return j;
            }

            // merge : 두 트리(집합)을 합병하는 함수.
            void merge(int p, int q) {
                if (U[p].depth == U[q].depth) {     // depth가 같은 두 트리를 합병하면
                    U[p].depth += 1;                // depth를 1증가
                    U[q].parent = p;
                } else if (U[p].depth < U[q].depth)
                    U[p].parent = q;
                else
                    U[q].parent = p;
            }

            // equal
            boolean equal(int p, int q) {
                if (p == q) return true;
                else return false;
            }
        }

        // ######## Krusukal's Algorithm start #########
        int i = 0, j = 0;
        int p = 0, q = 0;
        int[] e = new int[1];   // E에서 선택할 엣지 1개

        Arrays.sort(E, (o1, o2) -> Integer.compare(o1[2], o2[2]));     // 엣지의 가중치를 오름차순으로 정렬
        DisJointSetDataStructure2 disJointSetDataStructure2 = new DisJointSetDataStructure2(n); // initial(n)

        int k = 0;      // 집합 E의 순차 순회를 위한 인덱스
        while (F.size() < n - 1) {
            e = E[k];
            i = e[0];   // 첫 번째 노드
            j = e[1];   // 두 번째 노드
            p = disJointSetDataStructure2.find(i);  // 첫 번째 노드의 루트
            q = disJointSetDataStructure2.find(j);  // 두 번째 노드의 루트
            if (!disJointSetDataStructure2.equal(p, q)) {   // 루트가 같지 않다면
                disJointSetDataStructure2.merge(p, q);      // 서로 다른 두 집합 합병
                F.add(e);
            }
            k++;    // 다음으로 가중치가 가장 작은 간선 선택
        }
    }

    // MST를 나타내는 선택된 간선 집합 F를 화면에 출력하는 함수.
    static void printF(ArrayList<int[]> F) {
        System.out.println("집합 F : ");
        for (int[] edge : F) {
            System.out.print("(v" + edge[0] + ",v" + edge[1] + ") ");
        }
    }
}