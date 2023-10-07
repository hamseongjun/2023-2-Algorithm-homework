package four;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int index = 0;
        int max = 0, cmp = 0;

        for (int i = 1; i <= 9; i++) {
            cmp = scanner.nextInt();
            if (cmp > max) {
                max = cmp;
                index = i;
            }
        }
        System.out.println(max);
        System.out.println(index);
    }
}
