package wk12;
import java.util.ArrayList;

public class VankinsMile {

    public static void findBestPath(int board[][], int numRows, int numCols, int startRow, int startCol) {
        char directions[][] = new char[numRows][numCols];
        int values[][] = new int[numRows][numCols];
        
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                values[r][c] = Integer.MIN_VALUE;
            }
        }

        values[startRow][startCol] = board[startRow][startCol];
        directions[startRow][startCol] = 'S';

        for (int c = startCol + 1; c < numCols; c++) {
            values[startRow][c] = values[startRow][c - 1] + board[startRow][c];
            directions[startRow][c] = 'L';
        }

        for (int r = startRow + 1; r < numRows; r++) {
            values[r][startCol] = values[r - 1][startCol] + board[r][startCol];
            directions[r][startCol] = 'U';
        }

        for (int r = startRow + 1; r < numRows; r++) {
            for (int c = startCol + 1; c < numCols; c++) {
                int fromLeft = values[r][c - 1];
                int fromUp = values[r - 1][c];
                int fromDiagonal = values[r - 1][c - 1];
                
                int maxCell = Math.max(fromLeft, Math.max(fromUp, fromDiagonal));
                values[r][c] = maxCell + board[r][c];

                if (maxCell == fromDiagonal) {
                    directions[r][c] = 'D';
                } else if (maxCell == fromUp) {
                    directions[r][c] = 'U'; 
                } else {
                    directions[r][c] = 'L'; 
                }
            }
        }

        pathFinder(values, directions, numRows, numCols, startRow, startCol);
    }

    private static void pathFinder(int[][] values, char[][] directions, int numRows, int numCols, int startRow, int startCol) {
        printBoard(values, directions, numRows, numCols);
        int max = Integer.MIN_VALUE;
        int maxR = 0;
        int maxC = 0;
        ArrayList<Integer[]> path = new ArrayList<>();
        
        for (int i = startRow; i < numRows; i++)
            if (values[i][numCols - 1] > max) {
                max = values[i][numCols - 1];
                maxR = i;
                maxC = numCols - 1;
            }
        for (int j = startCol; j < numCols; j++)
            if (values[numRows - 1][j] > max) {
                max = values[numRows - 1][j];
                maxR = numRows - 1;
                maxC = j;
            }
        
        System.out.println("\nMaximum gain is: " + max);
        while (maxR >= startRow && maxC >= startCol) {
            Integer node[] = new Integer[]{maxR, maxC};
            path.add(node);
            if (directions[maxR][maxC] == 'D') {
                maxR--;
                maxC--;
            } else if (directions[maxR][maxC] == 'L') {
                maxC--;
            } else {
                maxR--;
            }
        }
        reverse(path);

        System.out.print("Path: ");
        if (path.size() == 0) return;
        Integer[] node;
        for (int i = 0; i < path.size() - 1; i++) {
            node = path.get(i);
            System.out.printf("[%d,%d] --> ", node[0], node[1]);
        }
        node = path.get(path.size() - 1);
        System.out.printf("[%d,%d]\n", node[0], node[1]);
    }

    private static void printBoard(int[][] values, char[][] directions, int numRows, int numCols) {
        System.out.println("\nSolution (Value/Direction) Board");
        if (numRows == 0 || numCols == 0) {
            System.out.println("[]");
            return;
        }
        for (int i = 0; i < numRows; i++) {
            String val = values[i][0] == Integer.MIN_VALUE ? "-inf" : String.valueOf(values[i][0]);
            StringBuilder out = new StringBuilder("[" + val + "/" + directions[i][0]);
            for (int j = 1; j < numCols; j++) {
                val = values[i][j] == Integer.MIN_VALUE ? "-inf" : String.valueOf(values[i][j]);
                out.append(", " + val + "/" + directions[i][j]);
            }
            out.append("]");
            System.out.println(out);
        }
    }

    private static void reverse(ArrayList<Integer[]> list) {
        for (int i = 0, j = list.size() - 1; i < j; i++, j--) {
            Integer[] temp = list.get(j);
            list.set(j, list.get(i));
            list.set(i, temp);
        }
    }
}
