package ro.tedyst;

import java.util.List;

public class Daemon implements Runnable {
    private Game game;
    private int maxTime;

    public Daemon(Game game, int maxTime){
        this.game = game;
        this.maxTime = maxTime;
    }

    public void run(){
        try {
            Thread.sleep(maxTime);
        } catch (InterruptedException e) {
            System.out.println("Error while sleeping in Worker: " + e);
            return;
        }
        for(Thread t : game.getThreadList())
            t.stop();
        game.showWorkerStats();
        game.setGameRunning(false);
    }
}
