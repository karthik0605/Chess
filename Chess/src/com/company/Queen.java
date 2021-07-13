package com.company;

import java.util.ArrayList;

/**
 * @author Karthik Balaji & Kush Khanna
 * @version 1
 * Period - 1
 * Implements Queen Piece
 */

public class Queen extends Piece{

    private Location loc;

    /**
     * Constructs Queen
     * @param isWhite color of queen
     */
    public Queen(boolean isWhite){
        super(isWhite);
    }

    /**
     * Constructs Queen
     * @param isWhite color of queen
     * @param x row loc
     * @param y col loc
     */
    public Queen(boolean isWhite, int x, int y){
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
     * Gets all valid moves for queen
     * @param board chess board
     * @return ArrayList of all valid moves for queen
     */
    public ArrayList<Location> getAllMoves(Board board){
        ArrayList<Location> moves = new ArrayList<Location>(14);
        Piece[][] chessBoard = board.getBoard();

        int x = this.loc.getX();
        int y = this.loc.getY();

        //Queen check up right diagonally
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

        //Queen check up left diagonally
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

        //Queen check down left diagonally
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

        //Queen check down right diagonally
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

        //Queen check up vertically
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

        //Queen check down vertically
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

        //Queen check right horizontally
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

        //Queen check right horizontally
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
        return "Q";
    }

    @Override
    protected Object clone()  {
        return new Queen( isWhite(), loc.getX(), loc.getY() );
    }

}
