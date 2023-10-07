package Three;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int min, max, cmp;
        int[] arr = new int[n];

        int first = scanner.nextInt();
        min = first; max = first;
        arr[0] = first;

        for (int i = 1; i < n; i++) {
            cmp = scanner.nextInt();
            if (cmp < min)
                min = cmp;
            if (cmp > max)
                max = cmp;
        }
        System.out.print(min + " " + max);
    }
}
