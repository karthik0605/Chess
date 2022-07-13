package com.company;

/**
 * @author Karthik Balaji & Kush Khanna
 * @version 1
 * Period - 1
 * Main Class
 */

public class Main {

    public static void main(String[] args) {

        Board chessBoard = new Board();
        Game game = new Game();
        Moves moves = new Moves(game);
        ChessGUI gui = new ChessGUI(game, chessBoard, moves);

    }
}
