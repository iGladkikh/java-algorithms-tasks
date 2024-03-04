import java.math.BigInteger;
import java.util.*;

public class Startup {
    static BigInteger amount;
    static BigInteger partnersCount;
    static int daysCount;
    static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        String[] in = scanner.nextLine().split(" ", 3);
        amount = new BigInteger(in[0]);
        partnersCount = new BigInteger(in[1]);
        daysCount = Integer.parseInt(in[2]);

        ArrayList<BigInteger> amounts = new ArrayList<>(List.of(amount));
        Map<Integer, ArrayList<BigInteger>> result = getAvailableAmount(amounts);

        if (result.containsKey(daysCount - 1)) {
            System.out.print(result.get(daysCount - 1).getLast());
        } else {
            System.out.print(-1);
        }
    }

    static Map<Integer, ArrayList<BigInteger>> getAvailableAmount(ArrayList<BigInteger> amounts) {
        ArrayList<BigInteger> checkAmounts = amounts;
        Map<Integer, ArrayList<BigInteger>> availableAmounts = new HashMap<>();
        for (int i = 0; i < daysCount; i++) {
            ArrayList<BigInteger> nextAvailableAmounts = getAvailableTomorrowAmounts(checkAmounts);
            if (!nextAvailableAmounts.isEmpty()) {
                availableAmounts.put(i, nextAvailableAmounts);
                checkAmounts = nextAvailableAmounts;

                if (i > 0 &&
                        availableAmounts.get(i - 1).size() == 1 &&
                        nextAvailableAmounts.size() == 1 &&
                        nextAvailableAmounts.getFirst()
                                .mod(availableAmounts.get(i - 1).getFirst())
                                .compareTo(BigInteger.ZERO) == 0
                ) {
                    int[] end = new int[daysCount - i - 1];
                    BigInteger finalResult = new BigInteger(
                            nextAvailableAmounts.getFirst().toString() +
                                    getStringFromArray(end));
                    ArrayList<BigInteger> finalAmounts = new ArrayList<>();
                    finalAmounts.add(finalResult);
                    availableAmounts.put(daysCount - 1, finalAmounts);
                    break;
                }
            } else {
                break;
            }
        }
        return availableAmounts;
    }

    static String getStringFromArray(int[] array) {
        StringBuilder builder = new StringBuilder(array.length);
        for (int ch : array) {
            builder.append(ch);
        }
        return builder.toString();
    }

    static ArrayList<BigInteger> getAvailableTomorrowAmounts(ArrayList<BigInteger> amounts) {
        ArrayList<BigInteger> nextAmounts = new ArrayList<>();
        for (BigInteger amount : amounts) {
            BigInteger nextTenAmount = amount.multiply(BigInteger.TEN);
            for (int j = 0; j <= 9; j++) {
                BigInteger nextAmount = nextTenAmount.add(BigInteger.valueOf(j));

                if (nextAmount.remainder(partnersCount).compareTo(BigInteger.ZERO) == 0) {
                    nextAmounts.add(nextAmount);
                }
            }
        }
        return nextAmounts;
    }
}
