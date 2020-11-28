package onitama.model;

import onitama.model.figures.Figure;
import onitama.model.moves.Hand;
import onitama.model.moves.Move;
import onitama.model.moves.MoveCard;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {

    private final Game game;
    private final Color color;

    private final List<Figure> figures;
    private final Hand hand;
    private MoveCard selectedCard;
    private Figure selectedFigure;
    private boolean isTurn = false;

    public Player(Game game, Color color) {
        this.game = game;
        this.color = color;
        this.hand = new Hand(2);
        this.figures = new ArrayList<>();
    }

    public List<Move> getAvailableMoves(){
        if(selectedCard != null && selectedFigure != null){
            return selectedCard.getAvailableMoves(selectedFigure);
        }
        return null;
    }

    public List<Move> getAllPossibleMoves(){
        ArrayList<Move> result = new ArrayList<>();
        for (MoveCard card : hand.getCards()) {
            for (Figure figure: figures){
                result.addAll(card.getAvailableMoves(figure));
            }
        }
        return result;
    }

    public void executeMove(Move move){
        if(move.getPlayer() != this || !hand.getCards().contains(move.getParentCard()) || !figures.contains(move.getFigure()))
            throw new IllegalArgumentException("Player can't execute this move");
        move.getFigure().moveTo(move.getDestination());
        hand.setCard(game.exchangeCard(move.getParentCard()),move.getParentCard());
        this.selectedFigure = null;
        this.selectedCard = null;
        game.nextTurn();
    }

    public void loose() {
        game.setGameFinished(true);
    }

    public MoveCard getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(MoveCard selectedCard) {
        this.selectedCard = selectedCard;
    }

    public Figure getSelectedFigure() {
        return selectedFigure;
    }

    public void setSelectedFigure(Figure selectedFigure) {
        this.selectedFigure = selectedFigure;
    }

    public void addFigure(Figure f) {
        figures.add(f);
    }

    public void removeFigure(Figure f) {
        figures.remove(f);
    }

    public boolean isTurn() {
        return isTurn;
    }

    public void setTurn(boolean turn) {
        isTurn = turn;
    }

    public List<Figure> getFigures() {
        return figures;
    }

    public Hand getHand() {
        return hand;
    }

    public Color getColor() {
        return color;
    }


    @Override
    public String toString() {
        return "{" +
                "cards=" + hand +
                '}';
    }
}
