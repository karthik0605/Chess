package com.company;

/**
 * @author Karthik Balaji & Kush Khanna
 * @version 1
 * Period - 1
 * Implements Location
 */

public class Location {

    private int x;
    private int y;

    /**
     * Constructs a Location
     * @param x row coordinate
     * @param y col coordinate
     */
    public Location(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Gets row coordinate
     * @return row coordinate
     */
    public int getX(){ return x; }

    /**
     * Gets col coordinate
     * @return col coordinate
     */
    public int getY(){ return y; }

    /**
     * Sets row coordinate
     * @param x row coordinate
     */
    public void setX(int x){ this.x = x; }

    /**
     * Sets col coordinate
     * @param y col coordinate
     */
    public void setY(int y){ this.y = y; }

    /**
     * Determines whether something is on the board
     * @param x row coordinate
     * @param y col coordinate
     * @return true if on board, false otherwise
     */
    public static boolean isOnBoard(int x, int y){
        return(x>=0 && x<=7 && y>=0 && y<=7);
    }

    /**
     * Checks if two locations are equal
     * @param other other location
     * @return true if locations are equal, false otherwise
     */
    public boolean equals(Location other){
        return(x == other.x && y == other.y);
    }

    /**
     * Converts for notation
     * @param board chess board
     * @param oLoc old location
     * @param nLoc new location
     * @return string for the piece notation
     */
    public static String toNotation(Board board, Location oLoc, Location nLoc) {
        String notation = "";

        if(board.getBoard()[oLoc.getX()][oLoc.getY()] != null && board.getBoard()[nLoc.getX()][nLoc.getY()] != null  && !board.getBoard()[oLoc.getX()][oLoc.getY()].toString().equals("P")){
            notation += board.getBoard()[oLoc.getX()][oLoc.getY()].toString() + "x";
        }
        else if( board.getBoard()[nLoc.getX()][nLoc.getY()] != null && board.getBoard()[oLoc.getX()][oLoc.getY()].toString().equals("P") ){
            notation += yToLetter(oLoc.getY()) + "x";
        }
        else{
            notation += board.getBoard()[oLoc.getX()][oLoc.getY()].toString();
        }

        notation += yToLetter(nLoc.getY());
        notation += (nLoc.getX() + 1);

        if(notation.charAt(0) == 'P'){
            return notation.substring(1);
        }
        return notation;
    }

    /**
     * Converts coordinate to letter for notation
     * @param num col coordinate
     * @return letter that corresponds to col number on board
     */
    public static String yToLetter(int num){
        switch (num + 1){
            case(1):
                return "a";
            case(2):
                return "b";
            case(3):
                return "c";
            case(4):
                return "d";
            case(5):
                return "e";
            case(6):
                return "f";
            case(7):
                return "g";
            case(8):
                return "h";
        }
        return "";
    }

    @Override
    public String toString(){
        return "X: " + x + " | " + "Y: " + y;
    }
}

