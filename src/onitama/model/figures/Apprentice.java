package onitama.model.figures;

import onitama.model.board.Field;
import onitama.model.Player;
import onitama.model.board.FigureType;

import java.security.InvalidParameterException;

public class Apprentice implements Figure{

    protected Player player;
    protected Field currentField;

    public Apprentice(Player p){
        player = p;
    }


    @Override
    public void move(Field f) {
        f.accept(this);
        currentField.setFigure(null);
        currentField = f;
    }

    @Override
    public void die() {
        currentField.setFigure(null);
        currentField = null;
        player.removeFigure(this);
    }

    @Override
    public void hitByFigure(Figure f) {
        if(f.getPlayer() == player) throw new InvalidParameterException("Can't hit own figure");
        die();
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    public Field getCurrentField() {
        return currentField;
    }

    public void setCurrentField(Field currentField) {
        this.currentField = currentField;
    }

    @Override
    public FigureType getFigureType() {
        return FigureType.Apprentice;
    }

    @Override
    public String toString() {
        return "A";
    }
}
