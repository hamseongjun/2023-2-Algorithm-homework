package HW3;

/*
 *
 * A[i][j] = 실질적인 최적의 평균 탐색 시간 값이 들어있음. 1행 n열이 최종적으로 구하고자 하는 결과
 * R[i][j]  = key i부터 j까지의 트리에선 루트가 k라는 의미.
 *
 */
public class OptimalBinarySearchTrees {
    static int n = 4;
    static double[] minavg = new double[1];     // 함수 호출시 value가 아닌 reference를 넘기기 위해 크기가 1인 배열을 사용.
    // 교재의 입력 데이터(Example 3.9)
    static double[] p = {0, 3.0 / 8.0, 3.0 / 8.0, 1.0 / 8.0, 1.0 / 8.0};    // p0는 실질적으로 존재x, p1부터 존재함.
    //임의의 자작 데이터
//    static double[] p = {0, 6.0 / 10.0, 3.0 / 10.0, 4.0 / 10.0, 1.0 / 10.0};
    static int[][] R = new int[n + 2][n + 1];

    public static void main(String[] args) {
        optsearchtree(n, p, minavg, R);
        System.out.printf("minavg : %.3f \n", minavg[0]);       // 확률 값은 소숫점 3자리까지만 출력
        printR(n, R);
    }

    static void optsearchtree(int n, double[] p, double[] minavg, int[][] R) {
        int i, j, k, diagonal;
        double[][] A = new double[n + 2][n + 1];
        // 초기화
        for (i = 1; i <= n; i++) {  // 최적 탐색 시간과 루트 배열 초기화
            A[i][i - 1] = 0;          // 왼쪽 서브 트리가 없을 때는 0
            A[i][i] = p[i];         // 노드가 1개인 트리는 depth가 0이니까 그냥 확률 값 그 자체
            R[i][i] = i;            // 노드가 1개인 트리는 내가 곧 루트
            R[i][i - 1] = 0;          // 트리가 없으니까 루트 없음 = 0
        }
        A[n + 1][n] = 0;
        R[n + 1][n] = 0;   // 오른쪽 서브트리가 없을 때는 0
        // 시그마 계산 (sigma[i][j] = 시그마 m은 i부터 j까지 p(m))
        double[][] sigmaP = new double[n + 1][n + 1];
        for (i = 1; i <= n; i++) {
            for (j = 1; j <= n; j++) {
                for (k = i; k <= j; k++) {
                    sigmaP[i][j] += p[k];
                }
            }
        }

        for (diagonal = 1; diagonal <= n - 1; diagonal++) {       // 1. 대각선 증가시키는 전체 반복문 (입력되는 트리의 전체 노드 갯수 증가)
            for (i = 1; i <= n - diagonal; i++) {                 // 2. i = 행 증가 (i j 둘 다 1씩 증가하니까 입력으로 들어온 key의 범위도 1씩 증가 (ex, 123, 234, 345)
                j = i + diagonal;                               //    j = 열 증가 (오른쪽 아래로 이동해야하므로 j와 같이 증가)
                double min = Double.MAX_VALUE;
                for (k = i; k <= j; k++) {                      // 3. k = 루트 : 고정된 입력 트리에서 i부터 j까지 중 누가 루트가 돼야 최적인지 반복 검사
                    if (A[i][k - 1] + A[k + 1][j] < min) {      // 더 작으면 min 값 갱신.
                        min = A[i][k - 1] + A[k + 1][j];
                        R[i][j] = k;    // 최적 트리의 루트가 k임을 기록.
                    }
                }
                A[i][j] = min;
                A[i][j] += sigmaP[i][j];      // 시그마 m은 i부터 j까지 p(m)
            }
        }
        printA(n, A);
        minavg[0] = A[1][n];
    }

    static void printA(int n, double[][] A) {
        System.out.println("A >>");
        for (int i = 1; i <= n + 1; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.printf("%5.3f ", A[i][j]);       // 확률 값은 소숫점 3자리까지만 출력.
            }
            System.out.println();
        }
        System.out.println();
    }

    static void printR(int n, int[][] R) {
        System.out.println("R >>");
        for (int i = 1; i <= n + 1; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.printf("%d ", R[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
