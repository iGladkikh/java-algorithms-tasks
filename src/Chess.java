import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Chess {
    static final int X_LENGTH = 8;
    static final int Y_LENGTH = 8;
    static Set<Integer> crossArea;
    static ArrayList<ChessPiece> board;
    static Scanner scanner;

    public static void main(String[] args) {
        board = new ArrayList<>();
        crossArea = new HashSet<>();
        scanner = new Scanner(System.in);

        for (int y = Y_LENGTH - 1; y >= 0; y--) {
            String in = scanner.nextLine();
            if (!in.isEmpty()) {
                for (int x = 0; x < in.length() && x < X_LENGTH; x++) {
                    if (in.charAt(x) == 'R') {
                        board.add(new Rook(x, y));
                    } else if (in.charAt(x) == 'B') {
                        board.add(new Bishop(x, y));
                    }
                }
            }
        }

        for (ChessPiece piece : board) {
            crossArea.add(piece.positionY * Y_LENGTH + piece.positionX);
            piece.getAvailableStepCount();
        }

        System.out.print(X_LENGTH * Y_LENGTH - crossArea.size());
    }


    static class Bishop extends ChessPiece {
        Bishop(int x, int y) {
            super(x, y);
        }

        int getAvailableStepCount() {
            return getAvailableStepsLeftUp() +
                    getAvailableStepsRightUp() +
                    getAvailableStepsRightDown() +
                    getAvailableStepsLeftDown();
        }

        int getAvailableStepsLeftUp() {
            int result = 0;
            int x = positionX;
            int y = positionY;
            while (x > 0 && y < Y_LENGTH - 1) {
                x--;
                y++;
                for (ChessPiece piece : board) {
                    if (id != piece.id
                            && x == piece.positionX
                            && y == piece.positionY
                    ) {
                        return result;
                    }
                }
                result++;
                crossArea.add(y * Y_LENGTH + x);
            }
//            System.out.println("LeftUp: " + result);
            return result;
        }

        int getAvailableStepsLeftDown() {
            int result = 0;
            int x = positionX;
            int y = positionY;
            while (x > 0 && y > 0) {
                x--;
                y--;
                for (ChessPiece piece : board) {
                    if (id != piece.id
                            && x == piece.positionX
                            && y == piece.positionY
                    ) {
                        return result;
                    }
                }
                crossArea.add(y * Y_LENGTH + x);
                result++;
            }
//            System.out.println("LeftDown: " + result);
            return result;
        }

        int getAvailableStepsRightUp() {
            int result = 0;
            int x = positionX;
            int y = positionY;
            while (x < X_LENGTH - 1 && y < Y_LENGTH - 1) {
                x++;
                y++;
                for (ChessPiece piece : board) {
                    if (id != piece.id
                            && x == piece.positionX
                            && y == piece.positionY
                    ) {
                        return result;
                    }
                }
                crossArea.add(y * Y_LENGTH + x);
                result++;
            }
//            System.out.println("RightUp: " + result);
            return result;
        }

        int getAvailableStepsRightDown() {
            int result = 0;
            int x = positionX;
            int y = positionY;
            while (x < X_LENGTH - 1 && y > 0) {
                x++;
                y--;
                for (ChessPiece piece : board) {
                    if (id != piece.id
                            && x == piece.positionX
                            && y == piece.positionY
                    ) {
                        return result;
                    }
                }
                crossArea.add(y * Y_LENGTH + x);
                result++;
            }
//            System.out.println("RightDown: " + result);
            return result;
        }
    }

    static class Rook extends ChessPiece {
        Rook(int x, int y) {
            super(x, y);
        }

        int getAvailableStepCount() {
            return getAvailableStepsUp() +
                    getAvailableStepsDown() +
                    getAvailableStepsLeft() +
                    getAvailableStepsRight();
        }

        int getAvailableStepsUp() {
            int result = 0;
            int i = 1;
            for (int y = positionY; y < Y_LENGTH - 1; y++) {
                for (ChessPiece piece : board) {
                    if (id != piece.id
                            && positionX == piece.positionX
                            && positionY + i == piece.positionY
                    ) {
                        return result;
                    }
                }
                crossArea.add((positionY + i) * Y_LENGTH + positionX);
                i++;
                result++;
            }
//            System.out.println("Up: " + result);
            return result;
        }

        int getAvailableStepsDown() {
            int result = 0;
            int i = 1;
            for (int y = positionY; y > 0; y--) {
                for (ChessPiece piece : board) {
                    if (id != piece.id
                            && positionX == piece.positionX
                            && positionY - i == piece.positionY
                    ) {
                        return result;
                    }
                }
                crossArea.add((positionY - i) * Y_LENGTH + positionX);
                i++;
                result++;
            }
//            System.out.println("Down: " + result);
            return result;
        }

        int getAvailableStepsLeft() {
            int result = 0;
            int i = 1;
            for (int x = positionX; x > 0; x--) {
                for (ChessPiece piece : board) {
                    if (id != piece.id
                            && positionX - i == piece.positionX
                            && positionY == piece.positionY
                    ) {
                        return result;
                    }
                }
                crossArea.add(positionY * Y_LENGTH + positionX - i);
                i++;
                result++;
            }
//            System.out.println("Left: " + result);
            return result;
        }

        int getAvailableStepsRight() {
            int result = 0;
            int i = 1;
            for (int x = positionX; x < X_LENGTH - 1; x++) {
                for (ChessPiece piece : board) {
                    if (id != piece.id
                            && positionX + i == piece.positionX
                            && positionY == piece.positionY
                    ) {
                        return result;
                    }
                }
                crossArea.add(positionY * Y_LENGTH + positionX + i);
                i++;
                result++;
            }
//            System.out.println("Right: " + result);
            return result;
        }
    }


    abstract static class ChessPiece {
        private static int lastId = 0;
        final int id;
        int positionX;
        int positionY;

        ChessPiece(int x, int y) {
            id = ++lastId;
            positionX = x;
            positionY = y;
        }

        abstract int getAvailableStepCount();

        @Override
        public String toString() {
            return getClass().getSimpleName() + "{" +
                    "id=" + id +
                    ", positionX=" + positionX +
                    ", positionY=" + positionY +
                    '}';
        }
    }
}
