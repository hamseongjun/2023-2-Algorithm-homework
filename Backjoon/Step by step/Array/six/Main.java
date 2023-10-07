package six;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int i, j, tmp;
        // 바구니 초기화
        int[] arr = new int[n];
        for (int init = 0; init < n; init++) {
            arr[init] = init + 1;
        }
        // 입력 및 swap
        for (int r = 0; r < m; r++) {
            i = scanner.nextInt();
            j = scanner.nextInt();
            tmp = arr[i-1];
            arr[i-1] = arr[j-1];
            arr[j-1] = tmp;
        }
        // 결과 출력
        for (int num : arr) {
            System.out.print(num + " ");
        }
        scanner.close();
    }
}
