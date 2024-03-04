import java.util.Scanner;

public class Tries {
    static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        String[] boy = scanner.nextLine().split(" ", 2);
        int boyTree = Integer.parseInt(boy[0]);
        int boyLength = Integer.parseInt(boy[1]);
        int boyTreeCount = boyLength * 2 + 1;
        int[] boyRange = {boyTree - boyLength, boyTree + boyLength};

        String[] girl = scanner.nextLine().split(" ", 2);
        int girlTree = Integer.parseInt(girl[0]);
        int girlLength = Integer.parseInt(girl[1]);
        int girlTreeCount = girlLength * 2 + 1;
        int[] girlRange = {girlTree - girlLength, girlTree + girlLength};

        int[] intersectedRange = new int[2];
        int intersectedTreeCount = 0;

        if (boyTree == girlTree) {
            intersectedRange = boyLength <= girlLength ? boyRange : girlRange;
            intersectedTreeCount = intersectedRange[1] - intersectedRange[0] + 1;
        } else {
            int[] lowerRange;
            int[] upperRange;

            if (boyTree < girlTree) {
                lowerRange = boyRange;
                upperRange = girlRange;
            } else {
                lowerRange = girlRange;
                upperRange = boyRange;
            }

            if (upperRange[0] <= lowerRange[1])
            {
                intersectedRange = upperRange[1] <= lowerRange[1]
                        ? upperRange
                        : new int[]{upperRange[0], lowerRange[1]};

                intersectedTreeCount = intersectedRange[1] - intersectedRange[0] + 1;
            }
        }
        System.out.print(boyTreeCount + girlTreeCount - intersectedTreeCount);
    }
}
