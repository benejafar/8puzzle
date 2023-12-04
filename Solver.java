import edu.princeton.cs.algs4.MinPQ;

public class Solver {
    private MinPQ<Node> minPq;
    public Solver(Board initial){
            minPq = new MinPQ<>();
            
    }

    private class Node{

        private Board board;
        private int moves;
        private Node prevNode;
        private int manhattan;


        public Node(Board board, Node prevNode){
            this.board = board;
            this.moves = prevNode == null ? 0 : prevNode.moves + 1;
            manhattan = board.manhattan();
        }
        // Board board = null;
        // int moves = 0;
        // Node prevNode = null;
        // int manhattan = board.manhattan();
    }
}
