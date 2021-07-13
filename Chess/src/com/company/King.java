package com.company;

import java.util.ArrayList;

public class King extends Piece {

    private Location loc;
    private boolean canCastle = true;

    /**
     * Constructs King
     * @param isWhite color of king
     */
    public King(boolean isWhite) {
        super(isWhite);
    }

    /**
     * Constructs King
     * @param isWhite color of king
     * @param x row loc
     * @param y col loc
     */
    public King(boolean isWhite, int x, int y) {
        super(isWhite);
        loc = new Location(x, y);
    }

    /**
     * Constructs King
     * @param isWhite color of king
     * @param x row loc
     * @param y col loc
     * @param cc whether it can castle or not
     */
    public King(boolean isWhite, int x, int y, boolean cc) {
        super(isWhite);
        loc = new Location(x, y);
        canCastle = cc;
    }

    /**
     * Gets row coordinate
     * @return row coordinate
     */
    public int getX() {
        return loc.getX();
    }

    /**
     * Gets col coordinate
     * @return col coordinate
     */
    public int getY() {
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
    public void setLoc(Location loc) {
        this.loc = loc;
    }

    /**
     * Returns whether king can castle
     * @return whether it can castle or not
     */
    public boolean getCanCastle() {
        return canCastle;
    }

    /**
     * Sets whether king can caste
     * @param bool sets whether king can castle
     */
    public void setCanCastle(boolean bool) {
        canCastle = bool;
    }

    /**
     * Used to prevent infinite loop in getAllMoves method
     * @param board chess board
     * @return ArrayList of all valid moves
     */
    public ArrayList<Location> getAllMovesKSafe(Board board) {
        ArrayList<Location> moves = new ArrayList<Location>(9);
        ArrayList<Location> finalMoves = new ArrayList<Location>(9);
        Piece[][] chessBoard = board.getBoard();

        int x = this.loc.getX();
        int y = this.loc.getY();

        moves.add(new Location(x, y + 1));
        moves.add(new Location(x, y - 1));
        moves.add(new Location(x + 1, y));
        moves.add(new Location(x - 1, y));
        moves.add(new Location(x + 1, y + 1));
        moves.add(new Location(x + 1, y - 1));
        moves.add(new Location(x - 1, y + 1));
        moves.add(new Location(x - 1, y - 1));

        if (canCastle && !isInCheck(board)) {
            if(loc.equals(new Location(0, 4)) || loc.equals(new Location(7, 4))){
                if (chessBoard[x][y + 1] == null && chessBoard[x][y + 2] == null && chessBoard[x][y + 3] instanceof Rook) {
                    if(!((Rook)(chessBoard[x][y + 3])).hasMoved()){
                        if(!isInCheck(board, new Location(x, y+1))){
                            moves.add((new Location(x, y + 2)));
                        }
                    }
                }

                if (chessBoard[x][y - 1] == null && chessBoard[x][y - 2] == null && chessBoard[x][y - 3] == null && chessBoard[x][y - 4] instanceof Rook) {
                    if(!((Rook)(chessBoard[x][y - 4])).hasMoved()) {
                        if(!isInCheck(board, new Location(x, y-1))){
                            if(!isInCheck(board, new Location(x, y-2))){
                                moves.add((new Location(x, y - 2)));
                            }
                        }
                    }
                }
            }
        }

        for (Location locs : moves) {
            if (Location.isOnBoard(locs.getX(), locs.getY())) {
                if (chessBoard[locs.getX()][locs.getY()] == null || chessBoard[locs.getX()][locs.getY()].isWhite() != this.isWhite()) {
                    if (!isInCheck(board, locs)) {
                        finalMoves.add(locs);
                    }
                }
            }
        }

        return finalMoves;
    }

    /**
     * Gets all valid moves for king
     * @param board chess board
     * @return ArrayList of all valid moves
     */
    public ArrayList<Location> getAllMoves(Board board) {
        ArrayList<Location> moves = new ArrayList<Location>(9);
        ArrayList<Location> finalMoves = new ArrayList<Location>(9);
        Piece[][] chessBoard = board.getBoard();

        int x = this.loc.getX();
        int y = this.loc.getY();

        moves.add(new Location(x, y + 1));
        moves.add(new Location(x, y - 1));
        moves.add(new Location(x + 1, y));
        moves.add(new Location(x - 1, y));
        moves.add(new Location(x + 1, y + 1));
        moves.add(new Location(x + 1, y - 1));
        moves.add(new Location(x - 1, y + 1));
        moves.add(new Location(x - 1, y - 1));

        //castle possibilities
        if (canCastle && !isInCheck(board)) {
            if(loc.equals(new Location(0, 4)) || loc.equals(new Location(7, 4))){
                if (chessBoard[x][y + 1] == null && chessBoard[x][y + 2] == null && chessBoard[x][y + 3] instanceof Rook) {
                    if(!((Rook)(chessBoard[x][y + 3])).hasMoved()){
                        if(!isInCheck(board, new Location(x, y+1))){
                            moves.add((new Location(x, y + 2)));
                        }
                    }
                }

                if (chessBoard[x][y - 1] == null && chessBoard[x][y - 2] == null && chessBoard[x][y - 3] == null && chessBoard[x][y - 4] instanceof Rook) {
                    if(!((Rook)(chessBoard[x][y - 4])).hasMoved()) {
                        if(!isInCheck(board, new Location(x, y-1))){
                            if(!isInCheck(board, new Location(x, y-2))){
                                moves.add((new Location(x, y - 2)));
                            }
                        }
                    }
                }
            }
        }

        //Normal on board and opposing piece filter
        for (Location locs : moves) {
            if (Location.isOnBoard(locs.getX(), locs.getY())) {
                if (chessBoard[locs.getX()][locs.getY()] == null || chessBoard[locs.getX()][locs.getY()].isWhite() != this.isWhite()) {
                    if (!isInCheck(board, locs)) {
                        finalMoves.add(locs);
                    }
                }
            }
        }

        moves.clear();

        //King to king check
        King tKing;
        Boolean sameMove = false;
        if(isWhite()) {
            tKing = board.getBlackKing();
        }
        else {
            tKing = board.getWhiteKing();
        }
        for(Location locs : finalMoves){
            for(Location kLocs: tKing.getAllMovesKSafe(board)){
                if(!sameMove && locs.equals(kLocs)){
                    sameMove = !sameMove;
                }
            }
            if(!sameMove){
                moves.add(locs);
            }
        }

        return moves;
    }

    /**
     * Determines whether king is in check
     * @param board chess board
     * @return boolean of whether king is in check
     */
    public boolean isInCheck(Board board) {
        ArrayList<Piece> pieces;
        if (this.isWhite()) {
            pieces = board.getAllBlackPieces();
        } else {
            pieces = board.getAllWhitePieces();
        }
        for (Piece p : pieces) {
            if(p instanceof Pawn){
                for (Location locs : ((Pawn) p).getAllMovesPawnAttack(board)) {
                    if (locs.equals(loc)) {
                        return true;
                    }
                }
            }
            else if (!(p instanceof King)) {
                for (Location locs : p.getAllMoves(board)) {
                    if (locs.equals(loc)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Determines whether king is in check
     * @param board chess board
     * @param tLoc temporary location
     * @return boolean of whether king is in check
     */
    public boolean isInCheck(Board board, Location tLoc) {
        ArrayList<Piece> pieces;
        if (isWhite()) {
            pieces = board.getAllBlackPieces();
        } else {
            pieces = board.getAllWhitePieces();
        }
        for (Piece p : pieces) {
            if(p instanceof Pawn){
                for (Location locs : ((Pawn) p).getAllMovesPawnAttack(board)) {
                    if (locs.equals(tLoc)) {
                        return true;
                    }
                }
            }
            else if (!(p instanceof King)) {
                for (Location locs : p.getAllMoves(board)) {
                    if (locs.equals(tLoc)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Determines whether king is in stalemate
     * @param board chess board
     * @return boolean of whether king is in stalemate
     */
    public boolean isStalemate(Board board){
        ArrayList<Piece> pieces;

        if(getAllMoves(board).isEmpty() && !isInCheck(board)){
            if(isWhite()) {
                pieces = board.getAllWhitePieces();
            }
            else {
                pieces = board.getAllBlackPieces();
            }
            for(Piece p:pieces){
                if(!p.getAllMoves(board).isEmpty()){
                    return false;
                }
            }
        }
        else{
            return false;
        }

        return true;

    }

    /**
     * Determines whether king is in checkmate
     * @param board chess board
     * @return boolean of whether king is in checkmate
     */
    public boolean isCheckmate(Board board){
        ArrayList<Piece> pieces;
        Piece[][] copyChessBoard = board.cloneChessBoard();
        Board copyBoard = new Board(copyChessBoard);
        Boolean checkmate = true;

        if(isInCheck(board)){

            if(isWhite()){
                pieces = board.getAllWhitePieces();
            }
            else{
                pieces = board.getAllBlackPieces();
            }
            for(Piece piece:pieces){
                for(Location locs:piece.getAllMoves(board)){
                    copyChessBoard[piece.getX()][piece.getY()] = null;
                    copyChessBoard[locs.getX()][locs.getY()] = null;
                    copyChessBoard[locs.getX()][locs.getY()] = (Piece) piece.clone();
                    copyChessBoard[locs.getX()][locs.getY()].setLoc(locs);
                    copyBoard.updateBoard(copyChessBoard);
                    if( checkmate && !((isWhite() && copyBoard.getWhiteKing().isInCheck(copyBoard)) || (isBlack() && copyBoard.getBlackKing().isInCheck(copyBoard))) ){
                        checkmate = false;
                    }
                    copyChessBoard = board.cloneChessBoard();
                }
            }
        }
        else{
            return false;
        }
        return checkmate;

    }

    @Override
    protected Object clone()  {
        return new King( isWhite(), loc.getX(), loc.getY(), canCastle );
    }

    public String toString(){
        return "K";
    }

}