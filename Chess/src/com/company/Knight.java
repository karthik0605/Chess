package com.company;

import java.util.ArrayList;

/**
 * @author Karthik Balaji & Kush Khanna
 * @version 1
 * Period - 1
 * Implements Knight Piece
 */

public class Knight extends Piece{

    private Location loc;

    /**
     * Constructs Knight
     * @param isWhite color of knight
     */
    public Knight(boolean isWhite){
        super(isWhite);
    }

    /**
     * Constructs Knight
     * @param isWhite color of knight
     * @param x row loc
     * @param y col loc
     */
    public Knight(boolean isWhite, int x, int y){
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
     * Gets location
     * @return location
     */
    public Location getLoc() {
        return loc;
    }

    /**
     * Sets location
     * @param loc location
     */
    public void setLoc(Location loc){
        this.loc = loc;
    }

    /**
     * Gets all valid moves for knight
     * @param board chess board
     * @return ArrayList of all valid moves for knight
     */
    public ArrayList<Location> getAllMoves(Board board){

        ArrayList<Location> moves = new ArrayList<Location>(8);
        ArrayList<Location> finalMoves = new ArrayList<Location>(8);
        Piece[][] chessBoard = board.getBoard();

        int x = this.loc.getX();
        int y = this.loc.getY();

        moves.add(new Location(x+2, y+1));
        moves.add(new Location(x+2, y-1));
        moves.add(new Location(x-2, y+1));
        moves.add(new Location(x-2, y-1));
        moves.add(new Location(x+1, y+2));
        moves.add(new Location(x-1, y+2));
        moves.add(new Location(x+1, y-2));
        moves.add(new Location(x-1, y-2));

        for(Location locs : moves){
            if(Location.isOnBoard(locs.getX(), locs.getY())) {
                if (chessBoard[locs.getX()][locs.getY()]==null || chessBoard[locs.getX()][locs.getY()].isWhite() != this.isWhite()){
                    finalMoves.add(locs);
                }
            }
        }

        return finalMoves;

    }

    @Override
    public String toString(){
        return "N";
    }

    @Override
    protected Object clone()  {
        return new Knight( isWhite(), loc.getX(), loc.getY() );
    }

}
