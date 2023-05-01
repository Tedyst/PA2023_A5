package ro.tedyst;

import java.util.Random;

public class Worker implements Runnable {
    private String name;
    private final int SLEEP_TIME_MILIS = 1000;
    private int x, y;
    private Game game;
    private boolean running;
    private int placedTokens = 0;
    Random random = new Random();

    public Worker(String name, Game game){

        this.name = name;
        this.x = random.nextInt(game.getGameMap().getSize());
        this.y = random.nextInt(game.getGameMap().getSize());
        this.running = false;
        this.game = game;
    }

    public void run(){
        System.out.println(name + " started");
        running = true;
        while(true){
            try {
                Thread.sleep(SLEEP_TIME_MILIS);
            } catch (InterruptedException e) {
                System.out.println("Error while sleeping in Worker: " + e);
                return;
            }
            if(!running)
                continue;
            y++;
            if(y == game.getGameMap().getSize()){
                y = 0;
                x ++;
                if(x == game.getGameMap().getSize())
                    return;
            }
            System.out.println(name + " is trying to visit " + x + " " + y);
            placedTokens += game.getGameMap().visit(x, y, this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public synchronized void setRunning(boolean running) {
        this.running = running;
    }

    public boolean getRunning(){
        return running;
    }

    public int getPlacedTokens() {
        return placedTokens;
    }
}
