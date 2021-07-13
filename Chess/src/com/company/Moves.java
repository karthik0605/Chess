package com.company;

import java.util.Stack;

/**
 * @author Karthik Balaji & Kush Khanna
 * @version 1
 * Period - 1
 * Implements Moves
 */

public class Moves {

    private Game game;
    private Stack<String> listOfMoves;
    private Stack<Piece[][]> prevBoard;

    /**
     * Constructs list of moves and the previous board
     * @param game chess game
     */
    public Moves(Game game){
        this.game = game;
        listOfMoves = new Stack<String>();
        prevBoard = new Stack<Piece[][]>();
    }

    /**
     * Stack of previous board
     * @return previous board
     */
    public Stack<Piece[][]> getPrevBoard(){
        return prevBoard;
    }

    /**
     * Used to keep track of previous board
     * @param board other board which is added to previous board
     */
    public void storePrevBoard(Board board){
        prevBoard.add(board.cloneChessBoard());
    }

    /**
     * For moving pieces
     * @param board chess board
     * @param oLoc old location
     * @param nLoc new location
     * @return whether a piece can move to a location
     */
    public boolean moveTo(Board board, Location oLoc, Location nLoc) {
        Piece[][] chessBoard = board.getBoard();

        for (Location locs : chessBoard[oLoc.getX()][oLoc.getY()].getAllMoves(board)) {
            if (locs.equals(nLoc)) {
                storePrevBoard(board);
                if((board.getWhiteKing().isInCheck(board) && game.isWhiteTurn()) || (board.getBlackKing().isInCheck(board) && game.isBlackTurn())){
                    Piece[][] tempBoard = board.cloneChessBoard();
                    Piece tempP = tempBoard[oLoc.getX()][oLoc.getY()];
                    tempBoard[oLoc.getX()][oLoc.getY()] = null;
                    tempP.setLoc(new Location(nLoc.getX(), nLoc.getY()));
                    tempBoard[nLoc.getX()][nLoc.getY()] = tempP;
                    Board tempB = new Board(tempBoard);
                    //System.out.println("here");
                    if( (game.isWhiteTurn() && tempB.getWhiteKing().isInCheck(tempB)) || (game.isBlackTurn() && tempB.getBlackKing().isInCheck(tempB)) ){
                        prevBoard.pop();
                        return false;
                    }
                }
                // Pawn Promotion
                Piece tempPiece = (Piece) chessBoard[oLoc.getX()][oLoc.getY()];
                if(chessBoard[oLoc.getX()][oLoc.getY()] instanceof Pawn){
                    ((Pawn) board.getBoard()[oLoc.getX()][oLoc.getY()]).setFirstMove(false);
                }
                if(chessBoard[oLoc.getX()][oLoc.getY()] instanceof Pawn && (nLoc.getX() == 0 ||  nLoc.getX() == 7)){
                    tempPiece = (ChessGUI.pawnPromotionOptions((board.getBoard()[oLoc.getX()][oLoc.getY()].isWhite()), nLoc));
                    listOfMoves.add(Location.toNotation(board, oLoc, nLoc) + "=" + tempPiece.toString());
                }
                if(chessBoard[oLoc.getX()][oLoc.getY()] instanceof Rook){
                    ((Rook) (tempPiece)).setHasMoved(true);
                }
                if(chessBoard[oLoc.getX()][oLoc.getY()] instanceof King){
                    if(moveIsCastle(board, oLoc, nLoc)){
                        if(!((King) tempPiece).getCanCastle()){
                            prevBoard.pop();
                            return false;
                        }
                        if(isRightSideCastle(board, oLoc, nLoc)){
                            Piece tempPiece2 = chessBoard[nLoc.getX()][nLoc.getY()+1];
                            chessBoard[nLoc.getX()][nLoc.getY()+1] = null;
                            chessBoard[nLoc.getX()][nLoc.getY()-1] = tempPiece2;
                            ((Rook) (tempPiece2)).setHasMoved(true);
                            chessBoard[nLoc.getX()][nLoc.getY()-1].setLoc(new Location(nLoc.getX(), nLoc.getY()-1));
                            listOfMoves.add("O-O");
                        }
                        else{
                            Piece tempPiece2 = chessBoard[nLoc.getX()][nLoc.getY()-2];
                            chessBoard[nLoc.getX()][nLoc.getY()-2] = null;
                            chessBoard[nLoc.getX()][nLoc.getY()+1] = tempPiece2;
                            ((Rook) (tempPiece2)).setHasMoved(true);
                            chessBoard[nLoc.getX()][nLoc.getY()+1].setLoc(new Location(nLoc.getX(), nLoc.getY()+1));
                            listOfMoves.add("O-O-O");
                        }
                    }
                    ((King) tempPiece).setCanCastle(false);
                }

                if(listOfMoves.size()!=prevBoard.size()){
                    String notation = Location.toNotation(board, oLoc, nLoc);
                    listOfMoves.add(notation);
                }

                Piece tempCapturePiece;
                if(chessBoard[nLoc.getX()][nLoc.getY()]!=null){
                    tempCapturePiece = (Piece) chessBoard[nLoc.getX()][nLoc.getY()].clone();
                }
                else{
                    tempCapturePiece = null;
                }
                chessBoard[oLoc.getX()][oLoc.getY()] = null;
                chessBoard[nLoc.getX()][nLoc.getY()] = tempPiece;
                chessBoard[nLoc.getX()][nLoc.getY()].setLoc(nLoc);
                board.updateBoard(chessBoard);


                if( (game.isWhiteTurn() && board.getWhiteKing().isInCheck(board)) || (game.isBlackTurn() && board.getBlackKing().isInCheck(board)) ){
                    listOfMoves.pop();
                    chessBoard[oLoc.getX()][oLoc.getY()] = tempPiece;
                    chessBoard[nLoc.getX()][nLoc.getY()] = tempCapturePiece;
                    chessBoard[oLoc.getX()][oLoc.getY()].setLoc(oLoc);
                    prevBoard.pop();
                    return false;
                }

                game.moveMade();
                return true;

            }
        }
        return false;
    }

    /**
     * Right side king castling
     * @param board chess board
     * @param oLoc old location
     * @param nLoc new location
     * @return whether the squares are free for right side castling
     */
    public boolean isRightSideCastle(Board board, Location oLoc, Location nLoc){
        return (-2==oLoc.getY()-nLoc.getY());
    }

    /**
     * Whether a move is a castling move
     * @param board chess board
     * @param oLoc old location
     * @param nLoc new location
     * @return boolean of whether a move is a castling move
     */
    public static boolean moveIsCastle(Board board, Location oLoc, Location nLoc){
         return (2==Math.abs(oLoc.getY()-nLoc.getY()));
    }

    /**
     * Empties the stack of moves
     */
    public void resetMoves(){
        listOfMoves.clear();
    }

    /**
     * Gets the stack of moves
     * @return stack of moves
     */
    public Stack<String> getListOfMoves() {
        return listOfMoves;
    }
}


