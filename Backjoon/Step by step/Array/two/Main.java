package two;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int x = scanner.nextInt();

        int[] arr = new int[n];
        ArrayList<Integer> smallers = new ArrayList<Integer>();

        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
            if (arr[i] < x) {
                smallers.add(arr[i]);
            }
        }
        for (int smaller : smallers) {
            System.out.print(smaller + " ");
        }
    }
}
