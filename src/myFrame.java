import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class myFrame extends JFrame implements ActionListener {

    int rows = Integer.valueOf(JOptionPane.showInputDialog("How many rows ?"));
    int cols = Integer.valueOf(JOptionPane.showInputDialog("How many columns ?"));
    int types = Integer.valueOf(JOptionPane.showInputDialog("How many types ?"));

    Color[] setColors = new Color[types];


    myFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(600, 200);
        this.setResizable(true);
        this.setVisible(true);


//            int rows = Integer.valueOf(JOptionPane.showInputDialog("How many rows ?"));
//            int columns = Integer.valueOf(JOptionPane.showInputDialog("How many columns ?"));
//            int types = Integer.valueOf(JOptionPane.showInputDialog("How many types ?"));

        generateButtons();


    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public JButton newButton(int x, int y, int w, int h, Color color) {
        JButton button = new JButton();
        button.setBounds(x, y, w, h);
        button.addActionListener(this);
        button.setBackground(color);

        return button;

    }

    public void generateButtons() {
        int x = 0;
        int y = 0;
        int w = this.getWidth() / types;
        int h = 200;

        for (int i = 0; i < types ; i++) {
            setColors[i] = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
            this.add(newButton(x, y, w, h, setColors[i]));
            x += w;
        }
        this.repaint();


        int g = JOptionPane.showConfirmDialog(null, "Are these colors okay?");
        while (g != JOptionPane.YES_OPTION) {
            this.getContentPane().removeAll();
            x = 0;
            for (int i = 0; i < types; i++) {
                setColors[i] = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
                this.add(newButton(x, y, w, h, setColors[i]));
                x += w;
            }
            this.repaint();
            g = JOptionPane.showConfirmDialog(null, "Are these colors okay?");


        }

            this.dispose();



    }


    public Color[] getColor(){
        return setColors;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}



