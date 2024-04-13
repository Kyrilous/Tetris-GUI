import javax.swing.*;
import java.awt.*;

public class NextTile extends JFrame {
    Color[] colors;
    int randomIndex;
    NextTile(Color[] colors){
        this.colors = colors;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(600, 200);
        this.setResizable(true);
        this.setVisible(true);
        this.setRandomColor();
    }

public Color getColor(){
    return colors[randomIndex];
}

public void setRandomColor(){
        randomIndex = (int) Math.floor(Math.random() * colors.length);
        this.getContentPane().setBackground(getColor());
        this.repaint();
}
}
