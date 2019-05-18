package x.gui;

import x.ucf.UcfCoder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Random;

public class Map extends JTable {

    Random random = new Random();

    private int mapSize = 129;
    private int cellSize = 5;

    public Map(){
        setModel(new DefaultTableModel(this.mapSize, this.mapSize));
        setRowHeight(this.cellSize);
        for(int i = 0; i < this.mapSize; i++){
            getColumnModel().getColumn(i).setMinWidth(this.cellSize);
            getColumnModel().getColumn(i).setMaxWidth(this.cellSize);
        }

        for (int i = 0; i < this.mapSize; i++){
            for (int j = 0; j < this.mapSize; j++){
                setValueAt(0x0000, j, i);
            }
        }
        generateLandscape();
    }

    private final void generateLandscape(){
        setValueAt(UcfCoder.LANDSCAPE_MOUNTAIN_LOW, 0, 0);
        setValueAt(UcfCoder.LANDSCAPE_MOUNTAIN_LOW, 0, 0 + this.mapSize - 1);
        setValueAt(UcfCoder.LANDSCAPE_MOUNTAIN_LOW, 0 + this.mapSize - 1, 0);
        setValueAt(UcfCoder.LANDSCAPE_MOUNTAIN_LOW, 0 + this.mapSize - 1, 0 + this.mapSize - 1);

        float landscapeShif = 15.0f;

        for(int bigStep = this.mapSize - 1; bigStep >= 2; bigStep /= 2, landscapeShif /=2.0){
            int smallStep = bigStep / 2;
            //Diamond step
            for (int x = smallStep; x < this.mapSize; x += bigStep){
                for (int y = smallStep; y < this.mapSize; y++ ){
                    int topLeftValue = (int)(getValueAt(y - smallStep, x - smallStep));
                    int topRightValue = (int)(getValueAt(y - smallStep, x + smallStep));
                    int bottomLeftValue = (int)(getValueAt(y + smallStep, x - smallStep));
                    int bottomRightValue = (int)(getValueAt(y + smallStep, x + smallStep));
                    float average = (topLeftValue + topRightValue + bottomLeftValue + bottomRightValue)/4;
                    float centralValue = average + random.nextInt(3) * landscapeShif - landscapeShif;
                    setValueAt(centralValue, y, x);
                }
            }
        }
    }

    private static  final  int getValueRange(int value, int min, int max){
        return Math.max(Math.min(value, max), min);
    }
}
