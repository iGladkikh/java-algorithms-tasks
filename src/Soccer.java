import java.util.ArrayList;
import java.util.Scanner;

public class Soccer {
    static final int GAMES_COUNT = 2;
    static Scanner scanner;
    static ArrayList<int[]> games;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        games = new ArrayList<>(GAMES_COUNT);

        for (int i = 0; i < GAMES_COUNT; i++) {
            int[] game = new int[2];
            String[] scannerGame = scanner.nextLine().split(":", 2);
            game[0] = Integer.parseInt(scannerGame[0]);
            game[1] = Integer.parseInt(scannerGame[1]);
            games.add(game);
        }

        int gameLocate = Integer.parseInt(scanner.nextLine());

        int totalScoreTeam1 = 0;
        int totalScoreTeam2 = 0;
        for (int[] game : games) {
            int scoreTeam1 = game[0];
            int scoreTeam2 = game[1];
            totalScoreTeam1 += scoreTeam1;
            totalScoreTeam2 += scoreTeam2;
        }

        int result;

        if (totalScoreTeam1 > totalScoreTeam2) {
            result = 0;
        } else if (totalScoreTeam1 == totalScoreTeam2 && totalScoreTeam1 == 0) {
            result = 1;
        } else {
            result = totalScoreTeam2 - totalScoreTeam1 + 1;

            if ((gameLocate == 1 && result + games.getLast()[0] - 1 > games.getFirst()[1])
                    || (gameLocate == 2 && games.getFirst()[0] > games.getLast()[1])
            ) {
                --result;
            }
        }

        System.out.print(result);
    }
}
