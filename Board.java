import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Iterator;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdRandom;

public class Board {
    private int[][] tiles;
    private int size;

    public Board(int[][] tiles) {
        this.tiles = tiles;
        this.size = tiles.length;
    }

    @Override
    public String toString() {
        StringBuilder boardString = new StringBuilder();
        boardString.append(size+"\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boardString.append(tiles[i][j]).append(" ");
            }
            boardString.append("%n");
        }

        return String.format(boardString.toString());
    }

    public int dimension() {
        return size;
    }

    // to find the hamming priority number
    public int hamming() {
        int actual = 1;
        int hamming = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tiles[i][j] == actual) {
                    hamming += 1;
                }
                actual += 1;
            }
        }
        return hamming;
    }

    // to find the manhattan distance
    public int manhattan() {
        int manhattan = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int actual = tiles[i][j];
                if (actual == 0)
                    continue;
                int x = (actual - 1) / size;
                int y = actual - x * 3 - 1;
                manhattan += Math.abs(x - i);
                manhattan += Math.abs(y - j);
            }
        }
        return manhattan;
    }

    // to check the the goal board is achieved
    public boolean isGoal() {
        int actual = 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tiles[i][j] != 0 && tiles[i][j] != actual) {
                    return false;
                }
                actual++;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Board other = (Board) obj;
        if (!Arrays.deepEquals(tiles, other.tiles)) // java 2022
            return false;
        if (size != other.size)
            return false;
        return true;
    }

    public Iterable<Board> neighbors() {
        Queue<Board> neighboringBoard = new Queue<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (this.tiles[i][j] == 0) {
                    if (i > 0 && j > 0) {
                        Board dummyBoard = new Board(this.twoDCopy());
                        exch(dummyBoard, i, j, i - 1, j);
                        Board secDummyBoard = new Board(this.twoDCopy());
                        exch(secDummyBoard, i, j, i, j - 1);
                        neighboringBoard.enqueue(dummyBoard);
                        neighboringBoard.enqueue(secDummyBoard);
                        // for corner case of j == size - 1
                        if (i < size - 1 && j == size - 1) {
                            Board rightSide = new Board(this.twoDCopy());
                            exch(rightSide, i, j, i + 1, j);
                            neighboringBoard.enqueue(rightSide);
                        }
                        if (i == size - 1 && j < size - 1) {
                            Board bottBoard = new Board(this.twoDCopy());
                            exch(bottBoard, i, j, i, j + 1);
                            neighboringBoard.enqueue(bottBoard);
                        }
                    }
                    if (i < size - 1 && j < size - 1) {
                        Board dummyBoard = new Board(this.twoDCopy());
                        exch(dummyBoard, i, j, i + 1, j);
                        Board secDummyBoard = new Board(this.twoDCopy());
                        exch(secDummyBoard, i, j, i, j + 1);
                        neighboringBoard.enqueue(dummyBoard);
                        neighboringBoard.enqueue(secDummyBoard);
                        if (i == 0 && j > 0) {
                            Board topZero = new Board(this.twoDCopy());
                            exch(topZero, i, j, i, j - 1);
                            neighboringBoard.enqueue(topZero);
                        }
                        if (j == 0 && i > 0) {
                            Board leftZero = new Board(this.twoDCopy());
                            exch(leftZero, i, j, i - 1, j);
                            neighboringBoard.enqueue(leftZero);
                        }
                    }
                    if (i == 0 && j == size - 1) {
                        Board dummyBoard = new Board(this.twoDCopy());
                        exch(dummyBoard, i, j, 0, size - 2);
                        Board secDummyBoard = new Board(this.twoDCopy());
                        exch(secDummyBoard, i, j, 1, size - 1);
                        neighboringBoard.enqueue(dummyBoard);
                        neighboringBoard.enqueue(secDummyBoard);
                    }
                    if (i == size - 1 && j == 0) {
                        Board dummyBoard = new Board(this.twoDCopy());
                        exch(dummyBoard, i, j, size - 2, 0);
                        Board secDummyBoard = new Board(this.twoDCopy());
                        exch(secDummyBoard, i, j, size - 1, 1);
                        neighboringBoard.enqueue(dummyBoard);
                        neighboringBoard.enqueue(secDummyBoard);
                    }
                }
            }
        }
        return neighboringBoard;
    }


    //it will randomly exchange two tiles
    public Board twin() {
        Board twinBoard = new Board(this.twoDCopy());
        int i1 = 0, j1 = 0;
        do {
            i1 = StdRandom.uniformInt(size);
            j1 = StdRandom.uniformInt(size);
        } while (twinBoard.tiles[i1][j1] == 0);

        int i2 = 0, j2 = 0;
        do {
            i2 = StdRandom.uniformInt(size);
            j2 = StdRandom.uniformInt(size);
        } while (twinBoard.tiles[i2][j2] == 0 || twinBoard.tiles[i2][j2] == twinBoard.tiles[i1][j1]);
        exch(twinBoard, i1, j1, i2, j2);
        return twinBoard;
    }

    private int[][] twoDCopy() {
        int[][] copy = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                copy[i][j] = tiles[i][j];
            }
        }
        return copy;
    }

    // for exchanging one digit with onether (0 with other neighbors)
    private void exch(Board board, int i, int j, int i2, int j2) {
        int temp = board.tiles[i][j];
        board.tiles[i][j] = board.tiles[i2][j2];
        board.tiles[i2][j2] = temp;
    }

    public static void main(String[] args) {
        int[][] tiles = new int[3][3];
        tiles[0][0] = 8;
        tiles[0][1] = 2;
        tiles[0][2] = 6;
        tiles[1][0] = 0;
        tiles[1][1] = 3;
        tiles[1][2] = 1;
        tiles[2][0] = 4;
        tiles[2][1] = 7;
        tiles[2][2] = 5;

        int[][] goal = new int[3][3];
        goal[0][0] = 1;
        goal[0][1] = 2;
        goal[0][2] = 3;
        goal[1][0] = 4;
        goal[1][1] = 5;
        goal[1][2] = 6;
        goal[2][0] = 7;
        goal[2][1] = 8;
        goal[2][2] = 0;

        Board board = new Board(tiles);
        // System.out.println(board.toString());
        // System.out.println(board.manhattan());
        // Board goalBoard = new Board(goal);
        // System.out.println(board.isGoal());
        // Board secodnGoalBoard = new Board(goal);
        // System.out.println(secodnGoalBoard.equals(secodnGoalBoard));
        // System.out.println(board);
        // System.out.println("the initial board \n" + board);
        // Iterable<Board> neighbors = board.neighbors();
        // for (Board b : neighbors) {
        //     System.out.println(b.toString());
        // }

        System.out.println(board);
        Board twinBoard = board.twin();
        System.out.println(twinBoard);

    }

}
