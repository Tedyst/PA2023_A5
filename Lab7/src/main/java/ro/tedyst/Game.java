package ro.tedyst;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private GameMap gameMap;
    private SharedMemory sharedMemory;
    private List<Worker> workerList;
    private List<Thread> threadList;
    private Boolean gameRunning;

    public Game(int mapSize, int workerCount){
        sharedMemory = new SharedMemory(mapSize * mapSize * mapSize);
        gameMap = new GameMap(mapSize, sharedMemory);
        workerList = new ArrayList<>();
        for(int i=0; i<workerCount; i++)
            workerList.add(new Worker("Worker " + i, this));
        threadList = new ArrayList<>();
    }

    public void start(){
        for(Worker worker : workerList){
            threadList.add(new Thread(worker));
        }
        for(Thread t : threadList)
            t.start();
        new Thread(new Daemon(this, 5500)).start();
        Scanner scan = new Scanner(System.in);
        while (Boolean.TRUE.equals(gameRunning)) {
            String text = scan.nextLine();
            System.out.println("Read " + text + " from keyboard");
            switch (text.strip()) {
                case "stop":
                    for (Worker w : workerList)
                        w.setRunning(false);
                    break;
                case "start":
                    for (Worker w : workerList)
                        w.setRunning(true);
                    break;
                case "exit":
                    showWorkerStats();
                    for (Thread t : threadList)
                        t.stop();
                    return;
            }
        }
    }

    public void showWorkerStats(){
        for(Worker w : workerList)
            System.out.println(w.getName() + " placed " + w.getPlacedTokens() + " Tokens");
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public List<Thread> getThreadList() {
        return threadList;
    }

    public List<Worker> getWorkerList() {
        return workerList;
    }

    public SharedMemory getSharedMemory() {
        return sharedMemory;
    }

    public Boolean getGameRunning() {
        return gameRunning;
    }

    public void setGameRunning(Boolean gameRunning) {
        this.gameRunning = gameRunning;
    }
}
