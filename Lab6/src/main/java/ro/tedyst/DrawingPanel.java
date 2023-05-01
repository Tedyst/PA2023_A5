package ro.tedyst;

import ro.tedyst.game.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 800, H = 600, MAX_DISTANCE_CLICK = 5;
    private int numVertices;
    private double edgeProbability;
    private int[] x, y;
    BufferedImage image; //the offscreen image
    Graphics2D graphics; //the tools needed to draw in the image
    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        initPanel();
        createBoard();
    }
    private void initPanel() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(frame.getGame() == null)
                    return;
                if(frame.getGame().getState() != GameState.PLAYER_1_TURN)
                    return;
                Edge edge = GameUtils.getClosestEdge(frame.getGame().getEdges(), e.getX(), e.getY(), MAX_DISTANCE_CLICK);
                if (edge == null || edge.getColor() != EdgeColor.NONE)
                    return;
                frame.getGame().clickOnEdge(edge);
                GameUtils.runAINextMove(frame.getGame());
                if(frame.getGame().isFinished()){
                    createBoard();
                    switch(frame.getGame().getState()){
                        case PLAYER_1_WIN:
                            frame.createPopup("Game finished! Winner: BLUE");
                            break;
                        case PLAYER_2_WIN:
                            frame.createPopup("Game finished! Winner: RED");
                            break;
                        case DRAW:
                            frame.createPopup("Game finished! Winner: DRAW");
                            break;
                    }
                    return;
                }
                createBoard();
            }
        });
    }
    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 800, 600);
    }
    final void createBoard() {
        if(frame.getGame() == null)
            return;
        createOffscreenImage();
        drawLines();
        drawVertices();
        repaint();
    }

    private void drawLines() {
        Stroke oldStroke = graphics.getStroke();
        graphics.setStroke(new BasicStroke(3));
        for(Edge e : this.frame.getGame().getEdges()){
            switch(e.getColor()){
                case RED:
                    graphics.setColor(Color.RED);
                    break;
                case BLUE:
                    graphics.setColor(Color.BLUE);
                    break;
                case NONE:
                    graphics.setColor(Color.BLACK);
                    break;
            }
            graphics.drawLine(e.getSource().getX(), e.getSource().getY(), e.getTarget().getX(), e.getTarget().getY());
        }
        graphics.setStroke(oldStroke);
    }
    private void drawVertices() {
        graphics.setColor(Color.BLACK);
        for(Node n : this.frame.game.getNodes())
            graphics.fillOval(n.getX() - 10, n.getY() - 10, 20, 20);
    }
    @Override
    public void update(Graphics g) { } //No need for update

    //Draw the offscreen image, using the original graphics
    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.drawImage(image, 0, 0, this);
    }
}