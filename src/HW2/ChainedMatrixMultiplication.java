package HW2;

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
        for (diagonal = 1; diagonal <= n-1; diagonal++) {
            for (i = 1; i <= n - diagonal; i++) {
                j = i + diagonal;
                M[i][j] = Integer.MAX_VALUE;
                for (k = i; k <= j - 1; k++) {
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
            int k = P[i][j];
            System.out.print("(");
            order(i, k);
            order(k+1, j);
            System.out.print(")");
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
