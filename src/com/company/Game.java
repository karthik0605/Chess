package com.company;

/**
 * @author Karthik Balaji & Kush Khanna
 * @version 1
 * Period - 1
 * Implements Chess Game
 */

public class Game {

    private boolean isWhiteTurn = true;

    public Game(){

    }

    /**
     * Whether it is white's turn
     * @return boolean of whether it is white's turn
     */
    public boolean isWhiteTurn(){
        return isWhiteTurn;
    }

    /**
     * Whether it is black's turn
     * @return boolean of whether it is black's turn
     */
    public boolean isBlackTurn(){
        return !isWhiteTurn;
    }

    /**
     * Sets turn to who's turn it is
     * @param isWhiteTurn boolean used for setting the turn
     */
    public void setWhiteTurn(boolean isWhiteTurn){
        this.isWhiteTurn = isWhiteTurn;
    }

    /**
     * Makes it the opposing color's turn after a move has been made
     */
    public void moveMade(){
        isWhiteTurn = !isWhiteTurn;
    }

}
