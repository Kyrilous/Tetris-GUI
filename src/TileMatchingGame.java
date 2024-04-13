import javax.swing.JOptionPane;
import java.awt.*;

public class TileMatchingGame implements BoardListener  {
    NextTile tile;
    myFrame frame;
    GameBoard board;



    public static void main(String[] args) {
        TileMatchingGame game =  new TileMatchingGame();
        game.run();



        }

        public void run(){
            frame = new myFrame();
            int rows = frame.getRows();
            int cols = frame.getCols();
            Color[] color = frame.getColor();
            board = new GameBoard(rows, cols, this);
            tile = new NextTile(color);
        }



    @Override
    public void onColClicked(int col) {
        int rows = board.rows;
        if(board.getButtonAt(0, col).isLocked()) {
            //lost = true;
            JOptionPane.showMessageDialog(null, "You Lost!");
            System.exit(0);

        }else {
            int row = 0;
            while(row != rows - 1 && !board.getButtonAt(row + 1, col).isLocked()) {
                row++;
            }
            ColorButton button = board.getButtonAt(row, col);
            button.setBackground(tile.getColor());
            button.lock();
            tile.setRandomColor();


           // find and remove sets
            while(board.findSets()) {
           JOptionPane.showMessageDialog(null,"A set has been found!");
                board.removeSets();
            }
        }

    }
}








