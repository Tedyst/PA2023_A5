package ro.tedyst;

import java.util.*;

public class SharedMemory {
    private Queue<Token> tokens;

    public SharedMemory(int n){
        List<Token> tokens_list = new ArrayList<>();
        for(int i=0;i<n;i++)
            tokens_list.add(new Token(i));
        Collections.shuffle(tokens_list);
        tokens = new LinkedList<>(tokens_list);
    }

    public synchronized Token extractToken(){
        return tokens.poll();
    }

    public List<Token> extractMultipleTokens(int count){
        List<Token> result = new ArrayList<>();
        for(int i=0; i<count; i++)
            result.add(extractToken());
        return result;
    }

}
