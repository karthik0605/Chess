package com.company;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Karthik Balaji & Kush Khanna
 * @version 1
 * Period - 1
 * Implements Chess GUI
 */

public class ChessGUI {

    private Board board;
    private Moves moves;
    private Game game;
    private Piece[][] chessBoard;
    private JFrame fullFrame = new JFrame();
    private JPanel gameBoard = new JPanel(new GridLayout(8,8));
    private JPanel verticalNotation = new JPanel(new GridLayout(8,1));
    private JPanel horizontalNotation = new JPanel(new GridLayout(1,8));
    private JButton[][] boardButtons = new JButton[8][8];

    private GridBagConstraints GBC = new GridBagConstraints();

    private JPanel sidePanel = new JPanel(new BorderLayout(10,10));

    private JPanel notationPanel = new JPanel(new GridLayout(1, 2));
    private JScrollPane scrollPane = new JScrollPane(notationPanel);
    private JPanel whiteMoves = new JPanel();
    private JPanel blackMoves = new JPanel();

    private JButton btnWhiteResign = new JButton("W Resign");
    private JButton btnBlackResign = new JButton("B Resign");
    private JButton btnDraw = new JButton("Draw");
    private JButton btnUndo = new JButton("Undo");


    private JPanel optionButtons = new JPanel(new FlowLayout());

    private static final Color BLACK_SQUARE_COLOR = Color.decode("#B58863");
    private static final Color WHITE_SQUARE_COLOR = Color.decode("#F0D9B5");
    private static final Color POSSIBLE_MOVES_COLOR = Color.decode("#F78700");
    private static final Color KING_IN_CHECK_COLOR = Color.decode("#EB4D45");
    private static final Color BACKGROUND_COLOR_NOTATION = Color.DARK_GRAY;
    private static final Color TEXT_COLOR_NOTATION = Color.WHITE;

    /**
     * Constructs all GUI
     * @param game game
     * @param board chess board
     * @param moves moves
     */
    public ChessGUI(Game game, Board board, Moves moves){
        this.game = game;
        this.board = board;
        this.moves = moves;
        chessBoard = board.getBoard();
        fullFrame.getContentPane().setLayout(new GridBagLayout());
        fullFrame.setPreferredSize(new Dimension(1200, 900));
        fullFrame.setResizable(false);
        //fullFrame.setLocationRelativeTo(null);

        addPanels();
        sidePanel();
        setNotationPanel();

        fullFrame.pack();
        fullFrame.setVisible(true);
        setUpChessBoardButtons();
        buttonListener();
        updateBoard();

        //FIXING UNFIXABLE ERROR
        gameBoard.setVisible(false);
        boardButtons[0][7].doClick();
        boardButtons[0][7].doClick();
        gameBoard.updateUI();
        gameBoard.setVisible(true);
        fullFrame.pack();
    }

