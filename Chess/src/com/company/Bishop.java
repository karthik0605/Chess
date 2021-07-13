package com.company;

import java.util.ArrayList;

/**
 * @author Karthik Balaji & Kush Khanna
 * @version 1
 * Period - 1
 * Implements Bishop Piece
 */

public class Bishop extends Piece{

    private Location loc;

    /**
     * Constructs Bishop
     * @param isWhite color of bishop
     */
    public Bishop(boolean isWhite){
        super(isWhite);
    }

    /**
     * Constructs Bishop
     * @param isWhite color of bishop
     * @param x row loc
     * @param y col loc
     */
    public Bishop(boolean isWhite, int x, int y){
        super(isWhite);
        loc = new Location(x, y);
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
     * Gets Location
     * @return location
     */
    public Location getLoc() {
        return loc;
    }

    /**
     * Sets Location
     * @param x row coordinate
     * @param y col coordinate
     */
    public void setLoc(int x, int y){
        loc.setX(x);
        loc.setY(y);
    }

    /**
     * Sets Location
     * @param loc location
     */
    public void setLoc(Location loc){
        this.loc = loc;
    }

    /**
     * Returns all valid moves for Bishop
     * @param board chess board
     * @return ArrayList of all moves
     */
    public ArrayList<Location> getAllMoves(Board board){
        ArrayList<Location> moves = new ArrayList<Location>(14);
        Piece[][] chessBoard = board.getBoard();

        int x = this.loc.getX();
        int y = this.loc.getY();

        /*//pinned piece test start
        chessBoard[x][y] = null;
        if(isWhite()){
            if(board.getWhiteKing().isInCheck(board)){
                chessBoard[x][y] = this;
                return moves;
            }
        }
        else{
            if(board.getBlackKing().isInCheck(board)){
                chessBoard[x][y] = this;
                return moves;
            }
        }
        chessBoard[x][y] = this;
        //pinned piece test end*/

        //Bishop check up right diagonally
        for(int i = 1; i < 8; i++){
            if(!((x+i)<0 || (x+i)>7 || (y+i)<0 || (y+i)>7) ) {
                if (chessBoard[x + i][y + i] == null){
                    moves.add(new Location(x + i, y + i));
                }
                else if(chessBoard[x + i][y + i].isWhite() != this.isWhite()){
                    moves.add(new Location(x + i, y + i));
                    break;
                }
                else if(chessBoard[x + i][y + i].isWhite() == this.isWhite()){
                    break;
                }
            }
        }

        //Bishop check up left diagonally
        for(int i = 1; i < 8; i++){
            if(!((x-i)<0 || (x-i)>7 || (y+i)<0 || (y+i)>7) ) {
                if (chessBoard[x - i][y + i] == null){
                    moves.add(new Location(x - i, y + i));
                }
                else if(chessBoard[x - i][y + i].isWhite() != this.isWhite()){
                    moves.add(new Location(x - i, y + i));
                    break;
                }
                else if(chessBoard[x - i][y + i].isWhite() == this.isWhite()){
                    break;
                }
            }
        }

        //Bishop check down left diagonally
        for(int i = 1; i < 8; i++){
            if(!((x-i)<0 || (x-i)>7 || (y-i)<0 || (y-i)>7) ) {
                if (chessBoard[x - i][y - i] == null){
                    moves.add(new Location(x - i, y - i));
                }
                else if(chessBoard[x - i][y - i].isWhite() != this.isWhite()){
                    moves.add(new Location(x - i, y - i));
                    break;
                }
                else if(chessBoard[x - i][y - i].isWhite() == this.isWhite()){
                    break;
                }
            }
        }

        //Bishop check down right diagonally
        for(int i = 1; i < 8; i++){
            if(!((x+i)<0 || (x+i)>7 || (y-i)<0 || (y-i)>7) ) {
                if (chessBoard[x + i][y - i] == null){
                    moves.add(new Location(x + i, y - i));
                }
                else if(chessBoard[x + i][y - i].isWhite() != this.isWhite()){
                    moves.add(new Location(x + i, y - i));
                    break;
                }
                else if(chessBoard[x + i][y - i].isWhite() == this.isWhite()){
                    break;
                }
            }
        }

        return moves;
    }

    public String toString(){
        return "B";
    }

    @Override
    protected Object clone()  {
        return new Bishop( isWhite(), loc.getX(), loc.getY() );
    }

}
