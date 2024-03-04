import java.util.Scanner;

public class Spaces {
    static int linesCount;
    static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        linesCount = Integer.parseInt(scanner.nextLine());

        long result = 0;
        for (int i = 0; i < linesCount; i++) {
            int spaceCount = Integer.parseInt(scanner.nextLine());
            result += getPressCount(spaceCount);
        }

        System.out.print(result);
    }

    public static int getPressCount(int spaceCount) {
        final int tab = 4;

        int result;
        if (spaceCount < 3) {
            result = spaceCount;
        } else if (spaceCount == 3) {
            result = 2;
        } else {
            result = spaceCount / tab;
            result += getPressCount(spaceCount % tab);
        }
        return result;
    }
}