    private void setNotationPanel(){

        JLabel mStr = new JLabel("Notation", SwingConstants.CENTER);
        mStr.setOpaque(true);
        mStr.setFont(new Font("Serif", Font.BOLD, 20));
        mStr.setBackground(BACKGROUND_COLOR_NOTATION);
        mStr.setForeground(TEXT_COLOR_NOTATION);;
        sidePanel.add(mStr, BorderLayout.NORTH);

        whiteMoves.setLayout(new BoxLayout(whiteMoves, BoxLayout.Y_AXIS));
        blackMoves.setLayout(new BoxLayout(blackMoves, BoxLayout.Y_AXIS));
        notationPanel.add(whiteMoves);
        notationPanel.add(blackMoves);
        whiteMoves.setBackground(BACKGROUND_COLOR_NOTATION);
        blackMoves.setBackground(BACKGROUND_COLOR_NOTATION);
        notationPanel.setBackground(BACKGROUND_COLOR_NOTATION);
        sidePanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void updateNotationPanel(String move, Boolean whiteTurn){
        JLabel mStr = new JLabel(move);
        mStr.setAlignmentX(Component.CENTER_ALIGNMENT);
        mStr.setHorizontalTextPosition(JLabel.CENTER);

        //Border border = BorderFactory.createLineBorder(Color.BLACK);
        //mStr.setBorder(border);

        mStr.setFont(new Font("Serif", Font.BOLD, 20));
        mStr.setForeground(TEXT_COLOR_NOTATION);

        if(whiteTurn){
            blackMoves.add( mStr );
        }
        else{
            whiteMoves.add( mStr );
        }
    }

    private void addPanels(){
        setNotations();

        GBC.gridx = 0;
        GBC.gridy = 0;
        GBC.weighty = 8;
        GBC.weightx = .8;
        GBC.fill = GridBagConstraints.BOTH;

        fullFrame.add(verticalNotation, GBC);

        GBC.gridx = 1;
        GBC.gridy = 0;
        GBC.weightx = 8;
        GBC.weighty = 8;
        GBC.fill = GridBagConstraints.BOTH;
        fullFrame.add(gameBoard, GBC);

        GBC.gridx = 1;
        GBC.gridy = 1;
        GBC.weighty = .2;
        GBC.weightx = 8;
        GBC.fill = GridBagConstraints.BOTH;

        fullFrame.add(horizontalNotation, GBC);

        GBC.gridx = 0;
        GBC.gridy = 1;
        GBC.weighty = .2;
        GBC.weightx = .8;
        GBC.fill = GridBagConstraints.BOTH;

        GBC.gridx = 2;
        GBC.gridy = 0;
        GBC.weighty = 8.2;
        GBC.weightx = 4;
        GBC.fill = GridBagConstraints.BOTH;
        fullFrame.add(sidePanel, GBC);

        /*JLabel pt = new JLabel("s", JLabel.CENTER);
        pt.setForeground(TEXT_COLOR_NOTATION);
        fullFrame.add(pt, GBC);*/


        fullFrame.getContentPane().setBackground(BACKGROUND_COLOR_NOTATION);
    }

    private void resetGame(){
        //reseting game
        board.resetBoard();
        chessBoard = board.getBoard();
        updateBoard();
        moves.resetMoves();
        moves.getPrevBoard().clear();
        game.setWhiteTurn(true);
        whiteMoves.removeAll();
        blackMoves.removeAll();
        resetBoardColors();
        notationPanel.updateUI();
        sidePanel.updateUI();
        gameBoard.updateUI();
        boardButtons[0][7].doClick();
        boardButtons[0][7].doClick();
        gameBoard.updateUI();
    }

    private void sidePanel(){

        optionButtons.add(btnWhiteResign);
        optionButtons.add(btnBlackResign);
        optionButtons.add(btnDraw);
        optionButtons.add(btnUndo);

        optionButtons.setBackground(BACKGROUND_COLOR_NOTATION);
        sidePanel.setOpaque(true);
        sidePanel.setBackground(BACKGROUND_COLOR_NOTATION);
        fullFrame.setBackground(BACKGROUND_COLOR_NOTATION);

        sidePanel.setBorder(new EmptyBorder(0, 0, 0, 0));

        sidePanel.add(optionButtons, BorderLayout.SOUTH);

        btnWhiteResign.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    gameEnd("Black Wins");
            }
        });

        btnBlackResign.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    gameEnd("White Wins");
            }
        });

        btnDraw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameEndDraw();
            }
        });

        btnUndo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(moves.getPrevBoard().size()>0){
                    chessBoard = moves.getPrevBoard().pop();
                    board.updateBoard(chessBoard);
                    updateBoard();
                    if (game.isBlackTurn())
                        whiteMoves.remove(whiteMoves.countComponents() - 1);
                    else {
                        blackMoves.remove(blackMoves.countComponents() - 1);
                    }
                    game.moveMade();
                    resetBoardColors();
                    notationPanel.updateUI();
                    sidePanel.updateUI();
                    gameBoard.updateUI();
                }
            }
        });

