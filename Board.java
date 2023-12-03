import java.util.Arrays;

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

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boardString.append(tiles[i][j]).append(" ");
                System.out.println(tiles[i][j]);
                System.out.println(tiles[i][j]);
            }
            boardString.append("%n");
        }

        return String.format(boardString.toString());
    }

    public int dimension(){
        return size;
    }

    //to find the hamming priority number
    public int hamming(){
        int actual = 1;
        int hamming = 0;
        for(int i = 0 ; i < size ; i ++){
            for(int j = 0 ; j < size ; j++){
                if(tiles[i][j] == actual){
                    hamming += 1;
                }
                actual += 1;
            }
        }
        return hamming;
    }

    //to find the manhattan distance
    public int manhattan(){
        int manhattan = 0;

        for(int i =0 ; i < size ; i++){
            for(int j = 0 ; j < size ; j++){
                int actual = tiles[i][j];
                if(actual == 0)continue;
                int x = (actual - 1)/size;
                int y = actual - x*3 -1;
                manhattan += Math.abs(x-i);
                manhattan += Math.abs(y-j);
            }
        }
        return manhattan;
    }

    // to check the the goal board is achieved
    public boolean isGoal(){
        int actual = 1;
        for(int i = 0 ; i < size ; i++){
            for(int j = 0 ; j < size ; j++){
                if(tiles[i][j] != 0 && tiles[i][j] != actual) {
                    return false;}
                actual ++;
            }
        }return true;
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

    public Iterable<Board> neighbors(){
        
    }

    // private void exch(){

    // }

    public static void main(String[] args) {
        int[][] tiles = new int[3][3];
        tiles[0][0] = 8;
        tiles[0][1] = 1;
        tiles[0][2] = 3;
        tiles[1][0] = 4;
        tiles[1][1] = 0;
        tiles[1][2] = 2;
        tiles[2][0] = 7;
        tiles[2][1] = 6;
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
        System.out.println(board.toString());
        System.out.println(board.manhattan());
        Board goalBoard = new Board(goal);
        System.out.println(board.isGoal());
        Board secodnGoalBoard = new Board(goal);
        System.out.println(secodnGoalBoard.equals(secodnGoalBoard));
    }

   

}
