package ajaragz.draughts.models;

import java.util.ArrayList;
import java.util.List;

public class GameBuilder {

    private Color color;
    private List<String> boardRows;

    public GameBuilder() {
        this.color = null;
        this.boardRows = new ArrayList<String>();
    }

    public GameBuilder color(Color color){
        assert color == Color.WHITE || color == Color.BLACK;
        this.color = color;
        return this;
    }

    public GameBuilder boardSetup(String... rows){
        assert rows.length == Coordinate.getDimension();
        for (String row : rows) {
            assert row.matches("[bBnN ]{"+ Coordinate.getDimension() + "}");
            this.boardRows.add(row);
        }
        return this;
    }

    public Game build(){
        if (boardRows.size() == 0){
            return new Game();
        }
        Board board = new Board();
        Game game = new Game(board);
        setColor(board, game);
        setBoard(board);
        return game;
    }

    private void setColor(Board board, Game game) {
        if (this.color == Color.BLACK){
            Coordinate origin = new Coordinate(5,0);
            board.put(origin,new Pawn(Color.WHITE));
            Coordinate destination = new Coordinate(4,1);
            game.move(origin, destination);
            board.remove(destination);
        }
    }

    private void setBoard(Board board) {
        for (int i = 0; i < this.boardRows.size(); i++){
            String row = this.boardRows.get(i);
            for (int j = 0; j < row.length(); j++){
                Color color = this.getColor(row.charAt(j));
                if (color != null){
                    board.put(new Coordinate(i,j),getPiece(row.charAt(j)));
                }
            }
        }
    }

    private Color getColor(char character) {
        switch (character) {
            case 'b':
            case 'B':
                return Color.WHITE;
            case 'n':
            case 'N':
                return Color.BLACK;
            default:
                return null;
        }
    }

    private Piece getPiece(char character) {
        Color color = getColor(character);
        if (Character.isUpperCase(character))
            return new Draught(color);
        return new Pawn(color);
    }
}

