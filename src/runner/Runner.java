package runner;

import x.gui.MainWindow;

public class Runner {
    public static void main(String[] args) {
        try {
            MainWindow mainWindow = new MainWindow();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
