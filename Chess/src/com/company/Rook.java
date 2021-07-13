package com.company;

import java.util.ArrayList;

/**
 * @author Karthik Balaji & Kush Khanna
 * @version 1
 * Period - 1
 * Implements Rook Piece
 */

public class Rook extends Piece{

    private Location loc;
    private boolean hasMoved = false;

    /**
     * Constructs Rook
     * @param isWhite color of rook
     */
    public Rook(boolean isWhite){
        super(isWhite);
    }

    /**
     * Constructs Rook
     * @param isWhite color of rook
     * @param x row loc
     * @param y col loc
     */
    public Rook(boolean isWhite, int x, int y){
        super(isWhite);
        loc = new Location(x, y);
    }

    /**
     * Constructs Rook
     * @param isWhite color of rook
     * @param x row loc
     * @param y col loc
     * @param hs whether rook has moved yet
     */
    public Rook(boolean isWhite, int x, int y, boolean hs){
        super(isWhite);
        loc = new Location(x, y);
        hasMoved = hs;
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
     * Gets value for whether rook has moved
     * @return boolean of whether rook has moved
     */
    public boolean hasMoved(){
        return hasMoved;
    }

    /**
     * Sets value for whether rook has moved
     * @param hMoved boolean of whether rook has moved
     */
    public void setHasMoved(boolean hMoved){
        hasMoved = hMoved;
    }

    /**
     * Sets location
     * @param loc location
     */
    public void setLoc(Location loc){
        this.loc = loc;
    }

    /**
     * Gets all valid moves for rook
     * @param board chess board
     * @return ArrayList of all valid moves for rook
     */
    public ArrayList<Location> getAllMoves(Board board){
        ArrayList<Location> moves = new ArrayList<Location>(14);
        Piece[][] chessBoard = board.getBoard();

        int x = this.loc.getX();
        int y = this.loc.getY();

        //Rook check up vertically
        for(int i = 1; i < 8; i++){
            if(!((x+i)<0 || (x+i)>7) ) {
                if (chessBoard[x + i][y] == null){
                    moves.add(new Location(x + i, y));
                }
                else if(chessBoard[x + i][y].isWhite() != this.isWhite()){
                    moves.add(new Location(x + i, y));
                    break;
                }
                else if(chessBoard[x + i][y].isWhite() == this.isWhite()){
                    break;
                }
            }
        }

        //Rook check down vertically
        for(int i = 1; i < 8; i++){
            if(!((x-i)<0 || (x-i)>7) ) {
                if (chessBoard[x - i][y] == null){
                    moves.add(new Location(x - i, y));
                }
                else if(chessBoard[x - i][y].isWhite() != this.isWhite()){
                    moves.add(new Location(x - i, y));
                    break;
                }
                else if(chessBoard[x - i][y].isWhite() == this.isWhite()){
                    break;
                }
            }
        }

        //Rook check right horizontally
        for(int i = 1; i < 8; i++){
            if(!((y - i)<0 || (y - i)>7) ) {
                if (chessBoard[x][y - i] == null){
                    moves.add(new Location(x, y - i));
                }
                else if(chessBoard[x][y - i].isWhite() != this.isWhite()){
                    moves.add(new Location(x, y - i));
                    break;
                }
                else if(chessBoard[x][y-i].isWhite() == this.isWhite()){
                    break;
                }
            }
        }

        //Rook check right horizontally
        for(int i = 1; i < 8; i++){
            if(!((y + i)<0 || (y + i)>7) ) {
                if (chessBoard[x][y + i] == null){
                    moves.add(new Location(x, y + i));
                }
                else if(chessBoard[x][y + i].isWhite() != this.isWhite()){
                    moves.add(new Location(x, y + i));
                    break;
                }
                else if(chessBoard[x][y+i].isWhite() == this.isWhite()){
                    break;
                }
            }
        }

        return moves;

    }

    public String toString(){
        return "R";
    }

    @Override
    protected Object clone()  {
        return new Rook( isWhite(), loc.getX(), loc.getY(), hasMoved );
    }

}
