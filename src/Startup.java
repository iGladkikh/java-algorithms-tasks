import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Startup {
    static long amount;
    static int partnersCount;
    static int daysCount;
    static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        String[] in = scanner.nextLine().split(" ", 3);
        amount = Long.parseLong(in[0]);
        partnersCount = Integer.parseInt(in[1]);
        daysCount = Integer.parseInt(in[2]);

        ArrayList<Long> amounts = new ArrayList<>(List.of(amount));
        ArrayList<ArrayList<Long>> result = getAvailableAmount(amounts);

        if (result.size() < daysCount) {
            System.out.print(-1);
        } else {
            System.out.print(result.getLast().getLast());
        }
    }

    static ArrayList<ArrayList<Long>> getAvailableAmount(ArrayList<Long> amounts) {
        ArrayList<Long> checkAmounts = amounts;
        ArrayList<ArrayList<Long>> availableAmounts = new ArrayList<>();
        for (int i = 0; i < daysCount; i++) {
            ArrayList<Long> nextAvailableAmounts = getAvailableTomorrowAmounts(checkAmounts);
            if (!nextAvailableAmounts.isEmpty()) {
                availableAmounts.add(nextAvailableAmounts);
                checkAmounts = nextAvailableAmounts;
            }
            else {
                break;
            }
        }
        return availableAmounts;
    }

    static ArrayList<Long> getAvailableTomorrowAmounts(ArrayList<Long> amounts) {
        ArrayList<Long> nextAmounts = new ArrayList<>();
        for (long amount : amounts) {
            for (int j = 0; j <= 9; j++) {
                long nextAmount = amount * 10 + j;
                if (nextAmount % partnersCount == 0) {
                    nextAmounts.add(nextAmount);
                }
            }
        }
        return nextAmounts;
    }
}
