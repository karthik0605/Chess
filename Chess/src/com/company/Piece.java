package com.company;

import java.util.ArrayList;

/**
 * @author Karthik Balaji & Kush Khanna
 * @version 1
 * Period - 1
 * Implements Piece abstract class
 */

public abstract class Piece{

    private boolean isWhite;

    /**
     * Constructs a Piece
     * @param isWhite color of piece
     */
    public Piece(boolean isWhite){
        this.setWhite(isWhite);
    }

    /**
     * Sets piece to color
     * @param isWhite color of piece
     */
    public void setWhite(boolean isWhite){
        this.isWhite = isWhite;
    }

    /**
     * Whether a piece is white
     * @return true if piece is white, false otherwise
     */
    public boolean isWhite(){
        return(this.isWhite);
    }

    /**
     * Whether a piece is black
     * @return true if piece is black, false otherwise
     */
    public boolean isBlack(){
        return(!this.isWhite);
    }

    /**
     * Abstract method used for getting row coordinate
     * @return row coordinate
     */
    public abstract int getX();

    /**
     * Abstract method used for getting col coordinate
     * @return col coordinate
     */
    public abstract int getY();

    /**
     * Abstract method used for setting location
     * @param loc location
     */
    public abstract void setLoc(Location loc);

    /**
     * Abstract method for getting a list of all valid moves for a piece
     * @param board chess board
     * @return ArrayList of all valid moves for a piece
     */
    public abstract ArrayList<Location> getAllMoves(Board board);

    protected abstract Object clone();

    @Override
    public abstract String toString();


}