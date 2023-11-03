package HW1;

public class Floyd2 {
    public static void main(String[] args) {
        // n, W[][], D[][], P[][] 생성 및 초기화
        int inf = 100_000_000;      // 충분히 큰 수를 infinity로 설정함.
        int n = 5;
        // 교재의 입력 데이터(Figure 3.2)
//        int[][] W = {
//                {0, 1, inf, 1, 5},
//                {9, 0, 3, 2, inf},
//                {inf, inf, 0, 4, inf},
//                {inf, inf, 2, 0, 3},
//                {3, inf, inf, inf, 0}
//        };
        // 임의의 자작 데이터
        int[][] W = {
                {0, 10, 5, inf, inf},
                {inf, 0, 2, inf, inf},
                {1, 13, 0, inf, inf},
                {8, inf, inf, 0, 3},
                {inf, 31, inf, 9, 0}
        };
        int[][] D = new int[n][n];
        int[][] P = new int[n][n];

        floyd2(n, W, D, P);   // floyd2 실행
        printD(n, D);         // D 출력
        printP(n, P);         // P 출력
        path(P, 5, 3);   // path(5,3) 출력
    }

    static void floyd2(int n, int[][] W, int[][] D, int[][] P) {
        int i, j, k;
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                P[i][j] = 0;
            }
        }
        // D = W 깊은 복사
        for (i = 0; i < W[0].length; i++) {
            System.arraycopy(W[i], 0, D[i], 0, W[i].length);
        }
        // D와 P배열 채우는 중
        for (k = 0; k < n; k++) {
            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    if (D[i][k] + D[k][j] < D[i][j]) {
                        P[i][j] = k + 1;
                        D[i][j] = D[i][k] + D[k][j];
                    }
                }
            }
            // 디버깅을 위한 과정 테스트
//            System.out.println("<<<<<<<<" + (k+1) + ">>>>>>>>>>");
//            printD(n, D);
//            printP(n, P);
        }
    }

    static void path(int[][] P, int q, int r) {
        if (P[q - 1][r - 1] != 0) {
            path(P, q, P[q - 1][r - 1]);
            System.out.print("v" + P[q - 1][r - 1] + " ");
            path(P, P[q - 1][r - 1], r);
        }
    }

    static void printD(int n, int[][] D) {
        System.out.println("D >>");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(D[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void printP(int n, int[][] P) {
        System.out.println("P >>");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(P[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
