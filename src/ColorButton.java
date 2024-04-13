import javax.swing.*;

public class ColorButton extends JButton {
    private int col;
    private boolean isLocked;

    public boolean isLocked(){
        return isLocked;
    }

    public void lock(){
        isLocked = true;
    }

    public void unlock(){
        isLocked = false;
    }

    ColorButton(int col){
        this.col = col;
    }

    public void copyFrom(ColorButton otherButton){
        this.setBackground(otherButton.getBackground());
        this.isLocked = otherButton.isLocked();
    }



    public int getCol(){
        return col;
    }


}