//        JButton j = new JButton("p");
//        optionButtons.add(j);
//
//        j.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                board.print();
//            }
//        });
//
//        JButton a = new JButton("a");
//        optionButtons.add(a);
//
//        a.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                Board t = new Board(moves.getPrevBoard().peek());
//                t.print();
//            }
//        });


    }

    private void setNotations(){
        int counter = 0;
        for(int i = 8; i>=1; i--){
            JLabel jl1 = new JLabel(i+"", JLabel.CENTER);
            jl1.setFont(new Font("Serif", Font.BOLD, 14));
            jl1.setForeground(TEXT_COLOR_NOTATION);
            verticalNotation.add(jl1);
            JLabel jl2 = new JLabel(Character.toString((char)(counter+97)), JLabel.CENTER);
            jl2.setFont(new Font("Serif", Font.BOLD, 14));
            jl2.setForeground(TEXT_COLOR_NOTATION);
            horizontalNotation.add( jl2 );
            counter ++;
        }
        verticalNotation.setBackground(BACKGROUND_COLOR_NOTATION);
        horizontalNotation.setBackground(BACKGROUND_COLOR_NOTATION);
    }

    /**
     * Button listener method for GUI
     */
    public void buttonListener(){
        for(int row = 0; row < 8; row ++) {
            for (int col = 0; col < 8; col++) {
                int nRow = row;
                int nCol = col;

                boardButtons[nRow][nCol].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        boolean shouldSelect = false;
                        if(boardButtons[nRow][nCol].getIcon()!=null){
                            if(chessBoard[nRow][nCol].isWhite() && game.isWhiteTurn() || chessBoard[nRow][nCol].isBlack() && game.isBlackTurn()){
                                showAllPossibleMoves(nRow, nCol);
                                shouldSelect = true;
                            }
                        }
                        for (int row = 0; row < 8; row++) {
                            for (int col = 0; col < 8; col++) {
                                if (boardButtons[row][col].isSelected()) {
                                    if(boardButtons[row][col].getIcon()!=null){
                                        if((chessBoard[row][col]!=null) && (chessBoard[row][col].isWhite() && game.isWhiteTurn() || chessBoard[row][col].isBlack() && game.isBlackTurn())) {
                                            if(!moves.moveTo(board, new Location(row, col), new Location(nRow, nCol))){
                                                chessBoard[row][col].setLoc(new Location(row, col));
                                            }
                                            else{
                                                updateNotationPanel(moves.getListOfMoves().peek(), game.isWhiteTurn());
                                            }
                                            boardButtons[row][col].setIcon(null);
                                            if(Moves.moveIsCastle(board, new Location(row, col), new Location(nRow, nCol) )){
                                                updateBoard();
                                            }
                                            boardButtons[row][col].setSelected(false);
                                            resetBoardColors();
                                            updateBoard();

                                            if(board.getWhiteKing().isInCheck(board)){
                                                boardButtons[board.getWhiteKing().getX()][board.getWhiteKing().getY()].setBackground(KING_IN_CHECK_COLOR);
                                            }
                                            if(board.getBlackKing().isInCheck(board)){
                                                boardButtons[board.getBlackKing().getX()][board.getBlackKing().getY()].setBackground(KING_IN_CHECK_COLOR);
                                            }
                                            //check checkmate
                                            if(board.getWhiteKing().isCheckmate(board)){
                                                gameEnd("Black Wins");
                                            }
                                            if(board.getBlackKing().isCheckmate(board)){
                                                gameEnd("White Wins");
                                            }
                                            if(board.getWhiteKing().isStalemate(board)){
                                                gameEnd("Stalemate");
                                            }
                                            if(board.getBlackKing().isStalemate(board)){
                                                gameEnd("Stalemate");
                                            }
                                        }
                                        shouldSelect = false;
                                    }
                                    break;
                                }
                            }
                        }
                        if (shouldSelect) {
                            boardButtons[nRow][nCol].setSelected(true);
                        }
                    }
                });
            }
        }
    }

    /**
     * For showing the squares of possible moves
     * @param x row coordinate
     * @param y col coordinate
     */
    public void showAllPossibleMoves(int x, int y){
        for(Location locs:board.getBoard()[x][y].getAllMoves(board)){
            boardButtons[locs.getX()][locs.getY()].setBackground(POSSIBLE_MOVES_COLOR);
        }
        updateBoard();

    }

    /**
     * Coloring the board so every other square is the same color
     */
    public void resetBoardColors(){
        for(int row = 0; row < 8; row ++){
            for(int col = 0; col < 8; col ++){
                if((row+col) % 2 == 0){
                    boardButtons[row][col].setBackground(BLACK_SQUARE_COLOR);
                }
                else{
                    boardButtons[row][col].setBackground(WHITE_SQUARE_COLOR);
                }
            }
        }
    }

    /**
     * Updates board with all images and GUI
     */
    public void updateBoard(){
        gameBoard.removeAll();
        for(int row = 7; row >= 0 ; row --) {
            for (int col = 0; col < 8; col++) {
                if(chessBoard[row][col]!=null){
                    switch (chessBoard[row][col].toString()){
                        case("P"):
                            if(chessBoard[row][col].isWhite()){
                                boardButtons[row][col].setIcon(new ImageIcon("src\\com\\company\\chess_piece_images\\big\\wpawn.png"));
                            }
                            else{
                                boardButtons[row][col].setIcon(new ImageIcon("src\\com\\company\\chess_piece_images\\big\\bpawn.png"));
                            }
                            break;
                        case("N"):
                            if(chessBoard[row][col].isWhite()){
                                boardButtons[row][col].setIcon(new ImageIcon("src\\com\\company\\chess_piece_images\\big\\wknight.png"));
                            }
                            else{
                                boardButtons[row][col].setIcon(new ImageIcon("src\\com\\company\\chess_piece_images\\big\\bknight.png"));
                            }
                            break;
                        case("B"):
                            if(chessBoard[row][col].isWhite()){
                                boardButtons[row][col].setIcon(new ImageIcon("src\\com\\company\\chess_piece_images\\big\\wbishop.png"));
                            }
                            else{
                                boardButtons[row][col].setIcon(new ImageIcon("src\\com\\company\\chess_piece_images\\big\\bbishop.png"));
                            }
                            break;
                        case("R"):
                            if(chessBoard[row][col].isWhite()){
                                boardButtons[row][col].setIcon(new ImageIcon("src\\com\\company\\chess_piece_images\\big\\wrook.png"));
                            }
                            else{
                                boardButtons[row][col].setIcon(new ImageIcon("src\\com\\company\\chess_piece_images\\big\\brook.png"));
                            }
                            break;
                        case("Q"):
                            if(chessBoard[row][col].isWhite()){
                                boardButtons[row][col].setIcon(new ImageIcon("src\\com\\company\\chess_piece_images\\big\\wqueen.png"));
                            }
                            else{
                                boardButtons[row][col].setIcon(new ImageIcon("src\\com\\company\\chess_piece_images\\big\\bqueen.png"));
                            }
                            break;
                        case("K"):
                            if(chessBoard[row][col].isWhite()){
                                boardButtons[row][col].setIcon(new ImageIcon("src\\com\\company\\chess_piece_images\\big\\wking.png"));
                            }
                            else{
                                boardButtons[row][col].setIcon(new ImageIcon("src\\com\\company\\chess_piece_images\\big\\bking.png"));
                            }
                            break;
                        default:
                            boardButtons[row][col].setText(chessBoard[row][col].toString());
                            break;
                    }
                    gameBoard.add(boardButtons[row][col]);
                }
                else{
                    gameBoard.add(boardButtons[row][col]);
                    boardButtons[row][col].setIcon(null);
                }
            }
        }
        gameBoard.revalidate();
        fullFrame.revalidate();
    }

    private void setUpChessBoardButtons(){
        for(int row = 0; row < 8; row ++){
            for(int col = 0; col < 8; col ++){
                boardButtons[row][col] = new JButton();
                boardButtons[row][col].setBorderPainted(false);
                if((row+col) % 2 == 0){
                        boardButtons[row][col].setBackground(BLACK_SQUARE_COLOR);
                }
                else {
                    boardButtons[row][col].setBackground(WHITE_SQUARE_COLOR);
                }
            }
        }
    }

    /**
     * Uses images for pawn promotion
     * @param isWhite color of pawn
     * @param loc location of pawn
     * @return piece that is promoted to
     */
    public static Piece pawnPromotionOptions(Boolean isWhite, Location loc){
        ImageIcon[] options = new ImageIcon[4];
        if(isWhite){
            options[0] = new ImageIcon("src\\com\\company\\chess_piece_images\\big\\wqueen.png");
            options[1] = new ImageIcon("src\\com\\company\\chess_piece_images\\big\\wbishop.png");
            options[2] = new ImageIcon("src\\com\\company\\chess_piece_images\\big\\wknight.png");
            options[3] = new ImageIcon("src\\com\\company\\chess_piece_images\\big\\wrook.png");
        }
        else{
            options[0] = new ImageIcon("src\\com\\company\\chess_piece_images\\big\\bqueen.png");
            options[1] = new ImageIcon("src\\com\\company\\chess_piece_images\\big\\bbishop.png");
            options[2] = new ImageIcon("src\\com\\company\\chess_piece_images\\big\\bknight.png");
            options[3] = new ImageIcon("src\\com\\company\\chess_piece_images\\big\\brook.png");
        }
        int response = JOptionPane.showOptionDialog(null, null, "Choose Piece",
        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        switch (response){
            case(0):
                return new Queen(isWhite, loc.getX(), loc.getY());
            case(1):
                return new Bishop(isWhite, loc.getX(), loc.getY());
            case(2):
                return new Knight(isWhite, loc.getX(), loc.getY());
            case(3):
                return new Rook(isWhite, loc.getX(), loc.getY());
        }
        return new Queen(isWhite, loc.getX(), loc.getY());

    }

    /**
     * GUI for what happens when the game ends
     * @param winner winner of the game
     */
    public void gameEnd(String winner){
        String[] options = new String[2];
        options[0] = "Play Again";
        options[1] = "Quit";
        int response = JOptionPane.showOptionDialog(null, winner, "GAME OVER",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if(response==1){
            fullFrame.dispose();
        }
        else{
            resetGame();
        }
    }

    /**
     * What to do if game ends in a draw
     */
    public void gameEndDraw(){
        String[] options = new String[2];
        options[0] = "Yes";
        options[1] = "No";
        int response = JOptionPane.showOptionDialog(null, "Do both Players Agree", "Draw?",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if(response==0){
            gameEnd("Draw");
        }
    }

}
