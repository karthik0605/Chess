package com.company;

import java.util.ArrayList;

/**
 * @author Karthik Balaji & Kush Khanna
 * @version 1
 * Period - 1
 * Implements Chess Board
 */

class Board {

    private Piece[][] board;

    /**
     * Constructs Board
     */
    public Board(){
        board = new Piece[8][8];
        resetBoard();
        //print();
    }

    /**
     * Constructs board
     * @param chessBoard array for board
     */
    public Board(Piece[][] chessBoard){
        board = chessBoard;
        //print();
    }

    /**
     * Gets the board
     * @return chess board
     */
    public Piece[][] getBoard(){
        return board;
    }

    /**
     * Gets all pieces on the board
     * @return ArrayList of all pieces on board
     */
    public ArrayList<Piece> getAllPieces(){
        ArrayList<Piece> pieces = new ArrayList<Piece>(32);
        for(int row = 0; row <= 7; row ++ ){
            for(int col = 0; col <= 7; col ++ ){
                if(board[row][col] != null){
                    pieces.add(board[row][col]);
                }
            }
        }
        return pieces;
    }

    /**
     * Gets white king
     * @return white king
     */
    public King getWhiteKing(){
        for(int row = 0; row <= 7; row ++ ) {
            for (int col = 0; col <= 7; col++) {
                if(board[row][col] != null && board[row][col].isWhite() && board[row][col] instanceof King){
                    return (King)(board[row][col]);
                }
            }
        }
        return null;
    }

    /**
     * Gets black king
     * @return black king
     */
    public King getBlackKing(){
        for(int row = 0; row <= 7; row ++ ) {
            for (int col = 0; col <= 7; col++) {
                if(board[row][col] != null && board[row][col].isBlack() && board[row][col] instanceof King){
                    return (King)(board[row][col]);
                }
            }
        }
        return null;
    }

    /**
     * Gets all white pieces on the board
     * @return ArrayList of all white pieces on the board
     */
    public ArrayList<Piece> getAllWhitePieces(){
        ArrayList<Piece> pieces = new ArrayList<Piece>(16);
        for(int row = 0; row <= 7; row ++ ){
            for(int col = 0; col <= 7; col ++ ){
                if(board[row][col]!=null && board[row][col].isWhite()){
                    pieces.add(board[row][col]);
                }
            }
        }
        return pieces;
    }

    /**
     * Gets all black pieces on the board
     * @return ArrayList of all black pieces on the board
     */
    public ArrayList<Piece> getAllBlackPieces(){
        ArrayList<Piece> pieces = new ArrayList<Piece>(16);
        for(int row = 0; row <= 7; row ++ ){
            for(int col = 0; col <= 7; col ++ ){
                if(board[row][col]!=null && board[row][col].isBlack()){
                    pieces.add(board[row][col]);
                }
            }
        }
        return pieces;
    }

    /**
     * Clones chess board
     * @return new board, 2d array
     */
    public Piece[][] cloneChessBoard(){
        Piece[][] holder = new Piece[8][8];
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board[0].length; col++){
                if(board[row][col]!=null){
                    holder[row][col] = (Piece) board[row][col].clone();
                }
            }
        }
        return holder;
    }

    /**
     * For testing purposes
     */
    public void print(){
        for(int row = 7; row >= 0 ; row --) {
            for (int col = 0; col < 8; col++) {
                if(board[row][col]!=null)
                    System.out.print( board[row][col].toString() + " ");
                else{
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
        System.out.println("-----------------");
    }

    /**
     * Updates board to new position
     * @param chessBoard chess board
     */
    public void updateBoard(Piece[][] chessBoard){
        board = chessBoard;
    }

    /**
     * Resets board to starting position
     */
    public void resetBoard(){

        board[0][0] = new Rook(true, 0, 0);
        board[0][1] = new Knight(true, 0, 1);
        board[0][2] = new Bishop(true, 0, 2);
        board[0][3] = new Queen(true, 0, 3);
        board[0][4] = new King(true, 0, 4);
        board[0][5] = new Bishop(true, 0, 5);
        board[0][6] = new Knight(true, 0, 6);
        board[0][7] = new Rook(true, 1, 7);
        board[1][0] = new Pawn(true, 1, 0);
        board[1][1] = new Pawn(true, 1, 1);
        board[1][2] = new Pawn(true, 1, 2);
        board[1][3] = new Pawn(true, 1, 3);
        board[1][4] = new Pawn(true, 1, 4);
        board[1][5] = new Pawn(true, 1, 5);
        board[1][6] = new Pawn(true, 1, 6);
        board[1][7] = new Pawn(true, 1, 7);

        board[7][0] = new Rook(false, 7, 0);
        board[7][1] = new Knight(false, 7, 1);
        board[7][2] = new Bishop(false, 7, 2);
        board[7][3] = new Queen(false, 7, 3);
        board[7][4] = new King(false, 7, 4);
        board[7][5] = new Bishop(false, 7, 5);
        board[7][6] = new Knight(false, 7, 6);
        board[7][7] = new Rook(false, 7, 7);
        board[6][0] = new Pawn(false, 6, 0);
        board[6][1] = new Pawn(false, 6, 1);
        board[6][2] = new Pawn(false, 6, 2);
        board[6][3] = new Pawn(false, 6, 3);
        board[6][4] = new Pawn(false, 6, 4);
        board[6][5] = new Pawn(false, 6, 5);
        board[6][6] = new Pawn(false, 6, 6);
        board[6][7] = new Pawn(false, 6, 7);



        for(int row = 2; row < 6; row ++) {
            for (int col = 0; col < 8; col++) {
                board[row][col] = null;
            }
        }

//        board[0][4] = new King(true, 0, 4);
//        board[0][3] = new Queen(true, 0, 3);
//        board[7][4] = new King(false, 7, 4);


        /*for(int row = 0; row < 1; row ++) {
            for (int col = 0; col < 8; col++) {
                board[row][col] = null;
            }
        }

        board[0][4] = new King(true, 0, 4);*/


//        board[4][4] = new King(true, 4, 4);
//        board[4][5] = new Knight(true, 4, 5);
//        board[4][7] = new Queen(false, 4, 7);
//        board[1][1] = new King(false, 1, 1);


    }

}

