package onitama.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import onitama.model.moves.MoveCard;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class MoveCardStorage {


    private final List<MoveCard> cards;

    public MoveCardStorage(String filepath) throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileReader fr = new FileReader(filepath);
        Type cardListType = new TypeToken<List<MoveCard>>() {}.getType();
        cards = gson.fromJson(fr, cardListType);

    }

    public Stack<MoveCard> getRandomCards(int amount){
        if (amount > cards.size()) throw new IllegalArgumentException();

        List<MoveCard> clone = new ArrayList<>(cards);
        Stack<MoveCard> returnCards = new Stack<>();
        Random rand = new Random();

        for(int i = 0; i < amount; i++){
            MoveCard rc = clone.get(rand.nextInt(clone.size()));
            returnCards.push(rc);
            clone.remove(rc);
        }
        return returnCards;
    }


}