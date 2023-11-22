package HW2;

/*
*
* d = 행렬들의 열의 수
* P[i][j] = i부터 j 사이에서 어디를 쪼개야 하는지 (k가 무엇인지)
* M[i][j] = 실질적인 최솟값을 저장하는 행렬
 */
public class ChainedMatrixMultiplication {
    static int n = 6;
    // 교재의 입력 데이터(Example 3.5)
    static int[] d = {5, 2, 3, 4, 6, 7, 8};
    // 임의의 자작 데이터
//    static int[] d = {6, 2, 3, 7, 4, 2, 9};
    static int[][] P = new int[n+1][n+1];

    public static void main(String[] args) {
        int ans = minmult(n, d, P);
        System.out.println("최솟값 : " + ans);
        printP(n, P);
        order(1, n);
    }

    static int minmult(int n, int[] d, int[][] P) {
        int i, j, k, diagonal;
        int[][] M = new int[n+1][n+1];
        for (i = 1; i <= n; i++)
            M[i][i] = 0;
        for (diagonal = 1; diagonal <= n-1; diagonal++) {       // 1. 대각선 방향으로 계산하는 전체 반복문
            for (i = 1; i <= n - diagonal; i++) {               // 2. i = 대조군의 행 (n - diagonal개의 대조군 존재)
                j = i + diagonal;                               //    j = 대조군의 열 (오른쪽 아래로 이동해야하므로 i와 같이 증가)
                M[i][j] = Integer.MAX_VALUE;
                for (k = i; k <= j - 1; k++) {                  // 3. k = 비교군의 위치를 1씩 이동시키는 반복문.
                    if (M[i][j] > M[i][k] + M[k + 1][j] + d[i - 1] * d[k] * d[j]) {
                        M[i][j] = M[i][k] + M[k + 1][j] + d[i - 1] * d[k] * d[j];
                        P[i][j] = k;
                    }
                }
            }
        }
        printM(n, M);
        return M[1][n];
    }

    static void order(int i, int j) {
        if (i == j) System.out.print("A" + i);
        else {
            int k = P[i][j];            // k = 어디를 쪼개야 돼?
            System.out.print("(");      // 일단 괄호 먼저 열고
            order(i, k);                // k 앞 부분
            order(k+1, j);           // k 뒷 부분
            System.out.print(")");      // 괄호 닫고
        }
    }

    static void printM(int n, int[][] M) {
        System.out.println("M >>");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.printf("%4d ", M[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    static void printP(int n, int[][] P) {
        System.out.println("P >>");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(P[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
