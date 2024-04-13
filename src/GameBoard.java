import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard extends JFrame implements ActionListener {

  int rows;
  int cols;
  BoardListener boardListener;

  ColorButton[][] buttons;

  GameBoard(int rows, int cols, BoardListener boardListener) {
    this.rows = rows;
    this.cols = cols;
    this.boardListener = boardListener;
    this.buttons = new ColorButton[rows][cols];

    JFrame gameBoard = new JFrame();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new GridLayout(rows, cols));

    createBoard();

    this.setSize(600, 600);
    this.setVisible(true);
    this.setResizable(true);


  }


  public void createBoard() {
    int x = 0;
    int y = 0;
    int w = this.getWidth() / rows;
    int h = 200;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
//                this.add(new ColorButton());
        ColorButton button = new ColorButton(j);
        button.setBounds(x, y, w, h);
        button.addActionListener(this);
        this.add(button);
        buttons[i][j] = button;
        x += w;
      }
    }

    this.pack();

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    ColorButton button = (ColorButton) e.getSource();
    int column = button.getCol();
    boardListener.onColClicked(column);

  }


  public ColorButton getButtonAt(int row, int col) {
    return buttons[row][col];
  }


  //finds sets of three or more in a row or column
  public boolean findSets() {
    boolean hasSet = false;
    boolean[][] set = new boolean[buttons.length][buttons[0].length];
    for (int r = 0; r < buttons.length; r++) {
      for (int c = 0; c < buttons[r].length; c++) {
        if (buttons[r][c].isLocked() && c + 2 < buttons[r].length
            && buttons[r][c].getBackground() == buttons[r][c + 1].getBackground()
            && buttons[r][c].getBackground() == buttons[r][c + 2].getBackground()) {
          set[r][c] = set[r][c + 1] = set[r][c + 2] = true;
        }
        if (buttons[r][c].isLocked() && r + 2 < buttons.length
            && buttons[r][c].getBackground() == buttons[r + 1][c].getBackground()
            && buttons[r][c].getBackground() == buttons[r + 2][c].getBackground()) {
          set[r][c] = set[r + 1][c] = set[r + 2][c] = true;
        }
      }
    }
    for (int r = 0; r < buttons.length; r++) {
      for (int c = 0; c < buttons[r].length; c++) {
        if (set[r][c]) {
          buttons[r][c].setText("DELETE");
          hasSet = true;
        }
      }
    }
    return hasSet;
  }

  //removes all *'s in the buttons and shifts everything above them down
  public void removeSets() {
    for (int r = 0; r < buttons.length; r++) {
      for (int c = 0; c < buttons[r].length; c++) {
        if (buttons[r][c].getText().equals("DELETE")) {
          buttons[r][c].setText("");
          for (int i = r; i > 0; i--) {
            buttons[i][c].copyFrom(buttons[i - 1][c]);
          }
          buttons[0][c].setBackground(null);
          buttons[0][c].unlock();

        }
      }
    }
    this.revalidate();
    this.repaint();
  }

}