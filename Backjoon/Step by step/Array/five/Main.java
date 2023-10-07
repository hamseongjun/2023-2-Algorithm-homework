package five;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int[] arr = new int[n];
        int i, j, k;

        for (int r = 0; r < m; r++) {
            i = scanner.nextInt();
            j = scanner.nextInt();
            k = scanner.nextInt();
            for (int rr = i; rr <= j; rr++) {
                arr[rr-1] = k;
            }
        }
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
