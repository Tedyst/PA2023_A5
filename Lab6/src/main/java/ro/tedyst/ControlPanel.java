package ro.tedyst;

import com.fasterxml.jackson.databind.ObjectMapper;
import ro.tedyst.game.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton exitBtn = new JButton("Exit");
    JButton loadBtn = new JButton("Load");
    JButton saveBtn = new JButton("Save");
    JButton exportBtn = new JButton("Export");

    ObjectMapper mapper = new ObjectMapper();

    public ControlPanel(MainFrame frame) {
        this.frame = frame; init();
    }
    private void init() {
        //change the default layout manager (just for fun)
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));

        add(loadBtn);
        add(saveBtn);
        add(exportBtn);
        add(exitBtn);
        //add all buttons ...TODO
        //configure listeners for all buttons
        exitBtn.addActionListener(this::exitGame);
        saveBtn.addActionListener(this::saveGame);
        loadBtn.addActionListener(this::loadGame);
        exportBtn.addActionListener(this::exportGame);
    }
    private void exitGame(ActionEvent e) {
        frame.dispose();
    }
    private void saveGame(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                mapper.writeValue(file, frame.getGame());
            } catch (IOException ex) {
                System.out.println("Exception while saving Game: " + ex);
            }
        }
    }
    private void loadGame(ActionEvent e){
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                Game g = mapper.readValue(file, Game.class);
                frame.setGame(g);
                System.out.println(g);
            } catch (IOException ex) {
                System.out.println("Exception while loading Game: " + ex);
            }
        }
    }
    private void exportGame(ActionEvent e){
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedImage image = frame.canvas.image;
                ImageIO.write(image, "PNG", file);
            } catch (IOException ex) {
                System.out.println("Exception while exporting Game: " + ex);
            }
        }
    }
}
