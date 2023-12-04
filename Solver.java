import java.util.Comparator;

import edu.princeton.cs.algs4.MinPQ;

public class Solver {
    private MinPQ<Node> minPq;
    public Solver(Board initial){
            minPq = new MinPQ<>(new NodeComparator());
            minPq.insert(new Node(initial, null));

    }

    private class Node{

        private Board board;
        private int moves;
        private Node prevNode;
        private int manhattan;


        public Node(Board board, Node prevNode){
            this.board = board;
            this.moves = (prevNode == null ? 0 : prevNode.moves + 1);
            this.prevNode = prevNode;
            manhattan = board.manhattan();
        }

        public Integer getPriority(){
            return moves + manhattan;
        }

        public Node getPrevNode(){
            return this.prevNode;
        }
        // Board board = null;
        // int moves = 0;
        // Node prevNode = null;
        // int manhattan = board.manhattan();
    }

    private static class NodeComparator implements Comparator<Node>{

        @Override
        public int compare(Node o1, Node o2) {
            return o1.getPriority().compareTo(o2.getPriority());
        }
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
        Board board = new Board(tiles);
        Solver solver = new Solver(board);

        System.out.println("no error but logic need to check");
    }
}
