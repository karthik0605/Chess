package com.company;


import java.util.ArrayList;

/**
 * @author Karthik Balaji & Kush Khanna
 * @version 1
 * Period - 1
 * Implements Pawn Piece
 */

public class Pawn extends Piece{

    private boolean firstMove = true;
    private Location loc;

    /**
     * Constructs Pawn
     * @param isWhite color of pawn
     */
    public Pawn(boolean isWhite){
        super(isWhite);
    }

    /**
     * Constructs Pawn
     * @param isWhite color of pawn
     * @param x row loc
     * @param y col loc
     */
    public Pawn(boolean isWhite, int x, int y){
        super(isWhite);
        loc = new Location(x, y);
    }

    /**
     * Constructs Pawn
     * @param isWhite color of pawn
     * @param x row loc
     * @param y col loc
     * @param fM whether it is the pawn's first move
     */
    public Pawn(boolean isWhite, int x, int y, boolean fM){
        super(isWhite);
        loc = new Location(x, y);
        firstMove = fM;
    }

    /**
     * Gets row coordinate
     * @return row coordinate
     */
    public int getX(){
        return loc.getX();
    }

    /**
     * Gets col coordinate
     * @return col coordinate
     */
    public int getY(){
        return loc.getY();
    }

    /**
     * Gets location
     * @return location
     */
    public Location getLoc() {
        return loc;
    }

    /**
     * Sets boolean for first move
     * @param firstMove boolean of whether it is the first move or not
     */
    public void setFirstMove(boolean firstMove){
        this.firstMove = firstMove;
    }

    /**
     * Sets location
     * @param loc location
     */
    public void setLoc(Location loc){
        this.loc = loc;
    }

    /**
     * Gets all valid moves for pawn
     * @param board chess board
     * @return ArrayList of all valid moves for pawn
     */
    public ArrayList<Location> getAllMoves(Board board) {

        ArrayList<Location> moves = new ArrayList<Location>(4);
        Piece[][] chessBoard = board.getBoard();

        int x = this.loc.getX();
        int y = this.loc.getY();

        //Basic Pawn Moves
        if(isWhite()){
            //System.out.println(Location.isOnBoard(x, y+1) && chessBoard[x][y+1] == null);
            if( Location.isOnBoard(x+1, y) && chessBoard[x+1][y] == null ){
                moves.add(new Location(x+1, y));
                if( Location.isOnBoard(x+2, y) && chessBoard[x+2][y] == null && firstMove ){
                    moves.add(new Location(x+2, y));
                }
            }
            //captures
            if( Location.isOnBoard(x+1, y+1) && chessBoard[x+1][y+1] != null && chessBoard[x+1][y+1].isBlack() ){
                moves.add(new Location(x+1, y+1));
            }
            if( Location.isOnBoard(x+1, y-1) && chessBoard[x+1][y-1] != null && chessBoard[x+1][y-1].isBlack() ){
                moves.add(new Location(x+1, y-1));
            }
        }
        else{
            if( Location.isOnBoard(x-1, y) && chessBoard[x-1][y] == null ){
                moves.add(new Location(x-1, y));
                if( Location.isOnBoard(x-2, y) && chessBoard[x-2][y] == null && firstMove ){
                    moves.add(new Location(x-2, y));
                }
            }
            //captures
            if( Location.isOnBoard(x-1, y+1) && chessBoard[x-1][y+1] != null && chessBoard[x-1][y+1].isWhite() ){
                moves.add(new Location(x-1, y+1));
            }
            if( Location.isOnBoard(x-1, y-1) && chessBoard[x-1][y-1] != null && chessBoard[x-1][y-1].isWhite() ){
                moves.add(new Location(x-1, y-1));
            }
        }

        return moves;

    }

    /**
     * Gets all valid moves where the pawn is attacking a piece, as these are diagonal
     * @param board chess board
     * @return ArrayList of all valid pawn attacking moves
     */
    public ArrayList<Location> getAllMovesPawnAttack(Board board) {

        ArrayList<Location> moves = new ArrayList<Location>(4);
        Piece[][] chessBoard = board.getBoard();

        int x = this.loc.getX();
        int y = this.loc.getY();

        //Basic Pawn Moves
        if(isWhite()){
            //captures
            if( Location.isOnBoard(x+1, y+1) ){
                moves.add(new Location(x+1, y+1));
            }
            if( Location.isOnBoard(x+1, y-1) ){
                moves.add(new Location(x+1, y-1));
            }
        }
        else{
            //captures
            if( Location.isOnBoard(x-1, y+1) ){
                moves.add(new Location(x-1, y+1));
            }
            if( Location.isOnBoard(x-1, y-1) ){
                moves.add(new Location(x-1, y-1));
            }
        }
        return moves;
    }

    @Override
    protected Object clone()  {
        return new Pawn( isWhite(), loc.getX(), loc.getY() , firstMove );
    }

    public String toString(){
        return "P";
    }

}