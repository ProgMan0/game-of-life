// Maybe it's cringe but anyway

public class Main {
    public static void main(String[] args) {
        char[][] board = new char[22][50];
        initBoard(board);

        while (true) {
            clearConsole();
            printBoard(board);
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            updateBoard(board);
        }
    }

    static void updateBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                boolean alive = checkIsAliveSquare(i, j, board);
                if (alive) {
                    board[i][j] = '*';
                } else {
                    board[i][j] = ' ';
                }
            }
        }
    }

    static boolean checkIsAliveSquare(int y, int x, char[][] board) {
        int liveCount = 0;

        int[] dx = {1, 1, 0, -1, -1, -1, 0, 1};
        int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

        for (int i = 0; i < 8; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny >= 0 && ny < board.length && nx >= 0 && nx < board[0].length) {
                if (board[ny][nx] == '*') {
                    liveCount += 1;
                }
            }
        }

        if (board[y][x] == '*' && (liveCount == 2 || liveCount == 3)) {
            return true;
        }

        if (board[y][x] == ' ' && liveCount == 3) {
            return true;
        }

        return false;
    }

    static void initBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (Math.random() < 0.1) {
                    board[i][j] = '*';
                } else {
                    board[i][j] = ' ';
                }
            }
        }
    }

    static void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
