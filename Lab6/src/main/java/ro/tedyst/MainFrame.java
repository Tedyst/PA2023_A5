package ro.tedyst;

import ro.tedyst.game.Game;

import javax.swing.*;

import java.awt.*;


public class MainFrame extends JFrame {
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;
    Popup popup;

    Game game;

    public MainFrame() {
        super("My Drawing Application");
        init();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
        canvas.createBoard();
    }

    public void createGame() {
        game = new Game(
                canvas.H,
                canvas.W,
                (int) configPanel.dotsSpinner.getValue(),
                (double) configPanel.linesCombo.getSelectedItem()
        );
        canvas.createBoard();
        if(popup != null){
            popup.hide();
            popup = null;
        }
    }

    public void createPopup(String message) {
        popup = PopupFactory.getSharedInstance().getPopup(null, new JLabel(
                message
        ), 100, 100);
        popup.show();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //create the components
        configPanel = new ConfigPanel(this);
        canvas = new DrawingPanel(this);
        controlPanel = new ControlPanel(this);

        createGame();

        //arrange the components in the container (frame)
        //JFrame uses a BorderLayout by default
        add(canvas, BorderLayout.CENTER);
        add(configPanel, BorderLayout.NORTH);
        add(controlPanel, BorderLayout.SOUTH);

        //invoke the layout manager
        pack();
    }
}
