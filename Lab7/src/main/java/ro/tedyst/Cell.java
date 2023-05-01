package ro.tedyst;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    List<Token> tokenList = new ArrayList<>();

    public List<Token> getTokenList() {
        return tokenList;
    }

    public void addTokens(List<Token> tokens){
        tokenList.addAll(tokens);
    }

    public boolean visited(){
        return !tokenList.isEmpty();
    }
}
