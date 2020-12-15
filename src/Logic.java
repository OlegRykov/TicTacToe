import java.util.Random;

public class Logic {

    static int SIZE;
    static int DOTS_TO_WIN;

    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    static final char DOT_EMPTY = '.';

    static char[][] map;

    static Random random = new Random();

    static boolean gameFinished;

    static int FIRST_X;
    static int FIRST_Y;
    static int LAST_X;
    static int LAST_Y;

    public static void go() {
        gameFinished = true;

        printMap();
        if (checkWin(DOT_X)) {
            System.out.println("Вы выиграли!!!");
            return;
        }
        if (isFull()) {
            System.out.println("Ничья.");
            return;
        }

        aiTurn();
        printMap();
        if (checkWin(DOT_O)) {
            System.out.println("Компьютер победил.");
            return;
        }
        if (isFull()) {
            System.out.println("Ничья.");
            return;
        }

        gameFinished = false;
    }

    static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    static void printMap() {
        System.out.print("  ");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.printf("%c ", map[i][j]);
            }
            System.out.println();
        }
    }

    static void humanTurn(int x, int y) {
        if (isCellValid(y, x)) {
            map[y][x] = DOT_X;
            go();
        }
    }

    static void aiTurn() {
        int x;
        int y;

        if (attack() == true) {

        } else if (defence() == true) {

        } else {
            do {
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);
            } while (!isCellValid(y, x));
            map[y][x] = DOT_O;
        }
    }

    static boolean isCellValid(int y, int x) {
        if (y < 0 || x < 0 || y >= SIZE || x >= SIZE) {
            return false;
        }
        return map[y][x] == DOT_EMPTY;
    }

    static boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean checkWin(char c) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (c == map[i][j] && checkMainDiagonal(i, j, c) == true) {
                    FIRST_Y = i;
                    FIRST_X = j;
                    return true;
                }
                if (c == map[i][j] && checkSideDiagonal(i, j, c) == true) {
                    FIRST_Y = i;
                    FIRST_X = j;
                    return true;
                }
                if (c == map[i][j] && checkHorizontal(i, j, c) == true) {
                    FIRST_Y = i;
                    FIRST_X = j;
                    return true;
                }
                if (c == map[i][j] && checkVertical(i, j, c) == true) {
                    FIRST_Y = i;
                    FIRST_X = j;
                    return true;
                }
            }
        }
        return false;
    }

    static boolean checkSideDiagonal(int y, int x, char c) {
        int countSideDiagonal = 0;
        int count = 0;

        while (count != DOTS_TO_WIN) {
            if (y < 0 || x >= SIZE) {
                return false;
            }
            if (map[y][x] == c && y < SIZE && x < SIZE && y >= 0 && x >= 0) {
                LAST_Y = y;
                LAST_X = x;

                y--;
                x++;
                countSideDiagonal++;
            }
            count++;
        }
        if (countSideDiagonal == DOTS_TO_WIN) {
            return true;
        }
        return false;
    }

    static boolean checkMainDiagonal(int y, int x, char c) {
        int countMainDiagonal = 0;
        int count = 0;

        while (count != DOTS_TO_WIN) {
            if (y >= SIZE || x >= SIZE) {
                return false;
            }
            if (map[y][x] == c && y < SIZE && x < SIZE && y >= 0 && x >= 0) {
                LAST_Y = y;
                LAST_X = x;

                y++;
                x++;
                countMainDiagonal++;
            }
            count++;
        }
        if (countMainDiagonal == DOTS_TO_WIN) {
            return true;
        }
        return false;
    }

    static boolean checkHorizontal(int y, int x, char c) {
        int countHorizontal = 0;
        int count = 0;

        while (count != DOTS_TO_WIN) {
            if (x >= SIZE) {
                return false;
            }
            if (map[y][x] == c && y < SIZE && x < SIZE && y >= 0 && x >= 0) {
                LAST_Y = y;
                LAST_X = x;

                x++;
                countHorizontal++;
            }
            count++;
        }
        if (countHorizontal == DOTS_TO_WIN) {
            return true;
        }
        return false;
    }

    static boolean checkVertical(int y, int x, char c) {
        int countVertical = 0;
        int count = 0;

        while (count != DOTS_TO_WIN) {
            if (y >= SIZE) {
                return false;
            }
            if (map[y][x] == c && y < SIZE && x < SIZE && y >= 0 && x >= 0) {
                LAST_Y = y;
                LAST_X = x;

                y++;
                countVertical++;
            }
            count++;
        }
        if (countVertical == DOTS_TO_WIN) {
            return true;
        }
        return false;
    }

    static boolean defence() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    map[i][j] = DOT_X;
                    if (checkWin(DOT_X) == true) {
                        map[i][j] = DOT_O;
                        return true;
                    } else {
                        map[i][j] = DOT_EMPTY;
                    }
                }
            }
        }
        return false;
    }

    static boolean attack() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    map[i][j] = DOT_O;
                    if (checkWin(DOT_O) == true) {
                        map[i][j] = DOT_O;
                        return true;
                    } else {
                        map[i][j] = DOT_EMPTY;
                    }
                }
            }
        }
        return false;
    }
}
